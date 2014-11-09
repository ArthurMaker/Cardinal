package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.CustomClasses.Weapon;
import de.Syranda.RPG.Plugin.Main;

public class ChangeItemListener implements Listener{

	Main c;
	
	public ChangeItemListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(PlayerItemHeldEvent event) {
		
		RPlayer rp = c.rPlayer.get(event.getPlayer().getUniqueId().toString());
		
		if(c.weapons.containsKey(event.getPlayer().getInventory().getItem(event.getPreviousSlot()))) {
			
			rp.setWeapon(null);
			rp.calculateStats();
			
		}
		
		if(!c.weapons.containsKey(event.getPlayer().getInventory().getItem(event.getNewSlot()))) return;
		
		Weapon w = c.weapons.get(event.getPlayer().getInventory().getItem(event.getNewSlot()));
		
		if(w.getStats().getLevel() > rp.getStats().getLevel()) {
			
			event.getPlayer().sendMessage("§cYour level is too low to use this item!");
			event.setCancelled(true);
			return;
			
		} else {
			
			rp.setWeapon(w);
			rp.calculateStats();			
			
		}
		
		
	}
	
}
