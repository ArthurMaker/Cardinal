package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import de.Syranda.RPG.Plugin.Main;

public class EntityExplodeListener implements Listener{
	
	Main c;
	
	public EntityExplodeListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(EntityExplodeEvent event) {
		
		event.setCancelled(true);
		
		event.getLocation().getWorld().createExplosion(event.getLocation().getX(), event.getLocation().getY(), event.getLocation().getZ(), event.getYield(), false, false);
		
	}

}
