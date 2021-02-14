package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;

public class PayExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(args.length == 2) {
			if(Game.isValidPlayerName(args[0])) {
				if (Eco.get(p) >= Game.convertToDouble(args[1])) {
					Player t = Bukkit.getPlayer(args[0]);
						Eco.remove(p, Game.convertToDouble(args[1]));
						p.sendMessage("§6Du hast §c" + args[1] + " §6$ an "+ t.getPlayerListName() + " §6gezahlt");
						Eco.add(t, Game.convertToDouble(args[1]));
						t.sendMessage(p.getPlayerListName() + " §6hat dir §c" + args[1] + " §6$ gezahlt");
						Eco.log(p.getName(), t.getName(), Game.convertToDouble(args[1]));
				} else {
					p.sendMessage("§6Du hast nicht genug Geld");
				}
			} else {
				p.sendMessage("§6Der Spieler §c" + args[0] + "§6existiert nicht");
			}
		}
		return false;
	}

}
