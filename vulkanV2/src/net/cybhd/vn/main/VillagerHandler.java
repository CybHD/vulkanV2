package net.cybhd.vn.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.cybhd.vn.job.Job;

public class VillagerHandler implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission(Game.getAdminPermission())) {
			new JobVillager();
			p.sendMessage("§6Villager gespawned");
		}
		return false;
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof Villager)) {
			return;
		}
		Villager v = (Villager) e.getRightClicked();
		if (v.getCustomName().equals(CoinVillager.VILLAGER_NAME)) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
			YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);
			Long timeCollected = stats.getLong("Reward.Time");
			if (System.currentTimeMillis() > timeCollected + 86400000) {
				if (p.hasPermission(Game.getSuPremPermission())) {
					Eco.add(p, 800D, true);
					p.sendMessage("§6Du hast §c800 §6$ tägliche Belohnung erhalten");
				} else if (p.hasPermission(Game.getPremPermission())) {
					Eco.add(p, 450D, true);
					p.sendMessage("§6Du hast §c450 §6$ tägliche Belohnung erhalten");
				} else {
					Eco.add(p, 100D, true);
					p.sendMessage("§6Du hast §c100 §6$ tägliche Belohnung erhalten");
				}
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
				stats.set("Reward.Time", System.currentTimeMillis());
				try {
					stats.save(fstats);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				Long time = (timeCollected + 86400000) - System.currentTimeMillis();
				p.sendMessage("§6Du hast kürzlich deine tägliche Belohnung eingesammelt");
				p.sendMessage("§6Komm in §c" + Game.formatTime(time) + " §6wieder um die Belohnung abzuholen");
			}
		} else if (v.getCustomName().equals(JobVillager.VILLAGER_NAME)) {
			e.setCancelled(true);
			Player p = e.getPlayer();
			p.openInventory(Job.getArbeitsamtInv(p));
		}
	}
}
