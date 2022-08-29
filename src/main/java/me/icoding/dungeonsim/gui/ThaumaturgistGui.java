package me.icoding.dungeonsim.gui;

import me.icoding.api.Gui;
import me.icoding.dungeonsim.item.ItemData;
import me.icoding.dungeonsim.item.Rarity;
import me.icoding.dungeonsim.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ThaumaturgistGui extends Gui implements Listener {
    public ThaumaturgistGui(Player player) {
        super("Accessory Bag Thaumaturgy", Size.SIZE_54);

        outline(ItemPreset.BLACK_STAINED_GLASS_PANE);

        int common = 0, uncommon = 0, rare = 0, epic = 0, legendary = 0, mythic = 0;

        ArrayList<ItemData> accessories = Utils.getAccessory(player);
        for (ItemData data : accessories) {
            if (data.tier() == Rarity.COMMON) common++;
            else if (data.tier() == Rarity.UNCOMMON) uncommon++;
            else if (data.tier() == Rarity.RARE) rare++;
            else if (data.tier() == Rarity.EPIC) epic++;
            else if (data.tier() == Rarity.LEGENDARY) legendary++;
            else if (data.tier() == Rarity.MYTHIC) mythic++;
        }

        ItemStack breakdown = new ItemStack(Material.PAPER);
        ItemMeta breakdownm = breakdown.getItemMeta();

        breakdownm.setDisplayName(ColorAPI.color("&aAccessories Breakdown"));
        breakdownm.setLore(ColorAPI.color(
                "&8Form your bag",
                "",
                "&622 MP &7x &d" + mythic + " Accs. &7= &6" + (22 * mythic),
                "&616 MP &7x &6" + legendary + " Accs. &7= &6" + (16 * legendary),
                "&612 MP &7x &5" + epic + " Accs. &7= &6" + (12 * epic),
                "&68 MP &7x &9" + rare + " Accs. &7= &6" + (8 * rare),
                "&65 MP &7x &a" + uncommon + " Accs. &7= &6" + (5 * uncommon),
                "&63 MP &7x &f" + common + " Accs. &7= &6" + (3 * common),
                "",
                "&7Total: &6" + Utils.getTotalMagicPower(common, uncommon, rare, epic, legendary, mythic) + " Magical Power"
        ));

        breakdown.setItemMeta(breakdownm);

        setItem(48, breakdown);

        setItem(49, closeButton());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {

    }
}
