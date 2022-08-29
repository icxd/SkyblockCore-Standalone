package me.icoding.dungeonsim.gui.mainmenu;

import me.icoding.api.Gui;
import me.icoding.dungeonsim.collection.Collection;
import me.icoding.dungeonsim.collection.CollectionCategory;
import me.icoding.dungeonsim.collection.reward.CollectionReward;
import me.icoding.dungeonsim.collection.reward.CollectionRewards;
import me.icoding.dungeonsim.player.PlayerHandler;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionsGui extends Gui implements Listener {
    public CollectionsGui(Player player, CollectionCategory category, ItemStack categoryStack) {
        super(category.id()+" Collection", Size.SIZE_54);
        outline(ItemPreset.BLACK_STAINED_GLASS_PANE);
        setItem(4, categoryStack);
        setItem(49, closeButton());

        FileConfiguration config = PlayerHandler.getPlayerData(player);

        for (Collection collection : Collection.values()) {
            int amount = config.getInt("player.collection."+category.name().toLowerCase()+"."+collection.getPath());
            int max = Utils.getClosestNumber(amount, collection.getRewards());
            int level = 0;
            for (CollectionRewards reward : collection.getRewards())
                if (amount >= reward.getRequirement())
                    level += 1;
            if (collection.getCategory() == category) {
                ItemStack stack = new ItemStack((amount == 0 ? Material.INK_SACK : collection.getItem()), 1, (short) (amount == 0 ? 8 : 0));
                ItemMeta meta = stack.getItemMeta();
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
                meta.setDisplayName(Utils.color("" + (amount == 0 ? "&c"+collection.getName() : "&e"+collection.getName()+" "+Utils.toRomanNumeral(level))));

                if (amount == 0) {
                    meta.setLore(Arrays.asList(
                            Utils.color("&7Find this item to add it to your"),
                            Utils.color("&7collection and unlock collection"),
                            Utils.color("&7rewards!")
                    ));
                } else {
                    List<String> lore = new ArrayList<>();


                    lore.addAll(Arrays.asList(
                            Utils.color("&7View all your "+collection.getName()+" Collection"),
                            Utils.color("&7progress and rewards!"),
                            Utils.color("&7"),
                            Utils.color("&7Progress to "+collection.getName()+" "+Utils.toRomanNumeral(level+1)+": &e"+(amount == 0 ? "0" : ((amount / max)*100))+"&6%"),
                            /*Utils.color(Utils.createProgressBar(amount, max, 20, "-", ChatColor.DARK_GREEN, ChatColor.WHITE)+" &e"+ amount +"&6/&e"+ max),*/
                            Utils.color("&7"),
                            Utils.color("&7Contributions:"),
                            Utils.color("&7"+player.getName()+": &e"+amount),
                            Utils.color("&7")
                    ));

                    if (collection.getRewards().size() > 0) {
                        lore.add(Utils.color("&7"+collection.getName()+" "+Utils.toRomanNumeral(level+1)+" Reward:"));
                        lore.add(Utils.color("&7  "+(collection.getRewards().get(level+1) != null ? collection.getRewards().get(level+1) : "Coming Soon &8(&4COMING SOON&8)")));
                        lore.add(Utils.color("&7"));
                    }

                    lore.add(Utils.color("&eClick to view!"));

                    meta.setLore(lore);
                }

                stack.setItemMeta(meta);
                addItem(stack);
            }
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) return;
        if (!ChatColor.stripColor(event.getClickedInventory().getTitle()).equalsIgnoreCase(this.getName())) return;

        event.setCancelled(true);

        ItemStack stack = event.getCurrentItem();

        switch (event.getCurrentItem().getType()) {
            case BARRIER:
                new CollectionGui(player).openInventory(player);
                break;
        }
    }
}
