package net.cybhd.vn.listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import net.cybhd.vn.main.Game;

public class BackpackListener implements Listener {

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals("§6Rucksack")) {
			Player p = (Player) e.getPlayer();
			Inventory inv = e.getInventory();
			File fbackpack = new File("plugins/vulkan/BACKPACKS/" + p.getName() + ".yml");
			YamlConfiguration backpack = YamlConfiguration.loadConfiguration(fbackpack);
			if (p.hasPermission(Game.getSuPremPermission())) {
				for (int i = 0; i < 54; i++) {
					backpack.set(String.valueOf(i), inv.getItem(i));
				}
			} else if (p.hasPermission(Game.getPremPermission())) {
				for (int i = 0; i < 27; i++) {
					backpack.set(String.valueOf(i), inv.getItem(i));
				}
			}
			try {
				backpack.save(fbackpack);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
