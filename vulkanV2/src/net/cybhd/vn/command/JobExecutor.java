package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import net.cybhd.vn.main.Game;

public class JobExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("createnpc")) {
					if (p.hasPermission(Game.getAdminPermission())) {
						Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
						v.setCustomName("§6Arbeitsamt");
						v.setBreed(false);
						v.setInvulnerable(true);
						v.setCanPickupItems(false);
						v.setSilent(true);
						v.setAI(false);
						v.setCollidable(false);
						p.sendMessage("§6Arbeitsamt Villager erstellt");
					}
				}
			}
		}
		return false;
	}
}