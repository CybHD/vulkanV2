package net.cybhd.vn.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetInv {

	@Deprecated
	public static Inventory abilitys(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§6Fähigkeiten");
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);

		ItemStack RGP = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemStack YGP = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		ItemStack GGP = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemStack BGP = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

		ItemMeta M_RGP = RGP.getItemMeta();
		ItemMeta M_YGP = YGP.getItemMeta();
		ItemMeta M_GGP = GGP.getItemMeta();
		ItemMeta M_BGP = BGP.getItemMeta();

		M_RGP.setDisplayName("§cZu teuer");
		M_YGP.setDisplayName("§eKaufen?");
		M_GGP.setDisplayName("§aIn Besitz");
		M_BGP.setDisplayName("§4Noch nicht verfügbar");

		RGP.setItemMeta(M_RGP);
		YGP.setItemMeta(M_YGP);
		GGP.setItemMeta(M_GGP);
		BGP.setItemMeta(M_BGP);

		inv.setItem(22, new ItemStack(Material.ARMOR_STAND));

		//

		if (stats.getInt(".health") == 0) {
			inv.setItem(3, RGP);
			inv.setItem(4, RGP);
			inv.setItem(5, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(13, YGP);
			} else {
				inv.setItem(13, RGP);
			}
		} else if (stats.getInt(".health") == 1) {
			inv.setItem(13, GGP);
			inv.setItem(4, RGP);
			inv.setItem(5, RGP);
			if (p.getLevel() >= 30) {
				inv.setItem(3, YGP);
			} else {
				inv.setItem(3, RGP);
			}
		} else if (stats.getInt(".health") == 2) {
			inv.setItem(13, GGP);
			inv.setItem(3, GGP);
			inv.setItem(5, RGP);
			if (p.getLevel() >= 40) {
				inv.setItem(4, YGP);
			} else {
				inv.setItem(4, RGP);
			}
		} else if (stats.getInt(".health") == 3) {
			inv.setItem(13, GGP);
			inv.setItem(3, GGP);
			inv.setItem(4, GGP);
			if (p.getLevel() >= 50) {
				inv.setItem(5, YGP);
			} else {
				inv.setItem(5, RGP);
			}
		} else if (stats.getInt(".health") == 4) {
			inv.setItem(13, GGP);
			inv.setItem(3, GGP);
			inv.setItem(4, GGP);
			inv.setItem(5, GGP);
		}

		//
		if (stats.getInt(".speed") == 0) {
			inv.setItem(10, RGP);
			inv.setItem(11, RGP);
			inv.setItem(19, RGP);
			inv.setItem(20, RGP);
			inv.setItem(1, RGP);
			inv.setItem(28, RGP);
			inv.setItem(29, RGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(21, YGP);
			} else {
				inv.setItem(21, RGP);
			}
		} else if (stats.getInt(".speed") == 1) {
			inv.setItem(10, RGP);
			inv.setItem(19, RGP);
			inv.setItem(20, RGP);
			inv.setItem(21, GGP);
			inv.setItem(1, RGP);
			inv.setItem(28, RGP);
			inv.setItem(29, RGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 25) {
				inv.setItem(11, YGP);
			} else {
				inv.setItem(11, RGP);
			}
		} else if (stats.getInt(".speed") == 2) {
			inv.setItem(10, RGP);
			inv.setItem(11, GGP);
			inv.setItem(19, RGP);
			inv.setItem(21, GGP);
			inv.setItem(1, RGP);
			inv.setItem(28, RGP);
			inv.setItem(29, RGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 25) {
				inv.setItem(20, YGP);
			} else {
				inv.setItem(20, RGP);
			}
		} else if (stats.getInt(".speed") == 3) {
			inv.setItem(10, RGP);
			inv.setItem(11, GGP);
			inv.setItem(19, RGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(1, RGP);
			inv.setItem(28, RGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 25) {
				inv.setItem(29, YGP);
			} else {
				inv.setItem(29, RGP);
			}
		} else if (stats.getInt(".speed") == 4) {
			inv.setItem(10, RGP);
			inv.setItem(19, RGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(11, GGP);
			inv.setItem(28, RGP);
			inv.setItem(29, GGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 30) {
				inv.setItem(1, YGP);
			} else {
				inv.setItem(1, RGP);
			}
		} else if (stats.getInt(".speed") == 5) {
			inv.setItem(1, GGP);
			inv.setItem(19, RGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(11, GGP);
			inv.setItem(28, RGP);
			inv.setItem(29, GGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 30) {
				inv.setItem(10, YGP);
			} else {
				inv.setItem(10, RGP);
			}
		} else if (stats.getInt(".speed") == 6) {
			inv.setItem(1, GGP);
			inv.setItem(10, GGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(11, GGP);
			inv.setItem(28, RGP);
			inv.setItem(29, GGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 30) {
				inv.setItem(19, YGP);
			} else {
				inv.setItem(19, RGP);
			}
		} else if (stats.getInt(".speed") == 7) {
			inv.setItem(1, GGP);
			inv.setItem(10, GGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(11, GGP);
			inv.setItem(19, GGP);
			inv.setItem(29, GGP);
			inv.setItem(37, RGP);
			if (p.getLevel() >= 30) {
				inv.setItem(28, YGP);
			} else {
				inv.setItem(28, RGP);
			}
		} else if (stats.getInt(".speed") == 8) {
			inv.setItem(1, GGP);
			inv.setItem(10, GGP);
			inv.setItem(11, GGP);
			inv.setItem(19, GGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(28, GGP);
			inv.setItem(29, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(37, YGP);
			} else {
				inv.setItem(37, RGP);
			}
		} else {
			inv.setItem(1, GGP);
			inv.setItem(10, GGP);
			inv.setItem(11, GGP);
			inv.setItem(19, GGP);
			inv.setItem(20, GGP);
			inv.setItem(21, GGP);
			inv.setItem(28, GGP);
			inv.setItem(29, GGP);
			inv.setItem(37, GGP);
		}
		
		if (stats.getInt(".jump") == 0) {
			inv.setItem(24, RGP);
			inv.setItem(25, RGP);
			inv.setItem(15, RGP);
			inv.setItem(33, RGP);
			inv.setItem(16, RGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(7, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(23, YGP);
			} else {
				inv.setItem(23, RGP);
			}
		} else if (stats.getInt(".jump") == 1) {
			inv.setItem(24, RGP);
			inv.setItem(25, RGP);
			inv.setItem(23, GGP);
			inv.setItem(33, RGP);
			inv.setItem(16, RGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(7, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(15, YGP);
			} else {
				inv.setItem(15, RGP);
			}
		} else if (stats.getInt(".jump") == 2) {
			inv.setItem(15, GGP);
			inv.setItem(25, RGP);
			inv.setItem(23, GGP);
			inv.setItem(33, RGP);
			inv.setItem(16, RGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(7, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(24, YGP);
			} else {
				inv.setItem(24, RGP);
			}
		} else if (stats.getInt(".jump") == 3) {
			inv.setItem(15, GGP);
			inv.setItem(25, RGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(16, RGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(7, RGP);
			if (p.getLevel() >= 20) {
				inv.setItem(33, YGP);
			} else {
				inv.setItem(33, RGP);
			}
		} else if (stats.getInt(".jump") == 4) {
			inv.setItem(15, GGP);
			inv.setItem(25, RGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(16, RGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(33, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(7, YGP);
			} else {
				inv.setItem(7, RGP);
			}
		} else if (stats.getInt(".jump") == 5) {
			inv.setItem(15, GGP);
			inv.setItem(25, RGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(7, GGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(33, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(16, YGP);
			} else {
				inv.setItem(16, RGP);
			}
		} else if (stats.getInt(".jump") == 6) {
			inv.setItem(15, GGP);
			inv.setItem(16, GGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(7, GGP);
			inv.setItem(34, RGP);
			inv.setItem(43, RGP);
			inv.setItem(33, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(25, YGP);
			} else {
				inv.setItem(25, RGP);
			}
		} else if (stats.getInt(".jump") == 7) {
			inv.setItem(15, GGP);
			inv.setItem(25, GGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(7, GGP);
			inv.setItem(16, GGP);
			inv.setItem(43, RGP);
			inv.setItem(33, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(34, YGP);
			} else {
				inv.setItem(34, RGP);
			}
		} else if (stats.getInt(".jump") == 8) {
			inv.setItem(15, GGP);
			inv.setItem(25, GGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(7, GGP);
			inv.setItem(34, GGP);
			inv.setItem(16, GGP);
			inv.setItem(33, GGP);
			if (p.getLevel() >= 30) {
				inv.setItem(43, YGP);
			} else {
				inv.setItem(43, RGP);
			}
		} else {
			inv.setItem(15, GGP);
			inv.setItem(25, GGP);
			inv.setItem(23, GGP);
			inv.setItem(24, GGP);
			inv.setItem(7, GGP);
			inv.setItem(34, GGP);
			inv.setItem(16, GGP);
			inv.setItem(33, GGP);
			inv.setItem(43, GGP);
		}
		

		if(stats.getInt(".health") == 2 && stats.getInt(".speed") == 1 && stats.getInt(".jump") == 1) {
			
			inv.setItem(31, YGP);
		} else {
			inv.setItem(31, BGP);
		}
		
		inv.setItem(39, BGP);
		inv.setItem(40, BGP);
		inv.setItem(41, BGP);

		inv.setItem(47, BGP);
		inv.setItem(48, BGP);
		inv.setItem(49, BGP);
		inv.setItem(50, BGP);
		inv.setItem(51, BGP);


		
		return inv;
	}
}
