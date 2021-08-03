package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Main;

public class SpawnExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getWorld().getName().equals("spawn")) {
				p.teleport(Main.getSpawnLoc());
			} else {
				p.sendMessage("�cDu kannst diesen Befehl hier nicht benutzen");
			}
		}
		return false;
	}
	
	

}
