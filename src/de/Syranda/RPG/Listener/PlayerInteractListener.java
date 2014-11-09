package de.Syranda.RPG.Listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.Syranda.RPG.Plugin.Main;

public class PlayerInteractListener implements Listener{
	
	Main c;
	
	public PlayerInteractListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}	

	@EventHandler
	public void onEvent(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if(event.getPlayer().getItemInHand().getType() == Material.WOOD_AXE && event.getPlayer().hasPermission("RPG.Markpoint")) {
				
				event.setCancelled(true);
				c.x1.put(event.getPlayer(), event.getClickedBlock().getLocation());
				event.getPlayer().sendMessage("§6[RPG] §7Location 1 selected.");
				
			}
			
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(event.getPlayer().getItemInHand().getType() == Material.WOOD_AXE && event.getPlayer().hasPermission("RPG.Markpoint")) {
				
				event.setCancelled(true);
				c.x2.put(event.getPlayer(), event.getClickedBlock().getLocation());
				event.getPlayer().sendMessage("§6[RPG] §7Location 2 selected.");
				
			}
			
		}
		
	}
	
}
