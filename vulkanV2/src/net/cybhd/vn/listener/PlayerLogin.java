package net.cybhd.vn.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.cybhd.vn.main.Main;

public class PlayerLogin implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		p.setBedSpawnLocation(Main.getSpawnLoc(), true);
	}
}
