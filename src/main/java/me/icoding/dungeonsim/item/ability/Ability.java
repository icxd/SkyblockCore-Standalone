package me.icoding.dungeonsim.item.ability;

import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public enum Ability {

    INSTANT_TRANSMISSION("Instant Transmission", AbilityActivation.RIGHT_CLICK, true, "&7Teleport &a8 blocks&7 ahead of %nl%&7you and gain &a+50 &f✦ Speed%nl%&7for &a3 seconds&7.", 50, 0, player -> {
        try {
            Location location = player.getTargetBlock((Set<Material>) null, 8).getLocation();
            location.setYaw(player.getLocation().getYaw());
            location.setPitch(player.getLocation().getPitch());
            player.teleport(location.add(0, 1, 0).add(0.5D, 0.0D, 0.5D));
        } catch (IllegalStateException ex) {}
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3f, 1f);
    }),
    BOOSTER_COOKIE("Cookie Buff", AbilityActivation.RIGHT_CLICK, false, "&7Consume to gain the &dCookie%nl%&dBuff &7for &b4 &7days:%nl%%nl%&8▶ &7Ability to gain &bBits&7!%nl%&8▶ &b+20% &7Skill XP%nl%&8▶ &b+15SYMBOL &7Magic Find%nl%&8▶ &7Keep &6coins&7 and &deffects&7 on death%nl%&8▶ &ePermafly&7 on private islands%nl%&8▶ &7Access &6/ah&7 and &6/bazaar&7 anywhere%nl%&8▶ &7Sell items directly to the trades menu%nl%&8▶ &7AFK &aimmunity&7 on your island%nl%&8▶ &7Toggle specific &dpotion effects%nl%&8▶ &7Access to &6/anvil &7and &6/etable", 0, 0, player -> {
        FileConfiguration config = PlayerHandler.getPlayerData(player);

        long totalTime = config.getLong("player.cookie.duration");
        long removeTime = TimeUnit.DAYS.toMillis(4);
        long finalTime = (totalTime + removeTime);

        config.set("player.cookie.duration", finalTime);
        config.set("player.cookie.hascookie", true);

        player.sendMessage("new time: " + finalTime);

        try { config.save(PlayerHandler.getPlayerFile(player)); }
        catch (IOException e) { e.printStackTrace(); }

        player.getInventory().setItemInHand(null);
        player.updateInventory();
    }),
    ;

    String name;
    AbilityActivation activation;
    boolean showThingy;
    String description;
    int manaCost;
    int cooldown;
    Consumer<Player> function;
    Ability(String name, AbilityActivation activation, boolean showThingy, String description, int manaCost, int cooldown, Consumer<Player> function) {
        this.name = name;
        this.activation = activation;
        this.showThingy = showThingy;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.function = function;
    }

    public String getName() {
        return name;
    }
    public AbilityActivation getActivation() {
        return activation;
    }
    public boolean isShowThingy() {
        return showThingy;
    }
    public String getDescription() {
        return description;
    }
    public int getManaCost() {
        return manaCost;
    }
    public int getCooldown() {
        return cooldown;
    }
    public Consumer<Player> getFunction() {
        return function;
    }
}
