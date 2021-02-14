package net.cybhd.vn.main;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

	public static void playSong(String name, Player p) {
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
	}
}
