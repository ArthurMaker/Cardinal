package de.Syranda.RPG.Listener;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.Syranda.RPG.Plugin.Main;

public class PlayerJoinListener implements Listener{
	
	Main c;
		
	public PlayerJoinListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
	
	}
	
	@EventHandler
	public void onEvent(PlayerJoinEvent event) throws SQLException {
		
		c.registerRPlayer(event.getPlayer());		
			
		event.getPlayer().setHealth(20.0);
		event.getPlayer().setFoodLevel(20);
		
		event.setJoinMessage("");
		
	}

}
