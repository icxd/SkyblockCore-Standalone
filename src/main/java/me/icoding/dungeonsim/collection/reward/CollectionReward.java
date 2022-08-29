package me.icoding.dungeonsim.collection.reward;

import org.bukkit.entity.Player;

public abstract class CollectionReward {

    private final Type type;
    public CollectionReward(Type type) {
        this.type = type;
    }

    public abstract void onAchieve(Player player);
    public abstract String toRewardString();

    enum Type {
        RECIPE, UPGRADE, EXPERIENCE;
    }

}
