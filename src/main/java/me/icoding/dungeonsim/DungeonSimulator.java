package me.icoding.dungeonsim;

import me.icoding.dungeonsim.commands.*;
import me.icoding.dungeonsim.cookie.CookieRunnable;
import me.icoding.dungeonsim.gui.ItemsGui;
import me.icoding.dungeonsim.gui.mainmenu.CollectionGui;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import me.icoding.dungeonsim.listeners.initialize.JoinListener;
import me.icoding.dungeonsim.listeners.initialize.LeaveListener;
import me.icoding.dungeonsim.listeners.items.ItemListener;
import me.icoding.dungeonsim.player.PlayerHandler;
import me.icoding.dungeonsim.stats.Stats;
import me.icoding.dungeonsim.stats.StatsRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class DungeonSimulator extends JavaPlugin {

    private static DungeonSimulator instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("items").setExecutor(new ItemsCommand());
        getCommand("collection").setExecutor(new CollectionCommand());
        getCommand("thaumaturgist").setExecutor(new ThaumaturgistCommand());
        getCommand("accessory").setExecutor(new AccessoryCommand());
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("nbt").setExecutor(new NBTCommand());

        PluginManager pm = getServer().getPluginManager();
        for (Class<?> listener : new Reflections().getSubTypesOf(Listener.class)) {
            try {
                pm.registerEvents((Listener) listener.newInstance(), this);
            } catch (InstantiationException | IllegalAccessException ignored) {
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            new PlayerHandler().initializePlayer(player);

            Stats.initializeStats(player);
        }

        new StatsRunnable();
        new CookieRunnable();
        try {
            new ItemManager().registerItems();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static DungeonSimulator getInstance() {
        return instance;
    }

}
