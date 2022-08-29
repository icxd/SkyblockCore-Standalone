package me.icoding.dungeonsim.item;

import org.bukkit.ChatColor;

public enum Rarity {

    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.LIGHT_PURPLE),
    DIVINE(ChatColor.AQUA),
    SPECIAL(ChatColor.RED),
    VERY_SPECIAL(ChatColor.RED),
    ;

    ChatColor color;
    Rarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor color() {
        return color;
    }

    public String lore() {
        return "" + this.color + ChatColor.BOLD + name().replace("_", " ");
    }
}
