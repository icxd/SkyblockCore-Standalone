package me.icoding.dungeonsim.gui;

import me.icoding.api.Gui;
import me.icoding.dungeonsim.item.ItemBuilder;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import me.icoding.dungeonsim.player.PlayerHandler;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AccessoryBagGui extends Gui implements Listener {

    public AccessoryBagGui(Player player) {
        super("Accessory Bag", Size.SIZE_54);

        for (ItemData itemData : Utils.getAccessory(player)) {
            addItem(new ItemBuilder().buildItem(itemData));
        }

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backm = back.getItemMeta();
        backm.setDisplayName(ColorAPI.color("&aGo Back"));
        back.setItemMeta(backm);

        setItem(45, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(46, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(47, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(48, back);
        setItem(49, closeButton());
        setItem(50, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(51, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(52, ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(53, ItemPreset.BLACK_STAINED_GLASS_PANE);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (!ChatColor.stripColor(event.getClickedInventory().getTitle()).equalsIgnoreCase(this.getName())) return;

        event.setCancelled(true);

        if (event.getClickedInventory() == player.getInventory()) {
            if (event.getCurrentItem() == null) return;

            ItemData data = ItemManager.getItemFromName(
                    event.getCurrentItem().getItemMeta() == null ?
                            event.getCurrentItem().getType().name() :
                            event.getCurrentItem().getItemMeta().getDisplayName()
            );

            List<String> s = PlayerHandler.getPlayerData(player).getStringList("player.accessories.bag");
            s.add(data.id());
            PlayerHandler.getPlayerData(player).set("player.accessories.bag", s);

            player.getInventory().remove(event.getCurrentItem());

            return;
        }

        if (event.getCurrentItem() == null) return;
        ItemData data = ItemManager.getItemFromName(
                event.getCurrentItem().getItemMeta() == null ?
                        event.getCurrentItem().getType().name() :
                        event.getCurrentItem().getItemMeta().getDisplayName()
        );

        switch (event.getSlot()) {
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                return;
        }

        List<String> s = PlayerHandler.getPlayerData(player).getStringList("player.accessories.bag");
        s.remove(data.id());
        PlayerHandler.getPlayerData(player).set("player.accessories.bag", s);

        player.getInventory().addItem(event.getCurrentItem());
    }
}
