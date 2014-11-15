package de.Syranda.RPG.Plugin;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.CustomClasses.Armor;
import de.Syranda.RPG.CustomClasses.DropRule;
import de.Syranda.RPG.CustomClasses.DuelManager;
import de.Syranda.RPG.CustomClasses.Item;
import de.Syranda.RPG.CustomClasses.REntity;
import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.CustomClasses.Set;
import de.Syranda.RPG.CustomClasses.Stats;
import de.Syranda.RPG.CustomClasses.Weapon;
import de.Syranda.RPG.Listener.AreaEnterListener;
import de.Syranda.RPG.Listener.AreaLeaveListener;
import de.Syranda.RPG.Listener.BlockBreakListener;
import de.Syranda.RPG.Listener.ChangeItemListener;
import de.Syranda.RPG.Listener.EntityDamageByEntityListener;
import de.Syranda.RPG.Listener.EntityDamageListener;
import de.Syranda.RPG.Listener.EntityDeathListener;
import de.Syranda.RPG.Listener.EntityExplodeListener;
import de.Syranda.RPG.Listener.EntitySpawnListener;
import de.Syranda.RPG.Listener.HealthRegenListener;
import de.Syranda.RPG.Listener.HungerRegenListener;
import de.Syranda.RPG.Listener.InventoryCloseListener;
import de.Syranda.RPG.Listener.ItemClickListener;
import de.Syranda.RPG.Listener.ItemDurabilityChangeListener;
import de.Syranda.RPG.Listener.PlayerDeathListener;
import de.Syranda.RPG.Listener.PlayerExpGetListener;
import de.Syranda.RPG.Listener.PlayerInteractListener;
import de.Syranda.RPG.Listener.PlayerJoinListener;
import de.Syranda.RPG.Listener.PlayerLeaveListener;
import de.Syranda.RPG.Listener.PlayerMoveListener;
import de.Syranda.RPG.Listener.PlayerRespawnListener;
import de.Syranda.RPG.Listener.ServerListPingListener;
import de.Syranda.RPG.Util.Areas;
import de.Syranda.RPG.Util.Config;
import de.Syranda.RPG.Util.Warps;

public class Main extends JavaPlugin{
	
	//Areas
	public List<Area> areas = new ArrayList<Area>();
	
	/*
	   List for managing plugin intern calculations
	   rPlayer : UUID(.toString) - RPlayer class
	   rEntity : Entity ID - REntity class	   
	   
	 */
	public HashMap<String, RPlayer> rPlayer = new HashMap<String, RPlayer>();
	public HashMap<Integer, REntity> rEntity = new HashMap<Integer, REntity>();
	
	//Area system
	public HashMap<Player, Location> x1 = new HashMap<Player, Location>();
	public HashMap<Player, Location> x2 = new HashMap<Player, Location>();	
	public HashMap<Player, Area> selectedArea = new HashMap<Player, Area>();	
	
	//No damage list
	public List<Player> noDmg = new ArrayList<Player>();
	
	//Item / Set lists
	public List<Set> sets = new ArrayList<Set>();
	public HashMap<ItemStack, Item> items = new HashMap<ItemStack, Item>();
	public HashMap<ItemStack, Weapon> weapons = new HashMap<ItemStack, Weapon>();
	public HashMap<ItemStack, Armor> armor = new HashMap<ItemStack, Armor>();
	
	//Plugin configuration classes
	public Areas ar;
	public static Connection conn;
	public Warps w;
	
	//Level map
	public HashMap<Integer, Integer> levels = new HashMap<Integer, Integer>();		
	
	//Warp map
	public HashMap<String, Location> warps = new HashMap<String, Location>();
	
	//Drop rules
	public List<DropRule> dRules = new ArrayList<DropRule>();
	
	//Duel system
	public List<Area> usedArenas = new ArrayList<Area>();
	public List<String> inDuell = new ArrayList<String>();	
	public HashMap<RPlayer, DuelManager> duel = new HashMap<RPlayer, DuelManager>();
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
				
		new Config(this);
		ar = new Areas(this);
		w = new Warps(this);
		System.out.println("[Cardinal] Loaded " + areas.size() + " areas.");
		
		try {
			loadMySQL();
			System.out.println("[Cardinal] Connected to MySQL-Server!");
		} catch (SQLException e) {
			System.err.println("[Cardinal] Error connecting to MySQL-Server!");
		}
		
		showLevel();
		
		for(Player ps : Bukkit.getOnlinePlayers()) {
			
			try {
				registerRPlayer(ps);
			} catch (SQLException e) {
				
			}
			
		}
		
		new Executor(this);
		new PlayerInteractListener(this);
		
		if(!Customizer.EditMode) {
		
			new PlayerMoveListener(this);
			new PlayerJoinListener(this);
			new AreaEnterListener(this);
			new AreaLeaveListener(this);
			new EntityDamageListener(this);
			new EntityDamageByEntityListener(this);
			new EntitySpawnListener(this);
			new EntityDeathListener(this);
			new PlayerDeathListener(this);
			new PlayerRespawnListener(this);
			new PlayerExpGetListener(this);
			new PlayerLeaveListener(this);
			new BlockBreakListener(this);
			new ChangeItemListener(this);
			new ItemClickListener(this);
			new InventoryCloseListener(this);
			new ItemDurabilityChangeListener(this);
			new ServerListPingListener(this);
			new HealthRegenListener(this);
			new HungerRegenListener(this);
			new EntityExplodeListener(this);
			
		}
		
	}
	
	@Override
	public void onDisable() {

		for(RPlayer rp:rPlayer.values()) {
			
			try {
				rp.saveData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		for(World w:Bukkit.getWorlds()) {
			
			for(Entity e : w.getEntities()) {
				
				if(e instanceof LivingEntity && !(e instanceof Player)) ((LivingEntity) e).setHealth(0.0);
				
			}
			
		}
		
	}
	
	private void loadMySQL() throws SQLException {
		
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + Customizer.host + ":" + Customizer.port + "/" + Customizer.database, Customizer.user, Customizer.pass);
		
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "playerdata` (`id` INTEGER AUTO_INCREMENT, `uuid` varchar(100), `permbuffs` varchar(300), `isGM` BOOLEAN, `maxHealth` INTEGER, `maxStamina` INTEGER, `luk` INTEGER, `vit` INTEGER, `str` INTEGER, `armor` INTEGER, `level` INTEGER, `exp` INTEGER, `items` text, PRIMARY KEY(`id`))");
		ps.executeUpdate();
		
		Statement s = conn.createStatement();
		ResultSet rs = null;
		
		try {
			
			rs = s.executeQuery("SELECT * FROM `leveldata`");
		
		} catch(Exception e) {
		}
		
		if(rs == null) {
			
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "leveldata` (`level` INTEGER AUTO_INCREMENT, `exp` Integer, PRIMARY KEY(`level`))");
			ps2.executeUpdate();
			
			for(int i = 1; i < 100; i++) {
				
				int exp = (int) (i*10+Math.pow(i, 3));
				
				PreparedStatement ps3 = (PreparedStatement) conn.prepareStatement("INSERT INTO `" + Customizer.TablePrefix + "leveldata` (`exp`) VALUES (" + exp + ")");
				ps3.executeUpdate();
				
			}
			
		}
		
		PreparedStatement ps4 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "sets` (`id` INTEGER AUTO_INCREMENT, `name` varchar(50), `ItemIDs` varchar(100), PRIMARY KEY(`id`))");
		ps4.executeUpdate();
		
		PreparedStatement ps5 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "items` (`id` INTEGER AUTO_INCREMENT, `name` varchar(50), `Item` varchar(100), `Displayname` varchar(100), `Lore` varchar(100), `RunID` INTEGER, PRIMARY KEY(`id`))");
		ps5.executeUpdate();
		
		PreparedStatement ps6 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "weapons` (`id` INTEGER AUTO_INCREMENT, `luk` INTEGER, `vit` INTEGER, `str` INTEGER, `armor` INTEGER, `level` INTEGER, PRIMARY KEY(`id`))");
		ps6.executeUpdate();
		
		PreparedStatement ps7 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "armor` (`id` INTEGER AUTO_INCREMENT, `luk` INTEGER, `vit` INTEGER, `str` INTEGER, `armor` INTEGER, `level` INTEGER, PRIMARY KEY(`id`))");
		ps7.executeUpdate();
		
		Statement s2 = conn.createStatement();
		ResultSet rs2 = s2.executeQuery("SELECT * FROM `" + Customizer.TablePrefix + "items`");
		
		while(rs2.next()) {
			
			ItemStack item = new ItemStack(Material.valueOf(rs2.getString(3)));
			ItemMeta im = item.getItemMeta();
			im.setDisplayName(rs2.getString(4).replace('&', '§'));
			
			ArrayList<String> list = new ArrayList<String>();			
			String[] raw = rs2.getString(5).replace('&', '§').split("/n");
			
			for(String r:raw) {
				
				list.add(r);
				
			}
			
			im.setLore(list);
			item.setItemMeta(im);
			
			items.put(item, new Item(rs2.getInt(1), rs2.getString(2), item, rs2.getInt(6)));
			
		}
		
		System.out.println("[Cardinal] Loaded " + items.size() + " items.");
		
		Statement s3 = conn.createStatement();
		ResultSet rs3 = s3.executeQuery("SELECT * FROM `" + Customizer.TablePrefix + "weapons`");
		
		while(rs3.next()) {
			
			for(Item i:items.values()) {
				
				if(rs3.getInt(1) == i.getId()) {
					
					Stats stats = new Stats(0, 0, 0, 0, rs3.getInt(2), rs3.getInt(3), rs3.getInt(4), rs3.getInt(5), rs3.getInt(6), 0, 0);
					ItemStack itemStack = i.getItem();
					ItemMeta im = itemStack.getItemMeta();
					List<String> list = im.getLore();
					list.add(" ");
					list.add("§7Luk: +" + rs3.getInt(2));
					list.add("§7Vit: +" + rs3.getInt(3));
					list.add("§7Str: +" + rs3.getInt(4));
					list.add("§7Armor: +" + rs3.getInt(5));
					list.add(" ");
					list.add("§7Level: " + rs3.getInt(6));
					im.setLore(list);
					itemStack.setItemMeta(im);
					Weapon w = new Weapon(rs3.getInt(1), i.getName(), itemStack, i.getOnUse(), stats);
					weapons.put(itemStack, w);
					items.remove(itemStack);
					
				}
				
			}
			
		}
		
		System.out.println("[Cardinal] Loaded " + weapons.size() + " weapons.");
		
		Statement s4 = conn.createStatement();
		ResultSet rs4 = s4.executeQuery("SELECT * FROM `" + Customizer.TablePrefix + "armor`");
		
		while(rs4.next()) {
			
			for(Item i:items.values()) {
				
				if(rs4.getInt(1) == i.getId()) {
					
					Stats stats = new Stats(0, 0, 0, 0, rs4.getInt(2), rs4.getInt(3), rs4.getInt(4), rs4.getInt(5), rs4.getInt(6), 0, 0);
					ItemStack itemStack = i.getItem();
					ItemMeta im = itemStack.getItemMeta();
					List<String> list = im.getLore();
					list.add(" ");
					list.add("§7Luk: +" + rs4.getInt(2));
					list.add("§7Vit: +" + rs4.getInt(3));
					list.add("§7Str: +" + rs4.getInt(4));
					list.add("§7Armor: +" + rs4.getInt(5));
					list.add(" ");
					list.add("§7Level: " + rs4.getInt(6));
					im.setLore(list);
					itemStack.setItemMeta(im);
					Armor w = new Armor(rs4.getInt(1), i.getName(), itemStack, i.getOnUse(), stats);
					armor.put(itemStack, w);
					items.remove(itemStack);
					
				}
				
			}
			
		}
		
		System.out.println("[Cardinal] Loaded " + armor.size() + " armors.");
		
		Statement s5 = conn.createStatement();
		ResultSet rs5 = s5.executeQuery("SELECT * FROM `" + Customizer.TablePrefix + "sets`");
		
		while(rs5.next()) {
						
			List<ItemStack> items = new ArrayList<ItemStack>();
			String[] itemList = rs5.getString(3).split(":");
			
			for(String il : itemList) {
				
				for(Weapon w:weapons.values()) {
					
					if(w.getId() == Integer.valueOf(il)) items.add(w.getItem());
					
				}
				
				for(Armor w:armor.values()) {
					
					if(w.getId() == Integer.valueOf(il)) items.add(w.getItem());
					
				}
				
				for(Item w:this.items.values()) {
					
					if(w.getId() == Integer.valueOf(il) && !items.contains(w.getItem())) items.add(w.getItem());
					
				}
				
			}
			
			Set set = new Set(rs5.getInt(1), rs5.getString(2), items);		
			sets.add(set);
			
		}
		
		System.out.println("[Cardinal] Loaded " + sets.size() + " sets.");		
		
		PreparedStatement ps8 = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS `" + Customizer.TablePrefix + "drops` (`id` INTEGER AUTO_INCREMENT, `EntityType` varchar(30), `DropID` INTEGER, `levelCap` varchar(10), `chance` INTEGER, PRIMARY KEY(`id`))");
		ps8.executeUpdate();
		
		Statement s6 = conn.createStatement();
		ResultSet rs6 = s6.executeQuery("SELECT * FROM `" + Customizer.TablePrefix + "drops`");
		
		while(rs6.next()) {
					
			List<Item> itemDrops = new ArrayList<Item>();
			
			String[] drops = rs6.getString(3).split(":");
			
			for(String drop:drops) {
				
				int i = Integer.valueOf(drop);
				
				Item item = null;
				
				for(Item it:items.values()) {
					
					if(i == it.getId()) item = it;
					
				}
				
				if(item == null) continue;
			
				itemDrops.add(item);
				
			}
			
			dRules.add(new DropRule(rs6.getString(4), EntityType.valueOf(rs6.getString(2)), itemDrops, rs6.getInt(5)));
			
		}
		
	}
	
	private void showLevel() {
		
		Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
		
		for(int i = 1; i < 100; i++) {
			
			if(score.getTeam("lvl" + i) == null) {
				Team t = score.registerNewTeam("lvl" + i);
				t.setAllowFriendlyFire(true);
				t.setCanSeeFriendlyInvisibles(false);
				t.setPrefix("§7Lv. §6" + i + " §r");
			}
			
		}
		
		if(score.getTeam("gm") == null) {
			Team t = score.registerNewTeam("gm");
			t.setAllowFriendlyFire(true);
			t.setCanSeeFriendlyInvisibles(false);
			t.setPrefix("§6[GM] §r");
		}
		
	}
	
	public static Stats getStats(int level) {
		
		int x = 0;		
		for(int i = 1; i <= level; i++) {
			x += 10;		
		}
		int maxHealth = (100 + x*level) - (level*2);
		int str = level * 10;
		int armor = (level * 2) + level;
		int exp = level * 2 + 7;
		
		return new Stats(maxHealth, maxHealth, 0, 0, 0, 0, str, armor, level, exp, 0);
		
	}
	
	public static Stats lvlUp(int level, Stats s) {
		
		int maxHealth = s.getMaxHealth() + (10 * level);
		int vit = s.getVit() + (4 * level);
		int str = s.getStr() + (4 * level);
		int armor = s.getArmor() + (3 * level);
		
		return new Stats(maxHealth, maxHealth, 0, 0, s.getLuk(), vit, str, armor, s.getLevel(), s.getExp(), s.getExpToLvl());
		
	}

	public void registerRPlayer(Player p) throws SQLException {
		
		Statement s;
		
		try {
			
			s = Main.conn.createStatement();
			
		} catch(Exception e) {
			p.kickPlayer("§cMySQL-Error");
			return;
		}
 		
		ResultSet rs = s.executeQuery("SELECT * FROM `playerdata` WHERE uuid='" + p.getUniqueId() + "'");
		
		if(!rs.next()) {
			
			PreparedStatement ps = (PreparedStatement) Main.conn.prepareStatement("INSERT INTO `" + Customizer.TablePrefix + "playerdata` (`uuid`,`permBuffs`,`isGM`,`maxHealth`,`maxStamina`,`luk`,`vit`,`str`,`armor`,`level`,`exp`,`items`) VALUES ('" + p.getUniqueId() + "','',false,100,100,5,5,15,5,1,0,'1:2:3:4')");
			ps.executeUpdate();
			
			Statement s2 = Main.conn.createStatement();
			ResultSet rs2 = s2.executeQuery("SELECT exp FROM `leveldata` WHERE level=1");
			
			if(!rs2.next()) {
				p.kickPlayer("§cMySQL-Error");
				return;
			}
			
			Stats stats = new Stats(100, 100, 100, 100, 5, 5, 5, 5, 1, 0, rs2.getInt(1));
			RPlayer rp = new RPlayer(p, stats, false);
			rPlayer.put(p.getUniqueId().toString(), rp);
			if(!rp.isGM()) Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lvl1").addPlayer(p);
			else  Bukkit.getScoreboardManager().getMainScoreboard().getTeam("gm").addPlayer(p);
			p.setLevel(1);
			
			for(Set set:sets) {
				
				if(set.getName().equalsIgnoreCase(Customizer.StarterSet)) {
					
					for(ItemStack i:set.getItems()) {
						
						p.getInventory().addItem(i);
						
					}
					
				}
				
			}
			
			p.teleport(warps.get(Customizer.StarterSpawn));
			
			if(p.getInventory().getBoots() != null) {
				
				if(armor.containsKey(p.getInventory().getBoots())) {
					
					Armor a = armor.get(p.getInventory().getBoots());
					
					rp.setBoots(a);
					
				}
				
			}
			
			
			if(p.getInventory().getChestplate() != null) {
				
				if(armor.containsKey(p.getInventory().getChestplate())) {
					
					Armor a = armor.get(p.getInventory().getChestplate());
					
					rp.setChestplate(a);
					
				}
				
			}
			
			
			if(p.getInventory().getLeggings() != null) {
				
				if(armor.containsKey(p.getInventory().getLeggings())) {
					
					Armor a = armor.get(p.getInventory().getLeggings());
					
					rp.setLeggings(a);
					
				}
				
			}
			
			
			if(p.getInventory().getHelmet() != null) {
				
				if(armor.containsKey(p.getInventory().getHelmet())) {
					
					Armor a = armor.get(p.getInventory().getHelmet());
					
					rp.setHelmet(a);
					
				}
				
			}
			
			if(p.getItemInHand() != null) {
				
				if(weapons.containsKey(p.getItemInHand())) {
					
					Weapon w = weapons.get(p.getItemInHand());
					
					rp.setWeapon(w);
					
				}
				
			}
			
			rp.calculateStats();
			rp.getStats().setCurrentHealth(rp.getStats().getMaxHealth());
			
		} else {
			
			Statement s2 = Main.conn.createStatement();
			ResultSet rs2 = s2.executeQuery("SELECT exp FROM `leveldata` WHERE level=" + rs.getInt(11));
			
			if(!rs2.next()){
				p.kickPlayer("§cMySQL-Error");
				return;
			}
			
			Stats stats = new Stats(rs.getInt(5), rs.getInt(5), rs.getInt(6), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs2.getInt(1));
			RPlayer rp = new RPlayer(p, stats, rs.getBoolean(4));
			rPlayer.put(rs.getString(2), rp);
			if(!rp.isGM()) Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lvl" + rs.getInt(11)).addPlayer(p);
			else  Bukkit.getScoreboardManager().getMainScoreboard().getTeam("gm").addPlayer(p);
			p.setLevel(rs.getInt(11));
			
			float exp = 1F * rp.getStats().getExp() / rp.getStats().getExpToLvl();
			
			rp.getPlayer().setExp(exp);
			
			rp.reloadTab();
			
			if(p.getInventory().getBoots() != null) {
				
				if(armor.containsKey(p.getInventory().getBoots())) {
					
					Armor a = armor.get(p.getInventory().getBoots());
					
					rp.setBoots(a);
					
				}
				
			}
			
			
			if(p.getInventory().getChestplate() != null) {
				
				if(armor.containsKey(p.getInventory().getChestplate())) {
					
					Armor a = armor.get(p.getInventory().getChestplate());
					
					rp.setChestplate(a);
					
				}
				
			}
			
			
			if(p.getInventory().getLeggings() != null) {
				
				if(armor.containsKey(p.getInventory().getLeggings())) {
					
					Armor a = armor.get(p.getInventory().getLeggings());
					
					rp.setLeggings(a);
					
				}
				
			}
			
			
			if(p.getInventory().getHelmet() != null) {
				
				if(armor.containsKey(p.getInventory().getHelmet())) {
					
					Armor a = armor.get(p.getInventory().getHelmet());
					
					rp.setHelmet(a);
					
				}
				
			}
			
			if(p.getItemInHand() != null) {
				
				if(weapons.containsKey(p.getItemInHand())) {
					
					Weapon w = weapons.get(p.getItemInHand());
					
					rp.setWeapon(w);
					
				}
				
			}
		
			rp.calculateStats();
			rp.getStats().setCurrentHealth(rp.getStats().getMaxHealth());
			
		}
	}
	
	public static Inventory setUpChallangeInv(String player, String arena) {
		
		Inventory challangeInv = Bukkit.createInventory(null, 9, "§7Duel Request");
		
		ItemStack is2 = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta im2 = is2.getItemMeta();
		im2.setDisplayName("§cDecline");
		is2.setItemMeta(im2);
		
		challangeInv.setItem(8, is2);
		
		ItemStack is1 = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta im1 = is1.getItemMeta();
		im1.setDisplayName("§aAccept");
		im1.setLore(Arrays.asList(" ", "§7Enemy: §6" + player, "§7Arena: §6" + arena));
		is1.setItemMeta(im1);
		
		challangeInv.setItem(0, is1);
		
		return challangeInv;
		
	}
	
}
