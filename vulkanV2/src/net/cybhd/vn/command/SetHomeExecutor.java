package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetHomeExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (p.getLocation().getWorld().getName().equals("world")) {
			stats.set("Home.World", p.getLocation().getWorld().getName());
			stats.set("Home.X", p.getLocation().getX());
			stats.set("Home.Y", p.getLocation().getY());
			stats.set("Home.Z", p.getLocation().getZ());
			stats.set("Home.Yaw", p.getLocation().getYaw());
			stats.set("Home.Pitch", p.getLocation().getPitch());
			try {
				stats.save(fstats);
				p.sendMessage("§6Home gesetzt");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			p.sendMessage("§cDein Home darf sich nur in der normalen Welt befinden");
		}
		return false;
	}

}
