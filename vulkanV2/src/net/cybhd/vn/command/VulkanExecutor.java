package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;
import net.cybhd.vn.main.Util;

public class VulkanExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
			if (args.length == 2 && args[0].equalsIgnoreCase("setBeta")) {
				if (config.isSet("Beta.Player")) {
					ArrayList<String> list = (ArrayList<String>) config.getStringList("Beta.Player");
					list.add(args[1]);
					config.set("Beta.Player", list);
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(args[1]);
					config.set("Beta.Player", list);
				}
				try {
					config.save(Game.getConfigFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				Game.setPrices();
				p.sendMessage("§6Shop Preise aktualisiert");
			}
			if (args.length == 1 && args[0].equalsIgnoreCase("vanish")) {
				if (p.hasMetadata("vulkan.vanish")) {
					p.removeMetadata("vulkan.vanish", Main.getMain());
					p.sendMessage("§6Du bist nun nicht mehr im Vanish");
					p.setFlying(false);
					p.setAllowFlight(false);
					for (Player ps : Bukkit.getOnlinePlayers()) {
						ps.showPlayer(Main.getMain(), p);
					}
					p.setInvulnerable(false);
					Game.sendConsoleMSG(p.getPlayerListName() + ChatColor.DARK_GREEN + " > Vanish",
							ChatColor.DARK_GREEN);
				} else {
					p.setMetadata("vulkan.vanish", new FixedMetadataValue(Main.getMain(), true));
					p.sendMessage("§6Du bist nun im Vanish");
					p.setAllowFlight(true);
					p.setFlying(true);
					p.setInvulnerable(true);
					for (Player ps : Bukkit.getOnlinePlayers()) {
						ps.hidePlayer(Main.getMain(), p);
					}
					Game.sendConsoleMSG(p.getPlayerListName() + ChatColor.DARK_RED + " > Vanish", ChatColor.DARK_RED);
				}
			}
			if (args.length == 2 && args[0].equalsIgnoreCase("tp")) {
				if (p.hasMetadata("vulkan.vanish")) {
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						p.teleport(t.getLocation());
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				} else {
					p.sendMessage("§6Du kannst diesen Befehl nur im Vanish ausführen");
				}
			}
			if (args.length == 2 && args[0].equalsIgnoreCase("mute")) {
				if (p.hasPermission(Game.getAdminPermission()) || p.hasPermission(Game.getModPermission())
						|| p.hasPermission(Game.getSupPermission())) {
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						File fstats = new File("plugins/vulkan/PLAYERS/" + t.getName() + ".yml");
						YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
						if (stats.isSet("Mute.isMuted")) {
							if (stats.getBoolean("Mute.isMuted")) {
								stats.set("Mute.isMuted", false);
								try {
									stats.save(fstats);
								} catch (IOException e) {
									e.printStackTrace();
								}
								p.sendMessage(
										"§6Der Spieler " + t.getPlayerListName() + " §6ist nun nicht mehr gemutet");
							} else {
								stats.set("Mute.isMuted", true);
								try {
									stats.save(fstats);
								} catch (IOException e) {
									e.printStackTrace();
								}
								p.sendMessage("§6Der Spieler " + t.getPlayerListName() + " §6ist nun gemutet");
							}
						} else {
							stats.set("Mute.isMuted", true);
							try {
								stats.save(fstats);
							} catch (IOException e) {
								e.printStackTrace();
							}
							p.sendMessage("§6Der Spieler " + t.getPlayerListName() + " §6ist nun gemutet");
						}
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				}
			}
			if (args.length >= 3 && args[0].equalsIgnoreCase("kick")) {
				if (p.hasPermission(Game.getAdminPermission()) || p.hasPermission(Game.getModPermission())
						|| p.hasPermission(Game.getSupPermission())) {
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						StringBuilder sb1 = new StringBuilder();
						for (int i = 2; i < args.length; i++) {
							sb1.append(args[i]).append(" ");
						}
						if (sb1.toString().equalsIgnoreCase("")) {
							sender.sendMessage("§cDu musst einen Grund für den Kick angeben");
							return true;
						}
						t.kickPlayer("\n§4Du wurdest vom Server gekickt \n\n\n§6Grund: §c" + sb1.toString() + "\n");
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				}
			}
			if (args.length >= 3 && args[0].equalsIgnoreCase("tempban")) {
				if (p.hasPermission(Game.getAdminPermission()) || p.hasPermission(Game.getModPermission())) {
					if (Game.isValidPlayerName(args[1])) {
						Player t = Bukkit.getPlayer(args[1]);
						StringBuilder sb1 = new StringBuilder();
						for (int i = 3; i < args.length; i++) {
							sb1.append(args[i]).append(" ");
						}
						if (sb1.toString().equalsIgnoreCase("")) {
							sender.sendMessage("§cDu musst einen Grund für den Tempban angeben");
							return true;
						}
						File fstats = new File("plugins/vulkan/PLAYERS/" + t.getName() + ".yml");
						YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
						stats.set("TempBan.TimeBanned", System.currentTimeMillis());
						stats.set("TempBan.Duration", Game.convertToLong(args[2]) * 60000);
						stats.set("TempBan.Reason", sb1.toString());
						try {
							stats.save(fstats);
						} catch (IOException e) {
							e.printStackTrace();
						}
						t.kickPlayer("\n§4Du wurdest temporär vom Server gebannt \n\n\n§6Grund: §c" + sb1.toString()
								+ "\n\n§6Dauer: " + formatTime(Game.convertToLong(args[2]) * 60000));
					} else {
						p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
					}
				}
			}
			if (args.length >= 2 && args[0].equalsIgnoreCase("stop")) {
				if (p.hasPermission(Game.getAdminPermission())) {
					StringBuilder sb1 = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						sb1.append(args[i]).append(" ");
					}
					if (sb1.toString().equalsIgnoreCase("")) {
						p.sendMessage("§cDu musst einen Grund für der Server Stopp angeben");
						return true;
					}
					Util u = new Util();
					u.stopServer(p, sb1.toString());
				}
			}
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
