package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.Syranda.RPG.Plugin.Main;

public class PlayerLoginListener implements Listener{

	Main c;
	
	public PlayerLoginListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(PlayerLoginEvent event) {
		
		if(event.getResult() == Result.KICK_WHITELIST) {
			event.setKickMessage("§cThe server currently down for maintenance");
			return;
		}
		
	}
	
}
