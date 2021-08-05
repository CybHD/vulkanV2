package net.cybhd.vn.job;

import java.io.File;
import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;

public class InventoryClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getView().getTitle().equals("§6§lArbeitsamt")) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			if (e.getRawSlot() == 18) {
				if (Job.getJob(p).equals("none")) {
					stats.set("Job.Name", "miner");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("§6Dein Job ist jetzt §cMiner");
				} else {
					p.closeInventory();
					p.sendMessage(
							"§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
				}
			}
			if (e.getRawSlot() == 20) {
				if (Job.getJob(p).equals("none")) {
					stats.set("Job.Name", "woodcutter");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("§6Dein Job ist jetzt §cHolzfäller");
				} else {
					p.closeInventory();
					p.sendMessage(
							"§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
				}
			}
			if (e.getRawSlot() == 22) {
				if (Job.getJob(p).equals("none")) {
					stats.set("Job.Name", "smith");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("§6Dein Job ist jetzt §cSchmied");
				} else {
					p.closeInventory();
					p.sendMessage(
							"§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
				}
			}
			if (e.getRawSlot() == 24) {
				if (Job.getJob(p).equals("none")) {
					stats.set("Job.Name", "fisher");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("§6Dein Job ist jetzt §cFischer");
				} else {
					p.closeInventory();
					p.sendMessage(
							"§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
				}
			}
			if (e.getRawSlot() == 26) {
				if (Job.getJob(p).equals("none")) {
					stats.set("Job.Name", "farmer");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("§6Dein Job ist jetzt §cFarmer");
				} else {
					p.closeInventory();
					p.sendMessage(
							"§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
				}
			}
			if (e.getRawSlot() == 53) {
				if (Job.getJob(p).equals("none")) {
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
					p.sendMessage("§cDu hast derzeit keinen Job den du Kündigen kannst");
					p.closeInventory();
				} else {
					stats.set("Job.Name", "none");
					try {
						stats.save(fstats);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.sendMessage("§6Du hast deinen Job erfolgreich gekündigt");
					p.closeInventory();
				}
			}
		} else if (e.getView().getTitle().equals("§6§lJob")) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			if (Job.getJob(p).equals("miner")) {
				if (e.getRawSlot() == 0) {
					// cobblestone
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_cobblestone_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_cobblestone_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 2) {
					// coal ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_coalore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_coalore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 4) {
					// copper ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_copperore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_copperore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 6) {
					// iron ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_ironore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_ironore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 8) {
					// gold ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_goldore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_goldore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 19) {
					// redstone ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_redstoneore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_redstoneore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 21) {
					// lapis ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_lapisore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_lapisore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 23) {
					// diamond ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_diamondore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_diamondore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 25) {
					// emerald ore
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Miner.XP", stats.getDouble("Job.Miner.XP") + Job.miner_emeraldore_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_miner_emeraldore_sell, true);
						Double xp = stats.getDouble("Job.Miner.XP");

						checkMinerLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				}
			} else if (Job.getJob(p).equals("woodcutter")) {
				if (e.getRawSlot() == 0) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP", stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_oaklog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_oaklog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 9) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_oakwood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_oakwood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 18) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_oaksapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_oaksapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 1) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_sprucelog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_sprucelog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 10) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_sprucewood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_sprucewood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 19) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_sprucesapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_sprucesapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 2) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_birchlog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_birchlog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 11) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_birchwood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_birchwood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 20) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_birchsapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_birchsapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 3) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_junglelog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_junglelog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 12) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_junglewood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_junglewood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 21) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_junglesapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_junglesapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 4) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_darkoaklog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_darkoaklog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 13) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_darkoakwood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_darkoakwood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 22) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_darkoaksapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_darkoaksapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 5) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_acacialog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_acacialog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 14) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_acaciawood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_acaciawood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 23) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_acaciasapling_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_acaciasapling_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 7) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_crimsonlog_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_crimsonlog_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 16) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_crimsonwood_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_crimsonwood_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				} else if (e.getRawSlot() == 25) {
					if (p.getInventory().contains(e.getCurrentItem().getType())) {
						Game.removeItem(p, e.getCurrentItem().getType());
						stats.set("Job.Woodcutter.XP",
								stats.getDouble("Job.Woodcutter.XP") + Job.woodcutter_crimsongrass_xp);
						try {
							stats.save(fstats);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Eco.add(p, Job.price_woodcutter_crimsongrass_sell, true);
						Double xp = stats.getDouble("Job.Woodcutter.XP");

						checkWoodcutterLevel(p, xp);
					} else {
						p.sendMessage("§cDu hast dieses Item nicht im Inventar");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
					}
				}
			} else if (Job.getJob(p).equals("smith")) {

			} else if (Job.getJob(p).equals("fisher")) {

			} else if (Job.getJob(p).equals("farmer")) {

			}
		}
	}

	public static void checkMinerLevel(Player p, Double xp) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (Job.getJobLevel(p, "miner") == 0) {
			if (xp >= 1000D) {
				stats.set("Job.Miner.XP", xp - 1000D);
				stats.set("Job.Miner.Level", 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Miner §cLevel 1");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "miner") == 1) {
			if (xp >= 2500D) {
				stats.set("Job.Miner.XP", xp - 2500D);
				stats.set("Job.Miner.Level", 2);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Miner §cLevel 2");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "miner") == 2) {
			if (xp >= 7500D) {
				stats.set("Job.Miner.XP", xp - 7500D);
				stats.set("Job.Miner.Level", 3);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Miner §cLevel 3");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "miner") == 3) {
			if (xp >= 15000D) {
				stats.set("Job.Miner.XP", xp - 15000D);
				stats.set("Job.Miner.Level", 4);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Miner §cLevel 4");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "miner") == 4) {
			if (xp >= 35000D) {
				stats.set("Job.Miner.XP", xp - 35000D);
				stats.set("Job.Miner.Level", 5);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Miner §cLevel 5");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		}
	}

	public static void checkWoodcutterLevel(Player p, Double xp) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (Job.getJobLevel(p, "woodcutter") == 0) {
			if (xp >= 1000D) {
				stats.set("Job.Woodcutter.XP", xp - 1000D);
				stats.set("Job.Woodcutter.Level", 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Holzfäller §cLevel 1");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "woodcutter") == 1) {
			if (xp >= 2500D) {
				stats.set("Job.Woodcutter.XP", xp - 2500D);
				stats.set("Job.Woodcutter.Level", 2);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Holzfäller §cLevel 2");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "woodcutter") == 2) {
			if (xp >= 7500D) {
				stats.set("Job.Woodcutter.XP", xp - 7500D);
				stats.set("Job.Woodcutter.Level", 3);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Holzfäller §cLevel 3");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "woodcutter") == 3) {
			if (xp >= 15000D) {
				stats.set("Job.Woodcutter.XP", xp - 15000D);
				stats.set("Job.Woodcutter.Level", 4);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Holzfäller §cLevel 4");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "woodcutter") == 4) {
			if (xp >= 35000D) {
				stats.set("Job.Woodcutter.XP", xp - 35000D);
				stats.set("Job.Woodcutter.Level", 5);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Holzfäller §cLevel 5");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		}
	}

	public static void checkSmithLevel(Player p, Double xp) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (Job.getJobLevel(p, "smith") == 0) {
			if (xp >= 1000D) {
				stats.set("Job.Smith.XP", xp - 1000D);
				stats.set("Job.Smith.Level", 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Schmied §cLevel 1");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "smith") == 1) {
			if (xp >= 2500D) {
				stats.set("Job.Smith.XP", xp - 2500D);
				stats.set("Job.Smith.Level", 2);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Schmied §cLevel 2");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "smith") == 2) {
			if (xp >= 7500D) {
				stats.set("Job.Smith.XP", xp - 7500D);
				stats.set("Job.Smith.Level", 3);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Schmied §cLevel 3");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "smith") == 3) {
			if (xp >= 15000D) {
				stats.set("Job.Smith.XP", xp - 15000D);
				stats.set("Job.Smith.Level", 4);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Schmied §cLevel 4");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "smith") == 4) {
			if (xp >= 35000D) {
				stats.set("Job.Smith.XP", xp - 35000D);
				stats.set("Job.Smith.Level", 5);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Schmied §cLevel 5");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		}
	}

	public static void checkFisherLevel(Player p, Double xp) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (Job.getJobLevel(p, "fisher") == 0) {
			if (xp >= 1000D) {
				stats.set("Job.Fisher.XP", xp - 1000D);
				stats.set("Job.Fisher.Level", 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 1");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "fisher") == 1) {
			if (xp >= 2500D) {
				stats.set("Job.Fisher.XP", xp - 2500D);
				stats.set("Job.Fisher.Level", 2);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 2");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "fisher") == 2) {
			if (xp >= 7500D) {
				stats.set("Job.Fisher.XP", xp - 7500D);
				stats.set("Job.Fisher.Level", 3);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 3");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "fisher") == 3) {
			if (xp >= 15000D) {
				stats.set("Job.Fisher.XP", xp - 15000D);
				stats.set("Job.Fisher.Level", 4);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 4");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "fisher") == 4) {
			if (xp >= 35000D) {
				stats.set("Job.Fisher.XP", xp - 35000D);
				stats.set("Job.Fisher.Level", 5);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 5");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		}
	}

	public static void checkFarmerLevel(Player p, Double xp) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
		if (Job.getJobLevel(p, "farmer") == 0) {
			if (xp >= 1000D) {
				stats.set("Job.Farmer.XP", xp - 1000D);
				stats.set("Job.Farmer.Level", 1);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 1");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "farmer") == 1) {
			if (xp >= 2500D) {
				stats.set("Job.Farmer.XP", xp - 2500D);
				stats.set("Job.Farmer.Level", 2);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 2");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "farmer") == 2) {
			if (xp >= 7500D) {
				stats.set("Job.Farmer.XP", xp - 7500D);
				stats.set("Job.Farmer.Level", 3);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 3");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "farmer") == 3) {
			if (xp >= 15000D) {
				stats.set("Job.Farmer.XP", xp - 15000D);
				stats.set("Job.Farmer.Level", 4);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 4");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		} else if (Job.getJobLevel(p, "farmer") == 4) {
			if (xp >= 35000D) {
				stats.set("Job.Farmer.XP", xp - 35000D);
				stats.set("Job.Farmer.Level", 5);
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage("§6Du bist jetzt ein Fischer §cLevel 5");
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
			}
		}
	}
}
