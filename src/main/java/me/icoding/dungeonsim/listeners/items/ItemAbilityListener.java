package me.icoding.dungeonsim.listeners.items;

import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ability.Ability;
import me.icoding.dungeonsim.item.ability.AbilityActivation;
import me.icoding.dungeonsim.stats.StatsRunnable;
import me.icoding.dungeonsim.stats.replacements.Replacement;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemAbilityListener implements Listener {

    @EventHandler
    public void onAbilityUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getInventory().getItemInHand();
        if (stack == null || stack.getType() == Material.AIR) return;

        ItemData item = Utils.getItemData(stack);
        if (item == null) return;
        Ability ability = item.ability();
        if (ability == null) return;

        AbilityActivation activation = ability.getActivation();
        for (Action action : activation.getActions()) {
            if (event.getAction() == action) {
                int mana = StatsRunnable.MANA_MAP.get(player.getUniqueId());
                int cost = getFinalManaCost(player, item, ability.getManaCost());
                int resMana = mana - cost;

                if (resMana >= 0) {
                    StatsRunnable.MANA_MAP.remove(player.getUniqueId());
                    StatsRunnable.MANA_MAP.put(player.getUniqueId(), resMana);

                    ability.getFunction().accept(player);

                    long c = System.currentTimeMillis();
                    StatsRunnable.DEFENSE_REPLACEMENT_MAP.put(player.getUniqueId(), new Replacement() {
                        @Override
                        public String getReplacement() {
                            return Utils.color("&b-" + ability.getManaCost() + " Mana (&6" + ability.getName() + "&b)");
                        }

                        @Override
                        public long getEnd() {
                            return c + 4000;
                        }
                    });
                } else {
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, -4f);

                    long c = System.currentTimeMillis();
                    StatsRunnable.MANA_REPLACEMENT_MAP.put(player.getUniqueId(), new Replacement() {
                        @Override
                        public String getReplacement() {
                            return Utils.color("&c&lNOT ENOUGH MANA");
                        }

                        @Override
                        public long getEnd() {
                            return c + 4000;
                        }
                    });
                }
            }
        }
    }

    private int getFinalManaCost(Player player, ItemData item, int cost) {
        int updated = cost;
        return updated;
    }
}
