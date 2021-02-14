package net.cybhd.vn.listener;

import java.io.File;

import org.bukkit.Chunk;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.cybhd.vn.main.Game;

public class PlayerChunkChange implements Listener {

	@EventHandler
	public void onChunkChange(PlayerMoveEvent e) {
		Chunk cF = e.getFrom().getChunk();
		Chunk cT = e.getTo().getChunk();
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals("world")) {
			if (cF != cT) {
				File fchunks = new File("plugins/vulkan/CHUNKS.yml");
				YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
				if (chunks.isSet(cT.toString())) {
					Game.sendActionBarMSG(p, "§cGekauft");
				} else {
					Game.sendActionBarMSG(p, "§aNoch Frei");
				}
			}
		}
	}

}
