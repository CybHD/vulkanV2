package net.cybhd.vn.job;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.cybhd.vn.main.Game;

public class Job implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			// open job inventory where player can cut items in and recieve xp amount for
			// items
			if (!getJob(p).equals("none")) {
				if (p.hasPermission(Game.getAdminPermission())) {
					p.openInventory(getJobInv(p));
				} else {
					p.sendMessage("§4An diesem Befehl wird derzeit noch gearbeitet");
				}
			} else {
				p.sendMessage("§cDu brauchst einen Job um diesen Befehl nutzen zu können");
			}
		} else {
			p.sendMessage("§6Bitte nutze §c/job");
		}
		return false;
	}

	public static String getJob(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		return stats.getString("Job.Name");
	}

	public static Double getJobXP(Player p, String job) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double xp = 0D;
		switch (job.toLowerCase()) {
		case "miner":
			return stats.getDouble("Job.Miner.XP");
		case "woodcutter":
			return stats.getDouble("Job.Woodcutter.XP");
		case "smith":
			return stats.getDouble("Job.Smith.XP");
		case "fisher":
			return stats.getDouble("Job.Fisher.XP");
		case "farmer":
			return stats.getDouble("Job.Farmer.XP");
		default:
			break;
		}
		return xp;
	}

	public static Inventory getJobInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "§6§lJob");
		if (getJob(p).equals("miner")) {
			// TODO add lore with price and xp amount to items
			ArrayList<String> lore1 = new ArrayList<String>();
			ArrayList<String> lore2 = new ArrayList<String>();
			ArrayList<String> lore3 = new ArrayList<String>();
			ArrayList<String> lore4 = new ArrayList<String>();
			ArrayList<String> lore5 = new ArrayList<String>();
			ArrayList<String> lore6 = new ArrayList<String>();
			ArrayList<String> lore7 = new ArrayList<String>();
			ArrayList<String> lore8 = new ArrayList<String>();
			ArrayList<String> lore9 = new ArrayList<String>();
			inv.setItem(0, Game.createNamedItemStack(Material.COBBLESTONE, 1, "§7Bruchstein", lore1));
			inv.setItem(2, Game.createNamedItemStack(Material.COAL_ORE, 1, "§7Kohleerz", lore2));
			inv.setItem(4, Game.createNamedItemStack(Material.COPPER_ORE, 1, "§7Kupfererz", lore3));
			inv.setItem(6, Game.createNamedItemStack(Material.IRON_ORE, 1, "§7Eisenerz", lore4));
			inv.setItem(8, Game.createNamedItemStack(Material.GOLD_ORE, 1, "§7Golderz", lore5));
			inv.setItem(19, Game.createNamedItemStack(Material.REDSTONE_ORE, 1, "§7Redstoneerz", lore6));
			inv.setItem(21, Game.createNamedItemStack(Material.LAPIS_ORE, 1, "§7Lapiserz", lore7));
			inv.setItem(23, Game.createNamedItemStack(Material.DIAMOND_ORE, 1, "§7Diamanterz", lore8));
			inv.setItem(25, Game.createNamedItemStack(Material.EMERALD_ORE, 1, "§7Smaragderz", lore9));
		} else if (getJob(p).equals("woodcutter")) {

		} else if (getJob(p).equals("smith")) {

		} else if (getJob(p).equals("fisher")) {

		} else if (getJob(p).equals("farmer")) {

		}
		return inv;
	}

	public static Inventory getArbeitsamtInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§6§lArbeitsamt");
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		int minerLevel = stats.getInt("Job.Miner.Level");
		int woodcutterLevel = stats.getInt("Job.Woodcutter.Level");
		int smithLevel = stats.getInt("Job.Smith.Level");
		int fisherLevel = stats.getInt("Job.Fisher.Level");
		int farmerLevel = stats.getInt("Job.Farmer.Level");

		//
		// set Miner item
		//
		ArrayList<String> minerLore = new ArrayList<String>();
		switch (minerLevel) {
		case 0:
			minerLore.add("§6Level: §c0");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.WOODEN_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		case 1:
			minerLore.add("§6Level: §c1");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.STONE_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		case 2:
			minerLore.add("§6Level: §c2");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.IRON_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		case 3:
			minerLore.add("§6Level: §c3");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.GOLDEN_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		case 4:
			minerLore.add("§6Level: §c4");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.DIAMOND_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		case 5:
			minerLore.add("§6Level: §c5");
			minerLore.add("§6XP: §c" + getJobXP(p, "miner"));
			inv.setItem(18, Game.createNamedItemStack(Material.NETHERITE_PICKAXE, 1, "§7Minenarbeiter", minerLore));
			break;
		default:
			break;
		}

		//
		// set Woodcutter item
		//
		ArrayList<String> woodcutterLore = new ArrayList<String>();
		switch (woodcutterLevel) {
		case 0:
			woodcutterLore.add("§6Level: §c0");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.WOODEN_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		case 1:
			woodcutterLore.add("§6Level: §c1");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.STONE_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		case 2:
			woodcutterLore.add("§6Level: §c2");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.IRON_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		case 3:
			woodcutterLore.add("§6Level: §c3");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.GOLDEN_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		case 4:
			woodcutterLore.add("§6Level: §c4");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.DIAMOND_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		case 5:
			woodcutterLore.add("§6Level: §c5");
			woodcutterLore.add("§6XP: §c" + getJobXP(p, "woodcutter"));
			inv.setItem(20, Game.createNamedItemStack(Material.NETHERITE_AXE, 1, "§7Holzfäller", woodcutterLore));
			break;
		default:
			break;
		}

		//
		// set Smith item
		//
		ArrayList<String> smithLore = new ArrayList<String>();
		switch (smithLevel) {
		case 0:
			smithLore.add("§6Level: §c0");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.LEATHER_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		case 1:
			smithLore.add("§6Level: §c1");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.CHAINMAIL_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		case 2:
			smithLore.add("§6Level: §c2");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.IRON_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		case 3:
			smithLore.add("§6Level: §c3");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.GOLDEN_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		case 4:
			smithLore.add("§6Level: §c4");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.DIAMOND_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		case 5:
			smithLore.add("§6Level: §c5");
			smithLore.add("§6XP: §c" + getJobXP(p, "smith"));
			inv.setItem(22, Game.createNamedItemStack(Material.NETHERITE_CHESTPLATE, 1, "§7Schmied", smithLore));
			break;
		default:
			break;
		}

		//
		// set Fisher item
		//
		ArrayList<String> fisherLore = new ArrayList<String>();
		switch (fisherLevel) {
		case 0:
			fisherLore.add("§6Level: §c0");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		case 1:
			fisherLore.add("§6Level: §c1");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		case 2:
			fisherLore.add("§6Level: §c2");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		case 3:
			fisherLore.add("§6Level: §c3");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		case 4:
			fisherLore.add("§6Level: §c4");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		case 5:
			fisherLore.add("§6Level: §c5");
			fisherLore.add("§6XP: §c" + getJobXP(p, "fisher"));
			inv.setItem(24, Game.createNamedItemStack(Material.FISHING_ROD, 1, "§7Fischer", fisherLore));
			break;
		default:
			break;
		}

		//
		// set Farmer item
		//
		ArrayList<String> farmerLore = new ArrayList<String>();
		switch (farmerLevel) {
		case 0:
			farmerLore.add("§6Level: §c0");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.WOODEN_HOE, 1, "§7Farmer", farmerLore));
			break;
		case 1:
			farmerLore.add("§6Level: §c1");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.STONE_HOE, 1, "§7Farmer", farmerLore));
			break;
		case 2:
			farmerLore.add("§6Level: §c2");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.IRON_HOE, 1, "§7Farmer", farmerLore));
			break;
		case 3:
			farmerLore.add("§6Level: §c3");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.GOLDEN_HOE, 1, "§7Farmer", farmerLore));
			break;
		case 4:
			farmerLore.add("§6Level: §c4");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.DIAMOND_HOE, 1, "§7Farmer", farmerLore));
			break;
		case 5:
			farmerLore.add("§6Level: §c5");
			farmerLore.add("§6XP: §c" + getJobXP(p, "farmer"));
			inv.setItem(26, Game.createNamedItemStack(Material.NETHERITE_HOE, 1, "§7Farmer", farmerLore));
			break;
		default:
			break;
		}

		ArrayList<String> lore = new ArrayList<String>();
		if (getJob(p).equalsIgnoreCase("none")) {
			lore.add("§cArbeitslos");
		} else if (getJob(p).equalsIgnoreCase("miner")) {
			lore.add("§cMiner");
		} else if (getJob(p).equalsIgnoreCase("woodcutter")) {
			lore.add("§cHolzfäller");
		} else if (getJob(p).equalsIgnoreCase("smith")) {
			lore.add("§cSchmied");
		} else if (getJob(p).equalsIgnoreCase("fisher")) {
			lore.add("§cFischer");
		} else if (getJob(p).equalsIgnoreCase("farmer")) {
			lore.add("§cFarmer");
		}

		inv.setItem(49, Game.createNamedItemStack(Material.BOOK, 1, "§6Derzeitiger Job", lore));

		inv.setItem(53, Game.createNamedItemStack(Material.BARRIER, 1, "§4Job kündigen"));

		return inv;

	}

}
