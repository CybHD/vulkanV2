package net.cybhd.vn.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Clan;
import net.cybhd.vn.main.Eco;
import net.cybhd.vn.main.Game;
import net.cybhd.vn.main.Main;

public class ClanExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				String arg = args[0];
				String arg1 = args[1];
				if (arg.equalsIgnoreCase("create")) {
					if (!Clan.isMember(p)) {
						if (arg1.length() >= 4 && arg1.length() <= 8) {
							if (Eco.get(p) >= 2500) {
								Clan.create(p, arg1, System.currentTimeMillis());
								Eco.remove(p, 2500D);
							} else {
								p.sendMessage("§6Die Erstellung eines Clans kostet §c2500$");
							}
						} else {
							p.sendMessage("§6Der Clan Name muss zwischen §c4 §6und §c8 §6Zeichen lang sein");
						}
					} else {
						p.sendMessage("§cDu bist bereits in einem Clan");
					}
				} else if (arg.equalsIgnoreCase("invite")) {
					if ((!arg1.equalsIgnoreCase("accept")) && (!arg1.equalsIgnoreCase("deny"))) {
						if (Game.isValidPlayerName(arg1) && !(arg1.equalsIgnoreCase(p.getName()))) {
							if (Clan.isOwner(p, Clan.getClanName(p))) {
								Player t = Bukkit.getPlayer(arg1);
								Clan.invite(p, t);
								p.sendMessage("§6Du hast §c" + t.getName() + " §6in deinen Clan eingeladen");
							} else {
								p.sendMessage("§cDu bist derzeit in keinem Clan oder nicht der Owner des Clans");
							}
						} else {
							p.sendMessage("§6Der Spieler §c" + arg1 + " §6ist nicht online");
						}
					} else {
						if (arg1.equalsIgnoreCase("accept")) {
							if (p.hasMetadata("ClanInvite")) {
								String ClanName = p.getMetadata("ClanInvite").get(0).asString();
								Clan.addMember(p, ClanName);
								p.sendMessage(
										"§6Du bist erfolgreich dem Clan §c" + Clan.getClanName(p) + " §6beigetreten");
							} else {
								p.sendMessage("§6Du hast keine offenen Clan Einladungen");
							}
						} else if (arg1.equalsIgnoreCase("deny")) {
							if (p.hasMetadata("ClanInvite")) {
								p.sendMessage("§6Clan Einladung abgelehnt");
								p.removeMetadata("ClanInvite", Main.getMain());
							} else {
								p.sendMessage("§6Du hast keine offenen Clan Einladungen");
							}
						} else {
							p.sendMessage("§4Error");
						}
					}
				} else if (arg.equalsIgnoreCase("kick")) {
					if (Clan.isOwner(p, Clan.getClanName(p))) {
						if (Game.isValidPlayerName(arg1)) {
							Player t = Bukkit.getPlayer(arg1);
							if (Clan.isMember(t)) {
								Clan.removeMember(t, Clan.getClanName(p));
								p.sendMessage("§6Du hast " + t.getPlayerListName() + " §6aus dem Clan geworfen");
							} else {
								p.sendMessage("§6Der Spieler " + t.getPlayerListName() + " §6ist kein Member deines Clans");
							}
						} else {
							p.sendMessage("§6Der Spieler §c" + arg1 + " §6ist derzeit nicht online");
						}
					} else {
						p.sendMessage("§cDu bist derzeit in keinem Clan oder nicht der Owner des Clans");
					}
				}
			} else if (args.length == 1) {
				String arg = args[0];
				if (arg.equalsIgnoreCase("leave")) {
					Clan.leave(p);
				} else if (arg.equalsIgnoreCase("delete")) {
					// TODO Clan delete
				} else if (arg.equalsIgnoreCase("help")) {
					p.sendMessage("§6/clan create §c*NAME* §7- §6erstellt einen Clan");
					p.sendMessage("§6/clan leave §7- §6einen Clan verlassen");
					p.sendMessage("§6/clan invite §c*SPIELER* §7- §6lädt einen Spieler ein");
					p.sendMessage("§6/clan invite " + "§2accept" + " §7/ " + "§4deny");
					p.sendMessage("§6/clan kick §c*SPIELER* §7- §6kickt einen Spieler aus dem Clan");
				}
			} else if (args.length == 0) {
				if (Clan.isOwner(p, Clan.getClanName(p))) {
					p.sendMessage("§6Dein Clan: §c" + Clan.getClanName(p));
					p.sendMessage("§6Rang: §cOwner");
					p.sendMessage("§6Member: §c" + Clan.getMemberList(Clan.getClanName(p)));
				} else if (Clan.isMember(p)) {
					p.sendMessage("§6Dein Clan: §c" + Clan.getClanName(p));
					p.sendMessage("§6Rang: §cMember");
					p.sendMessage("§6Member: §c" + Clan.getMemberList(Clan.getClanName(p)));
				} else {
					p.sendMessage("§6Dein Clan: §cN/A");
					p.sendMessage("§6Rang: §cN/A");
					p.sendMessage("§6Member: §cN/A");
				}
			}
		}
		return false;
	}
}
