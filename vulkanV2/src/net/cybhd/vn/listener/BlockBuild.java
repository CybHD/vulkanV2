package net.cybhd.vn.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Main;

public class BlockBuild implements Listener {
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equalsIgnoreCase("spawn") || p.getWorld().getName().equalsIgnoreCase("ice_spawn")) {
			if (!(p.isOp() && p.getGameMode() == GameMode.CREATIVE)) {
				e.setCancelled(true);
			}
		}

		if (p.getWorld().getName().equals("world") || p.getWorld().getName().equals("world_nether")
				|| p.getWorld().getName().equals("world_the_end")) {
			int blocks = p.getMetadata("BlockPlace").get(0).asInt();
			p.setMetadata("BlockPlace", new FixedMetadataValue(Main.getMain(), blocks + 1));
		}
		
		 if (e.getBlockPlaced().getType().equals(Material.SPAWNER)) {

	            CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlockPlaced().getState();
	            try {
	                creatureSpawner.setCreatureTypeByName(e.getItemInHand().getItemMeta().getLore().get(0));
	            } catch (Exception ex) {} //Does nothing. Will error if vanilla spawner is placed since there is no lore.
	            creatureSpawner.update();
	        }
	}
}
