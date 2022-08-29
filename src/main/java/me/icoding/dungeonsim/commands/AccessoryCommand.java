package me.icoding.dungeonsim.commands;

import me.icoding.api.Command;
import me.icoding.dungeonsim.gui.AccessoryBagGui;
import me.icoding.dungeonsim.gui.ThaumaturgistGui;
import org.bukkit.entity.Player;

@Command.ExecutableBy(executor = Command.Executor.PLAYER)
@Command.Arguments(min = 0, max = 0)
public class AccessoryCommand extends Command {
    public AccessoryCommand() {
        super("accessory");
    }

    @Override
    public void execute(CommandContext context) {
        Player player = (Player) context.getSender();

        new AccessoryBagGui(player).openInventory(player);
    }
}
