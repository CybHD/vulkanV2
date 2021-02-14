package net.cybhd.vn.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlock implements Listener {

	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent e) {
		if (e.getEntity().getType() == EntityType.ENDERMAN) {
			e.setCancelled(true);
		}
	}
}
