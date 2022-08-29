package me.icoding.dungeonsim.item;

import me.icoding.dungeonsim.DungeonSimulator;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemManager {

    private static final HashMap<String, ItemData> ITEM_DATA = new HashMap<>();

    public void registerItems() throws InstantiationException, IllegalAccessException {
        for (Class<?> itemData : new Reflections().getSubTypesOf(ItemData.class)) {
            ITEM_DATA.put(((ItemData) itemData.newInstance()).id(), (ItemData) itemData.newInstance());
        }
    }

    public static ItemData getItem(String itemId) {
        return ITEM_DATA.get(itemId);
    }

    public static ItemData getItemFromName(String itemName) {
        for (ItemData item : ITEM_DATA.values()) if (toItemID(item.name()).equals(itemName)) return item;
        return null;
    }

    public static HashMap<String, ItemData> getItemData() {
        return ITEM_DATA;
    }

    private static String toItemID(String itemName) {
        return ChatColor.stripColor(itemName.replace("'", "").replace("\"", "").replace(" ", "_").toUpperCase());
    }
}
