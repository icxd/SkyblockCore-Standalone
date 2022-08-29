package me.icoding.dungeonsim.stats;

import me.icoding.dungeonsim.item.Category;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Stats {

    public static final Map<UUID, Integer> MAX_HEALTH_MAP = new HashMap<>();
    public static final Map<UUID, Integer> DEFENSE_MAP = new HashMap<>();
    public static final Map<UUID, Integer> INTELLIGENCE_MAP = new HashMap<>();
    public static final Map<UUID, Integer> SPEED_MAP = new HashMap<>();

    public static void initializeStats(Player player) {
        UUID uuid = player.getUniqueId();

        MAX_HEALTH_MAP.put(uuid, 100);
        DEFENSE_MAP.put(uuid, 0);
        INTELLIGENCE_MAP.put(uuid, 0);
        SPEED_MAP.put(uuid, 1);
    }

    public static void removePlayer(Player player) {
        MAX_HEALTH_MAP.remove(player.getUniqueId());
        DEFENSE_MAP.remove(player.getUniqueId());
        INTELLIGENCE_MAP.remove(player.getUniqueId());
        SPEED_MAP.remove(player.getUniqueId());
    }

    private static void updateHashMap(Player player, Map<UUID, Integer> map, int value) { map.put(player.getUniqueId(), map.get(player.getUniqueId()) + value); }

    public static void updateStats(Player player, ItemData item) {
        initializeStats(player);
        updateHashMap(player, MAX_HEALTH_MAP, item.stat_health());
        updateHashMap(player, DEFENSE_MAP, item.stat_defense());
        updateHashMap(player, INTELLIGENCE_MAP, item.stat_intelligence());
        updateHashMap(player, SPEED_MAP, item.stat_walk_speed());
    }

    /*public static void updateAccessories(Player player) {
        for (ItemStack stack : player.getInventory().getContents()) {
            ItemData item = getItemData(stack);
            if (item == null) return;
            if (item.category() != Category.ACCESSORY) return;

            updateStats(player, item);
        }
    }

    public static ItemData getItemData(ItemStack stack) {
        if (stack == null) return null;
        ItemMeta meta = stack.getItemMeta();
        if (meta.getDisplayName() == null) return null;
        String itemID = toItemID(meta.getDisplayName());
        return ItemManager.getItemFromName(itemID);
    }

    private static String toItemID(String itemName) {
        return ChatColor.stripColor(itemName.replace("'", "").replace("\"", "").replace(" ", "_").toUpperCase());
    }*/

}
