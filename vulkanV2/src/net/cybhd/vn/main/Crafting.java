package net.cybhd.vn.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Crafting {

	@SuppressWarnings("deprecation")
	public static void registerRecipe(String s) {
		switch (s) {
		case "Emerald_Sword":
			ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1, (short) 1);
			ItemMeta meta = item.getItemMeta();
			meta.setUnbreakable(true);
			meta.setDisplayName(ChatColor.GREEN + "Emerald Sword");
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
			item.setItemMeta(meta);
			item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);

			NamespacedKey key = new NamespacedKey(Main.getMain(), "emerald_sword");
			ShapedRecipe recipe = new ShapedRecipe(key, item);

			recipe.shape(" E ", " E ", " S ");
			recipe.setIngredient('E', Material.EMERALD_BLOCK);
			recipe.setIngredient('S', Material.BLAZE_ROD);

			Bukkit.addRecipe(recipe);
			break;
		/*
		case "EmeraldBlock1":
			list.add("§7Tier: §21");
			ItemStack emeraldblock1 = Game.createNamedItemStack(Material.EMERALD_BLOCK, 1, "§a§lSmaragdblock", list);

			NamespacedKey keyEBlock1 = new NamespacedKey(Main.getMain(), "emerald_block1");
			ShapedRecipe recipeEBlock1 = new ShapedRecipe(keyEBlock1, emeraldblock1);

			recipeEBlock1.shape("EEE", "EEE", "EEE");
			recipeEBlock1.setIngredient('E', Material.EMERALD_BLOCK);

			Bukkit.addRecipe(recipeEBlock1);
			list.clear();
			break;
	*/
		case "Flesh":
			ItemStack Leather = new ItemStack(Material.LEATHER);

			NamespacedKey keyFlesh = new NamespacedKey(Main.getMain(), "flesh_rotten");
			ShapedRecipe recipeFlesh = new ShapedRecipe(keyFlesh, Leather);

			recipeFlesh.shape("FF", "FF");
			recipeFlesh.setIngredient('F', Material.ROTTEN_FLESH);

			Bukkit.addRecipe(recipeFlesh);
			break;
		case "CoalOre":
			ItemStack CoalOre = new ItemStack(Material.COAL_ORE);

			NamespacedKey keyCoalOre = new NamespacedKey(Main.getMain(), "Ore_Coal");
			ShapedRecipe recipeCoalOre = new ShapedRecipe(keyCoalOre, CoalOre);

			recipeCoalOre.shape("SSS", "SOS", "SSS");
			recipeCoalOre.setIngredient('S', Material.STONE);
			recipeCoalOre.setIngredient('O', Material.COAL);

			Bukkit.addRecipe(recipeCoalOre);
			break;
		case "IronOre":
			ItemStack IronOre = new ItemStack(Material.IRON_ORE);

			NamespacedKey keyIronOre = new NamespacedKey(Main.getMain(), "Ore_Iron");
			ShapedRecipe recipeIronOre = new ShapedRecipe(keyIronOre, IronOre);

			recipeIronOre.shape("SSS", "SOS", "SSS");
			recipeIronOre.setIngredient('S', Material.STONE);
			recipeIronOre.setIngredient('O', Material.IRON_INGOT);

			Bukkit.addRecipe(recipeIronOre);
			break;
		case "GoldOre":
			ItemStack GoldOre = new ItemStack(Material.GOLD_ORE);

			NamespacedKey keyGoldOre = new NamespacedKey(Main.getMain(), "Ore_Gold");
			ShapedRecipe recipeGoldOre = new ShapedRecipe(keyGoldOre, GoldOre);

			recipeGoldOre.shape("SSS", "SOS", "SSS");
			recipeGoldOre.setIngredient('S', Material.STONE);
			recipeGoldOre.setIngredient('O', Material.GOLD_INGOT);

			Bukkit.addRecipe(recipeGoldOre);
			break;
		case "RedstoneOre":
			ItemStack RedstoneOre = new ItemStack(Material.REDSTONE_ORE);

			NamespacedKey keyRedstoneOre = new NamespacedKey(Main.getMain(), "Ore_Redstone");
			ShapedRecipe recipeRedstoneOre = new ShapedRecipe(keyRedstoneOre, RedstoneOre);

			recipeRedstoneOre.shape("SSS", "SOS", "SSS");
			recipeRedstoneOre.setIngredient('S', Material.STONE);
			recipeRedstoneOre.setIngredient('O', Material.REDSTONE);

			Bukkit.addRecipe(recipeRedstoneOre);
			break;
		case "LapisOre":
			ItemStack LapisOre = new ItemStack(Material.LAPIS_ORE);

			NamespacedKey keyLapisOre = new NamespacedKey(Main.getMain(), "Ore_Lapis");
			ShapedRecipe recipeLapisOre = new ShapedRecipe(keyLapisOre, LapisOre);

			recipeLapisOre.shape("SSS", "SOS", "SSS");
			recipeLapisOre.setIngredient('S', Material.STONE);
			recipeLapisOre.setIngredient('O', Material.LAPIS_LAZULI);

			Bukkit.addRecipe(recipeLapisOre);
			break;
		case "DiamondOre":
			ItemStack DiamondOre = new ItemStack(Material.DIAMOND_ORE);

			NamespacedKey keyDiamondOre = new NamespacedKey(Main.getMain(), "Ore_Diamond");
			ShapedRecipe recipeDiamondOre = new ShapedRecipe(keyDiamondOre, DiamondOre);

			recipeDiamondOre.shape("SSS", "SOS", "SSS");
			recipeDiamondOre.setIngredient('S', Material.STONE);
			recipeDiamondOre.setIngredient('O', Material.DIAMOND);

			Bukkit.addRecipe(recipeDiamondOre);
			break;
		case "EmeraldOre":
			ItemStack EmeraldOre = new ItemStack(Material.EMERALD_ORE);

			NamespacedKey keyEmeraldOre = new NamespacedKey(Main.getMain(), "Ore_Emerald");
			ShapedRecipe recipeEmeraldOre = new ShapedRecipe(keyEmeraldOre, EmeraldOre);

			recipeEmeraldOre.shape("SSS", "SOS", "SSS");
			recipeEmeraldOre.setIngredient('S', Material.STONE);
			recipeEmeraldOre.setIngredient('O', Material.EMERALD);

			Bukkit.addRecipe(recipeEmeraldOre);
			break;
			
		default:
			break;
		}
	}
}
