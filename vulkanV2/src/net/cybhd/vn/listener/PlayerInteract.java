package net.cybhd.vn.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.cybhd.vn.main.Game;

public class PlayerInteract implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getLocation().getWorld().getName().equals("spawn")) {
			if (!(p.hasPermission(Game.getAdminPermission()) && p.getGameMode() == GameMode.CREATIVE)) {
				if (e.getAction() != Action.RIGHT_CLICK_AIR) {
					e.setCancelled(true);
				}
			}
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
			if (!(p.getGameMode() == GameMode.CREATIVE)) {
				if (!(p.getWorld().getName().equals("world") || p.getWorld().getName().equals("world_nether")
						|| p.getWorld().getName().equals("world_the_end"))) {
					if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if (b.getType() == Material.OAK_TRAPDOOR) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
				if (p.hasMetadata("TARGET")) {
					String s = p.getMetadata("TARGET").get(0).asString();
					if (Game.isValidPlayerName(s)) {
						Player t = Bukkit.getPlayer(s);
						Location loc = t.getLocation();
						p.setCompassTarget(loc);
						p.sendMessage("�6Dein Kompass zeigt nun auf �c" + t.getName());
					}
				}
			}
		}
	}
}