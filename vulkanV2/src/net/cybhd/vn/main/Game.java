package net.cybhd.vn.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.cybhd.vn.listener.Shop;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Game implements Listener {

	public static Double getPriceRepair() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(getConfigFile());
		double price_Repair = config.getDouble("Repair.Price.Buy");
		return price_Repair;

	}
	
	public static String formatTime(long time) {
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

	public static int count = -1;

	public static void countAdd() {
		count++;
		Game.sendConsoleMSG("§6Counter: §c" + count, ChatColor.BLUE);
		if (count == 15) {
			Bukkit.broadcastMessage("§6Du hast dein Gehalt erhalten");
			for (Player ps : Bukkit.getOnlinePlayers()) {
				Eco.add(ps, 100D, true);
			}
			count = 0;
		}
	}

	public static void sendConsoleMSG(String msg, ChatColor c) {
		Main.getMain().getServer().getConsoleSender().sendMessage(c + msg);
	}

	public static void sendActionBarMSG(Player p, String msg) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
	}

	public static void sendOnlinePlayerMSG(String s) {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			ps.sendMessage(s);
		}
	}

	public void sendTitleMSG(Player p, String s) {
		p.spigot();
	}

	public void sendSubTitleMSG(Player p, String s) {

	}

	public static void sendPremiumReqMSG(Player p) {
		p.sendMessage("§cFür dieses Feature brauchst du §6§lPremium");
	}

	public static String COLOR_PLAYER = "§2";
	public static String COLOR_PREMIUM = "§6";
	public static String COLOR_SUPREMIUM = "§b";
	public static String COLOR_CONTENT = "§3";
	public static String COLOR_SUPPORTER = "§9";
	public static String COLOR_MODERATOR = "§c";
	public static String COLOR_ADMIN = "§4";

	public static String getPrefix() {
		String prefix = "§7[§l§6vulkan§4V2§r§7] ";
		return prefix;
	}

	public static String getPremPermission() {
		String perm = "vn.premium";
		return perm;
	}

	public static String getSuPremPermission() {
		String perm = "vn.supremium";
		return perm;
	}

	public static String getContentPermission() {
		String perm = "vn.content";
		return perm;
	}

	public static String getSupPermission() {
		String perm = "vn.sup";
		return perm;
	}

	public static String getModPermission() {
		String perm = "vn.mod";
		return perm;
	}

	public static String getAdminPermission() {
		String perm = "vn.admin";
		return perm;
	}

	public static Inventory getCraftInv() {
		Inventory inv = Bukkit.createInventory(null, 54, "§6§lWerkbank");
		ItemStack black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemStack air = new ItemStack(Material.AIR);
		ItemMeta bgMeta = black_glass.getItemMeta();
		bgMeta.setDisplayName(" ");
		black_glass.setItemMeta(bgMeta);
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, black_glass);
		}
		inv.setItem(10, air); //
		inv.setItem(11, air); // First Craft Row
		inv.setItem(12, air); //
		inv.setItem(19, air); //
		inv.setItem(20, air); // Second Craft Row
		inv.setItem(21, air); //
		inv.setItem(24, air); //
		inv.setItem(28, air); //
		inv.setItem(29, air); // Third Craft Row
		inv.setItem(30, air); //
		return inv;
	}

	public static void Startup() {
		sendConsoleMSG(">>> Initialisiere Startup <<<", ChatColor.YELLOW);
		if (!getConfigFile().exists()) {
			try {
				getConfigFile().createNewFile();
				Main.getMain().getServer().getConsoleSender()
						.sendMessage(ChatColor.DARK_RED + ">>> Keine Config gefunden... Stoppe Server <<<");
				Main.getMain().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + ">>> Erstelle Config <<<");
				setConfig();
				Bukkit.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
				Bukkit.getServer().shutdown();
			}
		} else {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + ">>> Config gefunden und geladen <<<");
			loadCraftingRecipe();
			loadFurnaceRecipe();
		}
		// create ability file
		File fab = new File("plugins/vulkan/ABILITYS.yml");
		YamlConfiguration ability = YamlConfiguration.loadConfiguration(fab);
		try {
			ability.save(fab);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create clans file
		File fclans = new File("plugins/vulkan/CLANS/CLANS.yml");
		YamlConfiguration clans = YamlConfiguration.loadConfiguration(fclans);
		try {
			clans.save(fclans);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create auction file
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		try {
			auction.save(fauction);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create chunk file
		File fchunks = new File("plugins/vulkan/CHUNKS.yml");
		YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
		try {
			chunks.save(fchunks);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create shop file
		File fshop = new File("plugins/vulkan/SHOP.yml");
		YamlConfiguration shop = YamlConfiguration.loadConfiguration(fshop);
		try {
			shop.save(fshop);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create reports file
		File frep = new File("plugins/vulkan/REPORTS.yml");
		YamlConfiguration rep = YamlConfiguration.loadConfiguration(frep);
		try {
			rep.save(frep);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendConsoleMSG(">>> Startup erfolgreich <<<", ChatColor.DARK_GREEN);
	}

	/*
	 * @EventHandler public void onWeatherChange(WeatherChangeEvent e) {
	 * YamlConfiguration config =
	 * YamlConfiguration.loadConfiguration(getConfigFile());
	 * if(!config.getBoolean("WeatherChange")) { e.setCancelled(true); } }
	 */

	public static void setConfig() { // Sets the config and default values on startup
		try {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.YELLOW + ">>> Setze Config Standardwerte <<<");
			YamlConfiguration config = YamlConfiguration.loadConfiguration(getConfigFile());
			config.set("CustomCraftingRecipe", true);
			config.set("CustomFurnaceRecipe", true);

			config.set("Hub.World", "ice_spawn");
			config.set("Hub.X", -187.5D);
			config.set("Hub.Y", 17D);
			config.set("Hub.Z", 197.5D);
			config.set("Hub.Yaw", -180F);
			config.set("Hub.Pitch", 0F);

			// Repair Price
			config.set("Repair.Price.Buy", 1000D);

			// Chunk Price
			config.set("Chunk.Price.Buy", 15000);
			config.set("Chunk.Price.Sell", 15000);

			// Item Price Buy
			config.set("Shop.Price.Buy.Dirt", 1);
			config.set("Shop.Price.Buy.CoarseDirt", 1);
			config.set("Shop.Price.Buy.Podzol", 2);
			config.set("Shop.Price.Buy.Grass", 3);
			config.set("Shop.Price.Buy.Stone", 3);
			config.set("Shop.Price.Buy.Granit", 1);
			config.set("Shop.Price.Buy.Diorit", 1);
			config.set("Shop.Price.Buy.Andesit", 1);
			config.set("Shop.Price.Buy.Cobblestone", 1);

			config.set("Shop.Price.Buy.Oak", 2);
			config.set("Shop.Price.Buy.Spruce", 2);
			config.set("Shop.Price.Buy.Birch", 2);
			config.set("Shop.Price.Buy.Jungle", 2);
			config.set("Shop.Price.Buy.Acacia", 2);
			config.set("Shop.Price.Buy.Darkoak", 2);
			config.set("Shop.Price.Buy.Oakleaves", 1.5);
			config.set("Shop.Price.Buy.Spruceleaves", 1.5);
			config.set("Shop.Price.Buy.Birchleaves", 1.5);

			config.set("Shop.Price.Buy.Jungleleaves", 1.5);
			config.set("Shop.Price.Buy.Acacialeaves", 1.5);
			config.set("Shop.Price.Buy.Darkoakleaves", 1.5);
			config.set("Shop.Price.Buy.Noteblock", 100);
			config.set("Shop.Price.Buy.Poweredrail", 15);
			config.set("Shop.Price.Buy.Detectorrail", 15);
			config.set("Shop.Price.Buy.Nametag", 250);
			config.set("Shop.Price.Buy.Piston", 35);
			config.set("Shop.Price.Buy.Cobweb", 25);

			config.set("Shop.Price.Buy.Tnt", 75);
			config.set("Shop.Price.Buy.Enchantingtable", 350);
			config.set("Shop.Price.Buy.Enderechest", 450);
			config.set("Shop.Price.Buy.Lilypad", 10);
			config.set("Shop.Price.Buy.Stonebricks", 4);
			config.set("Shop.Price.Buy.Mossystonebricks", 15);
			config.set("Shop.Price.Buy.Crackedstonebricks", 15);
			config.set("Shop.Price.Buy.Chiseledstonebricks", 15);
			config.set("Shop.Price.Buy.Smoothstone", 4);

			config.set("Shop.Price.Buy.Sand", 2);
			config.set("Shop.Price.Buy.Sandstone", 4);
			config.set("Shop.Price.Buy.Redsand", 2);
			config.set("Shop.Price.Buy.Redsandstone", 4);
			config.set("Shop.Price.Buy.Endstone", 7.5);
			config.set("Shop.Price.Buy.Enstonebricks", 10);
			config.set("Shop.Price.Buy.Enderpearl", 15);
			config.set("Shop.Price.Buy.Endereye", 25);
			config.set("Shop.Price.Buy.Dragonhead", 500);

			config.set("Shop.Price.Buy.String", 5);
			config.set("Shop.Price.Buy.Feather", 7.5);
			config.set("Shop.Price.Buy.Book", 15);
			config.set("Shop.Price.Buy.Gunpowder", 10);
			config.set("Shop.Price.Buy.Rabbithide", 50);
			config.set("Shop.Price.Buy.Firework", 25);
			config.set("Shop.Price.Buy.Snowball", 2);
			config.set("Shop.Price.Buy.Expbottle", 250);
			config.set("Shop.Price.Buy.Firecharge", 25);

			//
			// SELL
			//
			config.set("Shop.Price.Sell.Dirt", 0.25);
			config.set("Shop.Price.Sell.CoarseDirt", 0.75);
			config.set("Shop.Price.Sell.Podzol", 1);
			config.set("Shop.Price.Sell.Grass", 0.75);
			config.set("Shop.Price.Sell.Stone", 1);
			config.set("Shop.Price.Sell.Granit", 0.75);
			config.set("Shop.Price.Sell.Diorit", 0.75);
			config.set("Shop.Price.Sell.Andesit", 0.75);
			config.set("Shop.Price.Sell.Cobblestone", 0.25);

			config.set("Shop.Price.Sell.Oak", 1);
			config.set("Shop.Price.Sell.Spruce", 1);
			config.set("Shop.Price.Sell.Birch", 1);
			config.set("Shop.Price.Sell.Jungle", 1);
			config.set("Shop.Price.Sell.Acacia", 1);
			config.set("Shop.Price.Sell.Darkoak", 1);
			config.set("Shop.Price.Sell.Oakleaves", 0.25);
			config.set("Shop.Price.Sell.Spruceleaves", 0.25);
			config.set("Shop.Price.Sell.Birchleaves", 0.25);

			config.set("Shop.Price.Sell.Jungleleaves", 0.25);
			config.set("Shop.Price.Sell.Acacialeaves", 0.25);
			config.set("Shop.Price.Sell.Darkoakleaves", 0.25);
			config.set("Shop.Price.Sell.Noteblock", 15);
			config.set("Shop.Price.Sell.Poweredrail", 5);
			config.set("Shop.Price.Sell.Detectorrail", 5);
			config.set("Shop.Price.Sell.Nametag", 100);
			config.set("Shop.Price.Sell.Piston", 15);
			config.set("Shop.Price.Sell.Cobweb", 7.5);

			config.set("Shop.Price.Sell.Tnt", 15);
			config.set("Shop.Price.Sell.Enchantingtable", 65);
			config.set("Shop.Price.Sell.Enderechest", 50);
			config.set("Shop.Price.Sell.Lilypad", 5);
			config.set("Shop.Price.Sell.Stonebricks", 1.5);
			config.set("Shop.Price.Sell.Mossystonebricks", 3.5);
			config.set("Shop.Price.Sell.Crackedstonebricks", 3.5);
			config.set("Shop.Price.Sell.Chiseledstonebricks", 3.5);
			config.set("Shop.Price.Sell.Smoothstone", 1.5);

			config.set("Shop.Price.Sell.Sand", 0.25);
			config.set("Shop.Price.Sell.Sandstone", 0.75);
			config.set("Shop.Price.Sell.Redsand", 0.25);
			config.set("Shop.Price.Sell.Redsandstone", 0.75);
			config.set("Shop.Price.Sell.Endstone", 0.25);
			config.set("Shop.Price.Sell.Enstonebricks", 0.50);
			config.set("Shop.Price.Sell.Enderpearl", 1);
			config.set("Shop.Price.Sell.Endereye", 2);
			config.set("Shop.Price.Sell.Dragonhead", 100);

			config.set("Shop.Price.Sell.String", 0.5);
			config.set("Shop.Price.Sell.Feather", 0.5);
			config.set("Shop.Price.Sell.Book", 1);
			config.set("Shop.Price.Sell.Gunpowder", 0.75);
			config.set("Shop.Price.Sell.Rabbithide", 1);
			config.set("Shop.Price.Sell.Firework", 1);
			config.set("Shop.Price.Sell.Snowball", 0.1);
			config.set("Shop.Price.Sell.Expbottle", 100);
			config.set("Shop.Price.Sell.Firecharge", 10);

			config.save(getConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setTime() {
		World w = Bukkit.getWorld("world");
		Date date = new Date();
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(date);
		Integer currentHour = c.get(Calendar.HOUR_OF_DAY);
		switch (currentHour) {
		case 0:
			w.setTime(18000);
			break;
		case 1:
			w.setTime(19000);
			break;
		case 2:
			w.setTime(20000);
			break;
		case 3:
			w.setTime(21000);
			break;
		case 4:
			w.setTime(22000);
			break;
		case 5:
			w.setTime(23000);
			break;
		case 6:
			w.setTime(0);
			break;
		case 7:
			w.setTime(1000);
			break;
		case 8:
			w.setTime(2000);
			break;
		case 9:
			w.setTime(3000);
			break;
		case 10:
			w.setTime(4000);
			break;
		case 11:
			w.setTime(5000);
			break;
		case 12:
			w.setTime(6000);
			break;
		case 13:
			w.setTime(7000);
			break;
		case 14:
			w.setTime(8000);
			break;
		case 15:
			w.setTime(9000);
			break;
		case 16:
			w.setTime(10000);
			break;
		case 17:
			w.setTime(11000);
			break;
		case 18:
			w.setTime(12000);
			break;
		case 19:
			w.setTime(13000);
			break;
		case 20:
			w.setTime(14000);
			break;
		case 21:
			w.setTime(15000);
			break;
		case 22:
			w.setTime(16000);
			break;
		case 23:
			w.setTime(17000);
			break;
		case 24:
			w.setTime(18000);
			break;
		default:
			break;
		}
	}

	public static void loadCraftingRecipe() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(getConfigFile());
		if (config.getBoolean("CustomCraftingRecipe")) {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + ">>> Custom Crafting Recipe Enabled <<<");
			Crafting.registerRecipe("Emerald_Sword");
			Crafting.registerRecipe("op_apple");
			Crafting.registerRecipe("Flesh");
			Crafting.registerRecipe("CoalOre");
			Crafting.registerRecipe("IronOre");
			// Crafting.registerRecipe("GoldOre");
			// Crafting.registerRecipe("RedstoneOre");
			// Crafting.registerRecipe("LapisOre");
			// Crafting.registerRecipe("DiamondOre");
			// Crafting.registerRecipe("EmeraldOre");
			// Crafting.registerRecipe("EmeraldBlock1");
		} else {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.YELLOW + ">>> Custom Crafting Recipe Disabled <<<");
		}
	}

	public static void loadFurnaceRecipe() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(getConfigFile());
		if (config.getBoolean("CustomFurnaceRecipe") == true) {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + ">>> Custom Furnace Recipe Enabled <<<");
			Furnace.ROTTEN_FLESH();
		} else {
			Main.getMain().getServer().getConsoleSender()
					.sendMessage(ChatColor.YELLOW + ">>> Custom Furnace Recipe Disabled <<<");
		}
	}

	public static void sendSubMessage() {

	}

	public static File getConfigFile() {
		File fconfig = new File("plugins/vulkan/" + "config" + ".yml");
		return fconfig;
	}

	public static File getPlayerFile(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		return fstats;
	}

	public static void openAbilityInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§6§lFähigkeiten");

		ItemStack i1 = new ItemStack(Material.POTION);
		ItemStack i2 = new ItemStack(Material.POTION);
		ItemStack i3 = new ItemStack(Material.POTION);
		ItemStack i4 = new ItemStack(Material.POTION);
		ItemStack i5 = new ItemStack(Material.POTION);

		PotionMeta m1 = (PotionMeta) i1.getItemMeta();
		m1.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0), true);
		m1.setDisplayName("§4Leben");
		i1.setItemMeta(m1);

		PotionMeta m2 = (PotionMeta) i2.getItemMeta();
		m2.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1, 0), true);
		m2.setDisplayName("§fSchnelligkeit");
		i2.setItemMeta(m2);

		inv.setItem(0, i1);
		inv.setItem(2, i2);
		inv.setItem(4, i3);
		inv.setItem(6, i4);
		inv.setItem(8, i5);

		p.openInventory(inv);
	}

	public static void openShopInv(Player p) {

		/*
		 * Seltenheitsstufen: Gewöhnlich: Grau §7 Selten: Grün §a Sehr Selten: Blau §9
		 * Episch: §5%Lila% §l%FETT% Legendär: §e%Gelb% §l%FETT% §n%UNTERSTRICHEN%
		 * §7Gewöhnlich §aSelten §9Sehr Selten §5§lEpisch §e§lLEGENDÄR
		 */

		Inventory shop = Bukkit.createInventory(null, 54, "§6§lItem Shop");

		shop.addItem(newShopItem(Material.DIRT, "§7Erde", "§7Gewöhnlich", Shop.price_dirt_buy, Shop.price_dirt_sell));
		shop.addItem(newShopItem(Material.COARSE_DIRT, "§7Grobe Erde", "§7Gewöhnlich", Shop.price_coarsedirt_buy,
				Shop.price_coarsedirt_sell));
		shop.addItem(newShopItem(Material.PODZOL, "§7Podsol", "§7Gewöhnlich", Shop.price_podzol_buy,
				Shop.price_podzol_sell));
		shop.addItem(newShopItem(Material.GRASS_BLOCK, "§7Grasblock", "§7Gewöhnlich", Shop.price_gras_buy,
				Shop.price_gras_sell));
		shop.addItem(
				newShopItem(Material.STONE, "§7Stein", "§7Gewöhnlich", Shop.price_stone_buy, Shop.price_stone_sell));
		shop.addItem(newShopItem(Material.GRANITE, "§7Granit", "§7Gewöhnlich", Shop.price_granit_buy,
				Shop.price_granit_sell));
		shop.addItem(newShopItem(Material.DIORITE, "§7Diorit", "§7Gewöhnlich", Shop.price_diorit_buy,
				Shop.price_diorit_sell));
		shop.addItem(newShopItem(Material.ANDESITE, "§7Andesit", "§7Gewöhnlich", Shop.price_andesit_buy,
				Shop.price_andesit_sell));
		shop.addItem(newShopItem(Material.COBBLESTONE, "§7Bruchstein", "§7Gewöhnlich", Shop.price_cobblestone_buy,
				Shop.price_cobblestone_sell));

		shop.addItem(newShopItem(Material.OAK_LOG, "§7Eichenstamm", "§7Gewöhnlich", Shop.price_oak_buy,
				Shop.price_oak_sell));
		shop.addItem(newShopItem(Material.SPRUCE_LOG, "§7Fichtenstamm", "§7Gewöhnlich", Shop.price_spruce_buy,
				Shop.price_spruce_sell));
		shop.addItem(newShopItem(Material.BIRCH_LOG, "§7Birkenstamm", "§7Gewöhnlich", Shop.price_birch_buy,
				Shop.price_birch_sell));
		shop.addItem(newShopItem(Material.JUNGLE_LOG, "§7Tropenbaumstamm", "§7Gewöhnlich", Shop.price_jungle_buy,
				Shop.price_jungle_sell));
		shop.addItem(newShopItem(Material.ACACIA_LOG, "§7Akazienstamm", "§7Gewöhnlich", Shop.price_acacia_buy,
				Shop.price_acacia_sell));
		shop.addItem(newShopItem(Material.DARK_OAK_LOG, "§7Schwarzeichenstamm", "§7Gewöhnlich", Shop.price_darkoak_buy,
				Shop.price_darkoak_sell));
		shop.addItem(newShopItem(Material.OAK_LEAVES, "§7Eichenlaub", "§7Gewöhnlich", Shop.price_oakleaves_buy,
				Shop.price_oakleaves_sell));
		shop.addItem(newShopItem(Material.SPRUCE_LEAVES, "§7Fichtenlaub", "§7Gewöhnlich", Shop.price_spruceleaves_buy,
				Shop.price_spruceleaves_sell));
		shop.addItem(newShopItem(Material.BIRCH_LEAVES, "§7Birkenlaub", "§7Gewöhnlich", Shop.price_birchleaves_buy,
				Shop.price_birchleaves_sell));

		shop.addItem(newShopItem(Material.JUNGLE_LEAVES, "§7Tropenbaumlaub", "§7Gewöhnlich",
				Shop.price_jungleleaves_buy, Shop.price_jungleleaves_sell));
		shop.addItem(newShopItem(Material.ACACIA_LEAVES, "§7Akazienlaub", "§7Gewöhnlich", Shop.price_acacialeaves_buy,
				Shop.price_acacialeaves_sell));
		shop.addItem(newShopItem(Material.DARK_OAK_LEAVES, "§7Schwarzeichenlaub", "§7Gewöhnlich",
				Shop.price_darkoakleaves_buy, Shop.price_darkoakleaves_sell));
		shop.addItem(newShopItem(Material.NOTE_BLOCK, "§9Notenblock", "§9Sehr Selten", Shop.price_noteblock_buy,
				Shop.price_noteblock_sell));
		shop.addItem(newShopItem(Material.POWERED_RAIL, "§aAntriebsschiene", "§aSelten", Shop.price_poweredrail_buy,
				Shop.price_poweredrail_sell));
		shop.addItem(newShopItem(Material.DETECTOR_RAIL, "§aSensorschiene", "§aSelten", Shop.price_detectorrail_buy,
				Shop.price_detectorrail_sell));
		shop.addItem(newShopItem(Material.NAME_TAG, "§9Nametag", "§9Sehr Selten", Shop.price_nametag_buy,
				Shop.price_nametag_sell));
		shop.addItem(
				newShopItem(Material.PISTON, "§aKolben", "§aSelten", Shop.price_piston_buy, Shop.price_piston_sell));
		shop.addItem(newShopItem(Material.COBWEB, "§9Spinnennetz", "§9Sehr Selten", Shop.price_cobweb_buy,
				Shop.price_cobweb_sell));

		shop.addItem(newShopItem(Material.TNT, "§9TNT", "§9Sehr Selten", Shop.price_tnt_buy, Shop.price_tnt_sell));
		shop.addItem(newShopItem(Material.ENCHANTING_TABLE, "§9Zaubertisch", "§9Sehr Selten",
				Shop.price_enchantingtable_buy, Shop.price_enchantingtable_sell));
		shop.addItem(newShopItem(Material.ENDER_CHEST, "§9Endertruhe", "§9Sehr Selten", Shop.price_enderchest_buy,
				Shop.price_enderchest_sell));
		shop.addItem(newShopItem(Material.LILY_PAD, "§aSeerosenblatt", "§aSelten", Shop.price_lilypad_buy,
				Shop.price_lilypad_sell));
		shop.addItem(newShopItem(Material.STONE_BRICKS, "§7Steinziegel", "§7Gewöhnlich", Shop.price_stonebricks_buy,
				Shop.price_stonebricks_sell));
		shop.addItem(newShopItem(Material.MOSSY_STONE_BRICKS, "§aBemooste Steinziegel", "§aSelten",
				Shop.price_mossystonebricks_buy, Shop.price_mossystonebricks_sell));
		shop.addItem(newShopItem(Material.CRACKED_STONE_BRICKS, "§aRissige Steinziegel", "§aSelten",
				Shop.price_crackedstonebricks_buy, Shop.price_crackedstonebricks_sell));
		shop.addItem(newShopItem(Material.CHISELED_STONE_BRICKS, "§aGemeißelte Steinziegel", "§aSelten",
				Shop.price_chiseledstonebricks_buy, Shop.price_chiseledstonebricks_sell));
		shop.addItem(newShopItem(Material.SMOOTH_STONE, "§aGlatter Stein", "§aSelten", Shop.price_smoothstone_buy,
				Shop.price_smoothstone_sell));

		shop.addItem(newShopItem(Material.SAND, "§7Sand", "§7Gewöhnlich", Shop.price_sand_buy, Shop.price_sand_sell));
		shop.addItem(newShopItem(Material.SANDSTONE, "§7Sandstein", "§7Gewöhnlich", Shop.price_sandstone_buy,
				Shop.price_sandstone_sell));
		shop.addItem(newShopItem(Material.RED_SAND, "§7Roter Sand", "§7Gewöhnlich", Shop.price_redsand_buy,
				Shop.price_redsand_sell));
		shop.addItem(newShopItem(Material.RED_SANDSTONE, "§7Roter Sandstein", "§7Gewöhnlich",
				Shop.price_redsandstone_buy, Shop.price_redsandstone_sell));
		shop.addItem(newShopItem(Material.END_STONE, "§9Endstein", "§9Sehr Selten", Shop.price_endstone_buy,
				Shop.price_endstone_sell));
		shop.addItem(newShopItem(Material.END_STONE_BRICKS, "§9Endsteinziegel", "§9Sehr Selten",
				Shop.price_endstonebricks_buy, Shop.price_endstonebricks_sell));
		shop.addItem(newShopItem(Material.ENDER_PEARL, "§9Enderperle", "§9Sehr Selten", Shop.price_enderpearl_buy,
				Shop.price_enderpearl_sell));
		shop.addItem(newShopItem(Material.ENDER_EYE, "§9Enderauge", "§9Sehr Selten", Shop.price_eyeofender_buy,
				Shop.price_eyeofender_sell));
		shop.addItem(newShopItem(Material.DRAGON_HEAD, "§5§lDrachenkopf", "§5§lEpisch", Shop.price_dragonhead_buy,
				Shop.price_dragonhead_sell));

		shop.addItem(
				newShopItem(Material.STRING, "§7Faden", "§7Gewöhnlich", Shop.price_string_buy, Shop.price_string_sell));
		shop.addItem(newShopItem(Material.FEATHER, "§7Feder", "§7Gewöhnlich", Shop.price_feather_buy,
				Shop.price_feather_sell));
		shop.addItem(
				newShopItem(Material.WRITABLE_BOOK, "§aBuch", "§aSelten", Shop.price_book_buy, Shop.price_book_sell));
		shop.addItem(newShopItem(Material.GUNPOWDER, "§aSchwarzpulver", "§aSelten", Shop.price_gunpoweder_buy,
				Shop.price_gunpoweder_sell));
		shop.addItem(newShopItem(Material.RABBIT_FOOT, "§9Hasenpfote", "§9Sehr Selten", Shop.price_rabbithide_buy,
				Shop.price_rabbithide_sell));
		shop.addItem(newShopItem(Material.FIREWORK_ROCKET, "§9Feuerwerk", "§9Sehr Selten", Shop.price_firework_buy,
				Shop.price_firework_sell));
		shop.addItem(newShopItem(Material.SNOWBALL, "§aSchneeball", "§aSelten", Shop.price_snowball_buy,
				Shop.price_snowball_sell));
		shop.addItem(newShopItem(Material.FIRE_CHARGE, "§aFeuerkugel", "§aSelten", Shop.price_firecharge_buy,
				Shop.price_firecharge_sell));
		shop.addItem(newShopItem(Material.EXPERIENCE_BOTTLE, "§e§lLevelflasche", "§e§lLEGENDÄR",
				Shop.price_expbottle_buy, Shop.price_expbottle_sell));

		/*
		 * Seltenheitsstufen: Gewöhnlich: Grau §7 Selten: Grün §a Sehr Selten: Blau §9
		 * Episch: §5%Lila% §l%FETT% Legendär: §e%Gelb% §l%FETT% §n%UNTERSTRICHEN%
		 * §7Gewöhnlich §aSelten §9Sehr Selten §5§lEpisch §e§lLEGENDÄR
		 */
		p.openInventory(shop);
	}

	public static Inventory getWarpInv(Player p) {
		String world = p.getWorld().getName();

		Inventory warp = Bukkit.createInventory(null, 9, "§l§6WARP");
		// warp.setItem(1, Game.createNamedItemStack(Material.DANDELION, 1, "§e1.16"));
		warp.setItem(4, Game.createNamedItemStack(Material.ALLIUM, 1, "§aWarp"));
		// warp.setItem(7, Game.createNamedItemStack(Material.POPPY, 1, "§c1.17"));

		if (world.equalsIgnoreCase("world")) {
			// wenn klick auf mittlere blume dann teleport zwischen spawn und lastlocation
			// bzw. wenn man world ist dann loc als lastloc speichern und zu warp
			// teleportieren
		}
		return warp;
	}

	public static Inventory getBackPackInv(Player p) {
		Inventory inv = null;
		File fbackpack = new File("plugins/vulkan/BACKPACKS/" + p.getName() + ".yml");
		YamlConfiguration backpack = YamlConfiguration.loadConfiguration(fbackpack);
		if (p.hasPermission(Game.getSuPremPermission())) {
			inv = Bukkit.createInventory(null, 54, "§6Rucksack");
			for (int i = 0; i < 54; i++) {
				inv.setItem(i, backpack.getItemStack(String.valueOf(i)));
			}
		} else if (p.hasPermission(Game.getPremPermission())) {
			inv = Bukkit.createInventory(null, 27, "§6Rucksack");
			for (int i = 0; i < 27; i++) {
				inv.setItem(i, backpack.getItemStack(String.valueOf(i)));
			}
		}
		return inv;
	}
	
	public static Inventory getDailyRewardInv(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		Inventory inv = Bukkit.createInventory(null, 27, "§6§lTäglichen Belohnung");
		Boolean reward1Collected = stats.getBoolean("Reward.1.Collected");
		if (reward1Collected) {
			ArrayList<String> lore = new ArrayList<String>();
			inv.setItem(0, Game.createNamedItemStack(Material.GUNPOWDER, 1, "§7Bereits eingesammelt", lore));
		} else {
			ArrayList<String> lore = new ArrayList<String>();
			inv.setItem(0, Game.createNamedItemStack(Material.GOLD_INGOT, 1, "§6Einsammeln", lore));
		}
		
		return inv;
	}

	public static void openShopInv(Player p, int j) {
		File fshop = new File("plugins/vulkan/SHOP.yml");
		YamlConfiguration shop = YamlConfiguration.loadConfiguration(fshop);
		Inventory shopINV = Bukkit.createInventory(null, 27);
		for (int i = 0; i < 26; i++) {
			Material m = Material.getMaterial(shop.getString(i + ".material"));
			ItemStack is = new ItemStack(m);
			shopINV.addItem(is);
		}
	}

	public static void openRepInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§cReport");

		ItemStack i1 = new ItemStack(Material.IRON_SWORD); // Hacking
		ItemMeta m1 = i1.getItemMeta();
		m1.setDisplayName("§6Hacking");
		i1.setItemMeta(m1);
		ItemStack i2 = new ItemStack(Material.TNT); // Griefing
		ItemMeta m2 = i2.getItemMeta();
		m2.setDisplayName("§6Griefing");
		i2.setItemMeta(m2);
		ItemStack i3 = new ItemStack(Material.DIAMOND); // Betrug
		ItemMeta m3 = i3.getItemMeta();
		m3.setDisplayName("§6Betrug");
		i3.setItemMeta(m3);

		inv.setItem(2, i1);
		inv.setItem(4, i2);
		inv.setItem(6, i3);

		p.openInventory(inv);
	}

	public static void openWorkInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§6Arbeitsamt");
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		inv.setItem(0, Game.createNamedItemStack(Material.COD, 1, "§7Fischer"));

		// Miner
		ArrayList<String> lore = new ArrayList<String>();
		switch (stats.getInt("Job.Miner.Level")) {
		case 1:
			lore.add("§8Level: §a1");
			inv.setItem(4, Game.createNamedItemStack(Material.WOODEN_AXE, 1, "§7Holzfäller", lore));
			break;
		case 2:
			lore.add("§8Level: §22");
			inv.setItem(4, Game.createNamedItemStack(Material.STONE_AXE, 1, "§7Holzfäller", lore));
			break;
		case 3:
			lore.add("§8Level: §e3");
			inv.setItem(4, Game.createNamedItemStack(Material.IRON_AXE, 1, "§7Holzfäller", lore));
			break;
		case 4:
			lore.add("§8Level: §c4");
			inv.setItem(4, Game.createNamedItemStack(Material.GOLDEN_AXE, 1, "§7Holzfäller", lore));
			break;
		case 5:
			lore.add("§8Level: §45");
			inv.setItem(4, Game.createNamedItemStack(Material.DIAMOND_AXE, 1, "§7Holzfäller", lore));
			break;
		default:
			lore.add("§8Level: §a1");
			inv.setItem(4, Game.createNamedItemStack(Material.WOODEN_AXE, 1, "§7Holzfäller", lore));
			break;
		}
		lore.clear();

		// Woodcutter
		switch (stats.getInt("Job.Woodcutter.Level")) {
		case 1:
			lore.add("§8Level: §a1");
			inv.setItem(7, Game.createNamedItemStack(Material.WOODEN_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		case 2:
			lore.add("§8Level: §22");
			inv.setItem(7, Game.createNamedItemStack(Material.STONE_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		case 3:
			lore.add("§8Level: §e3");
			inv.setItem(7, Game.createNamedItemStack(Material.IRON_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		case 4:
			lore.add("§8Level: §c4");
			inv.setItem(7, Game.createNamedItemStack(Material.GOLDEN_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		case 5:
			lore.add("§8Level: §45");
			inv.setItem(7, Game.createNamedItemStack(Material.DIAMOND_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		default:
			lore.add("§8Level: §a1");
			inv.setItem(7, Game.createNamedItemStack(Material.WOODEN_PICKAXE, 1, "§7Minenarbeiter", lore));
			break;
		}
		lore.clear();

		p.openInventory(inv);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equals("Report")) {
			if (p.hasMetadata("REPORTS")) {
				String s = p.getMetadata("REPORTS").get(0).asString();
				File frep = new File("plugins/vulkan/REPORTS.yml");
				YamlConfiguration rep = YamlConfiguration.loadConfiguration(frep);
				if (e.getRawSlot() <= 8) {
					switch (e.getRawSlot()) {
					case 2:
						ArrayList<String> li1 = (ArrayList<String>) rep.getStringList("Hacking");
						p.removeMetadata("REPORTS", Main.getMain());
						li1.add(s);
						rep.set("Hacking", li1);
						try {
							rep.save(frep);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						p.closeInventory();
						p.sendMessage("§6Du hast den Spieler §c" + s + " §6erfolgreich gemeldet §7(§cHacking§7)");
						break;
					case 4:
						ArrayList<String> li2 = (ArrayList<String>) rep.getStringList("Griefing");
						p.removeMetadata("REPORTS", Main.getMain());
						li2.add(s);
						rep.set("Griefing", li2);
						try {
							rep.save(frep);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						p.closeInventory();
						p.sendMessage("§6Du hast den Spieler §c" + s + " §6erfolgreich gemeldet §7(§cGriefing§7)");
						break;
					case 6:
						ArrayList<String> li3 = (ArrayList<String>) rep.getStringList("Betrug");
						p.removeMetadata("REPORTS", Main.getMain());
						li3.add(s);
						rep.set("Betrug", li3);
						try {
							rep.save(frep);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						p.closeInventory();
						p.sendMessage("§6Du hast den Spieler §c" + s + " §6erfolgreich gemeldet §7(§cBetrug§7)");
						break;
					default:
						break;
					}
					e.setCancelled(true);
				}
			}
		}
	}

	public static ItemStack newShopItem(Material m, String name, String rarity, double buy, double sell) {
		ItemStack item = new ItemStack(m, 1);
		ItemMeta mItem = item.getItemMeta();
		mItem.setDisplayName(name);
		List<String> Litem = new ArrayList<String>();
		Litem.add("§8Seltenheit: " + rarity);
		Litem.add("§8Kaufen: §c" + buy + " §6$");
		Litem.add("§8Verkaufen: §a" + sell + " §6$");
		mItem.setLore(Litem);
		item.setItemMeta(mItem);
		return item;
	}

	public static boolean isValidPlayerName(String name) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (name.equals(p.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isTeamMember(Player p) {
		if (p.hasPermission(Game.getAdminPermission()) || p.hasPermission(Game.getModPermission())
				|| p.hasPermission(Game.getSupPermission())) {
			return true;
		} else {
			return false;
		}
	}

	public static void removeItem(Player p, Material mat) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			ItemStack itm = p.getInventory().getItem(i);
			if (itm != null && itm.getType().equals(mat)) {
				int amt = itm.getAmount() - 1;
				itm.setAmount(amt);
				p.getInventory().setItem(i, amt > 0 ? itm : null);
				p.updateInventory();
				break;
			}
		}
	}

	public static Boolean containsNewerItems(Player p) {
		// check if player has items in inventory which are 1.16.5 upwards
		return false;
	}

	public static String getUsername(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		return stats.getString("MCHype.Username");
	}

	/*
	 * public static String getUsernameFormatted(Player p) { File fstats = new
	 * File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml"); YamlConfiguration
	 * stats = YamlConfiguration.loadConfiguration(fstats); return
	 * stats.getString("MCHype.Username") + " §7(§e" + p.getName() + "§7)§r"; }
	 */

	public static String getUsernameFormatted(Player p) {
		return p.getName();
	}

	public static void setUsername(Player p, String username) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		stats.set("MCHype.Username", username);
		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeItem(Player p, Material mat, int amount) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			ItemStack itm = p.getInventory().getItem(i);
			if (itm != null && itm.getType().equals(mat)) {
				int amt = itm.getAmount() - amount;
				itm.setAmount(amt);
				p.getInventory().setItem(i, amt > 0 ? itm : null);
				p.updateInventory();
				break;
			}
		}
	}

	public static ItemStack createNamedItemStack(Material m, Integer amount, String name) {
		ItemStack i = new ItemStack(m, amount);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		return i;
	}

	public static ItemStack createNamedItemStack(Material m, Integer amount, String name, ArrayList<String> lore) {
		ItemStack i = new ItemStack(m, amount);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		i.setItemMeta(meta);
		return i;
	}

	public static Integer convertToInt(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public static Long convertToLong(String s) {
		long l = 0;
		try {
			l = Long.parseLong(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public static Double convertToDouble(String s) {
		Double d = 0.0D;
		try {
			d = Double.parseDouble(s.replace(",", "."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Boolean isValidDouble(String s) {
		Boolean b = true;
		try {
			Double.parseDouble(s.replace(",", "."));
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public static void setPrices() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());

		Chunk.price_buy = config.getDouble("Chunk.Price.Buy");
		Chunk.price_sell = config.getDouble("Chunk.Price.Sell");

		Shop.price_dirt_buy = config.getDouble("Shop.Price.Buy.Dirt");
		Shop.price_coarsedirt_buy = config.getDouble("Shop.Price.Buy.CoarseDirt");
		Shop.price_podzol_buy = config.getDouble("Shop.Price.Buy.Podzol");
		Shop.price_gras_buy = config.getDouble("Shop.Price.Buy.Grass");
		Shop.price_stone_buy = config.getDouble("Shop.Price.Buy.Stone");
		Shop.price_granit_buy = config.getDouble("Shop.Price.Buy.Granit");
		Shop.price_diorit_buy = config.getDouble("Shop.Price.Buy.Diorit");
		Shop.price_andesit_buy = config.getDouble("Shop.Price.Buy.Andesit");
		Shop.price_cobblestone_buy = config.getDouble("Shop.Price.Buy.Cobblestone");

		Shop.price_oak_buy = config.getDouble("Shop.Price.Buy.Oak");
		Shop.price_spruce_buy = config.getDouble("Shop.Price.Buy.Spruce");
		Shop.price_birch_buy = config.getDouble("Shop.Price.Buy.Birch");
		Shop.price_jungle_buy = config.getDouble("Shop.Price.Buy.Jungle");
		Shop.price_acacia_buy = config.getDouble("Shop.Price.Buy.Acacia");
		Shop.price_darkoak_buy = config.getDouble("Shop.Price.Buy.Darkoak");
		Shop.price_oakleaves_buy = config.getDouble("Shop.Price.Buy.Oakleaves");
		Shop.price_spruceleaves_buy = config.getDouble("Shop.Price.Buy.Spruceleaves");
		Shop.price_birchleaves_buy = config.getDouble("Shop.Price.Buy.Birchleaves");

		Shop.price_jungleleaves_buy = config.getDouble("Shop.Price.Buy.Jungleleaves");
		Shop.price_acacialeaves_buy = config.getDouble("Shop.Price.Buy.Acacialeaves");
		Shop.price_darkoakleaves_buy = config.getDouble("Shop.Price.Buy.Darkoakleaves");
		Shop.price_noteblock_buy = config.getDouble("Shop.Price.Buy.Noteblock");
		Shop.price_poweredrail_buy = config.getDouble("Shop.Price.Buy.Poweredrail");
		Shop.price_detectorrail_buy = config.getDouble("Shop.Price.Buy.Detectorrail");
		Shop.price_nametag_buy = config.getDouble("Shop.Price.Buy.Nametag");
		Shop.price_piston_buy = config.getDouble("Shop.Price.Buy.Piston");
		Shop.price_cobweb_buy = config.getDouble("Shop.Price.Buy.Cobweb");

		Shop.price_tnt_buy = config.getDouble("Shop.Price.Buy.Tnt");
		Shop.price_enchantingtable_buy = config.getDouble("Shop.Price.Buy.Enchantingtable");
		Shop.price_enderchest_buy = config.getDouble("Shop.Price.Buy.Enderechest");
		Shop.price_lilypad_buy = config.getDouble("Shop.Price.Buy.Lilypad");
		Shop.price_stonebricks_buy = config.getDouble("Shop.Price.Buy.Stonebricks");
		Shop.price_mossystonebricks_buy = config.getDouble("Shop.Price.Buy.Mossystonebricks");
		Shop.price_crackedstonebricks_buy = config.getDouble("Shop.Price.Buy.Crackedstonebricks");
		Shop.price_chiseledstonebricks_buy = config.getDouble("Shop.Price.Buy.Chiseledstonebricks");
		Shop.price_smoothstone_buy = config.getDouble("Shop.Price.Buy.Smoothstone");

		Shop.price_sand_buy = config.getDouble("Shop.Price.Buy.Sand");
		Shop.price_sandstone_buy = config.getDouble("Shop.Price.Buy.Sandstone");
		Shop.price_redsand_buy = config.getDouble("Shop.Price.Buy.Redsand");
		Shop.price_redsandstone_buy = config.getDouble("Shop.Price.Buy.Redsandstone");
		Shop.price_endstone_buy = config.getDouble("Shop.Price.Buy.Endstone");
		Shop.price_endstonebricks_buy = config.getDouble("Shop.Price.Buy.Enstonebricks");
		Shop.price_enderpearl_buy = config.getDouble("Shop.Price.Buy.Enderpearl");
		Shop.price_eyeofender_buy = config.getDouble("Shop.Price.Buy.Endereye");
		Shop.price_dragonhead_buy = config.getDouble("Shop.Price.Buy.Dragonhead");

		Shop.price_string_buy = config.getDouble("Shop.Price.Buy.String");
		Shop.price_feather_buy = config.getDouble("Shop.Price.Buy.Feather");
		Shop.price_book_buy = config.getDouble("Shop.Price.Buy.Book");
		Shop.price_gunpoweder_buy = config.getDouble("Shop.Price.Buy.Gunpowder");
		Shop.price_rabbithide_buy = config.getDouble("Shop.Price.Buy.Rabbithide");
		Shop.price_firework_buy = config.getDouble("Shop.Price.Buy.Firework");
		Shop.price_snowball_buy = config.getDouble("Shop.Price.Buy.Snowball");
		Shop.price_expbottle_buy = config.getDouble("Shop.Price.Buy.Expbottle");
		Shop.price_firecharge_buy = config.getDouble("Shop.Price.Buy.Firecharge");

		//
		// SELL
		//
		Shop.price_dirt_sell = config.getDouble("Shop.Price.Sell.Dirt");
		Shop.price_coarsedirt_sell = config.getDouble("Shop.Price.Sell.CoarseDirt");
		Shop.price_podzol_sell = config.getDouble("Shop.Price.Sell.Podzol");
		Shop.price_gras_sell = config.getDouble("Shop.Price.Sell.Grass");
		Shop.price_stone_sell = config.getDouble("Shop.Price.Sell.Stone");
		Shop.price_granit_sell = config.getDouble("Shop.Price.Sell.Granit");
		Shop.price_diorit_sell = config.getDouble("Shop.Price.Sell.Diorit");
		Shop.price_andesit_sell = config.getDouble("Shop.Price.Sell.Andesit");
		Shop.price_cobblestone_sell = config.getDouble("Shop.Price.Sell.Cobblestone");

		Shop.price_oak_sell = config.getDouble("Shop.Price.Sell.Oak");
		Shop.price_spruce_sell = config.getDouble("Shop.Price.Sell.Spruce");
		Shop.price_birch_sell = config.getDouble("Shop.Price.Sell.Birch");
		Shop.price_jungle_sell = config.getDouble("Shop.Price.Sell.Jungle");
		Shop.price_acacia_sell = config.getDouble("Shop.Price.Sell.Acacia");
		Shop.price_darkoak_sell = config.getDouble("Shop.Price.Sell.Darkoak");
		Shop.price_oakleaves_sell = config.getDouble("Shop.Price.Sell.Oakleaves");
		Shop.price_spruceleaves_sell = config.getDouble("Shop.Price.Sell.Spruceleaves");
		Shop.price_birchleaves_sell = config.getDouble("Shop.Price.Sell.Birchleaves");

		Shop.price_jungleleaves_sell = config.getDouble("Shop.Price.Sell.Jungleleaves");
		Shop.price_acacialeaves_sell = config.getDouble("Shop.Price.Sell.Acacialeaves");
		Shop.price_darkoakleaves_sell = config.getDouble("Shop.Price.Sell.Darkoakleaves");
		Shop.price_noteblock_sell = config.getDouble("Shop.Price.Sell.Noteblock");
		Shop.price_poweredrail_sell = config.getDouble("Shop.Price.Sell.Poweredrail");
		Shop.price_detectorrail_sell = config.getDouble("Shop.Price.Sell.Detectorrail");
		Shop.price_nametag_sell = config.getDouble("Shop.Price.Sell.Nametag");
		Shop.price_piston_sell = config.getDouble("Shop.Price.Sell.Piston");
		Shop.price_cobweb_sell = config.getDouble("Shop.Price.Sell.Cobweb");

		Shop.price_tnt_sell = config.getDouble("Shop.Price.Sell.Tnt");
		Shop.price_enchantingtable_sell = config.getDouble("Shop.Price.Sell.Enchantingtable");
		Shop.price_enderchest_sell = config.getDouble("Shop.Price.Sell.Enderechest");
		Shop.price_lilypad_sell = config.getDouble("Shop.Price.Sell.Lilypad");
		Shop.price_stonebricks_sell = config.getDouble("Shop.Price.Sell.Stonebricks");
		Shop.price_mossystonebricks_sell = config.getDouble("Shop.Price.Sell.Mossystonebricks");
		Shop.price_crackedstonebricks_sell = config.getDouble("Shop.Price.Sell.Crackedstonebricks");
		Shop.price_chiseledstonebricks_sell = config.getDouble("Shop.Price.Sell.Chiseledstonebricks");
		Shop.price_smoothstone_sell = config.getDouble("Shop.Price.Sell.Smoothstone");

		Shop.price_sand_sell = config.getDouble("Shop.Price.Sell.Sand");
		Shop.price_sandstone_sell = config.getDouble("Shop.Price.Sell.Sandstone");
		Shop.price_redsand_sell = config.getDouble("Shop.Price.Sell.Redsand");
		Shop.price_redsandstone_sell = config.getDouble("Shop.Price.Sell.Redsandstone");
		Shop.price_endstone_sell = config.getDouble("Shop.Price.Sell.Endstone");
		Shop.price_endstonebricks_sell = config.getDouble("Shop.Price.Sell.Enstonebricks");
		Shop.price_enderpearl_sell = config.getDouble("Shop.Price.Sell.Enderpearl");
		Shop.price_eyeofender_sell = config.getDouble("Shop.Price.Sell.Endereye");
		Shop.price_dragonhead_sell = config.getDouble("Shop.Price.Sell.Dragonhead");

		Shop.price_string_sell = config.getDouble("Shop.Price.Sell.String");
		Shop.price_feather_sell = config.getDouble("Shop.Price.Sell.Feather");
		Shop.price_book_sell = config.getDouble("Shop.Price.Sell.Book");
		Shop.price_gunpoweder_sell = config.getDouble("Shop.Price.Sell.Gunpowder");
		Shop.price_rabbithide_sell = config.getDouble("Shop.Price.Sell.Rabbithide");
		Shop.price_firework_sell = config.getDouble("Shop.Price.Sell.Firework");
		Shop.price_snowball_sell = config.getDouble("Shop.Price.Sell.Snowball");
		Shop.price_expbottle_sell = config.getDouble("Shop.Price.Sell.Expbottle");
		Shop.price_firecharge_sell = config.getDouble("Shop.Price.Sell.Firecharge");
	}

	public static Boolean isTool(ItemStack i) {
		if (i.getType() == Material.NETHERITE_PICKAXE) {
			return true;
		}
		return false;
	}

	public static Inventory getBoostInv() {
		Inventory inv = Bukkit.createInventory(null, 27, "§6Boost");
		// inv.setItem(2, Game.createNamedItemStack(Material.ANVIL, 1, "Tool
		// verbessern"));

		// Effekte
		inv.setItem(10, Game.createNamedItemStack(Material.RABBIT_FOOT, 1, "§aSprungkraft"));
		inv.setItem(13, Game.createNamedItemStack(Material.FEATHER, 1, "§bSchnelligkeit"));
		inv.setItem(16, Game.createNamedItemStack(Material.GOLDEN_APPLE, 1, "§cResistenz"));
		return inv;
	}
}