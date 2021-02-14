package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class RainbowExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
			if (config.getStringList("Beta.Player").contains(p.getName())) {
				if (Main.hue.containsKey(p.getUniqueId())) {
					Main.hue.remove(p.getUniqueId());
					p.getInventory().setArmorContents(null);
					p.sendMessage("§6Du hast die Regenbogen Rüstung §cdeaktiviert");
				} else {
					Inventory inv = p.getInventory();
					if (inv.getItem(100) == null && inv.getItem(101) == null && inv.getItem(102) == null
							&& inv.getItem(103) == null) {
						Main.hue.put(p.getUniqueId(), 0.0F);
						p.sendMessage("§6Du hast die Regenbogen Rüstung §aaktiviert");
					} else {
						p.sendMessage("§6Deine Rüstungsslots müssen leer sein");
					}
				}
			} else {
				p.sendMessage("§6Nur Spieler die an der Beta teilgenommen haben, haben Zugang zu diesem Befehl");
			}
		}
		return false;
	}

}
