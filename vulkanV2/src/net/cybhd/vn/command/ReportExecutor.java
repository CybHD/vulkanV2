package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class ReportExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			if (Game.isValidPlayerName(args[0])) {
				if (!p.getName().equalsIgnoreCase(args[0])) {
					Game.openRepInv(p);
					p.setMetadata("REPORTS", new FixedMetadataValue(Main.getMain(), args[0]));
				} else {
					p.sendMessage("§cDu kannst dich nicht selbst reporten");
				}
			} else {
				p.sendMessage("§6Der Spieler §c" + args[0] + " §6ist nicht online");
			}
		} else {
			p.sendMessage("§c/report %SpielerName%");
		}
		return false;
	}

}
