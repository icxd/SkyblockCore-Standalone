package me.icoding.dungeonsim.skill.reward;

import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SkillRewardCoins extends SkillReward {
    private final int coins;

    public SkillRewardCoins(int coins) {
        super(Type.COINS);

        this.coins = coins;
    }

    @Override
    public void onAchieve(Player player) {
        FileConfiguration config = PlayerHandler.getPlayerData(player);
        File file = PlayerHandler.getPlayerFile(player);

        String path = "player.currency.coins";
        config.set(path, config.getInt(path) + coins);

        try { config.save(file); }
        catch (IOException ignored) {}
    }

    @Override
    public String toRewardString() {
        return "&6"+coins+" &7Coins";
    }
}
