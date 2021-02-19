package net.cybhd.vn.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;

import net.cybhd.vn.main.Main;

public class EntityPortal implements Listener {
	
	@EventHandler
	public void onEntityEnterPortal (EntityPortalEnterEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getWorld().getName().equals("spawn")) {
				p.teleport(Main.getWorldLoc());
			}
		}
	}

}
