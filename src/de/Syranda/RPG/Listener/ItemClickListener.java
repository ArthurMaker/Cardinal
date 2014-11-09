package de.Syranda.RPG.Listener;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.Syranda.RPG.CustomClasses.DuelManager;
import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class ItemClickListener implements Listener{

	Main c;
	
	public ItemClickListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEvent(InventoryClickEvent event) {
				
		RPlayer rp = c.rPlayer.get(event.getWhoClicked().getUniqueId().toString());
		
		if(!(event.getWhoClicked() instanceof Player)) return;
		
		if(event.getInventory().getTitle().startsWith("§7Duel")) {
					
			event.setCancelled(true);
			
			if(event.getCurrentItem() == null) return;
			
			if(event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
				
				final DuelManager duel = c.duel.get(rp);
								
				for(Player ps:Bukkit.getOnlinePlayers()) {
					
					if(duel.getArena().isInArea(ps.getLocation()) && (ps != duel.getPlayerOne().getPlayer() || ps != duel.getPlayerTwo().getPlayer())) {
						
						ps.teleport(duel.getSpectatorLocation());
						ps.sendMessage("§6[RPG] §7You were teleport due to a duel progressing in your former location!");
						
					}
					
				}
				
				c.inDuell.add(duel.getPlayerOne().getPlayer().getName());
				c.inDuell.add(duel.getPlayerTwo().getPlayer().getName());
				
				c.usedArenas.add(duel.getArena());
				
				duel.getPlayerOne().setDuel(true);
				duel.getPlayerTwo().setDuel(true);
				
				event.getWhoClicked().closeInventory();
				
				duel.getPlayerOne().getPlayer().teleport(duel.getPlayerOneLocation());
				duel.getPlayerTwo().getPlayer().teleport(duel.getPlayerTwoLocation());
				
				duel.getArena().addTag("noin");
				duel.getArena().addTag("noout");
								
				final int i = Bukkit.getScheduler().runTaskTimer(c, new Runnable() {
					
					int time = 10;
					
					public void run() {
						
						IChatBaseComponent icb = ChatSerializer.a("{\"text\":\"§7The duel starts in §6" + time + " §7seconds\"}");
						
						if(time == 0) {
							
							icb = ChatSerializer.a("{\"text\":\"§6FIGHT\"}");
							
						}						
						
						PacketPlayOutChat packet = new PacketPlayOutChat(icb, 2);
						((CraftPlayer) duel.getPlayerOne().getPlayer()).getHandle().playerConnection.sendPacket(packet);
						((CraftPlayer) duel.getPlayerTwo().getPlayer()).getHandle().playerConnection.sendPacket(packet);
						time--;
						
					}				
					
				}, 0, 20).getTaskId();			
				
				Bukkit.getScheduler().runTaskLater(c, new Runnable() {
					
					public void run() {

						Bukkit.getScheduler().cancelTask(i);
						
					}
					
				}, 220);				
				
			} else if(event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
				
				event.getWhoClicked().closeInventory();
				
			}
			 
		}
		
		
		rp.calculateStats();
		/*if(rp.getPlayer().getInventory().getBoots() != null)*/ rp.setBoots(c.armor.get(rp.getPlayer().getInventory().getBoots()));
		/*if(rp.getPlayer().getInventory().getLeggings()  != null)*/ rp.setLeggings(c.armor.get(rp.getPlayer().getInventory().getLeggings()));
		/*if(rp.getPlayer().getInventory().getChestplate()  != null)*/ rp.setChestplate(c.armor.get(rp.getPlayer().getInventory().getChestplate()));
		/*if(rp.getPlayer().getInventory().getHelmet() != null)*/ rp.setHelmet(c.armor.get(rp.getPlayer().getInventory().getHelmet()));
		
		if(rp.getHelmet() != null) {
		
			if(rp.getHelmet().getStats().getLevel() > rp.getStats().getLevel()) {
	
				rp.getPlayer().getInventory().addItem(rp.getHelmet().getItem());
				rp.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
				rp.setHelmet(null);
				rp.getPlayer().sendMessage("§cYour level is too low to use this item!");
	
			}
		
		}
		
		if(rp.getChestplate() != null) {
		
			if(rp.getChestplate().getStats().getLevel() > rp.getStats().getLevel()) {
	
				rp.getPlayer().getInventory().addItem(rp.getChestplate().getItem());
				rp.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
				rp.setChestplate(null);
				rp.getPlayer().sendMessage("§cYour level is too low to use this item!");
	
			}
		
		}
		
		if(rp.getLeggings() != null) {
		
			if(rp.getLeggings().getStats().getLevel() >rp. getStats().getLevel()) {
	
				rp.getPlayer().getInventory().addItem(rp.getLeggings().getItem());
				rp.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
				rp.setLeggings(null);
				rp.getPlayer().sendMessage("§cYour level is too low to use this item!");
	
			}
		
		}
		
		if(rp.getBoots() != null) {
		
			if(rp.getBoots().getStats().getLevel() > rp.getStats().getLevel()) {
	
				rp.getPlayer().getInventory().addItem(rp.getBoots().getItem());
				rp.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
				rp.setBoots(null);
				rp.getPlayer().sendMessage("§cYour level is too low to use this item!");
	
			}
			
		}
		
		rp.calculateStats();
		
	}
	
}
