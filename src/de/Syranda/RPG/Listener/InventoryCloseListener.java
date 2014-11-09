package de.Syranda.RPG.Listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class InventoryCloseListener implements Listener{

	Main c;
	
	public InventoryCloseListener(Main c) {
	
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler 
	public void onEvent(InventoryCloseEvent event) {
		
		RPlayer rp = c.rPlayer.get(event.getPlayer().getUniqueId().toString());
		
		if(event.getInventory().getTitle().startsWith("§7Duel") && !rp.isInDuel()) {
			
			c.duel.get(rp).getPlayerOne().getPlayer().sendMessage("§c" + rp.getPlayer().getName() + " declined the duel!");
			c.duel.remove(c.duel.get(rp).getPlayerOne());
			c.usedArenas.remove(c.duel.get(rp).getArena());
			c.duel.remove(rp);
			
			// TODO
			
		}
		
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
		rp.getPlayer().updateInventory();
		
	}
	
}
