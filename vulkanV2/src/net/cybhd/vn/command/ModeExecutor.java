package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;

public class ModeExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission(Game.getSupPermission())) {
				File fstats = Game.getPlayerFile(p);
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				if (!stats.getBoolean("Mode.isMode")) {
					stats.set("Mode.isMode", true);
					p.sendMessage(Game.getPrefix() + "§eSystem Mode §l§2aktiviert");
					Location loc = p.getLocation();
					// Save Location (x,y,z, yaw, pitch)
					stats.set("Mode.World", loc.getWorld().getName());
					stats.set("Mode.X", loc.getX());
					stats.set("Mode.Y", loc.getY());
					stats.set("Mode.Z", loc.getZ());
					stats.set("Mode.Yaw", loc.getYaw());
					stats.set("Mode.Pitch", loc.getPitch());
					try {
						stats.save(fstats);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					stats.set("Mode.isMode", false);
					try {
						stats.save(fstats);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage(Game.getPrefix() + " §eSystem Mode §l§4deaktiviert");
					Location loc = new Location(Bukkit.getWorld(stats.getString("Mode.World")),
							stats.getDouble("Mode.X"), stats.getDouble("Mode.Y"), stats.getDouble("Mode.Z"),
							(float) stats.getDouble("Mode.Yaw"), (float) stats.getDouble("Mode.Pitch"));
					p.teleport(loc);
				}
			} else {
				p.sendMessage("§cDu hast keine Rechte für diesen Befehl");
			}
		}
		return false;
	}

}
