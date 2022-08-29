package me.icoding.dungeonsim.skill.reward;

import me.icoding.dungeonsim.collection.reward.CollectionReward;

import java.util.ArrayList;
import java.util.Arrays;

public class SkillRewards extends ArrayList<SkillReward> {

    private final int requirement;

    public SkillRewards(int requirement, SkillReward... rewards) {
        super(Arrays.asList(rewards));
        this.requirement = requirement;
    }

    public int getRequirement() {
        return requirement;
    }
}
