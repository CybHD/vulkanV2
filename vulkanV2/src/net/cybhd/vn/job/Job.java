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

	public static Integer getJobLevel(Player p, String job) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		int level = 0;
		switch (job.toLowerCase()) {
		case "miner":
			return stats.getInt("Job.Miner.Level");
		case "woodcutter":
			return stats.getInt("Job.Woodcutter.Level");
		case "smith":
			return stats.getInt("Job.Smith.Level");
		case "fisher":
			return stats.getInt("Job.Fisher.Level");
		case "farmer":
			return stats.getInt("Job.Farmer.Level");
		default:
			break;
		}
		return level;
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
			ArrayList<String> lore1 = new ArrayList<String>();
			ArrayList<String> lore2 = new ArrayList<String>();
			ArrayList<String> lore3 = new ArrayList<String>();
			ArrayList<String> lore4 = new ArrayList<String>();
			ArrayList<String> lore5 = new ArrayList<String>();
			ArrayList<String> lore6 = new ArrayList<String>();
			ArrayList<String> lore7 = new ArrayList<String>();
			ArrayList<String> lore8 = new ArrayList<String>();
			ArrayList<String> lore9 = new ArrayList<String>();
			lore1.add("§6Preis: §c" + Job.price_miner_cobblestone_sell + " §6$");
			lore1.add("§6XP: §c" + Job.miner_cobblestone_xp);
			lore2.add("§6Preis: §c" + Job.price_miner_coalore_sell + " §6$");
			lore2.add("§6XP: §c" + Job.miner_coalore_xp);
			lore3.add("§6Preis: §c" + Job.price_miner_copperore_sell + " §6$");
			lore3.add("§6XP: §c" + Job.miner_copperore_xp);
			lore4.add("§6Preis: §c" + Job.price_miner_ironore_sell + " §6$");
			lore4.add("§6XP: §c" + Job.miner_ironore_xp);
			lore5.add("§6Preis: §c" + Job.price_miner_goldore_sell + " §6$");
			lore5.add("§6XP: §c" + Job.miner_goldore_xp);
			lore6.add("§6Preis: §c" + Job.price_miner_redstoneore_sell + " §6$");
			lore6.add("§6XP: §c" + Job.miner_redstoneore_xp);
			lore7.add("§6Preis: §c" + Job.price_miner_lapisore_sell + " §6$");
			lore7.add("§6XP: §c" + Job.miner_lapisore_xp);
			lore8.add("§6Preis: §c" + Job.price_miner_diamondore_sell + " §6$");
			lore8.add("§6XP: §c" + Job.miner_diamondore_xp);
			lore9.add("§6Preis: §c" + Job.price_miner_emeraldore_sell + " §6$");
			lore9.add("§6XP: §c" + Job.miner_emeraldore_xp);
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
			ArrayList<String> lore1 = new ArrayList<String>();
			ArrayList<String> lore2 = new ArrayList<String>();
			ArrayList<String> lore3 = new ArrayList<String>();
			ArrayList<String> lore4 = new ArrayList<String>();
			ArrayList<String> lore5 = new ArrayList<String>();
			ArrayList<String> lore6 = new ArrayList<String>();
			ArrayList<String> lore7 = new ArrayList<String>();
			ArrayList<String> lore8 = new ArrayList<String>();
			ArrayList<String> lore9 = new ArrayList<String>();
			ArrayList<String> lore10 = new ArrayList<String>();
			ArrayList<String> lore11 = new ArrayList<String>();
			ArrayList<String> lore12 = new ArrayList<String>();
			ArrayList<String> lore13 = new ArrayList<String>();
			ArrayList<String> lore14 = new ArrayList<String>();
			ArrayList<String> lore15 = new ArrayList<String>();
			ArrayList<String> lore16 = new ArrayList<String>();
			ArrayList<String> lore17 = new ArrayList<String>();
			ArrayList<String> lore18 = new ArrayList<String>();
			ArrayList<String> lore19 = new ArrayList<String>();
			ArrayList<String> lore20 = new ArrayList<String>();
			ArrayList<String> lore21 = new ArrayList<String>();
			lore1.add("§6Preis: §c" + Job.price_woodcutter_oaklog_sell + " §6$");
			lore1.add("§6XP: §c" + Job.woodcutter_oaklog_xp);
			lore2.add("§6Preis: §c" + Job.price_woodcutter_oakwood_sell + " §6$");
			lore2.add("§6XP: §c" + Job.woodcutter_oakwood_xp);
			lore3.add("§6Preis: §c" + Job.price_woodcutter_oaksapling_sell + " §6$");
			lore3.add("§6XP: §c" + Job.price_woodcutter_oaksapling_sell);
			lore4.add("§6Preis: §c" + Job.price_woodcutter_sprucelog_sell + " §6$");
			lore4.add("§6XP: §c" + Job.woodcutter_sprucelog_xp);
			lore5.add("§6Preis: §c" + Job.price_woodcutter_sprucewood_sell + " §6$");
			lore5.add("§6XP: §c" + Job.woodcutter_sprucewood_xp);
			lore6.add("§6Preis: §c" + Job.price_woodcutter_sprucesapling_sell + " §6$");
			lore6.add("§6XP: §c" + Job.woodcutter_sprucesapling_xp);
			lore7.add("§6Preis: §c" + Job.price_woodcutter_birchlog_sell + " §6$");
			lore7.add("§6XP: §c" + Job.woodcutter_birchlog_xp);
			lore8.add("§6Preis: §c" + Job.price_woodcutter_birchwood_sell + " §6$");
			lore8.add("§6XP: §c" + Job.woodcutter_birchwood_xp);
			lore9.add("§6Preis: §c" + Job.price_woodcutter_birchsapling_sell + " §6$");
			lore9.add("§6XP: §c" + Job.woodcutter_birchsapling_xp);
			lore10.add("§6Preis: §c" + Job.price_woodcutter_junglelog_sell + " §6$");
			lore10.add("§6XP: §c" + Job.woodcutter_junglelog_xp);
			lore11.add("§6Preis: §c" + Job.price_woodcutter_junglewood_sell + " §6$");
			lore11.add("§6XP: §c" + Job.woodcutter_junglewood_xp);
			lore12.add("§6Preis: §c" + Job.price_woodcutter_junglesapling_sell + " §6$");
			lore12.add("§6XP: §c" + Job.woodcutter_junglesapling_xp);
			lore13.add("§6Preis: §c" + Job.price_woodcutter_darkoaklog_sell + " §6$");
			lore13.add("§6XP: §c" + Job.woodcutter_darkoaklog_xp);
			lore14.add("§6Preis: §c" + Job.price_woodcutter_darkoakwood_sell + " §6$");
			lore14.add("§6XP: §c" + Job.woodcutter_darkoakwood_xp);
			lore15.add("§6Preis: §c" + Job.price_woodcutter_darkoaksapling_sell + " §6$");
			lore15.add("§6XP: §c" + Job.woodcutter_darkoaksapling_xp);
			lore16.add("§6Preis: §c" + Job.price_woodcutter_acacialog_sell + " §6$");
			lore16.add("§6XP: §c" + Job.woodcutter_acacialog_xp);
			lore17.add("§6Preis: §c" + Job.price_woodcutter_acaciawood_sell + " §6$");
			lore17.add("§6XP: §c" + Job.woodcutter_acaciawood_xp);
			lore18.add("§6Preis: §c" + Job.price_woodcutter_acaciasapling_sell + " §6$");
			lore18.add("§6XP: §c" + Job.woodcutter_acaciasapling_xp);
			lore19.add("§6Preis: §c" + Job.price_woodcutter_crimsonlog_sell + " §6$");
			lore19.add("§6XP: §c" + Job.woodcutter_crimsonlog_xp);
			lore20.add("§6Preis: §c" + Job.price_woodcutter_crimsonwood_sell + " §6$");
			lore20.add("§6XP: §c" + Job.woodcutter_crimsonwood_xp);
			lore21.add("§6Preis: §c" + Job.price_woodcutter_crimsongrass_sell + " §6$");
			lore21.add("§6XP: §c" + Job.woodcutter_crimsongrass_xp);
			inv.setItem(0, Game.createNamedItemStack(Material.OAK_LOG, 1, "§7Eichenstamm", lore1));
			inv.setItem(9, Game.createNamedItemStack(Material.OAK_PLANKS, 1, "§7Eichenholz", lore2));
			inv.setItem(18, Game.createNamedItemStack(Material.OAK_SAPLING, 1, "§7Eichensetzling", lore3));
			inv.setItem(1, Game.createNamedItemStack(Material.SPRUCE_LOG, 1, "§7Fichtenstamm", lore4));
			inv.setItem(10, Game.createNamedItemStack(Material.SPRUCE_PLANKS, 1, "§7Fichtenholz", lore5));
			inv.setItem(19, Game.createNamedItemStack(Material.SPRUCE_SAPLING, 1, "§7Fichtensetzling", lore6));
			inv.setItem(2, Game.createNamedItemStack(Material.BIRCH_LOG, 1, "§7Birkenstamm", lore7));
			inv.setItem(11, Game.createNamedItemStack(Material.BIRCH_PLANKS, 1, "§7Birkenholz", lore8));
			inv.setItem(20, Game.createNamedItemStack(Material.BIRCH_SAPLING, 1, "§7Birkensetzling", lore9));
			inv.setItem(3, Game.createNamedItemStack(Material.JUNGLE_LOG, 1, "§7Jungelstamm", lore10));
			inv.setItem(12, Game.createNamedItemStack(Material.JUNGLE_PLANKS, 1, "§7Jungelholz", lore11));
			inv.setItem(21, Game.createNamedItemStack(Material.JUNGLE_SAPLING, 1, "§7Jungelsetzling", lore12));
			inv.setItem(4, Game.createNamedItemStack(Material.DARK_OAK_LOG, 1, "§7Schwarzeichenstamm", lore13));
			inv.setItem(13, Game.createNamedItemStack(Material.DARK_OAK_PLANKS, 1, "§7Schwarzeichenholz", lore14));
			inv.setItem(22, Game.createNamedItemStack(Material.DARK_OAK_SAPLING, 1, "§7Schwarzeichensetzling", lore15));
			inv.setItem(5, Game.createNamedItemStack(Material.ACACIA_LOG, 1, "§7Akazienstamm", lore16));
			inv.setItem(14, Game.createNamedItemStack(Material.ACACIA_PLANKS, 1, "§7Akazienholz", lore17));
			inv.setItem(23, Game.createNamedItemStack(Material.ACACIA_SAPLING, 1, "§7Akaziensetzling", lore18));
			inv.setItem(7, Game.createNamedItemStack(Material.CRIMSON_STEM, 1, "§7Crimsonstamm", lore19));
			inv.setItem(16, Game.createNamedItemStack(Material.CRIMSON_PLANKS, 1, "§7Crimsonholz", lore20));
			inv.setItem(25, Game.createNamedItemStack(Material.CRIMSON_ROOTS, 1, "§7Crimsongras", lore21));
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
	
	public static double price_miner_cobblestone_sell = 1;
	public static double price_miner_coalore_sell = 1;
	public static double price_miner_copperore_sell = 1;
	public static double price_miner_ironore_sell = 1;
	public static double price_miner_goldore_sell = 1;
	public static double price_miner_redstoneore_sell = 1;
	public static double price_miner_lapisore_sell = 1;
	public static double price_miner_diamondore_sell = 1;
	public static double price_miner_emeraldore_sell = 1;
	public static double price_woodcutter_oaklog_sell = 1;
	public static double price_woodcutter_oakwood_sell = 1;
	public static double price_woodcutter_oaksapling_sell = 1;
	public static double price_woodcutter_sprucelog_sell = 1;
	public static double price_woodcutter_sprucewood_sell = 1;
	public static double price_woodcutter_sprucesapling_sell = 1;
	public static double price_woodcutter_birchlog_sell = 1;
	public static double price_woodcutter_birchwood_sell = 1;
	public static double price_woodcutter_birchsapling_sell = 1;
	public static double price_woodcutter_junglelog_sell = 1;
	public static double price_woodcutter_junglewood_sell = 1;
	public static double price_woodcutter_junglesapling_sell = 1;
	public static double price_woodcutter_darkoaklog_sell = 1;
	public static double price_woodcutter_darkoakwood_sell = 1;
	public static double price_woodcutter_darkoaksapling_sell = 1;
	public static double price_woodcutter_acacialog_sell = 1;
	public static double price_woodcutter_acaciawood_sell = 1;
	public static double price_woodcutter_acaciasapling_sell = 1;
	public static double price_woodcutter_crimsonlog_sell = 1;
	public static double price_woodcutter_crimsonwood_sell = 1;
	public static double price_woodcutter_crimsongrass_sell = 1;
	public static double miner_cobblestone_xp = 1;
	public static double miner_coalore_xp = 2;
	public static double miner_copperore_xp = 2;
	public static double miner_ironore_xp = 3;
	public static double miner_goldore_xp = 4;
	public static double miner_redstoneore_xp = 3;
	public static double miner_lapisore_xp = 4;
	public static double miner_diamondore_xp = 5;
	public static double miner_emeraldore_xp = 5;
	public static double woodcutter_oaklog_xp = 1;
	public static double woodcutter_oakwood_xp = 1;
	public static double woodcutter_oaksapling_xp = 1;
	public static double woodcutter_sprucelog_xp = 1;
	public static double woodcutter_sprucewood_xp = 1;
	public static double woodcutter_sprucesapling_xp = 1;
	public static double woodcutter_birchlog_xp = 1;
	public static double woodcutter_birchwood_xp = 1;
	public static double woodcutter_birchsapling_xp = 1;
	public static double woodcutter_junglelog_xp = 1;
	public static double woodcutter_junglewood_xp = 1;
	public static double woodcutter_junglesapling_xp = 1;
	public static double woodcutter_darkoaklog_xp = 1;
	public static double woodcutter_darkoakwood_xp = 1;
	public static double woodcutter_darkoaksapling_xp = 1;
	public static double woodcutter_acacialog_xp = 1;
	public static double woodcutter_acaciawood_xp = 1;
	public static double woodcutter_acaciasapling_xp = 1;
	public static double woodcutter_crimsonlog_xp = 1;
	public static double woodcutter_crimsonwood_xp = 1;
	public static double woodcutter_crimsongrass_xp = 1;
}
