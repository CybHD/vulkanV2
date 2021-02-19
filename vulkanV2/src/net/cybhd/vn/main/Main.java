package net.cybhd.vn.main;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scoreboard.Objective;

import net.cybhd.vn.ability.Ability;
import net.cybhd.vn.command.AbilitiesExecutor;
import net.cybhd.vn.command.AuctionExecutor;
import net.cybhd.vn.command.ClaimExecutor;
import net.cybhd.vn.command.ClanExecutor;
import net.cybhd.vn.command.CraftExecutor;
import net.cybhd.vn.command.EcoExecutor;
import net.cybhd.vn.command.HomeExecutor;
import net.cybhd.vn.command.HubExecutor;
import net.cybhd.vn.command.JobExecutor;
import net.cybhd.vn.command.ModeExecutor;
import net.cybhd.vn.command.MsgExecutor;
import net.cybhd.vn.command.PayExecutor;
import net.cybhd.vn.command.PingExecutor;
import net.cybhd.vn.command.PlotExecutor;
import net.cybhd.vn.command.RainbowExecutor;
import net.cybhd.vn.command.RepairExecutor;
import net.cybhd.vn.command.ReportExecutor;
import net.cybhd.vn.command.ReportsExecutor;
import net.cybhd.vn.command.SetHomeExecutor;
import net.cybhd.vn.command.ShopExecutor;
import net.cybhd.vn.command.StatsExecutor;
import net.cybhd.vn.command.TargetExecutor;
import net.cybhd.vn.command.TpAcceptExecutor;
import net.cybhd.vn.command.TpDenyExecutor;
import net.cybhd.vn.command.TpaExecutor;
import net.cybhd.vn.command.UnClaimExecutor;
import net.cybhd.vn.command.VulkanExecutor;
import net.cybhd.vn.command.WarpExecutor;
import net.cybhd.vn.listener.BlockBreak;
import net.cybhd.vn.listener.BlockBuild;
import net.cybhd.vn.listener.Chat;
import net.cybhd.vn.listener.CraftItem;
import net.cybhd.vn.listener.EntityChangeBlock;
import net.cybhd.vn.listener.EntityDamage;
import net.cybhd.vn.listener.EntityExplode;
import net.cybhd.vn.listener.EntityPortal;
import net.cybhd.vn.listener.EntitySpawn;
import net.cybhd.vn.listener.InventoryClick;
import net.cybhd.vn.listener.PlayerChunkChange;
import net.cybhd.vn.listener.PlayerDeath;
import net.cybhd.vn.listener.PlayerInteract;
import net.cybhd.vn.listener.PlayerInteractEntity;
import net.cybhd.vn.listener.PlayerJoin;
import net.cybhd.vn.listener.PlayerLogin;
import net.cybhd.vn.listener.PlayerPreLogin;
import net.cybhd.vn.listener.PlayerQuit;
import net.cybhd.vn.listener.PlayerRespawn;
import net.cybhd.vn.listener.PlayerPortal;
import net.cybhd.vn.listener.Shop;
import net.cybhd.vn.listener.WeatherChange;

public class Main extends JavaPlugin {

	public static Objective ob;
	public static HashMap<UUID, Integer> bed = new HashMap<UUID, Integer>();
	public static Main main;

	public static HashMap<UUID, Float> hue;

	@Override
	public void onEnable() {

		Messenger messenger = Bukkit.getMessenger();
		messenger.registerIncomingPluginChannel(this, "minecraft:brand", new BrandPluginMessageListener());
		messenger.registerOutgoingPluginChannel(this, "BungeeCord");

		Game.Startup();

		Bukkit.getPluginManager().registerEvents(new EntitySpawn(), this);
		Bukkit.getPluginManager().registerEvents(new CraftItem(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractEntity(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBuild(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPreLogin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		//Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChunkChange(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPortal(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
		//Bukkit.getPluginManager().registerEvents(new PlayerBedEnter(), this);
		//Bukkit.getPluginManager().registerEvents(new PlayerBedLeave(), this);
		Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
		Bukkit.getPluginManager().registerEvents(new Game(), this);
		Bukkit.getPluginManager().registerEvents(new Shop(), this);
		Bukkit.getPluginManager().registerEvents(new Chunk(), this);
		Bukkit.getPluginManager().registerEvents(new EntityExplode(), this);
		Bukkit.getPluginManager().registerEvents(new EntityPortal(), this);
		//Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new ReportsExecutor(), this);
		Bukkit.getPluginManager().registerEvents(new EntityChangeBlock(), this);
		// Bukkit.getPluginManager().registerEvents(new ProjectileHit(), this);
		Bukkit.getPluginManager().registerEvents(new Ability(), this);

		this.getCommand("l").setExecutor(new HubExecutor());
		this.getCommand("hub").setExecutor(new HubExecutor());
		this.getCommand("pay").setExecutor(new PayExecutor());
		this.getCommand("eco").setExecutor(new EcoExecutor());
		this.getCommand("tpa").setExecutor(new TpaExecutor());
		this.getCommand("msg").setExecutor(new MsgExecutor());
		this.getCommand("job").setExecutor(new JobExecutor());
		this.getCommand("warp").setExecutor(new WarpExecutor());
		this.getCommand("mode").setExecutor(new ModeExecutor());
		this.getCommand("ping").setExecutor(new PingExecutor());
		this.getCommand("shop").setExecutor(new ShopExecutor());
		this.getCommand("stats").setExecutor(new StatsExecutor());
		this.getCommand("craft").setExecutor(new CraftExecutor());
		this.getCommand("plot").setExecutor(new PlotExecutor());
		this.getCommand("home").setExecutor(new HomeExecutor());
		this.getCommand("lobby").setExecutor(new HubExecutor());
		this.getCommand("sethome").setExecutor(new SetHomeExecutor());
		this.getCommand("auction").setExecutor(new AuctionExecutor());
		this.getCommand("auktion").setExecutor(new AuctionExecutor());
		this.getCommand("rainbow").setExecutor(new RainbowExecutor());
		this.getCommand("tpaccept").setExecutor(new TpAcceptExecutor());
		this.getCommand("tpdeny").setExecutor(new TpDenyExecutor());
		this.getCommand("claim").setExecutor(new ClaimExecutor());
		this.getCommand("unclaim").setExecutor(new UnClaimExecutor());
		this.getCommand("target").setExecutor(new TargetExecutor());
		this.getCommand("report").setExecutor(new ReportExecutor());
		this.getCommand("vulkan").setExecutor(new VulkanExecutor());
		this.getCommand("vn").setExecutor(new VulkanExecutor());
		this.getCommand("repair").setExecutor(new RepairExecutor());
		// this.getCommand("login").setExecutor(new LoginExecutor());
		this.getCommand("reports").setExecutor(new ReportsExecutor());
		this.getCommand("abilitys").setExecutor(new AbilitiesExecutor());
		this.getCommand("abilities").setExecutor(new AbilitiesExecutor());
		this.getCommand("clan").setExecutor(new ClanExecutor());
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
	}

	@SuppressWarnings("unused")
	private void setArmor(Player p, float hue, float gradientSpeed) {
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

		org.bukkit.Color helmetColor = org.bukkit.Color.fromBGR(getRGB(hue).getRed(), getRGB(hue).getGreen(),
				getRGB(hue).getBlue());
		hue = handleColor(hue, gradientSpeed);
		org.bukkit.Color chestplateColor = org.bukkit.Color.fromBGR(getRGB(hue).getRed(), getRGB(hue).getGreen(),
				getRGB(hue).getBlue());
		hue = handleColor(hue, gradientSpeed);
		org.bukkit.Color leggingsColor = org.bukkit.Color.fromBGR(getRGB(hue).getRed(), getRGB(hue).getGreen(),
				getRGB(hue).getBlue());
		hue = handleColor(hue, gradientSpeed);
		org.bukkit.Color bootsColor = org.bukkit.Color.fromBGR(getRGB(hue).getRed(), getRGB(hue).getGreen(),
				getRGB(hue).getBlue());

		LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
		helmetMeta.setColor(helmetColor);
		helmet.setItemMeta(helmetMeta);

		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplateMeta.setColor(chestplateColor);
		chestplate.setItemMeta(chestplateMeta);

		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setColor(leggingsColor);
		leggings.setItemMeta(leggingsMeta);

		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(bootsColor);
		boots.setItemMeta(bootsMeta);

		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setBoots(boots);
	}

	private float handleColor(float hue, float speed) {
		hue += speed;
		if (hue > 1.0f) {
			hue = 0;
		}
		return hue;
	}

	private Color getRGB(float hue) {
		return Color.getHSBColor(hue, 1f, 1f);
	}

	@Override
	public void onDisable() {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			saveStats(ps);
		}
		System.out.println("Danke und bis bald");
	}

	public static void saveStats(Player p) {
		File fstats = new File("plugins/vulkan/PLAYERS/" + p.getName() + ".yml");
		YamlConfiguration stats = YamlConfiguration.loadConfiguration(fstats);

		stats.set(".timePlayed",
				stats.getLong(".timePlayed") + (System.currentTimeMillis() - stats.getLong(".timeJoined")));
		stats.set(".timeJoined", System.currentTimeMillis());

		stats.set(".blockBreak", stats.getInt(".blockBreak") + p.getMetadata("BlockBreak").get(0).asInt());
		p.setMetadata("BlockBreak", new FixedMetadataValue(Main.getMain(), 0));

		stats.set(".blockPlace", stats.getInt(".blockPlace") + p.getMetadata("BlockPlace").get(0).asInt());
		p.setMetadata("BlockPlace", new FixedMetadataValue(Main.getMain(), 0));

		try {
			stats.save(fstats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Main() {
		main = this;
	}

	public static Main getMain() {
		return main;
	}

	public static void connect(String server, Player p) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		p.sendPluginMessage(Main.getMain(), "BungeeCord", b.toByteArray());
	}

	public static Location getSpawnLoc() {
		Location spawnLOC = new Location(Bukkit.getWorld("spawn"), -2.5, 112, 0.5, -90, 0);
		return spawnLOC;
	}
	
//	OLD Spawn Loc
//	public static Location getSpawnLoc() {
//		Location spawnLOC = new Location(Bukkit.getWorld("spawn"), 607.5, 24, 116.5, -90, 0);
//		return spawnLOC;
//	}

	public static Location getSpawnLocByConfig() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Game.getConfigFile());
		String name = config.getString("Hub.World");
		Double x = config.getDouble("Hub.X");
		Double y = config.getDouble("Hub.Y");
		Double z = config.getDouble("Hub.Z");
		float yaw = (float) config.getDouble("Hub.Yaw");
		float pitch = (float) config.getDouble("Hub.Pitch");

		Location loc = new Location(Bukkit.getWorld(name), x, y, z, yaw, pitch);
		return loc;
	}

	public static Location getWorldLoc() {
		Location worldLOC = new Location(Bukkit.getWorld("world"), 104.5, 66, -6.5, 180, 0);
		return worldLOC;
	}
}
