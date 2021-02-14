package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class TpAcceptExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(p.hasMetadata("TPA")) {
			String s = p.getMetadata("TPA").get(0).asString();
			if(Game.isValidPlayerName(s)) {
				Location loc = p.getLocation();
				Player t = Bukkit.getPlayer(s);
				t.teleport(loc);
				p.removeMetadata("TPA", Main.getMain());
				p.sendMessage("§6Anfrage angenommen");
				t.sendMessage("§c" + p.getName() + " §6hat deine TP Anfrage angenommen");
			}
		} else {
			p.sendMessage("§cDu hast keine TP Anfragen");
		}
		return false;
	}
	
}
