package net.cybhd.vn.main;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.cybhd.vn.listener.Shop;

public class Eco {

	public static void set(Player p, Double amount) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set(".balance", amount);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double get(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double amount = Math.round(100.0 * stats.getDouble(".balance")) / 100.0;
		return amount;
	}
	public static void addOfflinePlayer(String name, Double amount) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + name + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double current = Math.round(100.0 * stats.getDouble(".balance")) / 100.0;
		stats.set(".balance", current + amount);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void add(Player p, Double amount, boolean silent) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double current = Math.round(100.0 * stats.getDouble(".balance")) / 100.0;
		stats.set(".balance", current + amount);
		try {
			stats.save(fstats);
			if (!silent) {
				p.sendMessage("§6Du erhältst §c" + amount + " §6$");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void add(Player p, Double amount) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double current = Math.round(100.0 * stats.getDouble(".balance")) / 100.0;
		stats.set(".balance", current + amount);
		try {
			stats.save(fstats);
			p.sendMessage("§6Du erhältst §c" + amount + " §6$");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void remove(Player p, Double amount) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Double current = Math.round(100.0 * stats.getDouble(".balance")) / 100.0;
		if ((current - amount) >= 0) {
			stats.set(".balance", current - amount);
			try {
				stats.save(fstats);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			p.sendMessage("§6Zu wenig Geld");
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 1);
		}
	}

	public static void log(String s1, String s2, Double amount) {
		String time = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		File fstats = new File("plugins/vulkan/PAYLOG.yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set(time, s1 + " > " + amount + " > " + s2);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Buy(int slot, Player p) {
		
		switch (slot) {
		case 0:
			if (Eco.get(p) >= Shop.price_dirt_buy) {
				Eco.remove(p, Shop.price_dirt_buy);
				p.getInventory().addItem(new ItemStack(Material.DIRT, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 1:
			if (Eco.get(p) >= Shop.price_coarsedirt_buy) {
				Eco.remove(p, Shop.price_coarsedirt_buy);
				p.getInventory().addItem(new ItemStack(Material.COARSE_DIRT, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 2:
			if (Eco.get(p) >= Shop.price_podzol_buy) {
				Eco.remove(p, Shop.price_podzol_buy);
				p.getInventory().addItem(new ItemStack(Material.PODZOL, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 3:
			if (Eco.get(p) >= Shop.price_gras_buy) {
				Eco.remove(p, Shop.price_gras_buy);
				p.getInventory().addItem(new ItemStack(Material.GRASS_BLOCK, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 4:
			if (Eco.get(p) >= Shop.price_stone_buy) {
				Eco.remove(p, Shop.price_stone_buy);
				p.getInventory().addItem(new ItemStack(Material.STONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 5:
			if (Eco.get(p) >= Shop.price_granit_buy) {
				Eco.remove(p, Shop.price_granit_buy);
				p.getInventory().addItem(new ItemStack(Material.GRANITE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 6:
			if (Eco.get(p) >= Shop.price_diorit_buy) {
				Eco.remove(p, Shop.price_diorit_buy);
				p.getInventory().addItem(new ItemStack(Material.DIORITE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 7:
			if (Eco.get(p) >= Shop.price_andesit_buy) {
				Eco.remove(p, Shop.price_andesit_buy);
				p.getInventory().addItem(new ItemStack(Material.ANDESITE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 8:
			if (Eco.get(p) >= Shop.price_cobblestone_buy) {
				Eco.remove(p, Shop.price_cobblestone_buy);
				p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
			//END OF FIRST ITEM SHOP ROW
		case 9:
			if (Eco.get(p) >= Shop.price_oak_buy) {
				Eco.remove(p, Shop.price_oak_buy);
				p.getInventory().addItem(new ItemStack(Material.OAK_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 10:
			if (Eco.get(p) >= Shop.price_spruce_buy) {
				Eco.remove(p, Shop.price_spruce_buy);
				p.getInventory().addItem(new ItemStack(Material.SPRUCE_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 11:
			if (Eco.get(p) >= Shop.price_birch_buy) {
				Eco.remove(p, Shop.price_birch_buy);
				p.getInventory().addItem(new ItemStack(Material.BIRCH_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 12:
			if (Eco.get(p) >= Shop.price_jungle_buy) {
				Eco.remove(p, Shop.price_jungle_buy);
				p.getInventory().addItem(new ItemStack(Material.JUNGLE_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 13:
			if (Eco.get(p) >= Shop.price_acacia_buy) {
				Eco.remove(p, Shop.price_acacia_buy);
				p.getInventory().addItem(new ItemStack(Material.ACACIA_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 14:
			if (Eco.get(p) >= Shop.price_darkoak_buy) {
				Eco.remove(p, Shop.price_darkoak_buy);
				p.getInventory().addItem(new ItemStack(Material.DARK_OAK_LOG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 15:
			if (Eco.get(p) >= Shop.price_oakleaves_buy) {
				Eco.remove(p, Shop.price_oakleaves_buy);
				p.getInventory().addItem(new ItemStack(Material.OAK_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 16:
			if (Eco.get(p) >= Shop.price_spruceleaves_buy) {
				Eco.remove(p, Shop.price_spruceleaves_buy);
				p.getInventory().addItem(new ItemStack(Material.SPRUCE_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 17:
			if (Eco.get(p) >= Shop.price_birchleaves_buy) {
				Eco.remove(p, Shop.price_birchleaves_buy);
				p.getInventory().addItem(new ItemStack(Material.BIRCH_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
			//END OF SECOND ITEM SHOP ROW
		case 18:
			if (Eco.get(p) >= Shop.price_jungleleaves_buy) {
				Eco.remove(p, Shop.price_jungleleaves_buy);
				p.getInventory().addItem(new ItemStack(Material.JUNGLE_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 19:
			if (Eco.get(p) >= Shop.price_acacia_buy) {
				Eco.remove(p, Shop.price_acacialeaves_buy);
				p.getInventory().addItem(new ItemStack(Material.ACACIA_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 20:
			if (Eco.get(p) >= Shop.price_darkoakleaves_buy) {
				Eco.remove(p, Shop.price_darkoakleaves_buy);
				p.getInventory().addItem(new ItemStack(Material.DARK_OAK_LEAVES, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 21:
			if (Eco.get(p) >= Shop.price_noteblock_buy) {
				Eco.remove(p, Shop.price_noteblock_buy);
				p.getInventory().addItem(new ItemStack(Material.NOTE_BLOCK, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 22:
			if (Eco.get(p) >= Shop.price_poweredrail_buy) {
				Eco.remove(p, Shop.price_poweredrail_buy);
				p.getInventory().addItem(new ItemStack(Material.POWERED_RAIL, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 23:
			if (Eco.get(p) >= Shop.price_detectorrail_buy) {
				Eco.remove(p, Shop.price_detectorrail_buy);
				p.getInventory().addItem(new ItemStack(Material.DETECTOR_RAIL, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 24:
			if (Eco.get(p) >= Shop.price_nametag_buy) {
				Eco.remove(p, Shop.price_nametag_buy);
				p.getInventory().addItem(new ItemStack(Material.NAME_TAG, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 25:
			if (Eco.get(p) >= Shop.price_piston_buy) {
				Eco.remove(p, Shop.price_piston_buy);
				p.getInventory().addItem(new ItemStack(Material.PISTON, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 26:
			if (Eco.get(p) >= Shop.price_cobweb_buy) {
				Eco.remove(p, Shop.price_cobweb_buy);
				p.getInventory().addItem(new ItemStack(Material.COBWEB, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		// END OF THIRD ITEM SHOP ROW
		case 27:
			if (Eco.get(p) >= Shop.price_tnt_buy) {
				Eco.remove(p, Shop.price_tnt_buy);
				p.getInventory().addItem(new ItemStack(Material.TNT, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 28:
			if (Eco.get(p) >= Shop.price_enchantingtable_buy) {
				Eco.remove(p, Shop.price_enchantingtable_buy);
				p.getInventory().addItem(new ItemStack(Material.ENCHANTING_TABLE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 29:
			if (Eco.get(p) >= Shop.price_enderchest_buy) {
				Eco.remove(p, Shop.price_enderchest_buy);
				p.getInventory().addItem(new ItemStack(Material.ENDER_CHEST, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 30:
			if (Eco.get(p) >= Shop.price_lilypad_buy) {
				Eco.remove(p, Shop.price_lilypad_buy);
				p.getInventory().addItem(new ItemStack(Material.LILY_PAD, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 31:
			if (Eco.get(p) >= Shop.price_stonebricks_buy) {
				Eco.remove(p, Shop.price_stonebricks_buy);
				p.getInventory().addItem(new ItemStack(Material.STONE_BRICKS, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 32:
			if (Eco.get(p) >= Shop.price_mossystonebricks_buy) {
				Eco.remove(p, Shop.price_mossystonebricks_buy);
				p.getInventory().addItem(new ItemStack(Material.MOSSY_STONE_BRICKS, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 33:
			if (Eco.get(p) >= Shop.price_crackedstonebricks_buy) {
				Eco.remove(p, Shop.price_crackedstonebricks_buy);
				p.getInventory().addItem(new ItemStack(Material.CRACKED_STONE_BRICKS, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 34:
			if (Eco.get(p) >= Shop.price_chiseledstonebricks_buy) {
				Eco.remove(p, Shop.price_chiseledstonebricks_buy);
				p.getInventory().addItem(new ItemStack(Material.CHISELED_STONE_BRICKS, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 35:
			if (Eco.get(p) >= Shop.price_smoothstone_buy) {
				Eco.remove(p, Shop.price_smoothstone_buy);
				p.getInventory().addItem(new ItemStack(Material.SMOOTH_STONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		//END OF FOURTH ITEM SHOP ROW
		case 36:
			if (Eco.get(p) >= Shop.price_sand_buy) {
				Eco.remove(p, Shop.price_sand_buy);
				p.getInventory().addItem(new ItemStack(Material.SAND, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 37:
			if (Eco.get(p) >= Shop.price_sandstone_buy) {
				Eco.remove(p, Shop.price_sandstone_buy);
				p.getInventory().addItem(new ItemStack(Material.SANDSTONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 38:
			if (Eco.get(p) >= Shop.price_redsand_buy) {
				Eco.remove(p, Shop.price_redsand_buy);
				p.getInventory().addItem(new ItemStack(Material.RED_SAND, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 39:
			if (Eco.get(p) >= Shop.price_redsandstone_buy) {
				Eco.remove(p, Shop.price_redsandstone_buy);
				p.getInventory().addItem(new ItemStack(Material.RED_SANDSTONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 40:
			if (Eco.get(p) >= Shop.price_endstone_buy) {
				Eco.remove(p, Shop.price_endstone_buy);
				p.getInventory().addItem(new ItemStack(Material.END_STONE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 41:
			if (Eco.get(p) >= Shop.price_endstonebricks_buy) {
				Eco.remove(p, Shop.price_endstonebricks_buy);
				p.getInventory().addItem(new ItemStack(Material.END_STONE_BRICKS, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 42:
			if (Eco.get(p) >= Shop.price_enderpearl_buy) {
				Eco.remove(p, Shop.price_enderpearl_buy);
				p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 43:
			if (Eco.get(p) >= Shop.price_eyeofender_buy) {
				Eco.remove(p, Shop.price_eyeofender_buy);
				p.getInventory().addItem(new ItemStack(Material.ENDER_EYE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 44:
			if (Eco.get(p) >= Shop.price_dragonhead_buy) {
				Eco.remove(p, Shop.price_dragonhead_buy);
				p.getInventory().addItem(new ItemStack(Material.DRAGON_HEAD, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		//END OF FITH ROW
		case 45:
			if (Eco.get(p) >= Shop.price_string_buy) {
				Eco.remove(p, Shop.price_string_buy);
				p.getInventory().addItem(new ItemStack(Material.STRING, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 46:
			if (Eco.get(p) >= Shop.price_feather_buy) {
				Eco.remove(p, Shop.price_feather_buy);
				p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 47:
			if (Eco.get(p) >= Shop.price_book_buy) {
				Eco.remove(p, Shop.price_book_buy);
				p.getInventory().addItem(new ItemStack(Material.WRITABLE_BOOK, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 48:
			if (Eco.get(p) >= Shop.price_gunpoweder_buy) {
				Eco.remove(p, Shop.price_gunpoweder_buy);
				p.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 49:
			if (Eco.get(p) >= Shop.price_rabbithide_buy) {
				Eco.remove(p, Shop.price_rabbithide_buy);
				p.getInventory().addItem(new ItemStack(Material.RABBIT_FOOT, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 50:
			if (Eco.get(p) >= Shop.price_firework_buy) {
				Eco.remove(p, Shop.price_firework_buy);
				p.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 51:
			if (Eco.get(p) >= Shop.price_snowball_buy) {
				Eco.remove(p, Shop.price_snowball_buy);
				p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 52:
			if (Eco.get(p) >= Shop.price_firecharge_buy) {
				Eco.remove(p, Shop.price_firecharge_buy);
				p.getInventory().addItem(new ItemStack(Material.FIRE_CHARGE, 1));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		case 53:
			if (Eco.get(p) >= Shop.price_expbottle_buy) {
				Eco.remove(p, Shop.price_expbottle_buy);
				p.getInventory().addItem(new ItemStack(Material.EXPERIENCE_BOTTLE, 64));
			} else {
				p.sendMessage("§6Zu wenig Geld");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
			break;
		}
		String time = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		File fstats = new File("plugins/vulkan/SHOP.yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set(time, p.getName() + " kauft " + slot);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Sell(int slot, Player p) {
		
		switch (slot) {
		case 0:
			Game.removeItem(p, Material.DIRT);
			Eco.add(p, Shop.price_dirt_sell);
			break;
		case 1:
			Game.removeItem(p, Material.COARSE_DIRT);
			Eco.add(p, Shop.price_coarsedirt_sell);
			break;
		case 2:
			Game.removeItem(p, Material.PODZOL);
			Eco.add(p, Shop.price_podzol_sell);
			break;
		case 3:
			Game.removeItem(p, Material.GRASS_BLOCK);
			Eco.add(p, Shop.price_gras_sell);
			break;
		case 4:
			Game.removeItem(p, Material.STONE);
			Eco.add(p, Shop.price_stone_sell);
			break;
		case 5:
			Game.removeItem(p, Material.GRANITE);
			Eco.add(p, Shop.price_granit_sell);
			break;
		case 6:
			Game.removeItem(p, Material.DIORITE);
			Eco.add(p, Shop.price_diorit_sell);
			break;
		case 7:
			Game.removeItem(p, Material.ANDESITE);
			Eco.add(p, Shop.price_andesit_sell);
			break;
		case 8:
			Game.removeItem(p, Material.COBBLESTONE);
			Eco.add(p, Shop.price_cobblestone_sell);
			break;
		case 9:
			Game.removeItem(p, Material.OAK_LOG);
			Eco.add(p, Shop.price_oak_sell);
			break;
		case 10:
			Game.removeItem(p, Material.SPRUCE_LOG);
			Eco.add(p, Shop.price_spruce_sell);
			break;
		case 11:
			Game.removeItem(p, Material.BIRCH_LOG);
			Eco.add(p, Shop.price_birch_sell);
			break;
		case 12:
			Game.removeItem(p, Material.JUNGLE_LOG);
			Eco.add(p, Shop.price_jungle_sell);
			break;
		case 13:
			Game.removeItem(p, Material.ACACIA_LOG);
			Eco.add(p, Shop.price_acacia_sell);
			break;
		case 14:
			Game.removeItem(p, Material.DARK_OAK_LOG);
			Eco.add(p, Shop.price_darkoak_sell);
			break;
		case 15:
			Game.removeItem(p, Material.OAK_LEAVES);
			Eco.add(p, Shop.price_oakleaves_sell);
			break;
		case 16:
			Game.removeItem(p, Material.SPRUCE_LEAVES);
			Eco.add(p, Shop.price_spruce_sell);
			break;
		case 17:
			Game.removeItem(p, Material.BIRCH_LEAVES);
			Eco.add(p, Shop.price_birchleaves_sell);
			break;
		case 18:
			Game.removeItem(p, Material.JUNGLE_LEAVES);
			Eco.add(p, Shop.price_jungleleaves_sell);
			break;
		case 19:
			Game.removeItem(p, Material.ACACIA_LEAVES);
			Eco.add(p, Shop.price_acacialeaves_sell);
			break;
		case 20:
			Game.removeItem(p, Material.DARK_OAK_LEAVES);
			Eco.add(p, Shop.price_darkoakleaves_sell);
			break;
		case 21:
			Game.removeItem(p, Material.NOTE_BLOCK);
			Eco.add(p, Shop.price_noteblock_sell);
			break;
		case 22:
			Game.removeItem(p, Material.POWERED_RAIL);
			Eco.add(p, Shop.price_poweredrail_sell);
			break;
		case 23:
			Game.removeItem(p, Material.DETECTOR_RAIL);
			Eco.add(p, Shop.price_detectorrail_sell);
			break;
		case 24:
			Game.removeItem(p, Material.NAME_TAG);
			Eco.add(p, Shop.price_nametag_sell);
			break;
		case 25:
			Game.removeItem(p, Material.PISTON);
			Eco.add(p, Shop.price_piston_sell);
			break;
		case 26:
			Game.removeItem(p, Material.COBWEB);
			Eco.add(p, Shop.price_cobweb_sell);
			break;
		case 27:
			Game.removeItem(p, Material.TNT);
			Eco.add(p, Shop.price_tnt_sell);
			break;
		case 28:
			Game.removeItem(p, Material.ENCHANTING_TABLE);
			Eco.add(p, Shop.price_enchantingtable_sell);
			break;
		case 29:
			Game.removeItem(p, Material.ENDER_CHEST);
			Eco.add(p, Shop.price_enderchest_sell);
			break;
		case 30:
			Game.removeItem(p, Material.LILY_PAD);
			Eco.add(p, Shop.price_lilypad_sell);
			break;
		case 31:
			Game.removeItem(p, Material.STONE_BRICKS);
			Eco.add(p, Shop.price_stonebricks_sell);
			break;
		case 32:
			Game.removeItem(p, Material.MOSSY_STONE_BRICKS);
			Eco.add(p, Shop.price_mossystonebricks_sell);
			break;
		case 33:
			Game.removeItem(p, Material.CRACKED_STONE_BRICKS);
			Eco.add(p, Shop.price_crackedstonebricks_sell);
			break;
		case 34:
			Game.removeItem(p, Material.CHISELED_STONE_BRICKS);
			Eco.add(p, Shop.price_chiseledstonebricks_sell);
			break;
		case 35:
			Game.removeItem(p, Material.SMOOTH_STONE);
			Eco.add(p, Shop.price_smoothstone_sell);
			break;
		case 36:
			Game.removeItem(p, Material.SAND);
			Eco.add(p, Shop.price_sand_sell);
			break;
		case 37:
			Game.removeItem(p, Material.SANDSTONE);
			Eco.add(p, Shop.price_sandstone_sell);
			break;
		case 38:
			Game.removeItem(p, Material.RED_SAND);
			Eco.add(p, Shop.price_redsandstone_sell);
			break;
		case 39:
			Game.removeItem(p, Material.RED_SANDSTONE);
			Eco.add(p, Shop.price_redsandstone_sell);
			break;
		case 40:
			Game.removeItem(p, Material.END_STONE);
			Eco.add(p, Shop.price_endstone_sell);
			break;
		case 41:
			Game.removeItem(p, Material.END_STONE_BRICKS);
			Eco.add(p, Shop.price_endstonebricks_sell);
			break;
		case 42:
			Game.removeItem(p, Material.ENDER_PEARL);
			Eco.add(p, Shop.price_enderpearl_sell);
			break;
		case 43:
			Game.removeItem(p, Material.ENDER_EYE);
			Eco.add(p, Shop.price_eyeofender_sell);
			break;
		case 44:
			Game.removeItem(p, Material.DRAGON_HEAD);
			Eco.add(p, Shop.price_dragonhead_sell);
			break;
		case 45:
			Game.removeItem(p, Material.STRING);
			Eco.add(p, Shop.price_string_sell);
			break;
		case 46:
			Game.removeItem(p, Material.FEATHER);
			Eco.add(p, Shop.price_feather_sell);
			break;
		case 47:
			Game.removeItem(p, Material.WRITABLE_BOOK);
			Eco.add(p, Shop.price_book_sell);
			break;
		case 48:
			Game.removeItem(p, Material.GUNPOWDER);
			Eco.add(p, Shop.price_gunpoweder_sell);
			break;
		case 49:
			Game.removeItem(p, Material.RABBIT_FOOT);
			Eco.add(p, Shop.price_rabbithide_sell);
			break;
		case 50:
			Game.removeItem(p, Material.FIREWORK_ROCKET);
			Eco.add(p, Shop.price_firework_sell);
			break;
		case 51:
			Game.removeItem(p, Material.SNOWBALL);
			Eco.add(p, Shop.price_snowball_sell);
			break;
		case 52:
			Game.removeItem(p, Material.FIRE_CHARGE);
			Eco.add(p, Shop.price_firecharge_sell);
			break;
		case 53:
			Game.removeItem(p, Material.EXPERIENCE_BOTTLE);
			Eco.add(p, Shop.price_expbottle_sell);
			break;
		}
		String time = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		File fstats = new File("plugins/vulkan/SHOP.yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set(time, p.getName() + " verkauft " + slot);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
