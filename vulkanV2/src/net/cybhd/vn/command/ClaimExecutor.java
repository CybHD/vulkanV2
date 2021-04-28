package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Chunk;
import net.cybhd.vn.main.Eco;

public class ClaimExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		// validate player world
		if (!p.getLocation().getWorld().getName().equals("world")
				&& !p.getLocation().getWorld().getName().equals("117")) {
			p.sendMessage("§cDu kannst diesen Befehl hier nicht ausführen");
			return true;
		}

		// check player eco
		if (!(Eco.get(p) >= Chunk.price_buy)) {
			p.sendMessage("§cDu hast nicht genug Geld um diesen Chunk zu claimen");
			return true;
		}

		File fchunks = new File("plugins/vulkan/CHUNKS.yml");
		YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
		if (!chunks.isSet(p.getLocation().getChunk().toString())) {
			chunks.set(p.getLocation().getChunk() + ".owner", p.getName());
			ArrayList<String> member = new ArrayList<String>();
			chunks.set(p.getLocation().getChunk() + ".member", member);
			try {
				chunks.save(fchunks);
				Eco.remove(p, Chunk.price_buy);
				p.sendMessage("§6Du hast diesen Chunk erfolgreich gekauft");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			p.sendMessage("§cDer Chunk wurde bereits gekauft");
		}
		return false;
	}

}
