package me.icoding.dungeonsim.item;

public class GemstoneSlot {

    private GemstoneType type;
    private boolean unlocked;
    private int price;
    private ItemData requiredItem;
    private int itemAmount;

    public GemstoneSlot(GemstoneType type, boolean unlocked, int price, ItemData requiredItem, int itemAmount) {
        this.type = type;
        this.unlocked = unlocked;
        this.price = price;
        this.requiredItem = requiredItem;
        this.itemAmount = itemAmount;
    }

    public GemstoneType getType() {
        return type;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public int getPrice() {
        return price;
    }

    public ItemData getRequiredItem() {
        return requiredItem;
    }

    public int getItemAmount() {
        return itemAmount;
    }
}
