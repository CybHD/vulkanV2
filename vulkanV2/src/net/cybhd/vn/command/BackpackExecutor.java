package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;

public class BackpackExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission(Game.getSuPremPermission())) {
			p.openInventory(Game.getBackPackInv(p));
		} else if (p.hasPermission(Game.getPremPermission())) {
			p.openInventory(Game.getBackPackInv(p));
		} else {
			p.sendMessage("§cKaufe dir den §6§lPREMIUM §r§coder §b§lSUPREMIUM §r§cRang um diesen Befehl benutzen zu können");
		}
		return false;
	}

}
