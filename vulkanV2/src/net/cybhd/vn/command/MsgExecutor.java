package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;

public class MsgExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length >= 2) {
			if (Game.isValidPlayerName(args[0]) && !p.getName().equals(args[0])) {
				Player t = Bukkit.getPlayer(args[0]);
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					sb.append(args[i] + " ");
				}
				String str = sb.toString();
				t.sendMessage("§9§lMSG §r§c" + p.getPlayerListName() + " §e> §7" + str);
				p.sendMessage("§9§lMSG §r§c" + t.getPlayerListName() + " §e< §7" + str);
			} else {
				p.sendMessage("§6Der Spieler §c" + args[0] + " §6ist nicht online");
			}
		} else {
			p.sendMessage("§c/msg %SpielerName% %Nachricht%");
		}
		return false;
	}

}
