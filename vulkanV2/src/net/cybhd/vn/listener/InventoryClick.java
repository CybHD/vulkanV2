package net.cybhd.vn.listener;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import net.cybhd.vn.main.Auction;
import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class InventoryClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (Main.hue != null) {
			if (Main.hue.containsKey(p.getUniqueId())) {
				if (e.getSlotType() == SlotType.ARMOR) {
					e.setCancelled(true);
				}
			}
		}
		if (e.getView().getTitle().equals("§6Reparieren")) {
			if (e.getRawSlot() <= 8) {
				e.setCancelled(true);
				if (e.getRawSlot() == 4) {
					if (!(p.getInventory().getItemInMainHand().getType() == Material.AIR)) {
						if (Eco.get(p) >= Game.getPriceRepair()) {
							ItemStack i = p.getInventory().getItemInMainHand();
							Damageable meta = (Damageable) i.getItemMeta();
							meta.setDamage(0);
							i.setItemMeta((ItemMeta) meta);
							Eco.remove(p, Game.getPriceRepair());
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
							p.sendMessage("§6Du hast dein Item erfolgreich repariert");
						}
					}
				}
			}
		}
		if (e.getView().getTitle().equals("§6§lWerkbank")) {
			if (e.getRawSlot() <= 8) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() >= 36 && e.getRawSlot() <= 53) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() >= 13 && e.getRawSlot() <= 17) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() >= 31 && e.getRawSlot() <= 35) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() == 22 || e.getRawSlot() == 23) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() == 25 || e.getRawSlot() == 26) {
				e.setCancelled(true);
				return;
			}
			if (e.getRawSlot() == 9 || e.getRawSlot() == 18 || e.getRawSlot() == 27) {
				e.setCancelled(true);
				return;
			}
		}
		if (e.getView().getTitle().contains("§cReport")) {
			e.setCancelled(true);
		}
		if (e.getView().getTitle().equals("§6Arbeitsamt")) {
			e.setCancelled(true);
		}
		if (e.getView().getTitle().equals("§6Auktionshaus")) {
			File fauction = new File("plugins/vulkan/auction.yml");
			YamlConfiguration auction = YamlConfiguration.loadConfiguration(fauction);
			if (e.getClickedInventory() != null) {
				if (e.getRawSlot() <= 54) {
					if (e.getCurrentItem() != null) {
						ItemStack clickedItem = e.getCurrentItem();
						e.setCancelled(true);
						boolean b = true;
						for (String key : auction.getConfigurationSection("Items").getKeys(false)) {
							ItemStack item = auction.getItemStack("Items." + key + ".Itemstack");
							if (clickedItem.getType() == item.getType()) {
								if (clickedItem.getAmount() == item.getAmount()) {
									if (clickedItem.getEnchantments().equals(item.getEnchantments())) {
										String seller = auction.getString("Items." + key + ".Seller");
										Double price = auction.getDouble("Items." + key + ".Price");
										ItemMeta meta = clickedItem.getItemMeta();
										ArrayList<String> lore = (ArrayList<String>) meta.getLore();
										if (lore.get(0).contains(seller)
												&& lore.get(1).contains(String.valueOf(price))) {
											if (Eco.get(p) >= price) {
												if (b) {
													b = false;
													Eco.remove(p, price);
													Auction.removeItem(Integer.valueOf(key));
													p.sendMessage("§6Du hast §c" + item.getAmount() + " "
															+ item.getType().name() + " §6von §c" + seller + " §6für §c"
															+ price + " §6$ im Auktionshaus gekauft");
													p.getInventory().addItem(item);
													Eco.addOfflinePlayer(seller, price);
													Auction.updateInventory();
													if (Game.isValidPlayerName(seller)) {
														Player t = Bukkit.getPlayer(seller);
														t.sendMessage(p.getPlayerListName() + " §6hat §c"
																+ item.getAmount() + " " + item.getType().name()
																+ " §6für §c" + price + " §6$"
																+ " §6von dir aus dem Auktionshaus gekauft");
													}
												}
											} else {
												p.sendMessage("§6Du hast nicht genug Geld");
											}
										}
									}
								}
							}
						}
					}
				} else {
					e.setCancelled(true);
				}
			}
		}
	}
}
