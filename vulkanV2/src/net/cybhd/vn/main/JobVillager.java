package net.cybhd.vn.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class JobVillager {

	public static final String VILLAGER_NAME = "§6§lArbeitsamt";

	public JobVillager() {
		Location loc = new Location(Bukkit.getWorld("spawn"), 11.5, 117, 9.5, -120, 0);
		Villager v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		v.setCustomName(VILLAGER_NAME);
		v.setCustomNameVisible(true);
		v.setAI(false);
		v.setPersistent(true);
	}
}
