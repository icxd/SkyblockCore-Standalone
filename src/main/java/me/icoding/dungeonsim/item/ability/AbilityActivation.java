package me.icoding.dungeonsim.item.ability;

import org.bukkit.event.block.Action;

import java.util.Arrays;
import java.util.List;

public enum AbilityActivation {

    RIGHT_CLICK(false, Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR),
    SNEAK_RIGHT_CLICK(true, Action.RIGHT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR),
    LEFT_CLICK(false, Action.LEFT_CLICK_BLOCK, Action.LEFT_CLICK_BLOCK),
    SNEAK_LEFT_CLICK(true, Action.LEFT_CLICK_BLOCK, Action.LEFT_CLICK_BLOCK),
    ;

    boolean sneak;
    List<Action> actions;
    AbilityActivation(boolean sneak, Action... actions) {
        this.sneak = sneak;
        this.actions = Arrays.asList(actions);
    }

    public String getName() { return name().replace("_", " "); }

    public List<Action> getActions() { return actions; }
    public boolean isSneak() { return sneak; }
}
