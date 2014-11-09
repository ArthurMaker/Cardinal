package de.Syranda.RPG.CustomClasses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.ProtocolInjector.PacketTabHeader;

import com.mysql.jdbc.PreparedStatement;

import de.Syranda.RPG.Plugin.Main;

public class RPlayer {
	
	private Player player;
	private Stats stats;
	private Stats defaultStats;
	private boolean isGM;
	private Armor helmet;
	private Armor chestplate;
	private Armor leggings;
	private Armor boots;
	private Weapon weapon;
	private boolean isInDuel = false;	
	
	private List<Buff> buffs = new ArrayList<Buff>();
	
	public RPlayer(Player player, Stats stats, boolean isGM) {
		
		this.player = player;
		this.stats = stats;
		this.defaultStats = stats;
		this.isGM = isGM;
		
	}
	
	public Player getPlayer() {
		
		return this.player;
		
	}
	
	public Stats getStats() {
		
		return this.stats;
		
	}
	
	public void setStats(Stats stats) {
		
		this.stats = stats;
		
	}
	
	public boolean isGM() {
		
		return this.isGM;
		
	}
	
	public void applyBuff(Buff buff) {
		
		this.buffs.add(buff);
		
	}
	
	public void removeBuff(Buff buff) {
		
		this.buffs.remove(buff);
		
	}
	
	public boolean hasBuff(Buff buff) {
		
		if(buffs.contains(buff)) return true;
		else return false;
		
	}
	
	public List<Buff> getBuffs() {
		
		return this.buffs;
		
	}
	
	public void setHelmet(Armor armor) {
		
		this.helmet = armor;
		
	}
	
	public Armor getHelmet() {
		
		return this.helmet;
		
	}	
	
	public void setChestplate(Armor armor) {
		
		this.chestplate = armor;
		
	}
	
	public Armor getChestplate() {
		
		return this.chestplate;
		
	}
	
	public void setLeggings(Armor armor) {
		
		this.leggings = armor;
		
	}
	
	public Armor getLeggings() {
		
		return this.leggings;
		
	}
	
	public void setBoots(Armor armor) {
		
		this.boots = armor;
		
	}
	
	public Armor getBoots() {
		
		return this.boots;
		
	}
	
	public void setWeapon(Weapon weapon) {
		
		this.weapon = weapon;
		
	}
	
	public Weapon getWeapon() {
		
		return this.weapon;
		
	}
	
	public void saveData() throws SQLException {
		
		PreparedStatement ps = (PreparedStatement) Main.conn.prepareStatement("UPDATE `playerdata` SET maxHealth=" + getDefaultStats().getMaxHealth() + ", maxStamina=" + getStats().getMaxStamina() + ", luk=" + getDefaultStats().getLuk() + ", vit=" + getDefaultStats().getVit() + ",str=" + getDefaultStats().getStr() + ",armor=" + getDefaultStats().getArmor() + ",level=" + getStats().getLevel() + ",exp=" + getStats().getExp() + " WHERE uuid='" + getPlayer().getUniqueId().toString() + "'");
		ps.executeUpdate();
		
	}
	
	public Stats getDefaultStats() {
		
		return this.defaultStats;
		
	}
	public void setDefaultStats(Stats s) {
		
		this.defaultStats = s;
		
	}
	
	public boolean isInDuel() {
		
		return this.isInDuel;
		
	}
	
	public void setDuel(boolean duel) {
		
		this.isInDuel = duel;
		
	}
	
	public void calculateStats() {
				
		int luk = defaultStats.getLuk();
		int vit = defaultStats.getVit();
		int str = defaultStats.getStr();
		int armor = defaultStats.getArmor();
		
		if(getHelmet() != null) {
			
			luk += getHelmet().getStats().getLuk();
			vit += getHelmet().getStats().getVit();
			str += getHelmet().getStats().getStr();
			armor += getHelmet().getStats().getArmor();
			
		}
		
		if(getChestplate() != null) {
			
			luk += getChestplate().getStats().getLuk();
			vit += getChestplate().getStats().getVit();
			str += getChestplate().getStats().getStr();
			armor += getChestplate().getStats().getArmor();
			
		}
		
		if(getLeggings() != null) {
			
			luk += getLeggings().getStats().getLuk();
			vit += getLeggings().getStats().getVit();
			str += getLeggings().getStats().getStr();
			armor += getLeggings().getStats().getArmor();
			
		}
		
		if(getBoots() != null) {
			
			luk += getBoots().getStats().getLuk();
			vit += getBoots().getStats().getVit();
			str += getBoots().getStats().getStr();
			armor += getBoots().getStats().getArmor();
			
		}
		
		if(getWeapon() != null) {
			
			luk += getWeapon().getStats().getLuk();
			vit += getWeapon().getStats().getVit();
			str += getWeapon().getStats().getStr();
			armor += getWeapon().getStats().getArmor();
			
		}
		
		
		int maxHealth = defaultStats.getMaxHealth() + (defaultStats.getMaxHealth() / 100 * vit);
		
		Stats s = new Stats(maxHealth, getStats().getCurrenHealth(), 0, 0, luk, vit, str, armor, getStats().getLevel(), getStats().getExp(), getStats().getExpToLvl());
		
		this.stats = s;
			
	}
	
	public void challange(RPlayer rp, Area arena) {
		
		ItemStack is1 = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta im1 = is1.getItemMeta();
		im1.setDisplayName("§aAccept");
		im1.setLore(Arrays.asList(" ", "§7Enemy: §6" + rp.getPlayer().getName(), "§7Arena: §6" + arena.getName()));
		is1.setItemMeta(im1);
		
		Main.challangeInv.setItem(0, is1);
		
		getPlayer().openInventory(Main.challangeInv);
		
	}
	
	public void reloadTab() {
		
		IChatBaseComponent header = ChatSerializer.a("{\"text\":\"§6RPG §7v" + Bukkit.getPluginManager().getPlugin("RPG").getDescription().getVersion() + "\n\"}");
		IChatBaseComponent footer = ChatSerializer.a("{\"text\":\"\n§7Level: §6" + getStats().getLevel() + " §7| Exp: §6" + getStats().getExp() + "§7/§6" + getStats().getExpToLvl() + "\"}");
		
		PacketTabHeader packet = new PacketTabHeader(header, footer);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		
	}
	
}
