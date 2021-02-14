package net.cybhd.vn.main;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class Util {
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
