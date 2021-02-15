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
import org.bukkit.scoreboard.Team;
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
		Objective obj = s.registerNewObjective("Main", "Main", "§e§n >>> §6§l§nMCHype.net §r§e§n<<<");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		obj.getScore("§1").setScore(11);
		//
		obj.getScore("§6Dein Rang:").setScore(10);
		//
		if (p.hasPermission(Game.getAdminPermission())) {
			obj.getScore("§4§oAdmin").setScore(9);
		} else if (p.hasPermission(Game.getModPermission())) {
			obj.getScore("§c§oModerator").setScore(9);
		} else if (p.hasPermission(Game.getSupPermission())) {
			obj.getScore("§9§oSupporter").setScore(9);
		} else if (p.hasPermission(Game.getPremPermission())) {
			obj.getScore("§6§oPremium").setScore(9);
		} else {
			obj.getScore("§2§oSpieler").setScore(9);
		}
		//
		obj.getScore("§2").setScore(8);
		//
		obj.getScore("§6Dein Geld:").setScore(7);
		//
		obj.getScore("§c§o" + Eco.get(p) + " §r§6$").setScore(6);
		//
		obj.getScore("§3").setScore(5);
		//
		obj.getScore("§6K §7/ §6D").setScore(4);
		//
		obj.getScore("§c§o" + stats.getInt("Kills") + " §r§7/ §c§o" + stats.getInt("Deaths")).setScore(3);
		//
		obj.getScore("§4").setScore(2);
		//
		obj.getScore("§6Spieler Online:").setScore(1);
		//
		obj.getScore("§c§o" + Bukkit.getOnlinePlayers().size()).setScore(0);

		// -- 11
		// Dein Rang: 10
		// *Rang* 9
		// -- 8
		// Dein Geld: 7
		// *Geld* 6
		// -- 5
		// K / D: 4
		// *kills* / *deaths* 3
		// -- 2
		// Online: 1
		// *x von MaxPlayer* 0

		return s;
	}

	public static void setTab(Player p) {
		Scoreboard s = p.getScoreboard();

		Team admin = s.registerNewTeam("0000Admin");
		Team mod = s.registerNewTeam("0001Mod");
		Team sup = s.registerNewTeam("0002Sup");
		Team prem = s.registerNewTeam("0003Prem");
		Team spieler = s.registerNewTeam("0004Spieler");

		admin.setPrefix("§4");
		admin.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		mod.setPrefix("§c");
		mod.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		sup.setPrefix("§9");
		sup.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		prem.setPrefix("§6");
		prem.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);
		spieler.setPrefix("§a");
		spieler.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.NEVER);

		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.hasPermission(Game.getAdminPermission())) {
				admin.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getModPermission())) {
				mod.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getSupPermission())) {
				sup.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getPremPermission())) {
				prem.addEntry(ps.getName());
			} else {
				spieler.addEntry(ps.getName());
			}
		}
	}

	public static void updateTab(Player p) {
		Scoreboard s = p.getScoreboard();

		Team admin = s.getTeam("0000Admin");
		Team mod = s.getTeam("0001Mod");
		Team sup = s.getTeam("0002Sup");
		Team prem = s.getTeam("0003Prem");
		Team spieler = s.getTeam("0004Spieler");

		if (admin == null || mod == null || sup == null || prem == null || spieler == null) {
			setTab(p);
			return;
		}

		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.hasPermission(Game.getAdminPermission())) {
				admin.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getModPermission())) {
				mod.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getSupPermission())) {
				sup.addEntry(ps.getName());
			} else if (ps.hasPermission(Game.getPremPermission())) {
				prem.addEntry(ps.getName());
			} else {
				spieler.addEntry(ps.getName());
			}
		}
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
		// int currentDays = (int) (time / 24000);
	}
}
