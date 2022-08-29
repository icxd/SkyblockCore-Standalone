package me.icoding.dungeonsim.reforge;

import java.util.List;

public interface PowerStone {

    String name();
    default List<String> description() { return null; }
    int combatLevelRequirement();

    default double stat_damage() { return 0; }
    default double stat_strength() { return 0; }
    default double stat_ferocity() { return 0; }
    default double stat_doubleelligence() { return 0; }
    default double stat_health() { return 0; }
    default double stat_defense() { return 0; }
    default double stat_attack_speed() { return 0; }
    default double stat_critical_damage() { return 0; }
    default double stat_critical_chance() { return 0; }
    default double stat_walk_speed() { return 0; }
    default double stat_true_defense() { return 0; }
}
