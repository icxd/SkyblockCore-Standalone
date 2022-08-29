package me.icoding.dungeonsim.event;

import me.icoding.dungeonsim.collection.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class CollectionLevelUpEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private Collection collection;
    private int oldLevel, newLevel;

    public CollectionLevelUpEvent(Player player, Collection collection, int oldLevel, int newLevel) {
        super(player);
        this.collection = collection;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public Collection getCollection() {
        return collection;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
