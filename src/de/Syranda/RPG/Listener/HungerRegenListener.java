package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import de.Syranda.RPG.Plugin.Main;

public class HungerRegenListener implements Listener{
	
	Main c;
	
	public HungerRegenListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}
	
	@EventHandler
	public void onEvent(FoodLevelChangeEvent event) {
		
		event.setCancelled(true);
		
	}

}
