package de.Syranda.RPG.Listener;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class PlayerLeaveListener implements Listener{
	
	Main c;
	
	public PlayerLeaveListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}

	@EventHandler
	public void onEvent(PlayerQuitEvent event) throws SQLException {
		
		RPlayer rp = c.rPlayer.get(event.getPlayer().getUniqueId().toString());
		
		try {
			rp.saveData();
		} catch(Exception e) {
			return;			
		}
		
		if(rp.isInDuel()) {
			
			rp.setDuel(false);
			
			RPlayer rpp;
			
			if(event.getPlayer() == c.duel.get(rp).getPlayerOne().getPlayer()) rpp = c.duel.get(rp).getPlayerTwo();
			else rpp = c.duel.get(rp).getPlayerOne();
			
			c.duel.get(rp).getPlayerOne().getPlayer().sendMessage("§6[RPG] " + rpp.getPlayer().getName() + " §7won the duel");
			c.duel.get(rp).getPlayerTwo().getPlayer().sendMessage("§6[RPG] " + rpp.getPlayer().getName() + " §7won the duel");
			c.duel.get(rp).getPlayerOne().setDuel(false);
			c.duel.get(rp).getPlayerTwo().setDuel(false);
			
			if(rp.getPlayer() != null) rp.getPlayer().teleport(c.duel.get(rp).getSpectatorLocation());
			if(rpp.getPlayer() != null) rpp.getPlayer().teleport(c.duel.get(rp).getSpectatorLocation());

			c.duel.get(rp).getArena().removeTag("noin");
			c.duel.get(rp).getArena().removeTag("noout");
			
			//Remove duel variables from lists
			c.usedArenas.remove(c.duel.get(rp).getArena());
			c.duel.remove(rpp);
			c.duel.remove(rp);
			
		}
		
		event.setQuitMessage("");
		
	}
	
}
