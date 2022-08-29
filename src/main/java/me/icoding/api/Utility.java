package me.icoding.api;

import com.google.common.collect.Lists;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utility {

    /**
     * It sends packets to players
     */
    public static class PacketAPI {
        /**
         * It sends a packet to all online players
         *
         * @param packet The packet you want to send.
         */
        public static void sendPacket(Packet<?> packet){
            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }

        /**
         * It sends a packet to a player
         *
         * @param player The player you want to send the packet to.
         * @param packet The packet you want to send.
         */
        public static void sendPacket(Player player, Packet<?> packet) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    /**
     * It's a class that allows you to color strings
     */
    public static class ColorAPI {
        /**
         * It takes a string, and replaces all instances of '&' with the Minecraft color code character
         *
         * @param message The message you want to color.
         * @return The message with the color codes translated.
         */
        public static String color(String message) {
            return ChatColor.translateAlternateColorCodes('&', message);
        }

        /**
         * It takes a variable amount of strings, and returns an ArrayList of the strings with color codes
         *
         * @return An ArrayList of Strings
         */
        public static ArrayList<String> color(String... messages) {
            ArrayList<String> f = new ArrayList<>();
            Arrays.stream(messages).forEach(e -> f.add(color(e)));
            return f;
        }

        /**
         * It takes a string and replaces all the color codes with the actual color codes
         *
         * @param message The message to be formatted.
         * @return A string with color codes.
         */
        public static String format(String message, Object... objects) {
            return color(String.format(message, objects));
        }
    }

    /**
     * It's a class that represents a region in a world
     */
    public static class Region {

        private final Location min;
        private final Location max;
        private String world;
        private Vector minV, maxV;

        // It's setting the min and max locations, and then it's setting the world name. Then it's getting the min and max
        // X, Y, and Z values. Then it's setting the min and max vectors.
        public Region(Location min, Location max) {
            this.min = min;
            this.max = max;

            this.world = min.getWorld().getName();

            double xPos1 = Math.min(min.getX(), max.getX());
            double yPos1 = Math.min(min.getY(), max.getY());
            double zPos1 = Math.min(min.getZ(), max.getZ());
            double xPos2 = Math.max(min.getX(), max.getX());
            double yPos2 = Math.max(min.getY(), max.getY());
            double zPos2 = Math.max(min.getZ(), max.getZ());

            minV = new Vector(xPos1,yPos1,zPos1);
            maxV = new Vector(xPos2,yPos2,zPos2);
        }

        /**
         * Returns true if the given location is within the bounding box.
         *
         * @param location The location to check
         * @return A boolean value.
         */
        public boolean containsLocation(Location location){
            return location.toVector().isInAABB(minV, maxV);
        }

        /**
         * It gets all the blocks in the region
         *
         * @return A list of blocks
         */
        public List<Block> getBlocks(){
            int minX,maxX,minY,maxY,minZ,maxZ;

            minX = min.getBlockX();
            minY = min.getBlockY();
            minZ = min.getBlockZ();
            maxX = max.getBlockX();
            maxY = max.getBlockY();
            maxZ = max.getBlockZ();

            List<Block> blocks = Lists.newArrayList();

            for(int x = maxX - minX; x <= maxX + minX; x++){
                for(int y = maxY - minY; y <= maxY + minY; y++){
                    for(int z = maxX - minZ; z <= maxZ + minZ; z++){
                        Block block = max.getWorld().getBlockAt(x,y,z);
                        if(block.getType() != Material.AIR){
                            blocks.add(block);
                        }
                    }
                }
            }

            return blocks;
        }

        /**
         * Returns true if the block is in the region, false otherwise.
         *
         * @param block The block you want to check if it's in the region.
         * @return A boolean value.
         */
        public boolean blockInLocation(Block block){
            return containsLocation(block.getLocation());
        }
    }

    /**
     * It's a wrapper for the Bukkit Logger class
     */
    public static class Log {
        private static final Logger LOGGER = Logger.getLogger("Minecraft");
        private static final String PREFIX = "[Lobby]";

        /**
         * `log` is a function that takes an object and a level and logs the object with the level
         *
         * @param o The object to log.
         * @param l The level of the log message.
         */
        private static void log(Object o, Level l) {
            LOGGER.log(l, PREFIX + " " + o);
        }

        /**
         * > The function `info` takes an object as an argument and calls the function `log` with the object and the level
         * `INFO`
         *
         * @param o The object to log.
         */
        public static void info(Object o) {
            log(o, Level.INFO);
        }

        /**
         * If the current log level is greater than or equal to the level of the message, log the message.
         *
         * @param o The object to log.
         */
        public static void warn(Object o) {
            log(o, Level.WARNING);
        }

        /**
         * If the current log level is greater than or equal to SEVERE, then log the object.
         *
         * @param o The object to log.
         */
        public static void severe(Object o) {
            log(o, Level.SEVERE);
        }
    }

    // It's a functional interface that takes two arguments and returns nothing.
    @FunctionalInterface
    public interface DuoConsumer<T, U> {
        /**
         * Accept takes two arguments, a T and a U, and returns void.
         *
         * @param t The first argument to the function
         * @param u The first argument to the operation
         */
        void accept(T t, U u);
        /**
         * Returns a composed DuoConsumer that performs, in sequence, this operation followed by the after operation.
         *
         * @param after the consumer to apply after this consumer is applied
         * @return A new DuoConsumer that will first call the original DuoConsumer and then the DuoConsumer passed in as a
         * parameter.
         */
        default DuoConsumer<T, U> andThen(DuoConsumer<? super T, ? super U> after) {
            Objects.requireNonNull(after);
            return (T t, U u) -> { accept(t, u); after.accept(t, u); };
        }
    }

    // It's a functional interface that takes three arguments and returns nothing.
    @FunctionalInterface
    public interface TriConsumer<T, U, V> {
        /**
         * Accept takes three arguments, a T, a U, and a V, and returns void.
         *
         * @param t The first argument to the function
         * @param u The first argument to the function.
         * @param v The value to be passed to the consumer
         */
        void accept(T t, U u, V v);
        /**
         * Returns a composed TriConsumer that performs, in sequence, this operation followed by the after operation.
         *
         * @param after the TriConsumer to apply after this
         * @return A TriConsumer that accepts three arguments and performs the action of the current TriConsumer and then
         * the action of the after TriConsumer.
         */
        default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
            Objects.requireNonNull(after);
            return (T t, U u, V v) -> { accept(t, u, v); after.accept(t, u, v); };
        }
    }

}
