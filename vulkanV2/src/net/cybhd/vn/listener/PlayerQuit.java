package net.cybhd.vn.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;
import net.cybhd.vn.main.Util;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		Game.sendConsoleMSG(p.getPlayerListName() + ChatColor.GOLD + " logged out at " + ChatColor.RED
				+ p.getLocation().getWorld().getName() + " X:" + p.getLocation().getX() + " Y:" + p.getLocation().getY()
				+ " Z:" + p.getLocation().getZ(), ChatColor.GOLD);

		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Long timeJoined = stats.getLong(".timeJoined");

		stats.set(".timePlayed", stats.getLong(".timePlayed") + (System.currentTimeMillis() - timeJoined));
		stats.set(".timeJoined", 0);

		stats.set(".blockBreak", stats.getInt(".blockBreak") + p.getMetadata("BlockBreak").get(0).asInt());
		p.setMetadata("BlockBreak", new FixedMetadataValue(Main.getMain(), 0));
		stats.set(".blockPlace", stats.getInt(".blockPlace") + p.getMetadata("BlockPlace").get(0).asInt());
		p.setMetadata("BlockPlace", new FixedMetadataValue(Main.getMain(), 0));

		stats.set("LastLocation.World", p.getLocation().getWorld().getName());
		stats.set("LastLocation.X", p.getLocation().getX());
		stats.set("LastLocation.Y", p.getLocation().getY());
		stats.set("LastLocation.Z", p.getLocation().getZ());
		stats.set("LastLocation.Yaw", p.getLocation().getYaw());
		stats.set("LastLocation.Pitch", p.getLocation().getPitch());

		try {
			stats.save(fstats);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (p.hasPermission(Game.getAdminPermission())) {
			e.setQuitMessage("§c< " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getModPermission())) {
			e.setQuitMessage("§c< " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getSupPermission())) {
			e.setQuitMessage("§c< " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getPremPermission())) {
			e.setQuitMessage("§c< " + p.getPlayerListName());
		} else {
			e.setQuitMessage("§c< " + p.getPlayerListName());
		}
		
		Bukkit.getScheduler().runTaskLater(Main.getMain(), new Runnable() {
			
			@Override
			public void run() {
				for (Player ps : Bukkit.getOnlinePlayers()) {
					ps.setScoreboard(Util.getBaseScoreboard(ps));
					Util.updateTab(ps);
				}
				
			}
		}, 1);
		
		if (Main.hue != null) {
			if (Main.hue.containsKey(p.getUniqueId())) {
				Main.hue.remove(p.getUniqueId());
				p.getInventory().setArmorContents(null);
			}
		}
	}
}
