package net.cybhd.vn.command;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ReportsExecutor implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("vk.mod")) {
		File frep = new File("plugins/vulkan/REPORTS.yml");
		YamlConfiguration rep = YamlConfiguration.loadConfiguration(frep);
		Inventory inv = Bukkit.createInventory(null, 27, "§cReport Liste");
		ArrayList<String> li1 = (ArrayList<String>) rep.getStringList("Hacking");
		ArrayList<String> li2 = (ArrayList<String>) rep.getStringList("Griefing");
		ArrayList<String> li3 = (ArrayList<String>) rep.getStringList("Betrug");
		for (int i = 0; i < li1.size(); i++) {
			String s = rep.getStringList("Hacking").get(i);
			inv.addItem(getPlayer(s, "§cHacking"));
		}
		for (int i = 0; i < li2.size(); i++) {
			String s = rep.getStringList("Griefing").get(i);
			inv.addItem(getPlayer(s, "§cGriefing"));
		}
		for (int i = 0; i < li3.size(); i++) {
			String s = rep.getStringList("Betrug").get(i);
			inv.addItem(getPlayer(s, "§cBetrug"));
		}
		p.openInventory(inv);
	} else {
		p.sendMessage("§cDu hast keine Rechte für diesen Befehl");
	}
		return false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getView().getTitle().equals("§cReport Liste")) {
			if (e.getRawSlot() <= 8) {
				e.setCancelled(true);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public ItemStack getPlayer(String name, String reason) {
		ItemStack i = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sMeta = (SkullMeta) i.getItemMeta();
		sMeta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
		i.setItemMeta(sMeta);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName("§6" + name);
		ArrayList<String> l = new ArrayList<String>();
		l.add(reason);
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

}
