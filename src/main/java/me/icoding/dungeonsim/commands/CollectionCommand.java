package me.icoding.dungeonsim.commands;

import me.icoding.api.Command;
import me.icoding.dungeonsim.gui.mainmenu.CollectionGui;
import org.bukkit.entity.Player;

@Command.ExecutableBy(executor = Command.Executor.PLAYER)
public class CollectionCommand extends Command {
    public CollectionCommand() {
        super("collection");
    }

    @Override
    public void execute(CommandContext context) {
        Player player = (Player) context.getSender();

        new CollectionGui(player).openInventory(player);
    }
}
