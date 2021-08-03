package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;

public class WarpExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getWorld().getName().equals("world") || p.getWorld().getName().equals("spawn")) {
				p.openInventory(Game.getWarpInv(p));
			} else {
				p.sendMessage("�cDu kannst /warp hier nicht benutzen");
			}
		}
		return false;
	}
}
