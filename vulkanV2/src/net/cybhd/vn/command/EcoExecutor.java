package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;

public class EcoExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission(Game.getModPermission()) || sender.hasPermission(Game.getAdminPermission())) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("get")) {
					Player p = (Player) sender;
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						Double current = Eco.get(t);
						p.sendMessage(t.getPlayerListName() + " §6hat gerade §c" + current + " §6$");
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("set")) {
					Player p = (Player) sender;
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						Eco.set(t, Game.convertToDouble(args[2]));
						p.sendMessage("§6Du hast das Geld von " + t.getPlayerListName() + " §6auf §c" + args[2]
								+ " §6$ gesetzt");
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				} else if (args[0].equalsIgnoreCase("add")) {
					Player p = (Player) sender;
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						Eco.add(t, Game.convertToDouble(args[2]));
						p.sendMessage("§6Du hast " + t.getPlayerListName() + " §c" + args[2] + " §6$ hinzugefügt");
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					Player p = (Player) sender;
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						Eco.remove(t, Game.convertToDouble(args[2]));
						p.sendMessage("§6Du hast " + t.getPlayerListName() + " §c" + args[2] + " §6$ entfernt");
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				}
			} else if (args.length == 0) {
				Player p = (Player) sender;
				p.sendMessage("§6Du hast §c" + Eco.get(p) + " §6$");
			}
		} else {
			if (args.length == 0) {
				Player p = (Player) sender;
				p.sendMessage("§6Du hast §c" + Eco.get(p) + " §6$");
			} else {
				sender.sendMessage("§6/eco");
			}
		}
		return false;
	}
}
