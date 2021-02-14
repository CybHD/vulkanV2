package net.cybhd.vn.listener;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHit implements Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			Location loc = e.getEntity().getLocation();
			if (loc.getWorld().getName().equals("world")) {
				File fchunks = new File("plugins/vulkan/CHUNKS.yml");
				YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
				if (chunks.isSet(loc.getChunk().toString())) {
					if (!chunks.getString(loc.getChunk() + ".owner").equalsIgnoreCase(p.getName())) {
						if (!chunks.getStringList(loc.getChunk() + ".member").contains(p.getName())) {
							
						}
					}
				}
			}
		}
	}
}
