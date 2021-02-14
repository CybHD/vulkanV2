package net.cybhd.vn.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

public class PlayerBedEnter implements Listener {

	public static ArrayList<String> inBed = new ArrayList<String>();

	@EventHandler
	public void onEnter(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		if (e.getBedEnterResult() == BedEnterResult.OK && p.getWorld().getName().equals("world")) {
			inBed.add(p.getName());
			Bukkit.broadcastMessage("§6Es schlafen jetzt §c" + inBed.size() + " §6von §c"
					+ Bukkit.getOnlinePlayers().size() / 2 + " §6Spielern");
			if (inBed.size() == (Bukkit.getOnlinePlayers().size() / 2)) {
				p.setBedSpawnLocation(p.getLocation(), true);
				p.getWorld().setTime(p.getWorld().getFullTime() + 12000);
				p.getWorld().setClearWeatherDuration(20 * 1 * 60 * 15);
				inBed.clear();
			}
		}
	}
}
