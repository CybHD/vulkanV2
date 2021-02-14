package net.cybhd.vn.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class BlockBreak implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getWorld().getName().equalsIgnoreCase("spawn") || p.getWorld().getName().equalsIgnoreCase("ice_spawn")) {
			if (!(p.isOp() && p.getGameMode() == GameMode.CREATIVE)) {
				e.setCancelled(true);
			}
		}
		if (p.getWorld().getName().equals("world") || p.getWorld().getName().equals("world_nether")
				|| p.getWorld().getName().equals("world_the_end")) {
			int blocks = p.getMetadata("BlockBreak").get(0).asInt();
			p.setMetadata("BlockBreak", new FixedMetadataValue(Main.getMain(), blocks + 1));
		}
		if (e.getBlock().getType() == Material.TALL_GRASS) {
			// TODO location check
			// TODO createCustomItem
			Location loc = e.getBlock().getLocation();
			if (loc == new Location(Bukkit.getWorld("world"), 1, 1, 1)) {
				e.setCancelled(true);
				ItemStack i = new ItemStack(Material.TALL_GRASS);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName("§2Weed");
				i.setItemMeta(m);
				loc.getWorld().dropItem(loc, i);
			}
		}
		if (e.getBlock().getType().equals(Material.SPAWNER)
				&& p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
			Location loc = e.getBlock().getLocation();
			CreatureSpawner cs = (CreatureSpawner) e.getBlock().getState();
			e.setExpToDrop(0);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(cs.getCreatureTypeName());
			ItemStack spawnerItem = Game.createNamedItemStack(e.getBlock().getType(), 1, "§6Spawner", lore);
			loc.getWorld().dropItemNaturally(loc, spawnerItem);
		}
	}
}
