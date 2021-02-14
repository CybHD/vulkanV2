package net.cybhd.vn.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class Clan {

	@Deprecated
	public static File fclans = new File("plugins/vulkan/CLANS/CLANS.yml");
	@Deprecated
	public static YamlConfiguration clans = YamlConfiguration.loadConfiguration(fclans);

	public static boolean isMember(Player p) { // is player a clan member
		Boolean b;
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (stats.isSet("Clan.Name")) {
			if (stats.getString("Clan.Name").equals("NA")) {
				b = false;
			} else {
				b = true;
			}
		} else {
			b = false;
			stats.set("Clan.Name", "NA");
			try {
				stats.save(fstats);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	/*
	public static boolean isClanMember(Player p, String name) { // is player member/owner of specific clan
		Boolean b = false;
		if (doesExist(name)) {
			ArrayList<String> member = (ArrayList<String>) clans.getStringList("Clans." + name + ".Member");
			if (member.contains(p.getName())) {
				b = true;
			} else if (isOwner(p, name)) {
				b = true;
			} else {
				b = false;
			}
		} else {
			return b;
		}
		return b;
	}
	*/

	public static boolean isOwner(Player p, String name) {
		Boolean b = false;
		if (doesExist(name)) {
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			if (stats.getBoolean("Clan.isOwner")) {
				b = true;
			}
		}
		return b;
	}

	@Deprecated
	public static String getOwner(String name) {
		String owner = "";
		return owner;
	}

	public static String getClanName(Player p) {
		String name = "";
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (isMember(p)) {
			name = stats.getString("Clan.Name");
		}
		return name;
	}

	public static boolean doesExist(String name) {
		Boolean b = false;
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
		if (config.isSet("Clan.Names")) {
			ArrayList<String> list = (ArrayList<String>) config.getStringList("Clan.Names");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equalsIgnoreCase(name)) {
					b = true;
				}
			}
		} else {
			ArrayList<String> list = new ArrayList<String>();
			list.add(name);
			config.set("Clan.Names", list);
			try {
				config.save(Game.getConfigFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	@Deprecated
	public static void create(Player p, String name) {
		if (!isMember(p)) {
			if (!doesExist(name)) {
				clans.set("Clans." + name, name);
				clans.set("Clans." + name + ".Owner", p.getName());
				ArrayList<String> member = new ArrayList<String>();
				clans.set("Clans." + name + ".Member", member);
				try {
					clans.save(fclans);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage("§6Clan erfolgreich erstellt");
			} else {
				p.sendMessage("Der Clan existiert bereits");
			}
		} else {
			p.sendMessage("§cDu bist bereits in einem Clan");
		}
	}

	public static void create(Player p, String name, Long timeCreated) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (!isMember(p)) {
			if (!doesExist(name)) {
				ArrayList<String> list = (ArrayList<String>) config.getStringList("Clan.Names");
				list.add(name);
				config.set("Clan.Names", list);
				stats.set("Clan.Name", name);
				stats.set("Clan.isOwner", true);
				stats.set("Clan.timeJoined", timeCreated);
				try {
					config.save(Game.getConfigFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					stats.save(fstats);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage("§6Clan erfolgreich erstellt");
			} else {
				p.sendMessage("Der Clan existiert bereits");
			}
		} else {
			p.sendMessage("§cDu bist bereits in einem Clan");
		}
	}

	public static void leave(Player p) {
		if (isMember(p)) {
			if (isOwner(p, getClanName(p))) {
				File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
				ArrayList<String> list = (ArrayList<String>) config.getStringList("Clan.Names");
				stats.set("Clan.Name", "NA");
				stats.set("Clan.isOwner", false);
				list.remove(getClanName(p));
				config.set("Clan.Names", list);
				try {
					stats.save(fstats);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					config.save(Game.getConfigFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage("§6Du hast den Clan verlassen");
				p.sendMessage("§cDa du Owner des Clans warst wurde dieser gelöscht");
			} else {
				File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
				YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
				stats.set("Clan.Name", "NA");
				try {
					stats.save(fstats);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage("§6Du hast den Clan verlassen");
			}
		} else {
			p.sendMessage("§cDu bist in keinem Clan");
		}
	}

	public static void invite(Player p, Player t) {
		if (!isMember(t)) {
			t.sendMessage("§6Du hast eine Clan Einladung erhalten " + "§7[§e" + getClanName(p) + "§7]");
			t.setMetadata("ClanInvite", new FixedMetadataValue(Main.getMain(), getClanName(p)));
		} else {
			p.sendMessage("§cClan Einladung gescheitert");
		}
	}

	public static void addMember(Player p, String name) {
		if (doesExist(name)) {
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			stats.set("Clan.Name", name);
			stats.set("Clan.isOwner", false);
			try {
				stats.save(fstats);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			p.sendMessage("§4ERROR Clan does not exist");
		}
	}

	public static void removeMember(Player p, String name) {
		if (doesExist(name)) {
			ArrayList<String> member = (ArrayList<String>) clans.getStringList("Clans." + name + ".Member");
			member.remove(p.getName());
			clans.set("Clans." + name + ".Member", member);
			try {
				clans.save(fclans);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			p.sendMessage("§4ERROR Clan does not exist");
		}
	}

	public static ArrayList<String> getMemberList(String name) {
		ArrayList<String> member = new ArrayList<String>();
		if (doesExist(name)) {
			member = (ArrayList<String>) clans.getStringList("Clans." + name + ".Member");
		}
		return member;
	}

}
