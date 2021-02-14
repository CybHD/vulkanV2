package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class TpaExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			if (!p.getName().equalsIgnoreCase(args[0])) {
				if (Game.isValidPlayerName(args[0])) {
					Player t = Bukkit.getPlayer(args[0]);
						t.setMetadata("TPA", new FixedMetadataValue(Main.getMain(), p.getName()));
						p.sendMessage("§6Anfrage gesendet");
						t.sendMessage("§6TP Anfrage erhalten §7(§c" + p.getName() + "§7)");
						t.sendMessage("§6Annehmen: §c/tpaccept §7| §6Ablehnen: §c/tpdeny");
					
				} else {
					p.sendMessage("§6Der Spieler §c" + args[0] + " §6ist nicht online");
				}
			} else {
				p.sendMessage("§cDu kannst dir keine TP Anfragen senden");
			}
		}
		return false;
	}

}
