package net.cybhd.vn.main;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Chunk implements Listener {

	public static YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
	public static double price_buy = config.getDouble("Chunk.Price.Buy");
	public static double price_sell = config.getDouble("Chunk.Price.Sell");

	@EventHandler(priority = EventPriority.HIGH)
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Location loc = e.getBlock().getLocation();
		if (!isPermitted(p, loc)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Location loc = e.getBlock().getLocation();
		if (!isPermitted(p, loc)) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent e) {
		if ((!(e.getEntity() instanceof Monster)) && e.getDamager() instanceof Player) {
			Location loc = e.getEntity().getLocation();
			Player p = (Player) e.getDamager();
			if (!isPermitted(p, loc)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Location loc = e.getClickedBlock().getLocation();
			if (!isPermitted(p, loc)) {
				e.setCancelled(true);
			}
		} else if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
			Location loc = e.getClickedBlock().getLocation();
			if (!isPermitted(p, loc)) {
				e.setCancelled(true);
			}
		}
	}

	public static Boolean isPermitted(Player p, Location loc) {
		Boolean b = false;
		if (p.getWorld().getName().equals("world")) {
			File fchunks = new File("plugins/vulkan/CHUNKS.yml");
			YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
			if (chunks.isSet(loc.getChunk().toString())) {
				if (!p.hasPermission(Game.getAdminPermission())) {
					if (chunks.getString(loc.getChunk() + ".owner").equals(p.getName())) {
						b = true;
					}
					if (chunks.getStringList(loc.getChunk() + ".member").contains(p.getName())) {
						b = true;
					}
					/*
					if (chunks.getString(loc.getChunk() + ".owner").equals(Clan.getOwner(Clan.getClanName(p)))) {
						b = true;
					}
					*/
				} else {
					if (p.hasMetadata("vulkan.vanish")) {
						b = true;
					} else {
						p.sendMessage("§cDu kannst nur im Vanish gekaufte Grundstücke bearbeiten");
					}
				}
			} else { // Triggert wenn Plot unclaimed
				b = true;
			}
		} else {
			b = true;
		}
		return b;
	}
}
