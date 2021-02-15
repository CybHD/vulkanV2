package net.cybhd.vn.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;

public class Shop implements Listener {

	public static double price_dirt_buy;
	public static double price_coarsedirt_buy;
	public static double price_podzol_buy;
	public static double price_gras_buy;
	public static double price_stone_buy;
	public static double price_granit_buy;
	public static double price_diorit_buy;
	public static double price_andesit_buy;
	public static double price_cobblestone_buy;

	public static double price_oak_buy;
	public static double price_spruce_buy;
	public static double price_birch_buy;
	public static double price_jungle_buy;
	public static double price_acacia_buy;
	public static double price_darkoak_buy;
	public static double price_oakleaves_buy;
	public static double price_spruceleaves_buy;
	public static double price_birchleaves_buy;

	public static double price_jungleleaves_buy;
	public static double price_acacialeaves_buy;
	public static double price_darkoakleaves_buy;
	public static double price_noteblock_buy;
	public static double price_poweredrail_buy;
	public static double price_detectorrail_buy;
	public static double price_nametag_buy;
	public static double price_piston_buy;
	public static double price_cobweb_buy;

	public static double price_tnt_buy;
	public static double price_enchantingtable_buy;
	public static double price_enderchest_buy;
	public static double price_lilypad_buy;
	public static double price_stonebricks_buy;
	public static double price_mossystonebricks_buy;
	public static double price_crackedstonebricks_buy;
	public static double price_chiseledstonebricks_buy;
	public static double price_smoothstone_buy;

	public static double price_sand_buy;
	public static double price_sandstone_buy;
	public static double price_redsand_buy;
	public static double price_redsandstone_buy;
	public static double price_endstone_buy;
	public static double price_endstonebricks_buy;
	public static double price_enderpearl_buy;
	public static double price_eyeofender_buy;
	public static double price_dragonhead_buy;

	public static double price_string_buy;
	public static double price_feather_buy;
	public static double price_book_buy;
	public static double price_gunpoweder_buy;
	public static double price_rabbithide_buy;
	public static double price_firework_buy;
	public static double price_snowball_buy;
	public static double price_expbottle_buy;
	public static double price_firecharge_buy;

	public static double price_dirt_sell;
	public static double price_coarsedirt_sell;
	public static double price_podzol_sell;
	public static double price_gras_sell;
	public static double price_stone_sell;
	public static double price_granit_sell;
	public static double price_diorit_sell;
	public static double price_andesit_sell;
	public static double price_cobblestone_sell;

	public static double price_oak_sell;
	public static double price_spruce_sell;
	public static double price_birch_sell;
	public static double price_jungle_sell;
	public static double price_acacia_sell;
	public static double price_darkoak_sell;
	public static double price_oakleaves_sell;
	public static double price_spruceleaves_sell;
	public static double price_birchleaves_sell;

	public static double price_jungleleaves_sell;
	public static double price_acacialeaves_sell;
	public static double price_darkoakleaves_sell;
	public static double price_noteblock_sell;
	public static double price_poweredrail_sell;
	public static double price_detectorrail_sell;
	public static double price_nametag_sell;
	public static double price_piston_sell;
	public static double price_cobweb_sell;

	public static double price_tnt_sell;
	public static double price_enchantingtable_sell;
	public static double price_enderchest_sell;
	public static double price_lilypad_sell;
	public static double price_stonebricks_sell;
	public static double price_mossystonebricks_sell;
	public static double price_crackedstonebricks_sell;
	public static double price_chiseledstonebricks_sell;
	public static double price_smoothstone_sell;

	public static double price_sand_sell;
	public static double price_sandstone_sell;
	public static double price_redsand_sell;
	public static double price_redsandstone_sell;
	public static double price_endstone_sell;
	public static double price_endstonebricks_sell;
	public static double price_enderpearl_sell;
	public static double price_eyeofender_sell;
	public static double price_dragonhead_sell;

	public static double price_string_sell;
	public static double price_feather_sell;
	public static double price_book_sell;
	public static double price_gunpoweder_sell;
	public static double price_rabbithide_sell;
	public static double price_firework_sell;
	public static double price_snowball_sell;
	public static double price_expbottle_sell;
	public static double price_firecharge_sell;

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getView().getTitle().equals("§6§lItem Shop")) {
			Game.setPrices();
			Player p = (Player) e.getWhoClicked();
			int slot = e.getRawSlot();
			if (slot <= 54) {
				if (e.getClick() == ClickType.LEFT) {
					if (!invFull(p)) {
						Eco.buy(slot, p);
					} else {
						p.sendMessage("§6Dein Inventar ist voll");
					}
				} else if (e.getClick() == ClickType.RIGHT) {
					if (e.getCurrentItem() != null) {
						if (p.getInventory().contains(e.getCurrentItem().getType())) {
							Eco.sell(slot, p);
						}
					}
				}
				e.setCancelled(true);
			}
		}
	}

	public boolean invFull(Player p) { // Funktioniert Gut!
		return p.getInventory().firstEmpty() == -1;
	}

	public static boolean isValidItem(ItemStack i) {
		if (i.getType() == Material.DIRT || i.getType() == Material.COARSE_DIRT || i.getType() == Material.PODZOL
				|| i.getType() == Material.GRASS_BLOCK || i.getType() == Material.STONE
				|| i.getType() == Material.GRANITE || i.getType() == Material.DIORITE
				|| i.getType() == Material.ANDESITE || i.getType() == Material.COBBLESTONE
				|| i.getType() == Material.OAK_LOG || i.getType() == Material.SPRUCE_LOG
				|| i.getType() == Material.BIRCH_LOG || i.getType() == Material.JUNGLE_LOG
				|| i.getType() == Material.ACACIA_LOG || i.getType() == Material.DARK_OAK_LOG
				|| i.getType() == Material.OAK_LEAVES || i.getType() == Material.SPRUCE_LEAVES
				|| i.getType() == Material.BIRCH_LEAVES || i.getType() == Material.JUNGLE_LEAVES
				|| i.getType() == Material.ACACIA_LEAVES || i.getType() == Material.DARK_OAK_LEAVES
				|| i.getType() == Material.NOTE_BLOCK || i.getType() == Material.POWERED_RAIL
				|| i.getType() == Material.DETECTOR_RAIL || i.getType() == Material.NAME_TAG
				|| i.getType() == Material.PISTON || i.getType() == Material.COBWEB || i.getType() == Material.TNT
				|| i.getType() == Material.ENCHANTING_TABLE || i.getType() == Material.ENDER_CHEST
				|| i.getType() == Material.LILY_PAD || i.getType() == Material.STONE_BRICKS
				|| i.getType() == Material.MOSSY_STONE_BRICKS || i.getType() == Material.CRACKED_STONE_BRICKS
				|| i.getType() == Material.CHISELED_STONE_BRICKS || i.getType() == Material.SMOOTH_STONE
				|| i.getType() == Material.SAND || i.getType() == Material.SANDSTONE || i.getType() == Material.RED_SAND
				|| i.getType() == Material.RED_SANDSTONE || i.getType() == Material.END_STONE
				|| i.getType() == Material.END_STONE_BRICKS || i.getType() == Material.ENDER_PEARL
				|| i.getType() == Material.ENDER_EYE || i.getType() == Material.DRAGON_HEAD
				|| i.getType() == Material.STRING || i.getType() == Material.FEATHER
				|| i.getType() == Material.WRITABLE_BOOK || i.getType() == Material.GUNPOWDER
				|| i.getType() == Material.RABBIT_FOOT || i.getType() == Material.FIREWORK_ROCKET
				|| i.getType() == Material.SNOWBALL || i.getType() == Material.EXPERIENCE_BOTTLE
				|| i.getType() == Material.FIRE_CHARGE) {
			return true;
		}
		return false;
	}
}
