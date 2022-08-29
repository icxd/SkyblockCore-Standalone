package me.icoding.dungeonsim.item;

import java.util.Arrays;
import java.util.List;

public enum GemstoneType {

    RUBY("❤"),
    AMETHYST("❈"),
    SAPPHIRE("✎"),
    JASPER("❁"),
    JADE("☘"),
    AMBER("⸕"),
    TOPAZ("✧"),

    OFFENSIVE("☠", JASPER, SAPPHIRE),
    DEFENSIVE("☤", RUBY, AMETHYST),
    COMBAT("⚔", JASPER, SAPPHIRE, RUBY, AMETHYST),
    MINING("✦", JADE, AMBER, TOPAZ),
    UNIVERSAL("❂", JASPER, SAPPHIRE, RUBY, AMETHYST, JADE, AMBER, TOPAZ),

    ;

    String icon;
    List<GemstoneType> baseTypes;

    GemstoneType(String icon) {
        this.icon = icon;
    }

    GemstoneType(String icon, GemstoneType... baseTypes) {
        this.icon = icon;
        this.baseTypes = Arrays.asList(baseTypes);
    }

}
