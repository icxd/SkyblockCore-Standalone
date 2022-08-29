package me.icoding.dungeonsim.item;

import me.icoding.dungeonsim.utils.Groups;
import me.icoding.dungeonsim.utils.Utils;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.UUID;

public class ItemBuilder {

    private static final String SKYBLOCK_ID = ChatColor.DARK_GRAY + "Skyblock ID: %s";

    public ItemStack buildItem(ItemData data) {
        ItemStack stack = new ItemStack(data.material(), 1, (short) data.durability());

        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound compound = (nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound());

        compound.setString("ID", data.id());
        compound.setString("Material", data.material().name());
        compound.setString("Name", data.name());
        compound.setString("Category", data.category().name());
        compound.setString("Tier", data.tier().name());
        compound.setString("Soulbound", data.soulbound().name());
        compound.setString("Skin", data.skin());
        if (data.unstackable())
            compound.setString("Unstackable", UUID.randomUUID().toString());
        compound.setBoolean("CanHaveAttributes", data.can_have_attributes());
        compound.setBoolean("DungeonItem", data.dungeon_item());
        compound.setBoolean("Museum", data.museum());
        compound.setBoolean("Glowing", data.glowing());

        nmsStack.setTag(compound);
        stack = CraftItemStack.asBukkitCopy(nmsStack);

        if (data.material().equals(Material.SKULL_ITEM)) Utils.skull(stack, data.skin());
        ItemMeta meta = stack.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();

        stat(lore, "Gear Score", data.gear_score(), false, "", ChatColor.LIGHT_PURPLE, data.dungeon_item());
        stat(lore, "Damage", data.stat_damage(), true, "", ChatColor.RED, data.dungeon_item());
        stat(lore, "Strength", data.stat_strength(), true, "", ChatColor.RED, data.dungeon_item());
        stat(lore, "Crit Chance", data.stat_critical_chance(), true, "%", ChatColor.RED, data.dungeon_item());
        stat(lore, "Crit Damage", data.stat_critical_damage(), true, "%", ChatColor.RED, data.dungeon_item());
        stat(lore, "Bonus Attack Speed", data.stat_attack_speed(), true, "", ChatColor.RED, data.dungeon_item());
        stat(lore, "Health", data.stat_health(), true, "", ChatColor.GREEN, data.dungeon_item());
        stat(lore, "Defense", data.stat_defense(), true, "", ChatColor.GREEN, data.dungeon_item());
        stat(lore, "Speed", data.stat_walk_speed(), true, "", ChatColor.GREEN, data.dungeon_item());
        stat(lore, "Intelligence", data.stat_intelligence(), true, "", ChatColor.GREEN, data.dungeon_item());
        stat(lore, "Ferocity", data.stat_ferocity(), true, "", ChatColor.GREEN, data.dungeon_item());
        stat(lore, "True Defense", data.stat_true_defense(), true, "", ChatColor.GREEN, data.dungeon_item());

        String gemstone_slots = "";
        if (data.gemstone_slot_1() != null)
            gemstone_slots += Utils.color(" &8[" + (data.gemstone_slot_1().isUnlocked() ? "&7" : "&8") + data.gemstone_slot_1().getType().icon + "&8]");
        if (data.gemstone_slot_2() != null)
            gemstone_slots += Utils.color(" &8[" + (data.gemstone_slot_2().isUnlocked() ? "&7" : "&8") + data.gemstone_slot_2().getType().icon + "&8]");
        if (data.gemstone_slot_3() != null)
            gemstone_slots += Utils.color(" &8[" + (data.gemstone_slot_3().isUnlocked() ? "&7" : "&8") + data.gemstone_slot_3().getType().icon + "&8]");
        if (data.gemstone_slot_4() != null)
            gemstone_slots += Utils.color(" &8[" + (data.gemstone_slot_4().isUnlocked() ? "&7" : "&8") + data.gemstone_slot_4().getType().icon + "&8]");
        if (data.gemstone_slot_5() != null)
            gemstone_slots += Utils.color(" &8[" + (data.gemstone_slot_5().isUnlocked() ? "&7" : "&8") + data.gemstone_slot_5().getType().icon + "&8]");

        if (!gemstone_slots.equals("")) lore.add(gemstone_slots);

        if (data.ability() != null) {
            if (data.ability().isShowThingy()) {
                lore.add(" ");
                lore.add(Utils.color("&6Ability: " + data.ability().getName() + "&e&l " + data.ability().getActivation().getName()));
            }
            for (String l : data.ability().getDescription().split("%nl%")) {
                lore.add(Utils.color(l));
            }
            if (data.ability().getManaCost() != 0)
                lore.add(Utils.color("&8Mana Cost: &3" + data.ability().getManaCost()));
            if (data.ability().getCooldown() != 0)
                lore.add(Utils.color("&8Cooldown: &a" + data.ability().getCooldown() + "s"));
            lore.add(" ");
        }

        if (data.soulbound() == BoundType.SOLO) lore.add(Utils.color("&8* Soulbound *"));
        else if (data.soulbound() == BoundType.COOP) lore.add(Utils.color("&8* Co-op Soulbound *"));

        if (data.requirement_type() != RequirementType.NONE) {
            for (RequirementType type : Groups.SLAYERS)
                if (type == data.requirement_type()) {
                    lore.add(Utils.color("&4☠ &cRequires &5" + data.requirement_type().getName() + " " + data.requirement_level()));
                    break;
                } else {
                    lore.add(Utils.color("&4❣ &cRequires &a" + data.requirement_type().getName() + " " + data.requirement_level()));
                    break;
                }
        }

        lore.add(data.tier().lore() + (data.dungeon_item() ? " DUNGEON " : " ") + (data.category().equals(Category.NONE) ? "" : data.category().name()));
        lore.add(String.format(SKYBLOCK_ID, data.id()));

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(Utils.color(data.tier().color() + data.name()));
        meta.setLore(lore);

        stack.setItemMeta(meta);

        if (data.material().equals(Material.LEATHER_BOOTS) || data.material().equals(Material.LEATHER_LEGGINGS) ||
                data.material().equals(Material.LEATHER_CHESTPLATE) || data.material().equals(Material.LEATHER_HELMET)) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) stack.getItemMeta();
            leatherArmorMeta.setColor(Color.fromRGB(data.leather_color_red(), data.leather_color_green(), data.leather_color_blue()));
            stack.setItemMeta(leatherArmorMeta);
        }
        if (data.glowing()) stack = glow(stack);

        return stack;
    }

    private void stat(ArrayList<String> lore, String statName, int value, boolean include, String ending, ChatColor color, boolean isDungeonItem) {
        if (value == 0) return;
        double starBonus = value + (value * (1 + ItemStar.ZERO.getStar()));
        lore.add(Utils.color("&7" + statName + ": " + color + (include ? (value < 0 ? "" : "+") : "") + value + ending + (isDungeonItem ? "&8 (+" + starBonus + ending + ")" : "")));
    }

    private ItemStack glow(ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbt = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagList ench = new NBTTagList();
        nbt.set("ench", ench);
        nmsItem.setTag(nbt);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

}
