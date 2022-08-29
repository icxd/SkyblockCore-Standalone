package me.icoding.dungeonsim.gui.mainmenu;

import me.icoding.api.Gui;
import me.icoding.dungeonsim.collection.Collection;
import me.icoding.dungeonsim.collection.CollectionCategory;
import me.icoding.dungeonsim.player.PlayerHandler;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CollectionGui extends Gui implements Listener {

    public CollectionGui() {
        this(null);
    }

    public CollectionGui(Player player) {
        super("Collection", Size.SIZE_54);

        if (player == null) return;

        fill(ItemPreset.BLACK_STAINED_GLASS_PANE);

        FileConfiguration config = PlayerHandler.getPlayerData(player);

        int farmingCollections = 0;
        int farmingCollectionsUnlocked = 0;
        int miningCollections = 0;
        int miningCollectionsUnlocked = 0;
        int combatCollections = 0;
        int combatCollectionsUnlocked = 0;
        int foragingCollections = 0;
        int foragingCollectionsUnlocked = 0;
        int fishingCollections = 0;
        int fishingCollectionsUnlocked = 0;
        int bossCollections = 0;
        int bossCollectionsUnlocked = 0;
        for (Collection collection : Collection.values()) {
            if (collection.getCategory() == CollectionCategory.FARMING) farmingCollections++;
            if (collection.getCategory() == CollectionCategory.MINING) miningCollections++;
            if (collection.getCategory() == CollectionCategory.COMBAT) combatCollections++;
            if (collection.getCategory() == CollectionCategory.FORAGING) foragingCollections++;
            if (collection.getCategory() == CollectionCategory.FISHING) fishingCollections++;
            if (collection.getCategory() == CollectionCategory.BOSS) bossCollections++;
        }

        try {
            for (String category : config.getConfigurationSection("player.collection").getKeys(false)) {
                for (String collection : config.getConfigurationSection("player.collection." + category).getKeys(false)) {
                    if (category.equals("farming")) farmingCollectionsUnlocked++;
                    if (category.equals("mining")) miningCollectionsUnlocked++;
                    if (category.equals("combat")) combatCollectionsUnlocked++;
                    if (category.equals("foraging")) foragingCollectionsUnlocked++;
                    if (category.equals("fishing")) fishingCollectionsUnlocked++;
                    if (category.equals("boss")) bossCollectionsUnlocked++;
                }
            }
        } catch (NullPointerException ignored) {
        }

        setItem(20, getItemStack(Material.GOLD_HOE, "Farming", farmingCollections, farmingCollectionsUnlocked));
        setItem(21, getItemStack(Material.STONE_PICKAXE, "Mining", miningCollections, miningCollectionsUnlocked));
        setItem(22, getItemStack(Material.STONE_SWORD, "Combat", combatCollections, combatCollectionsUnlocked));
        setItem(23, getItemStack(Material.SAPLING, "Foraging", foragingCollections, foragingCollectionsUnlocked));
        setItem(24, getItemStack(Material.FISHING_ROD, "Fishing", fishingCollections, fishingCollectionsUnlocked));
        setItem(31, getItemStack(Material.SKULL_ITEM, "Boss", bossCollections, bossCollectionsUnlocked));


        setItem(49, closeButton());
    }

    private ItemStack getItemStack(Material material, String name, int collections, int collectionsUnlocked) {
        ItemStack stack = new ItemStack(material, 1, (short) (material == Material.SAPLING ? 3 : (material == Material.SKULL_ITEM ? 1 : 0)));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(Utils.color("&" + (material == Material.SKULL_ITEM ? "5" : "a") + name + " Collection"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
        if (material == Material.SKULL_ITEM) {
            meta.setLore(Arrays.asList(Utils.color("&7View your progress and claim"), Utils.color("&7rewards you have obtained from"), Utils.color("&7defeating SkyBlock bosses!"), "",
                    Utils.color("&7Collection Unlocked: &e" + (collectionsUnlocked == 0 ? "0" : ((collectionsUnlocked / collections) * 100)) + "&6%"),
                    Utils.color(Utils.createProgressBar(collectionsUnlocked, collections, 20, "-", ChatColor.DARK_GREEN, ChatColor.WHITE) + " &e" + collectionsUnlocked + "&6/&e" + collections), "",
                    Utils.color("&eClick to view!")));
        } else {
            meta.setLore(Arrays.asList(Utils.color("&7View you " + name + " Collection!"), "",
                    Utils.color("&7Collection Unlocked: &e" + (collectionsUnlocked == 0 ? "0" : ((collectionsUnlocked / collections) * 100)) + "&6%"),
                    Utils.color(Utils.createProgressBar(collectionsUnlocked, collections, 20, "-", ChatColor.DARK_GREEN, ChatColor.WHITE) + " &e" + collectionsUnlocked + "&6/&e" + collections), "",
                    Utils.color("&eClick to view!")));
        }

        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (!ChatColor.stripColor(event.getClickedInventory().getTitle()).equalsIgnoreCase(this.getName())) return;

        event.setCancelled(true);

        ItemStack stack = event.getCurrentItem();

        switch (event.getCurrentItem().getType()) {
            case GOLD_HOE:
                new CollectionsGui(player, CollectionCategory.FARMING, stack).openInventory(player);
                break;
            case STONE_PICKAXE:
                new CollectionsGui(player, CollectionCategory.MINING, stack).openInventory(player);
                break;
            case STONE_SWORD:
                new CollectionsGui(player, CollectionCategory.COMBAT, stack).openInventory(player);
                break;
            case SAPLING:
                new CollectionsGui(player, CollectionCategory.FORAGING, stack).openInventory(player);
                break;
            case FISHING_ROD:
                new CollectionsGui(player, CollectionCategory.FISHING, stack).openInventory(player);
                break;
            case SKULL_ITEM:
                new CollectionsGui(player, CollectionCategory.BOSS, stack).openInventory(player);
                break;
        }
    }
}
