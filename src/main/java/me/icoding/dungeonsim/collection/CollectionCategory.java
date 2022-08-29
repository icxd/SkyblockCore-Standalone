package me.icoding.dungeonsim.collection;

public enum CollectionCategory {

    FARMING, MINING, COMBAT, FISHING, FORAGING, BOSS;

    public String id() { return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(); }

}
