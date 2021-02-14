package net.cybhd.vn.main;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RainbowArmor {
	
    public ItemStack helmet(final int b, final int g, final int r) {
        final ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
        final ItemMeta meta = item.getItemMeta();
        final LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
        ((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
        item.setItemMeta((ItemMeta)lmeta);
        return item;
    }
    
    public ItemStack chestplate(final int b, final int g, final int r) {
        final ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        final ItemMeta meta = item.getItemMeta();
        final LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
        ((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
        item.setItemMeta((ItemMeta)lmeta);
        return item;
    }
    
    public ItemStack leggings(final int b, final int g, final int r) {
        final ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        final ItemMeta meta = item.getItemMeta();
        final LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
        ((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
        item.setItemMeta((ItemMeta)lmeta);
        return item;
    }
    
    public ItemStack boots(final int b, final int g, final int r) {
        final ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        final ItemMeta meta = item.getItemMeta();
        final LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
        ((LeatherArmorMeta)meta).setColor(Color.fromBGR(b, g, r));
        item.setItemMeta((ItemMeta)lmeta);
        return item;
    }
}
