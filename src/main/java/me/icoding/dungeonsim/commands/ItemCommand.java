package me.icoding.dungeonsim.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.icoding.api.Command;
import me.icoding.dungeonsim.item.ItemBuilder;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command.ExecutableBy(executor = Command.Executor.PLAYER)
public class ItemCommand extends Command {
    public ItemCommand() {
        super("item");
    }

    @Override
    public void execute(CommandContext context) {
        Player player = (Player) context.getSender();

        ItemData data = ItemManager.getItem(context.getString(0).toUpperCase());
        ItemStack stack = new ItemBuilder().buildItem(data);

        player.getInventory().addItem(stack);
    }
}
