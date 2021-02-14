package net.cybhd.vn.command;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class StatsExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			Main.saveStats(p);
			
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			
			p.sendMessage("§7==== §c" + p.getName() + " §7====");
			p.sendMessage("§6Bb §7/ §6Bp: §c" + stats.getInt(".blockBreak") + " §7/ §c" + stats.getInt(".blockPlace"));
			p.sendMessage("§6K §7/ §6D: §c" + stats.getString("Kills") + " §7/ §c" + stats.getString("Deaths"));
			p.sendMessage("§6Spielzeit: " + formatTime(stats.getLong(".timePlayed")));
			
		} else if (args.length == 1){
			if (Game.isValidPlayerName(args[0])) {
				Player t = Bukkit.getPlayer(args[0]);
				Main.saveStats(t);
				
				File fstats = new File("plugins/vulkan/PLAYERS/" + t.getName() + ".yml");
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				
				p.sendMessage("§7==== §c" + t.getName() + " §7====");
				p.sendMessage("§6Bb §7/ §6Bp: §c" + stats.getInt(".blockBreak") + " §7/ §c" + stats.getInt(".blockPlace"));
				p.sendMessage("§6K §7/ §6D: §c" + stats.getString("Kills") + " §7/ §c" + stats.getString("Deaths"));
				p.sendMessage("§6Spielzeit: " + formatTime(stats.getLong(".timePlayed")));
				
				t.sendMessage("§c" + p.getName() + " §6hat deine Stats abgerufen");
			} else {
				p.sendMessage("§6Der Spieler §c" + args[0] + " §6ist nicht online");
			}
		} else {
			p.sendMessage("§6Nutze §c/stats");
		}
		return false;
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
		
		if(days != 0) {
			sb.append("§c" + days).append(" §6Tag").append(days == 1 ? " " : "e ");
		}
		if(hours != 0) {
			sb.append("§c" + hours).append(" §6Stunde").append(hours == 1 ? " " : "n ");
		}
		if(minutes != 0) {
			sb.append("§c" + minutes).append(" §6Minute").append(minutes == 1 ? " " : "n ");
		}
		if(seconds != 0) {
			sb.append("§c" + seconds).append(" §6Sekunde").append(seconds == 1 ? "" : "n");
		}
		return sb.toString().trim();
		
	}

}
