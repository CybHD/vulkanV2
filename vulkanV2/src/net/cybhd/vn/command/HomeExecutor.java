package net.cybhd.vn.command;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class HomeExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (p.getLocation().getWorld().getName().equals("world") || p.getLocation().getWorld().getName().equals("spawn")) {
			if (stats.isSet("Home.World")) {
				if (Bukkit.getWorld(stats.getString("Home.World")).getName().equals("world")) {
					Location loc = new Location(Bukkit.getWorld(stats.getString("Home.World")),
							stats.getDouble("Home.X"), stats.getDouble("Home.Y"), stats.getDouble("Home.Z"),
							Float.valueOf(stats.getString("Home.Yaw")), Float.valueOf(stats.getString("Home.Pitch")));
					if (loc.getWorld().getName().equals(Bukkit.getWorld("world").getName())) {
						p.teleport(loc);
					} else {
						p.sendMessage("§cDein Home darf sich nur in der normalen Welt befinden");
					}
				} else {
					p.sendMessage("§cDein Home darf sich nur in der normalen Welt befinden");
				}
			} else {
				p.sendMessage("§cDu hast noch kein Home");
			}
		} else {
			p.sendMessage("§6Du kannst §c/home §6nur in der normalen Welt oder am Warp benutzen");
		}
		return false;
	}

}
