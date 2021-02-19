package net.cybhd.vn.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortal implements Listener {

	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		
	}

	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e) {
		if (e.getBlock().getWorld().getName().equals("spawn")
				|| e.getBlock().getWorld().getName().equals("ice_spawn")) {
			if (e.getChangedType() == Material.NETHER_PORTAL || e.getBlock().getType() == Material.NETHER_PORTAL) {
				e.setCancelled(true);
			}
		}
	}
}