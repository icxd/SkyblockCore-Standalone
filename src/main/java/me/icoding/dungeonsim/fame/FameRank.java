package me.icoding.dungeonsim.fame;

import me.icoding.dungeonsim.DungeonSimulator;
import me.icoding.dungeonsim.player.PlayerHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public enum FameRank {

    NEW_PLAYER("New Player", 0, 1.0, 1, 4800),
    SETTLER("Settler", 20000, 1.1, 1, 5280),
    CITIZEN("Citizen", 80000, 1.2, 2, 5760),
    CONTRIBUTOR("Contributor", 200000, 1.3, 3, 6240),
    PHILANTHROPIST("Philanthropist", 400000, 1.4, 5, 6720),
    PATRON("Patron", 800000, 1.6, 10, 7680),
    FAMOUS_PLAYER("Famous Player", 1500000, 1.8, 20, 8640),
    ATTACHE("Attaché", 3000000, 1.9, 25, 9120),
    AMBASSADOR("Ambassador", 10000000, 2.0, 50, 9600),
    STATESPERSON("Statesperson", 20000000, 2.04, 75, 9792),
    SENATOR("Senator", 33000000, 2.08, 100, 9984),
    DIGNITARY("Dignitary", 50000000, 2.12, 100, 10176),
    COUNCILOR("Councilor", 72000000, 2.16, 100, 10368),
    MINISTER("Minister", 100000000, 2.2, 100, 10560),
    PREMIER("Premier", 135000000, 2.22, 100, 10656),
    CHANCELLOR("Chancellor", 178000000, 2.24, 100, 10752),
    SUPREME("Supreme", 230000000, 2.26, 100, 10848);

    String name;
    int requiredFame;
    double multiplier;
    int electionVotes;
    int bitsPerCookie;

    FameRank(String name, int requiredFame, double multiplier, int electionVotes, int bitsPerCookie) {
        this.name = name;
        this.requiredFame = requiredFame;
        this.multiplier = multiplier;
        this.electionVotes = electionVotes;
        this.bitsPerCookie = bitsPerCookie;
    }

    public String getName() { return name; }
    public int getRequiredFame() { return requiredFame; }
    public double getMultiplier() { return multiplier; }
    public int getElectionVotes() { return electionVotes; }
    public int getBitsPerCookie() { return bitsPerCookie; }

    public static FameRank getFameRank(Player player) {
        FileConfiguration config = PlayerHandler.getPlayerData(player);
        int fame = config.getInt("player.fame");

        FameRank[] ranks = FameRank.values();
        for (int i = 0; i < ranks.length; i++) {
            FameRank c = ranks[i];

            try {
                if (fame >= c.getRequiredFame() && fame < ranks[i+1].getRequiredFame())
                    return c;
            } catch(ArrayIndexOutOfBoundsException ignored) {
                return c;
            }
        }

        return NEW_PLAYER;
    }

}
