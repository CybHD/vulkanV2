package net.cybhd.vn.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Auction {

	public static int pos = 0;

	@SuppressWarnings("unused")
	public static int getShopItemAmount() {
		int i = 0;
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		for (String key : auction.getConfigurationSection("Items").getKeys(false)) {
			i++;
		}
		return i;
	}

	public static int getNextNumber() {
		int i = 0;
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		try {
			for (String key : auction.getConfigurationSection("Items").getKeys(false)) { // returns auction number
				i = Game.convertToInt(key) + 1;
			}
		} catch (Exception e) {
		}
		return i;
	}

	public static int getHighestNumber() {
		int i = 0;
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		for (String key : auction.getConfigurationSection("Items").getKeys(false)) { // returns auction number
			i = Game.convertToInt(key);
		}
		return i;
	}

	public void addItem(Player p, ItemStack i, Double price, boolean b) {
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		int nextNumber = Auction.getNextNumber();
		if (!(i.getType() == Material.SPAWNER)) {
			if (price <= 10000) {
				auction.set("Items." + nextNumber + ".Itemstack", i);
				auction.set("Items." + nextNumber + ".Seller", p.getName());
				auction.set("Items." + nextNumber + ".Price", price);
				try {
					auction.save(fauction);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.getInventory().removeItem(i);
				Bukkit.broadcastMessage(p.getPlayerListName() + " §6hat §c" + i.getAmount() + " §c" + i.getType().name()
						+ " §6für §c" + price + " §6$ in das Auktionshaus gestellt");
				updateInventory();
				p.sendMessage("§6Du hast dein Item erfolgreich bei der Auktion eingestellt");
			} else {
				p.sendMessage("§6Der maximale Preis für einen Auktionsgegenstand beträgt §c10000 §6$");
			}
		} else {
			p.sendMessage("§6Du kannst keine Spawner ins Auktionshaus stellen");
		}
	}

	public static void removeItem(Integer position) {
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		auction.set("Items." + position, null);
		try {
			auction.save(fauction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateInventory();
	}

	public static Inventory inv() {
		Inventory inv = Bukkit.createInventory(null, 54, "§6Auktionshaus");
		File fauction = new File("plugins/vulkan/auction.yml");
		YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
		for (String key : auction.getConfigurationSection("Items").getKeys(false)) {
			if (auction.getItemStack("Items." + key + ".Itemstack") != null) {
				ItemStack item = new ItemStack(auction.getItemStack("Items." + key + ".Itemstack"));
				ItemMeta meta = item.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("§7Verkäufer:§c " + auction.getString("Items." + key + ".Seller"));
				lore.add("§7Preis:§c " + auction.getDouble("Items." + key + ".Price") + " §6$");
				meta.setLore(lore);
				item.setItemMeta(meta);
				// TODO set Item instead of add
				inv.setItem(pos, item);
				++pos;
			}
		}
		pos = 0;
		return inv;
	}

	public static void updateInventory() {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.getOpenInventory().getTitle().equals("§6Auktionshaus")) {
				ps.closeInventory();
				ps.openInventory(inv());
			}
		}
	}
}
