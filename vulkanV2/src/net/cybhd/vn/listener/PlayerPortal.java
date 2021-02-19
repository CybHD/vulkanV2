package net.cybhd.vn.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.cybhd.vn.main.Main;

public class PlayerPortal implements Listener {

	@EventHandler
	public void onEnter(PlayerPortalEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.getWorld().getName().equals("spawn") || p.getWorld().getName().equals("ice_spawn")) {
			if (e.getCause() == TeleportCause.END_GATEWAY) {
				p.teleport(Main.getWorldLoc());
				e.setCancelled(true);
			}
		}
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