package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {

	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (e.getEntity().getWorld() == Bukkit.getWorld("spawn") || e.getEntity().getWorld().getName().equals("ice_spawn")) {
			if (e.getEntity() instanceof Villager) {
				return;
			}
			if(e.getEntity() instanceof Mob)
			e.setCancelled(true);
		}
	}
}
