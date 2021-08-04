package net.cybhd.vn.listener;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.cybhd.vn.main.Game;

public class EntityDamageByEntity implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			Location loc = p.getLocation();
			if (p.getWorld().getName().equals("world")) {
				File fchunks = new File("plugins/vulkan/CHUNKS.yml");
				YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
				if (chunks.isSet(loc.getChunk().toString())) {
					if (!chunks.getString(loc.getChunk() + ".owner").equals(d.getName())) {
						if (!chunks.getStringList(loc.getChunk() + ".member").contains(d.getName())) {
							e.setCancelled(true);
							d.sendMessage("§cDu hast keine Rechte in diesem Chunk");
						}
					}
				}
			}
		}
		if (e.getEntity() instanceof Villager && e.getDamager() instanceof Player) {
			Player d = (Player) e.getDamager();
			if (!d.hasPermission(Game.getAdminPermission())) {
				e.setCancelled(true);
			}
		}
	}
}
