package net.cybhd.vn.command;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Main;

public class WarpExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getLocation().getWorld() == Main.getSpawnLoc().getWorld()) {
				if (p.getHealth() >= 14) {
					File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
					YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
					if (stats.isSet("LastLocation.World")) {
						p.teleport(new Location(Bukkit.getWorld(stats.getString("LastLocation.World")),
								stats.getDouble("LastLocation.X"), stats.getDouble("LastLocation.Y"),
								stats.getDouble("LastLocation.Z"), (float) stats.getDouble("LastLocation.Yaw"),
								(float) stats.getDouble("LastLocation.Pitch")));
					} else {
						p.teleport(Main.getWorldLoc());
					}
				} else {
					p.sendMessage("§6Du musst mehr als 14 Lebenspunkte haben um §c/warp §6benutzen zu können");
				}
			} else if (p.getLocation().getWorld() == Bukkit.getWorld("world")) {
				p.teleport(Main.getSpawnLoc());
			} else {
				p.sendMessage("§cWarp funktioniert nur in der Spawn und normalen Welt");
			}
		}
		return false;
	}

}
