package me.icoding.dungeonsim.gui;

import me.icoding.api.Gui;

import me.icoding.dungeonsim.item.ItemBuilder;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.Collections;

public class ItemsGui extends Gui {
    public ItemsGui() {
        super("Items", Size.SIZE_54);

        Arrays.sort(ItemManager.getItemData().keySet().toArray());
        for (ItemData itemData : ItemManager.getItemData().values()) {
            addItem(new ItemBuilder().buildItem(itemData));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        try {
            if (!event.getClickedInventory().getTitle().equals("Items")) return;

            event.setCancelled(true);
        } catch (NullPointerException ignored) {}
    }
}
