package me.icoding.dungeonsim.item.items.accessory;

import me.icoding.dungeonsim.item.Category;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.Rarity;
import org.bukkit.*;

public class Item_BAT_PERSON_ARTIFACT implements ItemData {
    @Override public Material material() { return Material.SKULL_ITEM; }
    @Override public int durability() { return 3; }
    @Override public String skin() { return "ewogICJ0aW1lc3RhbXAiIDogMTYwMzg5ODc2Njg0NiwKICAicHJvZmlsZUlkIiA6ICJhMmY4MzQ1OTVjODk0YTI3YWRkMzA0OTcxNmNhOTEwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJiUHVuY2giLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ0NDRjMzk4MjcyMGIzMDkzOGY1MDRjNDM3NDIzMmIxMWE0ZjZmNTZjZDU3Yzk3M2Q4YWJiMDdmZDBkY2ZmNyIKICAgIH0KICB9Cn0="; }
    @Override public String name() { return "Bat Person Artifact"; }
    @Override public Category category() { return Category.ACCESSORY; }
    @Override public Rarity tier() { return Rarity.RARE; }
    @Override public String id() { return "BAT_PERSON_ARTIFACT"; }
}
