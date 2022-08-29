package me.icoding.dungeonsim.listeners.player;

import me.icoding.dungeonsim.DungeonSimulator;
import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new PlayerHandler().initializePlayer(player);
    }

}
