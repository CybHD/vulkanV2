package net.cybhd.vn.listener;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class PlayerPreLogin implements Listener {

	@EventHandler
	public void onPreLogin(AsyncPlayerPreLoginEvent e) {
		String name = e.getName();
		File fstats = new File("plugins/vulkan/PLAYERS/" + name + ".yml");
		if (fstats.exists()) {
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			if (stats.isSet("TempBan.TimeBanned") && stats.isSet("TempBan.Duration") && stats.isSet("TempBan.Reason")) {
				Long currentTime = System.currentTimeMillis();
				Long timeBanned = stats.getLong("TempBan.TimeBanned");
				Long duration = stats.getLong("TempBan.Duration");
				String reason = stats.getString("TempBan.Reason");
				if (timeBanned + duration > currentTime) {
					e.disallow(Result.KICK_BANNED, "\n§4Du wurdest temporär vom Server gebannt \n\n\n§6Grund: §c"
							+ reason + "\n\n§6Dauer: " + formatTime(timeBanned + duration - currentTime));
				}
			}
		}
	}

	private String formatTime(long time) {
		time /= 1000L;
		int days = (int) (time / 86400L);
		time -= (long) (86400 * days);
		int hours = (int) (time / 3600L);
		time -= (long) (3600 * hours);
		int minutes = (int) (time / 60L);
		time -= (long) (60 * minutes);
		int seconds = (int) time;
		StringBuilder sb = new StringBuilder();

		if (days != 0) {
			sb.append("§c" + days).append(" §6Tag").append(days == 1 ? " " : "e ");
		}
		if (hours != 0) {
			sb.append("§c" + hours).append(" §6Stunde").append(hours == 1 ? " " : "n ");
		}
		if (minutes != 0) {
			sb.append("§c" + minutes).append(" §6Minute").append(minutes == 1 ? " " : "n ");
		}
		if (seconds != 0) {
			sb.append("§c" + seconds).append(" §6Sekunde").append(seconds == 1 ? "" : "n");
		}
		return sb.toString().trim();

	}

}
