package me.icoding.dungeonsim.item;

import me.icoding.dungeonsim.item.ability.Ability;
import org.bukkit.Material;

public interface ItemData {
    Material material();
    String name();
    default Category category() { return Category.NONE; }
    default Rarity tier() { return Rarity.COMMON; };
    default BoundType soulbound() { return BoundType.NONE; }

    default int item_durability() { return 0; }
    default int durability() { return 0; }
    default double npc_sell_price() { return 0; }

    default int leather_color_red() { return 0; }
    default int leather_color_green() { return 0; }
    default int leather_color_blue() { return 0; }

    default String skin() { return ""; }

    default Ability ability() { return null; }

    default int gear_score() { return 0; }
    default int stat_damage() { return 0; }
    default int stat_strength() { return 0; }
    default int stat_ferocity() { return 0; }
    default int stat_intelligence() { return 0; }
    default int stat_health() { return 0; }
    default int stat_defense() { return 0; }
    default int stat_attack_speed() { return 0; }
    default int stat_critical_damage() { return 0; }
    default int stat_critical_chance() { return 0; }
    default int stat_walk_speed() { return 0; }
    default int stat_true_defense() { return 0; }

    default EssenceType essence_type() { return null; }
    default int costs_dungeonize() { return 0; }
    default int costs_star_1() { return 0; }
    default int costs_star_2() { return 0; }
    default int costs_star_3() { return 0; }
    default int costs_star_4() { return 0; }
    default int costs_star_5() { return 0; }

    default boolean unstackable() { return false; }
    default boolean can_have_attributes() { return false; }
    default boolean dungeon_item() { return false; }
    default boolean museum() { return false; }
    default boolean glowing() { return false; }

    default RequirementType requirement_type() { return RequirementType.NONE; }
    default int requirement_level() { return 0; }

    default CatacombsRequirementType catacombs_requirement_type() { return CatacombsRequirementType.NONE; }
    default int catacombs_requirement_level() { return 0; }

    default GemstoneSlot gemstone_slot_1() { return null; }
    default GemstoneSlot gemstone_slot_2() { return null; }
    default GemstoneSlot gemstone_slot_3() { return null; }
    default GemstoneSlot gemstone_slot_4() { return null; }
    default GemstoneSlot gemstone_slot_5() { return null; }

    String id();

}
