package me.icoding.dungeonsim.cookie;

import me.icoding.dungeonsim.DungeonSimulator;
import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CookieRunnable {

    public CookieRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    FileConfiguration config = PlayerHandler.getPlayerData(player);
                    if (!config.getBoolean("player.cookie.hascookie")) return;

                    long totalTime = config.getLong("player.cookie.duration");
                    long removeTime = TimeUnit.SECONDS.toMillis(1);
                    long finalTime = (totalTime - removeTime);

                    if (finalTime <= 0) {
                        config.set("player.cookie.hascookie", false);
                        config.set("player.cookie.duration", 0);
                        return;
                    }

                    player.sendMessage("new time: " + finalTime);
                    config.set("player.cookie.duration", finalTime);

                    try { config.save(PlayerHandler.getPlayerFile(player)); }
                    catch (IOException e) { e.printStackTrace(); }
                }
            }
        }.runTaskTimer(DungeonSimulator.getInstance(), 0, 20);
    }

}
