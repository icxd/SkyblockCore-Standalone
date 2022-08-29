package me.icoding.dungeonsim.utils;

import com.google.common.base.Strings;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.icoding.dungeonsim.collection.reward.CollectionRewards;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import me.icoding.dungeonsim.item.Rarity;
import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static String toRomanNumeral(int num) {
        StringBuilder sb = new StringBuilder();
        int times;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }
    public static ItemStack setSkull(ItemStack stack, String url) {
        if (url.isEmpty())
            return null;
        SkullMeta headMeta = (SkullMeta) stack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode((String.format("{textures:{SKIN:{url:\"%s\"}}}", url)).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        stack.setItemMeta(headMeta);

        return stack;
    }
    public static ItemStack skull(ItemStack head, String skin) {
        if (skin.isEmpty()) return head;
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedbata = skin.getBytes();
        profile.getProperties().put("textures", new Property("textures", new String(encodedbata)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
    public static ItemData getItemData(ItemStack stack) {
        if (stack == null) return null;
        ItemMeta meta = stack.getItemMeta();
        if (meta.getDisplayName() == null) return null;
        String itemID = toItemID(meta.getDisplayName());
        return ItemManager.getItemFromName(itemID);
    }
    public static String toItemID(String itemName) {
        return ChatColor.stripColor(itemName.replace("'", "").replace("\"", "").replace(" ", "_").toUpperCase());
    }
    public static String createProgressBar(int current, int max, int total, String character, ChatColor completedColor, ChatColor notCompletedColor) {
        float percent = (float) 0;
        if (current != 0) percent = (float) current / max;
        int progressBars = (int) (total * percent);

        return Strings.repeat("" + completedColor + character, progressBars)
                + Strings.repeat("" + notCompletedColor + character, total - progressBars);
    }
    public static int getClosestNumber(int value, List<CollectionRewards> rewards) {
        int distance = Math.abs(rewards.get(0).getRequirement() - value);
        int idx = 0;
        for(int c = 1; c < rewards.size(); c++){
            int cdistance = Math.abs(rewards.get(c).getRequirement() - value);
            if(cdistance < distance){
                idx = c;
                distance = cdistance;
            }
        }
        return rewards.get(idx).getRequirement();
    }
    public static boolean isCloseEnough(int value, int max) {
        int diff = Math.abs(max - value);
        if (value < max) return false;
        if (diff >= max) return true;
        return false;
    }
    public static int getMagicPowerFromRarity(Rarity rarity) {
        switch (rarity) {
            case COMMON: return 3;
            case UNCOMMON: return 5;
            case RARE: return 8;
            case EPIC: return 12;
            case LEGENDARY: return 16;
            case MYTHIC: return 22;
            default: return 0;
        }
    }
    public static double getStatMultiplier(int magicPower) {
        return Math.pow(29.97*(Math.log(0.0019*magicPower+1)), 1.2);
    }
    public static double getAccessoryStatBoost(double stat, ItemData accessory) {
        int mp = getMagicPowerFromRarity(accessory.tier());
        return Math.round(stat * getStatMultiplier(mp));
    }
    public static int getTotalMagicPower(int common, int uncommon, int rare, int epic, int legendary, int mythic) {
        return (3 * common) + (5 * uncommon) + (8 * rare) + (12 * epic) + (16 * legendary) + (22 * mythic);
    }
    public static ArrayList<ItemData> getAccessory(Player player) {
        ArrayList<ItemData> items = new ArrayList<>();
        List<String> accessories = PlayerHandler.getPlayerData(player).getStringList("player.accessories.bag");
        for (String id : accessories) {
            ItemData data = ItemManager.getItem(id);
            items.add(data);
        }
        return items;
    }
}
