package net.cybhd.vn.ability;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ability implements Listener {

	@SuppressWarnings({ "deprecation", "unused" })
	public static void set(Player p) {
		File fab = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration ability = YamlConfiguration.loadConfiguration(fab);
		int health = ability.getInt("Ability.Health");
		int jump = ability.getInt("Ability.Jump");
		int speed = ability.getInt("Ability.Speed");
		int reg = ability.getInt("Ability.Reg");
		int fres = ability.getInt("Ability.Fres");

		switch (health) {
		case 1:
			p.setMaxHealth(24);
			break;
		case 2:
			p.setMaxHealth(28);
			break;
		case 3:
			p.setMaxHealth(32);
			break;
		case 4:
			p.setMaxHealth(26);
			break;
		case 5:
			p.setMaxHealth(40);
			break;
		default:
			break;
		}

		switch (jump) {
		case 1:
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 1, false, false));
			break;
		case 2:
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 2, false, false));
			break;
		case 3:
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 3, false, false));
			break;
		default:
			break;
		}
	}
	@SuppressWarnings("unused")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equals("§6§lFähigkeiten")) {
			if (e.getRawSlot() <= 8) {
				e.setCancelled(true);
			}
		}
	}
}
