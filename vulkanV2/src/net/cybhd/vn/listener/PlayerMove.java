package net.cybhd.vn.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.cybhd.vn.main.Main;

public class PlayerMove implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getLocation().getWorld().getName().equals("spawn")) {
			Location loc = p.getLocation();
			if (loc.getY() <= 40) {
				p.teleport(Main.getSpawnLoc());
			}
		}
	}
}
