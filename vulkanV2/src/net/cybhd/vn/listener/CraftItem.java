package net.cybhd.vn.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftItem implements Listener {

	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if (e.getWhoClicked() instanceof Player) {
		}
	}
}