package net.cybhd.vn.main;

import java.io.UnsupportedEncodingException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BrandPluginMessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] msg) {
        try {
        	String brand = new String(msg, "UTF-8").substring(1);
        	if(!brand.equalsIgnoreCase("mchype")) {
        		p.kickPlayer("§cDu brauchst den offiziellen MCHype Client um dem Server beizutreten. Dein Client: §6" + brand + " §cMehr Infos findest du im §6Discord§f");
        		Game.sendConsoleMSG("KICKED Player " + ChatColor.GOLD + p.getName() + ChatColor.DARK_RED + " REASON: Wrong Client" , ChatColor.DARK_RED);
        	}
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
