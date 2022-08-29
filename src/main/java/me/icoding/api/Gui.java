package me.icoding.api;

import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Gui extends Utility implements Listener {

    private final String name;
    private final Size size;

    private final Inventory inventory;

    // Creating a new inventory with the specified name and size.
    public Gui(String name, Size size) {
        this.name = name;
        this.size = size;

        this.inventory = Bukkit.createInventory(null, size.size(), ColorAPI.color(name));
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the size of the object.
     *
     * @return The size of the object.
     */
    public Size getSize() {
        return size;
    }

    /**
     * > This function is called whenever a player clicks on an inventory
     *
     * @param event The event that was fired.
     */
    @EventHandler
    public abstract void onInventoryClick(InventoryClickEvent event);

    /**
     * This function sets the item in the specified slot to the specified item.
     *
     * @param slot The slot you want to set the item in.
     * @param stack The ItemStack you want to set in the slot.
     */
    public void setItem(int slot, ItemStack stack) { inventory.setItem(slot, stack); }

    /**
     * Sets the item in the specified slot to the specified material.
     *
     * @param slot The slot you want to set the item in.
     * @param material The material of the item.
     */
    public void setItem(int slot, Material material) { setItem(slot, new ItemStack(material)); }

    /**
     * Sets the item in the specified slot to the specified material and amount.
     *
     * @param slot The slot you want to set the item in.
     * @param material The material of the item.
     * @param amount The amount of the item you want to set.
     */
    public void setItem(int slot, Material material, int amount) { setItem(slot, new ItemStack(material, amount)); }

    /**
     * Sets the item in the specified slot to the specified material, amount, and data.
     *
     * @param slot The slot you want to set the item in.
     * @param material The material of the item.
     * @param amount The amount of the item you want to set.
     * @param data The data value of the item.
     */
    public void setItem(int slot, Material material, int amount, int data) { setItem(slot, new ItemStack(material, amount, (short) data)); }

    /**
     * Adds the given item to the inventory.
     *
     * @param stack The itemstack to add to the inventory.
     */
    public void addItem(ItemStack stack) { inventory.addItem(stack); }

    /**
     * Adds an item to the inventory.
     *
     * @param material The material of the item to add.
     */
    public void addItem(Material material) { addItem(new ItemStack(material)); }

    /**
     * Adds an item to the inventory.
     *
     * @param material The material of the item you want to add.
     * @param amount The amount of the item to add.
     */
    public void addItem(Material material, int amount) { addItem(new ItemStack(material, amount)); }

    /**
     * Adds an item to the inventory.
     *
     * @param material The material of the item.
     * @param amount The amount of the item you want to add.
     * @param data The data value of the item.
     */
    public void addItem(Material material, int amount, int data) { addItem(new ItemStack(material, amount, (short) data)); }

    /**
     * It sets the items in the specified slots to the specified item
     *
     * @param stack The ItemStack you want to set.
     */
    public void setItems(ItemStack stack, int... slots) {
        for (int slot : slots)
            setItem(slot, stack);
    }

    /**
     * For every slot in the inventory, set the item to the given item.
     *
     * @param stack The ItemStack to fill the inventory with.
     */
    public void fill(ItemStack stack) { for (int i = 0; i < inventory.getSize(); i++) { setItem(i, stack); } }

    /**
     * Fill the inventory with the given item, then remove the middle rows.
     *
     * @param stack The ItemStack that will be used to fill the inventory.
     */
    public void outline(ItemStack stack) {
        fill(stack);
        for (int i = 10; i < size.size()-10; i++)
            setItem(i, ItemPreset.AIR);
        switch (this.size) {
            case SIZE_36: setItems(stack, 17, 18); break;
            case SIZE_45: setItems(stack, 17, 18, 26, 27); break;
            case SIZE_54: setItems(stack, 17, 18, 26, 27, 35, 36); break;
        }
    }

    /**
     * Open the inventory for the player.
     *
     * @param player The player who is opening the inventory.
     */
    public void openInventory(Player player) { player.openInventory(inventory); }

    // A enum that is used to get the size of the inventory.
    public enum Size {
        SIZE_9,
        SIZE_18,
        SIZE_27,
        SIZE_36,
        SIZE_45,
        SIZE_54;

        /**
         * This function returns the size of the enum value.
         *
         * @return The size of the enum.
         */
        public int size() {
            return Integer.parseInt(name().replace("SIZE_", ""));
        }
    }

    public static ItemStack closeButton() {
        ItemStack stack = new ItemStack(Material.BARRIER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(Utils.color("&cClose"));
        stack.setItemMeta(meta);
        return stack;
    }

    public static class ItemPreset {
        public static final ItemStack AIR = new ItemStack(Material.AIR);

        public static final ItemStack BLACK_STAINED_GLASS_PANE = glassItem(GlassColor.BLACK);
        public static final ItemStack WHITE_STAINED_GLASS_PANE = glassItem(GlassColor.WHITE);

        /**
         * It creates a glass pane item with the specified color and a blank name
         *
         * @param color The color of the glass pane.
         * @return A glass pane with the color of the GlassColor enum.
         */
        public static ItemStack glassItem(GlassColor color) {
            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color.dye());
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(" ");
            stack.setItemMeta(meta);
            return stack;
        }
    }

    // A enum that is used to get the dye color of the glass pane.
    public enum GlassColor {
        WHITE(0),
        ORANGE(1),
        MAGENTA(2),
        LIGHT_BLUE(3),
        YELLOW(4),
        GREEN(5),
        LIGHT_PURPLE(6),
        GRAY(7),
        LIGHT_GRAY(8),
        DARK_AQUA(9),
        DARK_PURPLE(10),
        DARK_BLUE(11),
        BROWN(12),
        DARK_GREEN(13),
        DARK_RED(14),
        BLACK(15);

        int dye;
        GlassColor(int dye) {
            this.dye = dye;
        }

        /**
         * This function returns the value of the dye variable.
         *
         * @return The value of the variable dye.
         */
        public int dye() { return dye; }
    }

}
