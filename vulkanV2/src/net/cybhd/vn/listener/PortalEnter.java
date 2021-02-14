package net.cybhd.vn.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;

import net.cybhd.vn.main.Main;

public class PortalEnter implements Listener {

	@EventHandler
	public void onEnter(EntityPortalEnterEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getWorld().getName().equals("spawn") || p.getWorld().getName().equals("ice_spawn")) {
				p.teleport(Main.getWorldLoc());
			}
		}
	}

	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e) {
		if (e.getBlock().getWorld().getName().equals("spawn") || e.getBlock().getWorld().getName().equals("ice_spawn")) {
			if (e.getChangedType() == Material.NETHER_PORTAL || e.getBlock().getType() == Material.NETHER_PORTAL) {
				e.setCancelled(true);
			}
		}
	}
}
