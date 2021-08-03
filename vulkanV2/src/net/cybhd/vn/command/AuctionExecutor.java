package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.cybhd.vn.main.Auction;
import net.cybhd.vn.main.Game;

public class AuctionExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("sell")) { // /auction sell %price%
					if (Game.isValidDouble(args[1])) {
						if (!(p.getInventory().getItemInMainHand().getType() == Material.AIR)) {
							File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
							YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
							int itemsInAuction = stats.getInt("Auction.Items");
							if (itemsInAuction <= 4) {
								ItemStack i = p.getInventory().getItemInMainHand();
								Auction a = new Auction();
								if (Game.convertToDouble(args[1]) >= 0.01D) {
									a.addItem(p, i, Game.convertToDouble(args[1]), true);
								} else {
									p.sendMessage("�6Der geringste Preis f�r eine Auktion ist �c0.01 �6$");
									p.sendMessage("�6Dein Angebot wurde angepasst");
									a.addItem(p, i, 0.01D, true);
								}
								stats.set("Auction.Items", itemsInAuction + 1);
								try {
									stats.save(fstats);
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								p.sendMessage("�cDu darfst maximal 5 Items gleichzeitig in der Auktion haben");
							}
						} else {
							p.sendMessage("�6Halte das Item, das du verkaufen m�chtest in deiner Hand");
						}
					} else {
						p.sendMessage("�6" + args[1] + " �c $ ist kein g�ltiger Preis");
					}
				}
			} else if (args.length == 0) {
				p.openInventory(Auction.inv());
			}
		}
		return false;
	}

}
