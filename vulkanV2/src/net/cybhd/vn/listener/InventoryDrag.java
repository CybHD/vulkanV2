package net.cybhd.vn.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDrag implements Listener {

	@EventHandler
	public void onDrag(InventoryDragEvent e) {
		if (e.getView().getTitle().equals("§6§lWerkbank")) {
			
		}
	}
}
