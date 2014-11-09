package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

import de.Syranda.RPG.Plugin.Main;

public class ItemDurabilityChangeListener implements Listener{
	
	Main c;
	
	public ItemDurabilityChangeListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}
	
	@EventHandler
	public void onEvent(PlayerItemDamageEvent event) {
		
		event.setCancelled(true);
		
	}

}
