package me.icoding.dungeonsim.skill.reward;

import me.icoding.dungeonsim.collection.reward.CollectionReward;
import org.bukkit.entity.Player;

public abstract class SkillReward {

    private final Type type;
    public SkillReward(Type type) {
        this.type = type;
    }

    public abstract void onAchieve(Player player);
    public abstract String toRewardString();

    enum Type {
        STAT, ZONE, COINS
    }

}
