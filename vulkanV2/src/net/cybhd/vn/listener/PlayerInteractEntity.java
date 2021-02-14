package net.cybhd.vn.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.cybhd.vn.main.Game;

public class PlayerInteractEntity implements Listener {

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Villager) {
			Player p = e.getPlayer();
			Villager v = (Villager) e.getRightClicked();
			if (v.getCustomName() != null) {
				if (v.getCustomName().equalsIgnoreCase("§6Arbeitsamt")) {
					Game.openWorkInv(p);
				}
			}
		}
	}
}
