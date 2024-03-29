package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;

public class BoostExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission(Game.getPremPermission())) {
			p.openInventory(Game.getBoostInv());
		} else {
			Game.sendPremiumReqMSG(p);
		}
		return false;
	}

}
