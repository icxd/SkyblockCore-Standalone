package me.icoding.dungeonsim.collection;

import me.icoding.dungeonsim.collection.reward.CollectionRewards;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Collection {

    SEEDS("Seeds", CollectionCategory.FARMING, Material.SEEDS,
            new CollectionRewards(50),
            new CollectionRewards(100),
            new CollectionRewards(250),
            new CollectionRewards(1000),
            new CollectionRewards(2500),
            new CollectionRewards(5000)),
    WHEAT("Wheat", CollectionCategory.FARMING, Material.WHEAT,
            new CollectionRewards(50),
            new CollectionRewards(100),
            new CollectionRewards(250),
            new CollectionRewards(1000),
            new CollectionRewards(2500),
            new CollectionRewards(10000),
            new CollectionRewards(15000),
            new CollectionRewards(25000),
            new CollectionRewards(50000),
            new CollectionRewards(100000)),
    COBBLESTONE("Cobblestone", CollectionCategory.MINING, Material.COBBLESTONE,
            new CollectionRewards(50),
            new CollectionRewards(100),
            new CollectionRewards(250),
            new CollectionRewards(1000),
            new CollectionRewards(2500),
            new CollectionRewards(5000),
            new CollectionRewards(10000),
            new CollectionRewards(25000),
            new CollectionRewards(40000),
            new CollectionRewards(70000)),
    ;

    String name; String path; CollectionCategory category; Material item; List<CollectionRewards> rewards;
    Collection(String name, CollectionCategory category, Material item, CollectionRewards... rewards) {
        this.name = name;
        this.path = name().toLowerCase();
        this.category = category;
        this.item = item;
        this.rewards = Arrays.asList(rewards);
    }

    public static Collection getCollection(String collectionName) {
        return valueOf(collectionName);
    }

    public String getName() { return name; }
    public String getPath() { return path; }
    public CollectionCategory getCategory() { return category; }
    public Material getItem() { return item; }

    public List<CollectionRewards> getRewards() {
        return rewards;
    }
}
