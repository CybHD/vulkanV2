package net.cybhd.vn.command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.cybhd.vn.main.Chunk;
import net.cybhd.vn.main.Game;

public class PlotExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Location loc = p.getLocation();
		if (loc.getWorld().getName().equals("world")) {
			File fchunks = new File("plugins/vulkan/CHUNKS.yml");
			YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
			if (args.length == 2) {
				if (Game.isValidPlayerName(args[1]) || args[1].equals("*")) {
					if (chunks.isSet(loc.getChunk().toString())) {
						if (args[0].equalsIgnoreCase("add")) {
							if (chunks.getString(loc.getChunk() + ".owner").equals(p.getName())) {
								ArrayList<String> member = (ArrayList<String>) chunks
										.getStringList(loc.getChunk().toString() + ".member");
								if (!member.contains(args[1])) {
									if (!chunks.getString(loc.getChunk() + ".owner").equalsIgnoreCase(args[1])) {
										member.add(args[1]);
										chunks.set(loc.getChunk() + ".member", member);
										try {
											chunks.save(fchunks);
											p.sendMessage("§6Der Spieler §c" + args[1]
													+ " §6hat nun Rechte für diesen Chunk");
										} catch (IOException e) {
											e.printStackTrace();
										}
									} else {
										p.sendMessage("§cDer Spieler" + args[1] + " §6ist bereits Owner");
									}
								} else {
									p.sendMessage("§6Der Spieler §c" + args[1] + " §6hat Rechte für diesen Chunk");
								}
							} else {
								p.sendMessage("§cDu bist nicht der Besitzer dieses Chunks");
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							if (chunks.getString(loc.getChunk() + ".owner").equals(p.getName())) {
								ArrayList<String> member = (ArrayList<String>) chunks
										.getStringList(loc.getChunk().toString() + ".member");
								if (args[1].equals("*")) {
									member.clear();
									chunks.set(loc.getChunk() + ".member", member);
									try {
										chunks.save(fchunks);
										p.sendMessage("§6Du hast alle Member von diesem Plot entfernt");
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else if (member.contains(args[1])) {
									member.remove(args[1]);
									chunks.set(loc.getChunk() + ".member", member);
									try {
										chunks.save(fchunks);
										p.sendMessage("§6Der Spieler §c" + args[1]
												+ " §6hat nun keine Rechte mehr für diesen Chunk");
									} catch (IOException e) {
										e.printStackTrace();
									}
								} else {
									p.sendMessage("§6Der Spieler §c" + args[1]
											+ " §6hat bereits keine Rechte für diesen Chunk");
								}
							} else {
								p.sendMessage("§cDu bist nicht der Besitzer dieses Chunks");
							}
						} else if (args[0].equalsIgnoreCase("setowner")) {
							if (chunks.getString(loc.getChunk() + ".owner").equals(p.getName())) {
								chunks.set(loc.getChunk() + ".owner", args[1]);
								try {
									chunks.save(fchunks);
									p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nun der Owner dieses Chunks");
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								p.sendMessage("§cDu bist nicht der Besitzer dieses Chunks");
							}
						}
					} else {
						p.sendMessage("§cDieser Chunk wurde noch nicht gekauft");
					}
				} else {
					p.sendMessage("§6Der Spieler §c" + args[1] + " §6ist nicht online");
				}
			} else if (args.length == 1) {
				PlotHelp(p);
			} else if (args.length == 0) {
				PlotInfo(p);
			}
		} else {
			p.sendMessage("§cDu kannst diesen Befehl hier nicht ausführen");
		}
		return false;
	}

	private void PlotInfo(Player p) {
		Location loc = p.getLocation();
		File fchunks = new File("plugins/vulkan/CHUNKS.yml");
		YamlConfiguration chunks = YamlConfiguration.loadConfiguration(fchunks);
		if (chunks.isSet(loc.getChunk().toString())) {
			p.sendMessage("§6Der Owner dieses Chunks ist: §c" + chunks.getString(loc.getChunk().toString() + ".owner"));
			p.sendMessage("§6Die Member dieses Chunks sind: §c"
					+ chunks.getStringList(loc.getChunk().toString() + ".member"));
		} else {
			p.sendMessage("§6Dieser Chunk wurde noch nicht gekauft");
			p.sendMessage("§c/claim §7- §6um diesen Chunk für §c" + Chunk.price_buy + " §6$ zu kaufen");
		}
	}

	private void PlotHelp(Player p) {
		p.sendMessage("§c/plot §7- §6zeigt allgemeine Infos zum Chunk an");
		p.sendMessage("§c/plot setOwner %SpielerName% §7- §6setzt den Besitzer des Chunks");
		p.sendMessage("§c/plot add %SpielerName% §7- §6gibt einem Spieler Rechte für diesen Chunk");
		p.sendMessage("§c/plot remove %SpielerName% §7- §6entzieht einem Spieler die Rechte für diesen Chunk");
	}
}
