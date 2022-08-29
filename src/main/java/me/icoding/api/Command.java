package me.icoding.api;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.StringJoiner;

public abstract class Command extends Utility implements CommandExecutor {

    private final String name;
    private final ExecutableBy executableBy;
    private final Arguments arguments;

    // It's a constructor. It's used to create a new Command object.
    public Command(String name) {
        this.name = name;

        this.executableBy = getClass().getDeclaredAnnotation(ExecutableBy.class);
        this.arguments = getClass().getDeclaredAnnotation(Arguments.class);
    }

    /**
     * "This function is called when the command is executed."
     *
     * The `CommandContext` object is a class that contains all the information about the command. It contains the command
     * name, the arguments, the sender, and more
     *
     * @param context The context of the command.
     */
    public abstract void execute(CommandContext context);

    /**
     * If the command is executable by the sender, execute the command
     *
     * @param sender The CommandSender who executed the command.
     * @param command The command that was executed.
     * @param label The command label.
     * @param args The arguments passed to the command.
     * @return A boolean.
     */
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (arguments != null) {
            if (args.length < this.arguments.min()) {
                sender.sendMessage(ColorAPI.color(String.format("&cNot enough arguments. Usage: '%s'", arguments.usage())));
                return false;
            } else if (args.length > this.arguments.max()) {
                sender.sendMessage(ColorAPI.color(String.format("&cToo many arguments. Usage: '%s'", arguments.usage())));
                return false;
            }
        }

        if ((executableBy.executor().equals(Executor.PLAYER) && sender instanceof Player) ||
                (executableBy.executor().equals(Executor.CONSOLE) && !(sender instanceof Player)) ||
                executableBy.executor().equals(Executor.BOTH)) {
            execute(new CommandContext(sender, args));
            return true;
        } else if (executableBy.executor().equals(Executor.PLAYER)) {
            sender.sendMessage(ColorAPI.color("&cThis command can only be executed by a player."));
        } else {
            sender.sendMessage(ColorAPI.color("&cThis command can only be executed by the console."));
        }

        return false;
    }

    /**
     * If the sender is a player, return true, otherwise return false.
     *
     * @param sender The CommandSender that sent the command.
     * @return A boolean value.
     */
    public boolean isPlayer(CommandSender sender) { return sender instanceof Player; }

    /**
     * It's a class that allows you to easily get arguments from a command
     */
    public class CommandContext {
        private final CommandSender sender;
        private final String[] args;

        // It's a constructor. It's used to create a new CommandContext object.
        public CommandContext(CommandSender sender, String[] args) {
            this.sender = sender;
            this.args = args;
        }

        /**
         * If the index is out of bounds, return null.
         *
         * @param index The index of the argument to get.
         * @return The string at the index of the array.
         */
        public String getString(int index) {
            try { return args[index]; }
            catch(ArrayIndexOutOfBoundsException e) { return null; }
        }

        /**
         * If the index is out of bounds, return 0.
         *
         * @param index The index of the argument to get.
         * @return The integer at the index of the array.
         */
        public Integer getInteger(int index) {
            try { return Integer.getInteger(args[index]); }
            catch(ArrayIndexOutOfBoundsException e) { return 0; }
        }

        /**
         * If the index is out of bounds, return 0.
         *
         * @param index The index of the argument to get.
         * @return The double value of the integer at the index of the array.
         */
        public double getDouble(int index) {
            try { return Integer.getInteger(args[index]).doubleValue(); }
            catch(ArrayIndexOutOfBoundsException e) { return 0; }
        }

        /**
         * It takes in a start and end index and returns a string of the elements in the array from start to end.
         *
         * @param start The index of the first argument to be included in the string.
         * @param end The end of the string to be returned.
         * @return A string of the arguments from the start index to the end index.
         */
        public String getStringJoiner(int start, int end) {
            StringJoiner builder = new StringJoiner(" ");

            for(int i = (start - 1); i < end; i++){
                builder.add(args[i]);
            }
            return builder.toString();
        }

        public String joinArray(String[] array) {
            StringJoiner joiner = new StringJoiner(" ");
            for (String s : array)
                joiner.add(s);
            return joiner.toString();
        }

        /**
         * It takes a string, colors it, and sends it to the player
         *
         * @param message The message you want to send.
         */
        public void sendMessage(String message) { sender.sendMessage(ColorAPI.color(message)); }
        /**
         * Send a message to the sender, with the message formatted using the ColorAPI.
         *
         * @param message The message to send.
         */
        public void sendMessage(String message, Object... objects) { sender.sendMessage(ColorAPI.format(message, objects)); }

        /**
         * It takes a player and a message, and sends the message to the player
         *
         * @param player The player you want to send the message to.
         * @param message The message you want to send to the player.
         */
        public void sendMessage(Player player, String message) { player.sendMessage(ColorAPI.color(message)); }

        /**
         * It sends a message to a player, with the message being formatted using the ColorAPI
         *
         * @param player The player to send the message to.
         * @param message The message to send to the player.
         */
        public void sendMessage(Player player, String message, Object... objects) { player.sendMessage(ColorAPI.format(message, objects)); }

        /**
         * `getSender()` returns the `CommandSender` who sent the command
         *
         * @return The CommandSender object.
         */
        public CommandSender getSender() { return sender; }

        /**
         * Returns the arguments passed to the program.
         *
         * @return The arguments of the command.
         */
        public String[] getArguments() { return args; }
    }

    // It's an annotation. It's used to specify the executor of the command.
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.METHOD})
    public @interface ExecutableBy {
        Executor executor() default Executor.BOTH;
    }

    // It's an annotation. It's used to specify the arguments of the command.
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.METHOD})
    public @interface Arguments {
        String usage() default "";
        int min() default 0;
        int max() default 0;
    }

    // It's an enum. It's used to specify the executor of the command.
    public enum Executor {
        PLAYER, CONSOLE, BOTH;
    }

}
