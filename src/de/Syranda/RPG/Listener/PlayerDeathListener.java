package de.Syranda.RPG.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class PlayerDeathListener implements Listener{

	Main c;	
	
	public PlayerDeathListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(PlayerDeathEvent event) {
		
		event.setDroppedExp(0);
		event.setKeepLevel(true);
		event.setKeepInventory(true);
				
		RPlayer rp = c.rPlayer.get(event.getEntity().getPlayer().getUniqueId().toString());
		
		if(rp.isInDuel() && event.getEntity().getKiller() instanceof Player) {
		
			RPlayer rpp = c.rPlayer.get(event.getEntity().getKiller().getUniqueId().toString());
			
			c.duel.get(rp).getPlayerOne().getPlayer().sendMessage("§6" + event.getEntity().getKiller().getName() + " §7won the duel");
			c.duel.get(rp).getPlayerTwo().getPlayer().sendMessage("§6 " + event.getEntity().getKiller().getName() + " §7won the duel");
			c.duel.get(rp).getPlayerOne().setDuel(false);
			c.duel.get(rp).getPlayerTwo().setDuel(false);
			rp.getPlayer().teleport(c.duel.get(rp).getSpectatorLocation());
			rpp.getPlayer().teleport(c.duel.get(rp).getSpectatorLocation());
			
			c.duel.get(rp).getArena().removeTag("noin");
			c.duel.get(rp).getArena().removeTag("noout");
			
			//Remove duel variables from lists
			c.usedArenas.remove(c.duel.get(rp).getArena());
			c.duel.remove(rpp);
			c.duel.remove(rp);
			
		}
		
		event.setDeathMessage("");
		
	}
	
}
