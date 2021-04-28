package net.cybhd.vn.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLogin implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replace("-", "");
		
		String url = "jdbc:mysql://mchype.net:3306/mchype?autoReconnect=true&useSSL=false";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(url, "mchype", "G4353Bo*/]]WaSz6");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE mc_uuid = " + uuid);
			if (rs.next() == false) {
				p.sendMessage("§6Dein Minecraft Account §c" + p.getName() + " §7(§c" + uuid + " §7) §c wurde noch nicht mit einem MCHype Account verbunden");
			} else {
				do {
					String mc_verified = rs.getString("mc_verified");
					// prüfen ob minecraft account bereits verifiziert ist
					if (!mc_verified.equals("true")) {
						e.disallow(Result.KICK_OTHER, "§6Erstelle dir auf §cMCHype.net §6einen Account um dem Server beitreten zu können");
					}
				} while (rs.next());
			}
			conn.close();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
