package me.icoding.dungeonsim.utils;

import me.icoding.dungeonsim.item.RequirementType;

public class Groups {
    public static RequirementType[] SKILLS = new RequirementType[] {
            RequirementType.FARMING,
            RequirementType.MINING,
            RequirementType.COMBAT,
            RequirementType.FORAGING,
            RequirementType.FISHING,
            RequirementType.ENCHANTING,
            RequirementType.ALCHEMY,
            RequirementType.CARPENTRY,
            RequirementType.RUNECRAFTING,
            RequirementType.SOCIAL,
            RequirementType.TAMING
    };

    public static RequirementType[] SLAYERS = new RequirementType[] {
            RequirementType.ZOMBIE,
            RequirementType.SPIDER,
            RequirementType.WOLF,
            RequirementType.ENDERMAN,
            RequirementType.BLAZE
    };

}
