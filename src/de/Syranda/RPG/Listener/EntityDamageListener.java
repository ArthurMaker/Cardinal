package de.Syranda.RPG.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class EntityDamageListener implements Listener{
	
	Main c;
	
	public EntityDamageListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(EntityDamageEvent event) {
		
		if(event.getCause() == DamageCause.ENTITY_EXPLOSION && event.getEntity() instanceof Player) {
			
			//TODO
			
			RPlayer rp = c.rPlayer.get(((Player) event.getEntity()).getUniqueId().toString());	
			int armor = rp.getStats().getArmor();
			
			int damage = 200 - armor;
			if(damage < 0) damage = 0;
			
			rp.getStats().setCurrentHealth(rp.getStats().getCurrenHealth() - damage);
			float dmg = 1F * rp.getStats().getCurrenHealth() / rp.getStats().getMaxHealth();
			if(dmg < 0) dmg = 0;			
			((Player) event.getEntity()).setHealth(dmg * 20.0);
			event.setDamage(0.0);
			
			return;
			
		} else if(event.getCause() == DamageCause.ENTITY_EXPLOSION) {
			
			event.setCancelled(true);
			return;
			
		}
		
		if(event.getCause() == DamageCause.PROJECTILE && event.getCause() == DamageCause.CONTACT || event.getCause() == DamageCause.FALL) {
			event.setCancelled(true);
			return;
		}
				
	}

}
