package net.cybhd.vn.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class CoinVillager {

	public static final String VILLAGER_NAME = "§6§lTägliche Belohnung";

	public CoinVillager() {
		Location loc = new Location(Bukkit.getWorld("spawn"), 9.5, 115, -9.5, 45, 10);
		Villager v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		v.setCustomName(VILLAGER_NAME);
		v.setCustomNameVisible(true);
		v.setAI(false);
		v.setPersistent(true);
	}
}
