package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.cybhd.vn.main.Game;

public class EntityDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getWorld() == Bukkit.getWorld("spawn") || p.getWorld().getName().equals("ice_spawn")) {
				e.setCancelled(true);
			}
			Game.sendConsoleMSG(p.getPlayerListName() + " §6 took §c" + e.getDamage() + " §7( " + e.getDamage() / 2 + "§7)", ChatColor.RED);
		}
		
	}
}