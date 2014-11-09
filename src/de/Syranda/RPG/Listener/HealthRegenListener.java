package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import de.Syranda.RPG.Plugin.Main;

public class HealthRegenListener implements Listener{
	
	Main c;
	
	public HealthRegenListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}

	@EventHandler
	public void onEvent(EntityRegainHealthEvent event) {
		
		if(event.getRegainReason() != RegainReason.CUSTOM) event.setCancelled(true);
		
	}
	
}
