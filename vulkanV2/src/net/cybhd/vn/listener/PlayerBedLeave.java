package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerBedLeave implements Listener {

	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e) {
		if (PlayerBedEnter.inBed.size() >= 1) {
			PlayerBedEnter.inBed.remove(e.getPlayer().getName());
		}
		if (PlayerBedEnter.inBed.size() >= 1) {
			Bukkit.broadcastMessage("§6Es schlafen jetzt §c" + PlayerBedEnter.inBed.size() + " §6von §c"
					+ Bukkit.getOnlinePlayers().size() / 2 + " §6Spielern");
	}
	}
}
