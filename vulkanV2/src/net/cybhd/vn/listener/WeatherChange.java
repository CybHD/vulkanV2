package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChange implements Listener {

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		if (e.getWorld() == Bukkit.getWorld("spawn") || e.getWorld().getName().equals("ice_spawn")) {
			e.setCancelled(true);
		}
	}
}
