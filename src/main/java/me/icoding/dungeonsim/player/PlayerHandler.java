package me.icoding.dungeonsim.player;

import me.icoding.dungeonsim.DungeonSimulator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerHandler {

    public void initializePlayer(Player player) {
        FileConfiguration config = getPlayerData(player);
        File file = getPlayerFile(player);

        if (!file.exists()) {
            try {
                config.set("player.uuid", player.getUniqueId().toString());

                config.set("player.currency.coins", 0.00);
                config.set("player.currency.bits", 0);
                config.set("player.currency.gems", 0);

                config.set("player.cookie.hascookie", false);
                config.set("player.cookie.duration", 0);
                config.set("player.fame", 0);

                config.set("player.skills.farming", 0);
                config.set("player.skills.mining", 0);
                config.set("player.skills.combat", 0);
                config.set("player.skills.foraging", 0);
                config.set("player.skills.fishing", 0);
                config.set("player.skills.enchanting", 0);
                config.set("player.skills.alchemy", 0);
                config.set("player.skills.carpentry", 0);
                config.set("player.skills.runecrafting", 0);
                config.set("player.skills.social", 0);
                config.set("player.skills.taming", 0);
                config.set("player.skills.dungeoneering", 0);

                config.set("player.accessories.unlocked", "");
                config.set("player.accessories.bag", "");

                config.save(file);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static FileConfiguration getPlayerData(Player player) {
        File userData = new File(DungeonSimulator.getInstance().getDataFolder() + "/users", File.separator);
        File f = new File(userData, File.separator + player.getUniqueId().toString() + ".yml");
        return YamlConfiguration.loadConfiguration(f);
    }

    public static File getPlayerFile(Player player) {
        File userData = new File(DungeonSimulator.getInstance().getDataFolder() + "/users", File.separator);
        return new File(userData, File.separator + player.getUniqueId().toString() + ".yml");
    }

}
