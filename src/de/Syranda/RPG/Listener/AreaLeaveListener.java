package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.Syranda.RPG.CustomClasses.AreaLeaveEvent;
import de.Syranda.RPG.Plugin.Main;

public class AreaLeaveListener implements Listener{
	
	Main c;
	
	public AreaLeaveListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void enEvent(AreaLeaveEvent event) {
		
		if(event.getArea().hasTag("nodmg")) {
			
			c.noDmg.remove(event.getPlayer());
			
		}
		
		if(event.getArea().hasTag("noout")) {
			
			event.setCancelled(true);
			
		}
		
	}

}
