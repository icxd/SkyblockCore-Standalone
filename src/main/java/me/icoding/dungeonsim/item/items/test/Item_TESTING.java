package me.icoding.dungeonsim.item.items.test;

import me.icoding.dungeonsim.item.ItemData;
import org.bukkit.Material;

public class Item_TESTING implements ItemData {
    @Override public Material material() { return Material.BLAZE_POWDER; }
    @Override public String name() { return "Testing Item"; }

    @Override public int stat_damage() { return 999; }
    @Override public int stat_strength() { return 999; }
    @Override public int stat_attack_speed() { return 999; }
    @Override public int stat_critical_damage() { return 999; }
    @Override public int stat_ferocity() { return 999; }
    @Override public int stat_intelligence() { return 999; }
    @Override public int stat_defense() { return 999; }
    @Override public int stat_health() { return 999; }
    @Override public int stat_walk_speed() { return 999; }

    @Override public String id() { return "TESTING_ITEM"; }
}
