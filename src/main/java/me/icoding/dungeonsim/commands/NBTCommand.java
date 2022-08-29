package me.icoding.dungeonsim.commands;

import me.icoding.api.Command;
import me.icoding.dungeonsim.utils.Utils;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

@Command.ExecutableBy(executor = Command.Executor.PLAYER)
public class NBTCommand extends Command {
    public NBTCommand() {
        super("nbt");
    }

    @Override
    public void execute(CommandContext context) {
        if (!isPlayer(context.getSender())) return;
        Player player = (Player) context.getSender();
        ItemStack stack = CraftItemStack.asNMSCopy(player.getInventory().getItemInHand());
        if (stack == null) {
            context.sendMessage("&cYou need to hold an item!");
            return;
        }
        NBTTagCompound compound = stack.getTag();
        if (compound == null) {
            context.sendMessage("&cCannot get nbt data from item without any nbt data!");
            return;
        }
        context.sendMessage("&aNBT >");
        for (String key : compound.c()) if (!key.equals("display"))
            context.sendMessage("&e" + key + "&a: &r" + compound.get(key).toString());
    }
}
