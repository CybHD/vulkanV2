package net.cybhd.vn.main;

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {

	public static final Enchantment CHUNK = new EnchantmentWrapper("chunk", "Chunk", 1);
	
	public static void register() {
		
	}
	
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered) {
			//send message to console
		}
	}
}