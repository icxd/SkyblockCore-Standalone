package me.icoding.dungeonsim.item;

import org.bukkit.ChatColor;

public enum ItemStar {

    ZERO            ("&6", 0),
    ONE             ("&6✪", 0.1),
    TWO             ("&6✪✪", 0.2),
    THREE           ("&6✪✪✪", 0.3),
    FOUR            ("&6✪✪✪✪", 0.4),
    FIVE            ("&6✪✪✪✪✪", 0.5),
    SIX             ("&c✪&6✪✪✪✪", 0.55),
    SEVEN           ("&c✪✪&6✪✪✪", 0.6),
    EIGHT           ("&c✪✪✪&6✪✪", 0.65),
    NINE            ("&c✪✪✪✪&6✪", 0.7),
    TEN             ("&c✪✪✪✪✪", 0.75),

    ;

    String string;
    double star;

    ItemStar(String string, double star) {
        this.string = string;
        this.star = star;
    }

    public String getString() {
        return string;
    }

    public double getStar() {
        return star;
    }

    public ItemStar getNextStar() {
        return values()[Math.min(this.ordinal() + 1, values().length - 1)];
    }

    public String getItemName() {
        return " " + ChatColor.translateAlternateColorCodes('&', string);
    }

}
