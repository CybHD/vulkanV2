package net.cybhd.vn.listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.cybhd.vn.main.Clan;
import net.cybhd.vn.main.Game;

public class Chat implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage().replace("%", "Prozent");
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (stats.isSet("Mute.isMuted")) {
			if (stats.getBoolean("Mute.isMuted")) {
				e.setCancelled(true);
				p.sendMessage("§6Du bist gemutet. Kontaktiere den Support bei Fragen.");
				return;
			}
		}
		if (Clan.isMember(p)) {
			if (p.hasPermission(Game.getAdminPermission())) {
				e.setFormat("§7[§e" + Clan.getClanName(p) + "§7] " + "§4" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getModPermission())) {
				e.setFormat("§7[§e" + Clan.getClanName(p) + "§7] " + "§c" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getSupPermission())) {
				e.setFormat("§7[§e" + Clan.getClanName(p) + "§7] " + "§9" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getPremPermission())) {
				e.setFormat("§7[§e" + Clan.getClanName(p) + "§7] " + "§6" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else {
				e.setFormat("§7[§e" + Clan.getClanName(p) + "§7] " + "§2" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			}
		} else {
			if (p.hasPermission(Game.getAdminPermission())) {
				e.setFormat("§4" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getModPermission())) {
				e.setFormat("§c" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getSupPermission())) {
				e.setFormat("§9" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else if (p.hasPermission(Game.getPremPermission())) {
				e.setFormat("§6" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			} else {
				e.setFormat("§2" + Game.getUsernameFormatted(p) + " §e> §7" + msg);
			}
		}

		if (msg.contains("@")) {
			String[] array = msg.split("\\s+");
			for (String string : array) {
				if (string.contains("@")) {
					if (Game.isValidPlayerName(string.replace("@", ""))) {
						Player t = Bukkit.getPlayer(string.replace("@", ""));
						t.playSound(t.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				}
			}
		}
	}
}