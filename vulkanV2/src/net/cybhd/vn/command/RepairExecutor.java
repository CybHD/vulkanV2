package net.cybhd.vn.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.cybhd.vn.main.Game;

public class RepairExecutor implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 9, "§6Reparieren");
		ItemStack item = new ItemStack(Material.ANVIL);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName("§6Reparieren");
		List<String> lore = new ArrayList<String>();
		lore.add("§7Kaufen: §c" + Game.getPriceRepair() + " §6$");
		m.setLore(lore);
		item.setItemMeta(m);
		inv.setItem(4, item);
		p.openInventory(inv);
		return false;
	}

}
