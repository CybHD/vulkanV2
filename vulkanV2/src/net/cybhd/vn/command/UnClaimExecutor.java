package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Chunk;
import net.cybhd.vn.main.Eco;

public class UnClaimExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Location loc = p.getLocation();
		if (loc.getWorld().getName().equals("world")) {
			File fchunks = new File("plugins/vulkan/CHUNKS.yml");
			YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
			if(chunks.isSet(loc.getChunk().toString())) {
				if(chunks.getString(loc.getChunk().toString() + ".owner").equals(p.getName())) {
					chunks.set(loc.getChunk().toString(), null);
					try {
						chunks.save(fchunks);
						Eco.add(p, Chunk.price_sell);
						p.sendMessage("§6Du hast diesen Chunk erfolgreich für §c"+ Chunk.price_sell +" $ §6verkauft");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					p.sendMessage("§cDu bist nicht der Besitzer dieses Chunks");
				}
			} else {
				p.sendMessage("§cDieser Chunk gehört dir nicht");
			}
		} else {
			p.sendMessage("§cDu kannst diesen Befehl hier nicht ausführen");
		}
		return false;
	}
}
