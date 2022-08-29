package me.icoding.dungeonsim.listeners.collection;

import me.icoding.dungeonsim.collection.Collection;
import me.icoding.dungeonsim.event.CollectionLevelUpEvent;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CollectionLevelListener implements Listener {

    @EventHandler
    public void onCollectionLevelUp(CollectionLevelUpEvent event) {
        Player player = event.getPlayer();
        Collection collection = event.getCollection();
        int oldLevel = event.getOldLevel();
        int newLevel = event.getNewLevel();

        player.sendMessage(Utils.color("&e&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
        player.sendMessage(Utils.color("&6&l  COLLECTION LEVEL UP &e"+collection.getName()+" &8"+Utils.toRomanNumeral(oldLevel)+"➜&e"+Utils.toRomanNumeral(newLevel)));
        player.sendMessage(Utils.color(""));
        player.sendMessage(Utils.color("&a&l  REWARD"));
        player.sendMessage(Utils.color("&c     Coming Soon &8(&4COMING SOON&8)"));
        player.sendMessage(Utils.color("&e&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
    }

}
