package me.icoding.dungeonsim.listeners.items;

import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import me.icoding.dungeonsim.stats.Stats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemListener implements Listener {

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        int newSlot = event.getNewSlot(), oldSlot = event.getPreviousSlot();

        if (newSlot != oldSlot) {
            ItemStack stack = player.getInventory().getItem(newSlot);
            if (stack == null) { Stats.initializeStats(player); return; }
            ItemMeta meta = stack.getItemMeta();
            if (meta.getDisplayName() == null) { Stats.initializeStats(player); return; }
            String itemID = toItemID(meta.getDisplayName());
            ItemData item = ItemManager.getItemFromName(itemID);
            if (item == null) { Stats.initializeStats(player); return; }

            Stats.updateStats(player, item);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        ItemStack stack = event.getItemDrop().getItemStack();
        if (stack == null) { Stats.initializeStats(player); return; }
        ItemMeta meta = stack.getItemMeta();
        if (meta.getDisplayName() == null) { Stats.initializeStats(player); return; }
        String itemID = toItemID(meta.getDisplayName());
        ItemData item = ItemManager.getItemFromName(itemID);
        if (item == null) { Stats.initializeStats(player); return; }

        Stats.updateStats(player, item);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

        ItemStack stack = event.getItem().getItemStack();
        if (stack == null) { Stats.initializeStats(player); return; }
        ItemMeta meta = stack.getItemMeta();
        if (meta.getDisplayName() == null) { Stats.initializeStats(player); return; }
        String itemID = toItemID(meta.getDisplayName());
        ItemData item = ItemManager.getItemFromName(itemID);
        if (item == null) { Stats.initializeStats(player); return; }

        Stats.updateStats(player, item);
    }

    private String toItemID(String itemName) {
        return ChatColor.stripColor(itemName.replace("'", "").replace("\"", "").replace(" ", "_").toUpperCase());
    }

}
