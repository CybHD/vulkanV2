package net.cybhd.vn.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class Util {
	
	public static Scoreboard getBaseScoreboard(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = s.registerNewObjective("Main", "Main" , "§6§lMCHype.net");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore("§1").setScore(11);
		//
		obj.getScore("§6Dein Rang:").setScore(10);
		//
		if (p.hasPermission(Game.getAdminPermission())) {
		obj.getScore("§4Admin").setScore(9);
		} else if (p.hasPermission(Game.getModPermission())) {
			obj.getScore("§cModerator").setScore(9);
		} else if (p.hasPermission(Game.getSupPermission())) {
			obj.getScore("§9Supporter").setScore(9);
		} else if (p.hasPermission(Game.getPremPermission())) {
			obj.getScore("§6Premium").setScore(9);
		} else {
			obj.getScore("§aSpieler").setScore(9);
		}
		//
		obj.getScore("§2").setScore(8);
		//
		obj.getScore("§6Dein Geld:").setScore(7);
		//
		obj.getScore("§c" + Eco.get(p) + " §6$").setScore(6);
		//
		obj.getScore("§3").setScore(5);
		//
		obj.getScore("§6K §7/ §6D").setScore(4);
		//
		obj.getScore("§c" + stats.getInt("Deaths") + " §7/ §c" + stats.getInt("Kills")).setScore(3);
		//
		obj.getScore("§4").setScore(2);
		//
		obj.getScore("§6Spieler Online:").setScore(1);
		//
		obj.getScore("§c" + Bukkit.getOnlinePlayers().size()).setScore(0);
		
		
		s.registerNewTeam("Player");
		s.getTeam("Player").setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		
		//--					11
		//Dein Rang:			10
		//*Rang*				9
		//--					8
		//Dein Geld:			7
		//*Geld*				6
		//--					5
		//K / D:				4
		//*kills* / *deaths*	3
		//--					2
		//Online:				1
		//*x von MaxPlayer*		0
		
		return s;
	}
	
	
	public void stopServer(Player p, String s1) {
		new BukkitRunnable() {

			@Override
			public void run() {
				for (Player ps : Bukkit.getOnlinePlayers()) {
					ps.kickPlayer("\n§4Der Server wurde gestoppt \n\n\n§6Grund: §c" + s1.toString() + "\n");
					Bukkit.getServer().shutdown();
				}

			}
		}.runTaskLater(Main.getMain(), 200);

		Bukkit.broadcastMessage(
				"§6§m+                         §e§m                           §6§m                         +");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("  §c§lAnkündigung :");
		Bukkit.broadcastMessage("  §7Der Server wird in §610 Sekunden §7gestoppt von " + p.getPlayerListName() + "§7.");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("  §c§lGrund :");
		Bukkit.broadcastMessage("  §e" + s1.toString());
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(
				"§6§m+                         §e§m                           §6§m                         +");

	}
	
	
	public void sendGitMSG(Player p) {
		p.sendMessage("§l§3vulkanV2 §r§3ist jetzt Open-Source");
		TextComponent here = new TextComponent();
		here.setText("--> Github Repository <--");
		here.setBold(true);
		here.setColor(ChatColor.DARK_AQUA);
		here.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://github.com/CybHD/vulkanV2"));

		p.spigot().sendMessage(here);
	}
	
	public void calculateNextDayTime(Long time, World w) {
		//int currentDays = (int) (time / 24000);
	}
}
