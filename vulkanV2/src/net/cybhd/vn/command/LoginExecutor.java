package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Main;

public class LoginExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 1) {
				if (!p.hasMetadata("logged")) {
					
					p.setMetadata("logged", new FixedMetadataValue(Main.getMain(), ""));
					p.sendMessage("§2Erfolgreich eingeloggt");
					
				} else {
					p.sendMessage("§cDu bist bereits eingeloggt");
				}
			} else {
				p.sendMessage("§cBitte Passwort angeben");
			}
		}
		return false;
	}

}
