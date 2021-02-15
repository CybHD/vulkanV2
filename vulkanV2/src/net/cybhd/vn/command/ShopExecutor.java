package net.cybhd.vn.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.listener.Shop;
import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;

public class ShopExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			Game.openShopInv(p);
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("sell")) {
				if (p.getInventory().getItemInMainHand() != null
						|| p.getInventory().getItemInMainHand().getType() != Material.AIR) {
					if (Shop.isValidItem(p.getInventory().getItemInMainHand())) {
						int amount = p.getInventory().getItemInMainHand().getAmount();
							Eco.sell(p.getInventory().getItemInMainHand().getType(), p, amount);
							p.getInventory().remove(p.getInventory().getItemInMainHand());
						p.sendMessage("§6Item erfolgreich im Shop verkauft");
					} else {
						p.sendMessage("§6Dieses Item wird nicht im Shop gehandelt");
					}
				} else {
					p.sendMessage("§cDu hast kein gültiges Item in der Hand");
				}
			}
		} else if (args.length == 3) {

		} else {
			p.sendMessage("§c/shop");
		}
		return false;
	}

}
