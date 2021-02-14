package net.cybhd.vn.main;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class Furnace {
	public static void ROTTEN_FLESH() {
		ItemStack result = new ItemStack(Material.COOKED_BEEF);
		@SuppressWarnings("deprecation")
		FurnaceRecipe recipe = new FurnaceRecipe(result, Material.ROTTEN_FLESH);
		recipe.setExperience(1.0F);
		Main.getMain().getServer().addRecipe((Recipe) recipe);
	}
}
