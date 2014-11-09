package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class PlayerRespawnListener implements Listener{

	Main c;
	
	public PlayerRespawnListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
	
	}
	
	@EventHandler
	public void onEvent(PlayerRespawnEvent event) {
		
		event.setRespawnLocation(c.warps.get("spawn"));
		
		RPlayer rp = c.rPlayer.get(event.getPlayer().getUniqueId().toString());
		rp.calculateStats();
		rp.getStats().setCurrentHealth(c.rPlayer.get(event.getPlayer().getUniqueId().toString()).getStats().getMaxHealth());		
		
	}
	
}
