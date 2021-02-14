package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class TpDenyExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasMetadata("TPA")) {
			String s = p.getMetadata("TPA").get(0).asString();
			if (Game.isValidPlayerName(s)) {
				Player t = Bukkit.getPlayer(s);
				p.removeMetadata("TPA", Main.getMain());
				p.sendMessage("§6TP Anfrage abgelehnt");
				t.sendMessage("§c" + p.getName() + " §6hat deine TP Anfrage abgelehnt");
			}
		} else {
			p.sendMessage("§cDu hast keine TP Anfragen");
		}
		return false;
	}

}
