package me.icoding.dungeonsim.gui;

import me.icoding.api.Gui;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CommunityShop extends Gui implements Listener {
    public CommunityShop() {
        super("Community Shop", Size.SIZE_54);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {

    }
}
