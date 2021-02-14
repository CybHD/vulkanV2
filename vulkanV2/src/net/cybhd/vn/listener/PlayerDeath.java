package net.cybhd.vn.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		// add death to stats
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set("Deaths", stats.getInt("Deaths") + 1);
		try {
			stats.save(fstats);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Game.sendConsoleMSG(p.getPlayerListName() + ChatColor.GOLD + " died at " + ChatColor.RED
				+ p.getLocation().getWorld().getName() + " X:" + p.getLocation().getX() + " Y:" + p.getLocation().getY()
				+ " Z:" + p.getLocation().getZ(), ChatColor.GOLD);

		// killer instanceof Player
		if (e.getEntity().getKiller() instanceof Player) {
			Player k = e.getEntity().getKiller();
			File fkills = new File("plugins/vulkan/PLAYERS/" + k.getName() + ".yml");
			YamlConfiguration kills = YamlConfiguration.loadConfiguration(fkills);

			e.setDeathMessage("§c" + k.getName() + "§6 hat §c" + p.getName() + " §6getötet");

			// head drop
			if (k.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
				ItemStack head = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta mSkull = (SkullMeta) head.getItemMeta();
				mSkull.setOwningPlayer(p);
				head.setItemMeta(mSkull);
				k.getWorld().dropItemNaturally(k.getLocation(), head);
			}

			// add kill to stats
			kills.set("Kills", kills.getInt("Kills") + 1);
			try {
				kills.save(fkills);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// custom death message
		} else {
			e.setDeathMessage("§c" + p.getName() + " §6ist gestorben");
		}

		if (Main.hue != null) {
			if (Main.hue.containsKey(p.getUniqueId())) {
				Main.hue.remove(p.getUniqueId());
				e.getDrops().remove(p.getInventory().getHelmet());
				e.getDrops().remove(p.getInventory().getChestplate());
				e.getDrops().remove(p.getInventory().getLeggings());
				e.getDrops().remove(p.getInventory().getBoots());
				p.sendMessage("§6Deine Regenbogen Rüstung wurde abgelegt");
			}
		}
	}
}