package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class TargetExecutor implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			if (!p.getName().equalsIgnoreCase(args[0])) {
				if (Game.isValidPlayerName(args[0])) {
					Player t = Bukkit.getPlayer(args[0]);
					if (p.getItemInHand().getType() == Material.COMPASS) {
						p.setCompassTarget(t.getLocation());
						p.setMetadata("TARGET", new FixedMetadataValue(Main.getMain(), t.getName()));
						t.sendMessage("§c" + p.getName() + " §6hat seinen Kompass auf dich gerichtet");
					}
				} else {
					p.sendMessage("§6Der Spieler §c" + args[0] + " §6ist nicht online");
				}
			} else {
				p.sendMessage("§cDu kannst dich nicht selbst targeten");
			}
		}
		return false;
	}

}
