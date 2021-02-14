package net.cybhd.vn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.EntityPlayer;


public class PingExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§6Dein Ping: §c" + getPing(p) + " §6ms");
		}
		return false;
	}

	public static int getPing(Player p) {
		CraftPlayer pingcraft = (CraftPlayer) p;
		EntityPlayer pingentity = pingcraft.getHandle();
		return pingentity.ping;
	}
}
