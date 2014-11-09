package de.Syranda.RPG.Listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.CustomClasses.AreaEnterEvent;
import de.Syranda.RPG.CustomClasses.AreaLeaveEvent;
import de.Syranda.RPG.CustomClasses.AreaMoveEvent;
import de.Syranda.RPG.Plugin.Main;

public class PlayerMoveListener implements Listener {
	
	Main c;
	
	public PlayerMoveListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}

	@EventHandler
	public void onEvent(PlayerMoveEvent event) {
		
		Location l1 = event.getFrom();
		Location l2 = event.getTo();
		
		for(Area a:c.areas) {
			
			if(!a.isInArea(l1) && a.isInArea(l2)) {
				
				AreaEnterEvent e = new AreaEnterEvent(event.getPlayer(), a);
				
				c.getServer().getPluginManager().callEvent(e);
				
				if(e.isCancelled()) {
					
					event.setTo(event.getFrom());
					
				}
				
			} else if(a.isInArea(l1) && !a.isInArea(l2)) {
			
				AreaLeaveEvent e = new AreaLeaveEvent(event.getPlayer(), a);
				
				c.getServer().getPluginManager().callEvent(e);
				
				if(e.isCancelled()) {
					
					event.setTo(event.getFrom());
					
				}
				
			} else if(a.isInArea(l1) && a.isInArea(l2)) {
								
				AreaMoveEvent e = new AreaMoveEvent(event.getPlayer(), a);
				
				c.getServer().getPluginManager().callEvent(e);
				
				if(e.isCancelled()) {
					
					event.setTo(event.getFrom());
					
				}
					
			}
			
		}
		
	}
	
}
