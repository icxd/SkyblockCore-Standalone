package me.icoding.dungeonsim.listeners.collection;

import me.icoding.dungeonsim.DungeonSimulator;
import me.icoding.dungeonsim.collection.Collection;
import me.icoding.dungeonsim.collection.reward.CollectionRewards;
import me.icoding.dungeonsim.event.CollectionLevelUpEvent;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import me.icoding.dungeonsim.player.PlayerHandler;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;

public class CollectionListener implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItemDrop();

        item.setMetadata("playerDrop", new FixedMetadataValue(DungeonSimulator.getInstance(), true));
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItem();
        int amount = item.getItemStack().getAmount();

        if (item.hasMetadata("playerDrop")) return;

        ItemData data = Utils.getItemData(item.getItemStack());

        String id = "";
        if (data != null) id = data.id();
        else id = item.getItemStack().getType().name();

        Collection collection = null;
        try { collection = Collection.getCollection(id); }
        catch (IllegalArgumentException ignored) {}

        if (collection == null) return;

        FileConfiguration config = PlayerHandler.getPlayerData(player);
        File file = PlayerHandler.getPlayerFile(player);

        String fullPath = "player.collection."+collection.getCategory().name().toLowerCase()+"."+collection.getPath();
        if (config.getInt(fullPath) == 0) {
            player.sendMessage(Utils.color("&r &r &6&lCOLLECTION UNLOCKED &e"+collection.getName()));
        }

        int level = 0;
        for (int i = 1; i <= amount; i++) {
            config.set(fullPath, config.getInt(fullPath)+i);

            int newCollection = config.getInt(fullPath);

            for (CollectionRewards reward : collection.getRewards()) {
                level++;
                if (Utils.isCloseEnough(newCollection, reward.getRequirement())) {
                    Bukkit.getServer().getPluginManager().callEvent(new CollectionLevelUpEvent(player, collection, level-1, level));
                }
            }
        }

        try { config.save(file); }
        catch (IOException ignored) {}
    }



}
