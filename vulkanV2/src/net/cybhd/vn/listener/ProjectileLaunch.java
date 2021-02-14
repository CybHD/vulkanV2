package net.cybhd.vn.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileLaunch implements Listener {
	@EventHandler
	public void onProj(ProjectileLaunchEvent event) {
		if (event.getEntityType() != EntityType.ARROW)
			return;
		if (!(event.getEntity() instanceof Arrow))
			return;
		Arrow a = (Arrow) event.getEntity();
		a.setGravity(false);
	}
}