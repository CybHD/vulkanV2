package net.cybhd.vn.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode implements Listener {

	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		if(e.getEntity().getType() == EntityType.CREEPER) {
			e.blockList().clear();
		}
		//|| e.getEntityType() == EntityType.PRIMED_TNT
	}
}
