package me.icoding.dungeonsim.reforge.powerstones;

import me.icoding.dungeonsim.reforge.PowerStone;

import java.util.Arrays;
import java.util.List;

public class SilkyReforge implements PowerStone {
    @Override public String name() { return "Silky"; }
    @Override public List<String> description() { return Arrays.asList("&8&oOlder Spiders often make", "&8&obetter quality string. It is", "&8&osaid that female ones are even", "&8&omore sturdy, but it was never", "&8&oproven."); }
    @Override public int combatLevelRequirement() { return 15; }

    @Override public double stat_critical_damage() { return 24; }
}
