package me.icoding.dungeonsim.stats;

import me.icoding.dungeonsim.DungeonSimulator;
import me.icoding.dungeonsim.item.Category;
import me.icoding.dungeonsim.stats.replacements.Replacement;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsRunnable {

    public static final Map<UUID, Replacement> DEFENSE_REPLACEMENT_MAP = new HashMap<>();
    public static final Map<UUID, Replacement> MANA_REPLACEMENT_MAP = new HashMap<>();

    public static final Map<UUID, Integer> MANA_MAP = new HashMap<>();

    public StatsRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();

                    if (player.getInventory().getItemInHand() == null ||
                        player.getInventory().getItemInHand().getItemMeta() == null)
                        Stats.initializeStats(player);

                    player.setSaturation(1000.0f);
                    player.setFoodLevel(20);

                    int maxHealth = Stats.MAX_HEALTH_MAP.get(uuid);

                    double healthBonus = 1;

                    player.setHealthScale(Math.min(40.0, 20.0 + ((maxHealth - 100.0) / 25.0)));

                    if (player.getHealth() <= player.getMaxHealth()) {
                        player.setHealth(Math.min(player.getMaxHealth(), (player.getHealth() + 1.5 + ((int) player.getMaxHealth() * 0.01) +
                                ((1.5 + ((int) player.getMaxHealth() * 0.01))/* * 0.0*/)) * healthBonus));
                    }

                    int defense = Stats.DEFENSE_MAP.get(uuid);

                    int speed = Stats.SPEED_MAP.get(uuid);
                    player.setWalkSpeed(Math.min((float) (speed / 5.0), 1.0f));

                    EntityHuman human = ((CraftHumanEntity) player).getHandle();
                    float absorption = human.getAbsorptionHearts();
                    ChatColor color = absorption > 0.0f ? ChatColor.GOLD : ChatColor.RED;

                    Replacement defenseReplacement = DEFENSE_REPLACEMENT_MAP.get(uuid);
                    if (defenseReplacement != null && System.currentTimeMillis() >= defenseReplacement.getEnd()) {
                        DEFENSE_REPLACEMENT_MAP.remove(player.getUniqueId());
                        defenseReplacement = null;
                    }

                    Replacement manaReplacement = MANA_REPLACEMENT_MAP.get(uuid);
                    if (manaReplacement != null && System.currentTimeMillis() >= manaReplacement.getEnd()) {
                        MANA_REPLACEMENT_MAP.remove(player.getUniqueId());
                        manaReplacement = null;
                    }

                    int manaPool = 100 + Stats.INTELLIGENCE_MAP.get(uuid);
                    if (!MANA_MAP.containsKey(uuid)) MANA_MAP.put(uuid, manaPool);

                    int mana = MANA_MAP.get(uuid);
                    if (mana <= manaPool) {
                        MANA_MAP.remove(uuid);
                        MANA_MAP.put(uuid, Math.min(manaPool, Math.min(manaPool, mana + (manaPool / 50) +
                                (int) ((manaPool / 50)/* * 0.0*/))));
                    }

                    sendActionBar(player,
                            color + "" + Math.round(player.getHealth() + absorption) +
                                    "/" + maxHealth + "❤     " +
                                    (defenseReplacement == null ? (defense != 0 ? "" + ChatColor.GREEN + defense + "❈ Defense     " : "")
                                            : defenseReplacement.getReplacement() + "     ") +
                                    (manaReplacement == null ? "" + ChatColor.AQUA + MANA_MAP.get(player.getUniqueId()) + "/" + manaPool + "✎ Mana"
                                            : manaReplacement.getReplacement() + "     ")
                    );
                }
            }
        }.runTaskTimer(DungeonSimulator.getInstance(), 0, 2);
    }

    private void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
