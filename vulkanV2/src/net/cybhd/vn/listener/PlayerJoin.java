package net.cybhd.vn.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import net.cybhd.vn.ability.Ability;
import net.cybhd.vn.main.Clan;
import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;
import net.cybhd.vn.main.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");

		AttributeInstance instance = p.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
		instance.setBaseValue(16);

		p.setBedSpawnLocation(Main.getSpawnLoc(), false);
		p.setMetadata("BlockBreak", new FixedMetadataValue(Main.getMain(), 0));
		p.setMetadata("BlockPlace", new FixedMetadataValue(Main.getMain(), 0));

		if (!fstats.exists()) {
			try {
				fstats.createNewFile();
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				stats.set("UUID", p.getUniqueId().toString());
				stats.set("Ability.Health", 0);
				stats.set("Ability.Jump", 0);
				stats.set("Ability.Speed", 0);
				stats.set("Ability.Reg", 0);
				stats.set("Ability.FRes", 0);
				stats.set("Auction.Items", 0);
				stats.set("Job.Name", "none");
				stats.set("Job.Miner.Level", 0);
				stats.set("Job.Miner.XP", 0);
				stats.set("Job.Woodcutter.Level", 0);
				stats.set("Job.Woodcutter.XP", 0);
				stats.set("Job.Smith.Level", 0);
				stats.set("Job.Smith.XP", 0);
				stats.set("Job.Fisher.Level", 0);
				stats.set("Job.Fisher.XP", 0);
				stats.set("Job.Farmer.Level", 0);
				stats.set("Job.Farmer.XP", 0);
				stats.set("Kills", 0);
				stats.set("Deaths", 0);
				stats.set("Entity.Kills", 0);
				stats.set(".balance", 1000);
				stats.set(".timePlayed", 0);
				stats.set(".timeJoined", System.currentTimeMillis());
				stats.set(".blockPlace", 0);
				stats.set(".blockBreak", 0);
				stats.save(fstats);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			stats.set(".timeJoined", System.currentTimeMillis());
			try {
				stats.save(fstats);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		File fbackpack = new File("plugins/vulkan/BACKPACKS/" + p.getName() + ".yml");
		YamlConfiguration backpack = YamlConfiguration.loadConfiguration(fbackpack);
		
		//create backpack file
		if (!fbackpack.exists()) {
			try {
				fbackpack.createNewFile();
				for (int i = 0; i < 54; i++) {
					ItemStack is = new ItemStack(Material.AIR);
					backpack.set(String.valueOf(i), is);
				}
				backpack.save(fbackpack);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		// Player List Name
		if (Clan.isMember(p)) {
			if (p.hasPermission(Game.getAdminPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§4" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getModPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§c" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getSupPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§9" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getContentPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§3" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getSuPremPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§b" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getPremPermission())) {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§6" + Game.getUsernameFormatted(p));
			} else {
				p.setPlayerListName("§7[§e" + Clan.getClanName(p) + "§7] " + "§2" + Game.getUsernameFormatted(p));
			}
		} else {
			if (p.hasPermission(Game.getAdminPermission())) {
				p.setPlayerListName("§4" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getModPermission())) {
				p.setPlayerListName("§c" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getSupPermission())) {
				p.setPlayerListName("§9" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getContentPermission())) {
				p.setPlayerListName("§3" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getSuPremPermission())) {
				p.setPlayerListName("§b" + Game.getUsernameFormatted(p));
			} else if (p.hasPermission(Game.getPremPermission())) {
				p.setPlayerListName("§6" + Game.getUsernameFormatted(p));
			} else {
				p.setPlayerListName("§2" + Game.getUsernameFormatted(p));
			}
		}

		// Join Message
		if (p.hasPermission(Game.getAdminPermission())) {
			e.setJoinMessage("§a> " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getModPermission())) {
			e.setJoinMessage("§a> " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getSupPermission())) {
			e.setJoinMessage("§a> " + p.getPlayerListName());
		} else if (p.hasPermission(Game.getPremPermission())) {
			e.setJoinMessage("§a> " + p.getPlayerListName());
		} else {
			e.setJoinMessage("§a> " + p.getPlayerListName());
		}

		TextComponent tc = new TextComponent();
		tc.setText("hier");
		tc.setBold(true);
		tc.setUnderlined(true);
		tc.setColor(ChatColor.RED);
		tc.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://mchype.net/vulkanV2/regelwerk"));

		TextComponent tc2 = new TextComponent();
		tc2.setColor(ChatColor.GOLD);
		tc2.setText("Die offiziellen Regeln findest du ");

		ComponentBuilder cb = new ComponentBuilder();
		cb.append(tc2);
		cb.append(tc);
		BaseComponent[] tc3 = cb.create();

		p.spigot().sendMessage(tc3);
		
		//Util u = new Util();
		//u.sendGitMSG(p);

		Ability.set(p);
		
		if (p.hasMetadata("vulkan.vanish")) {
			p.removeMetadata("vulkan.vanish", Main.getMain());
		}

		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.hasMetadata("vulkan.vanish")) {
				if (!p.hasPermission(Game.getAdminPermission())) {
					p.hidePlayer(Main.getMain(), ps);
				}
			}
			ps.setScoreboard(Util.getBaseScoreboard(ps));
		}
		
		Util.setTab(p);
		
		for (Player ps: Bukkit.getOnlinePlayers()) {
			Util.updateTab(ps);
		}
		
		// Set Shop Prices
		Game.setPrices();

		p.teleport(Main.getSpawnLoc());
	}
}
