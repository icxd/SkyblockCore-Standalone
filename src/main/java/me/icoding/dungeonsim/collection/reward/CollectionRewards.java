package me.icoding.dungeonsim.collection.reward;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionRewards extends ArrayList<CollectionReward> {

    private final int requirement;

    public CollectionRewards(int requirement, CollectionReward... rewards) {
        super(Arrays.asList(rewards));
        this.requirement = requirement;
    }

    public int getRequirement() {
        return requirement;
    }
}
