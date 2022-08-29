package me.icoding.dungeonsim.commands;

import me.icoding.api.Command;
import me.icoding.dungeonsim.gui.ItemsGui;
import org.bukkit.entity.Player;

@Command.ExecutableBy(executor = Command.Executor.PLAYER)
@Command.Arguments(min = 0, max = 0)
public class ItemsCommand extends Command {
    public ItemsCommand() {
        super("items");
    }

    @Override
    public void execute(CommandContext context) {
        Player player = (Player) context.getSender();

        new ItemsGui().openInventory(player);
    }
}
