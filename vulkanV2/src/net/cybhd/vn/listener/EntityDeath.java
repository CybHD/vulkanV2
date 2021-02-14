package net.cybhd.vn.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				// add Entity Kill to stats
				File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				stats.set("Entity.Kills", stats.getInt("Entity.Kills") + 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
