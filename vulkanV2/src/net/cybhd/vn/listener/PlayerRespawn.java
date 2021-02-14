package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.cybhd.vn.main.Main;

public class PlayerRespawn implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getScheduler().runTaskLater(Main.getMain(), new Runnable() {
			@Override
			public void run() {
				p.teleport(p.getBedSpawnLocation());
			}
		}, 1);
	}
}
