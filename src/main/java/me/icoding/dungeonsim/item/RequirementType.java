package me.icoding.dungeonsim.item;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringJoiner;

public enum RequirementType {

    NONE,
    ZOMBIE,
    SPIDER,
    WOLF,
    ENDERMAN,
    BLAZE,

    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    ENCHANTING,
    ALCHEMY,
    CARPENTRY,
    RUNECRAFTING,
    SOCIAL,
    TAMING,

    CATACOMBS_FLOOR,
    CATACOMBS_LEVEL,

    // farming
    CACTUS,
    CARROT,
    COCOA_BEANS,
    FEATHER,
    LEATHER,
    MELON,
    MUSHROOM,
    MUTTON,
    NETHER_WART,
    POTATO,
    PUMPKIN,
    RAW_CHICKEN,
    RAW_PORKCHOP,
    RAW_RABBIT,
    SEEDS,
    SUGAR_CANE,
    WHEAT,

    // mining
    COAL,
    COBBLESTONE,
    DIAMOND,
    EMERALD,
    END_STONE,
    GEMSTONE,
    GLOWSTONE_DUST,
    GOLD_INGOT,
    GRAVEL,
    HARD_STONE,
    ICE,
    IRON_INGOT,
    LAPIS_LAZULI,
    MITHRIL,
    NETHER_QUARTZ,
    NETHERRACK,
    OBSIDIAN,
    REDSTONE,
    SAND,

    // combat
    BLAZE_ROD,
    BONE,
    ENDER_PEARL,
    GHAST_TEAR,
    GUNPOWDER,
    MAGMA_CREAM,
    ROTTAN_FLESH,
    SLIMEBALL,
    SPIDER_EYE,
    STRING,

    // foraging
    ACACIA_WOOD,
    BIRCH_WOOD,
    DARK_OAK_WOOD,
    JUNGLE_WOOD,
    OAK_WOOD,
    SPRUCE_WOOD,

    // fishing
    CLAY,
    CLOWNFISH,
    INK_SACK,
    LILY_PAD,
    PRISMARINE_CRYSTALS,
    PRISMARINE_SHARD,
    PUFFERFISH,
    RAW_FISH,
    RAW_SALMON,
    SPONGE,

    // dungeon
    BONZO,
    SCARF,
    THE_PROFESSOR,
    THRON,
    LIVID,
    SADAN,
    NECRON;

    public String getName() {
        StringJoiner s = new StringJoiner(" ");
        for (String l : name().split("_"))
            s.add(l.substring(0, 1).toUpperCase() + l.substring(1).toLowerCase());
        return s.toString();
    }
}
