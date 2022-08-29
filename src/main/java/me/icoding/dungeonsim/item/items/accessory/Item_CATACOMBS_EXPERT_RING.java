package me.icoding.dungeonsim.item.items.accessory;

import me.icoding.dungeonsim.item.Category;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.Rarity;
import org.bukkit.*;

public class Item_CATACOMBS_EXPERT_RING implements ItemData {
    @Override public Material material() { return Material.SKULL_ITEM; }
    @Override public int durability() { return 3; }
    @Override public String skin() { return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA3OGM2OGY2Zjk2NjkzNzBlYTM5YmU3Mjk0NWEzYTg2ODhlMGUwMjRlNWQ2MTU4ZmQ4NTRmYjJiODBmYiJ9fX0"; }
    @Override public String name() { return "Catacombs Expert Ring"; }
    @Override public Category category() { return Category.ACCESSORY; }
    @Override public Rarity tier() { return Rarity.EPIC; }
    @Override public String id() { return "CATACOMBS_EXPERT_RING"; }
}
