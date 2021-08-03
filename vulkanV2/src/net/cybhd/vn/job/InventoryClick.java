package net.cybhd.vn.job;

import java.io.File;
import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
					p.sendMessage("§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
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
					p.sendMessage("§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
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
					p.sendMessage("§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
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
					p.sendMessage("§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
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
					p.sendMessage("§cDu musst deinen derzeitigen Job erst Kündigen bevor du einen neuen anfangen kannst");
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
		}
	}
}
