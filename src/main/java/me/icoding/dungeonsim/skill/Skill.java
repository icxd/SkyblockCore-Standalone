package me.icoding.dungeonsim.skill;

import me.icoding.dungeonsim.skill.reward.SkillRewards;

import java.util.Arrays;
import java.util.List;

public enum Skill {

    COMBAT("Combat", "Warrior", "&fDeals &a%s% &fmore damage to mobs.",
            new SkillRewards(50));

    String name;
    String secondaryName;
    String description;
    List<SkillRewards> rewards;
    Skill(String name, String secondaryName, String description, SkillRewards... rewards) {
        this.name = name;
        this.secondaryName = secondaryName;
        this.description = description;
        this.rewards = Arrays.asList(rewards);
    }

    public String getName() {
        return name;
    }

    public List<SkillRewards> getRewards() {
        return rewards;
    }

    public String getDescription() {
        return description;
    }

    public String getSecondaryName() {
        return secondaryName;
    }
}
