package me.icoding.dungeonsim.item.items.other;

import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.Rarity;
import me.icoding.dungeonsim.item.ability.Ability;
import org.bukkit.Material;

public class Item_BOOSTER_COOKIE implements ItemData {
    @Override public Material material() { return Material.COOKIE; }
    @Override public String name() { return "Booster Cookie"; }
    @Override public boolean glowing() { return true; }
    @Override public Rarity tier() { return Rarity.LEGENDARY; }
    @Override public boolean unstackable() { return true; }
    @Override public String id() { return "BOOSTER_COOKIE"; }
    @Override public Ability ability() { return Ability.BOOSTER_COOKIE; }
}
