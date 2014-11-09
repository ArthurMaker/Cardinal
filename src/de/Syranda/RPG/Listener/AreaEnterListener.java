package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.Syranda.RPG.CustomClasses.AreaEnterEvent;
import de.Syranda.RPG.Plugin.Main;

public class AreaEnterListener implements Listener{
	
	Main c;
	
	public AreaEnterListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}

	@EventHandler
	public void onEvent(AreaEnterEvent event) {
		
		if(event.getArea().hasTag("nodmg")) {
			
			c.noDmg.add(event.getPlayer());
			
		}
		
		if(event.getArea().hasTag("noin")) {
			
			event.setCancelled(true);
			
		}
		
	}
	
}
