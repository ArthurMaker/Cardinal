package de.Syranda.RPG.Listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.Syranda.RPG.CustomClasses.DuelManager;
import de.Syranda.RPG.CustomClasses.REntity;
import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class EntityDamageByEntityListener implements Listener{
	
	Main c;
	
	public EntityDamageByEntityListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
			
	}

	@EventHandler
	public void onEvent(EntityDamageByEntityEvent event) {
		
		if(!(event.getEntity() instanceof LivingEntity)) return;
		
		if(event.getEntity() instanceof Villager) return;
		
		if(event.getEntity() == null || event.getDamager() == null) {
			event.setCancelled(true);
			return;			
		}
		
		if(event.getCause() == DamageCause.ENTITY_EXPLOSION || event.getCause() == DamageCause.BLOCK_EXPLOSION) {
			event.setCancelled(true);			
			return;
		}
		
		if(event.getEntity() instanceof Player && !(event.getDamager() instanceof Player)) {
			
			RPlayer rp = c.rPlayer.get(((Player) event.getEntity()).getUniqueId().toString());	
			int armor = rp.getStats().getArmor();
			REntity re = c.rEntity.get(event.getDamager().getEntityId());
			int str = re.getStats().getStr();
			
			int damage = str - armor;
			if(damage < 0) damage = 0;
			
			rp.getStats().setCurrentHealth(rp.getStats().getCurrenHealth() - damage);
			float dmg = 1F * rp.getStats().getCurrenHealth() / rp.getStats().getMaxHealth();
			if(dmg < 0) dmg = 0;			
			rp.getPlayer().setHealth(dmg * 20.0);
			event.setDamage(0.0);
			
			
		} else if(!(event.getEntity() instanceof Player) && event.getDamager() instanceof Player) {
			
			event.setDamage(0.0);
			
			RPlayer rp = c.rPlayer.get(((Player) event.getDamager()).getUniqueId().toString());	
			int str = rp.getStats().getStr();
			REntity re = c.rEntity.get(event.getEntity().getEntityId());
			int armor = re.getStats().getArmor();
			
			int damage = str - armor;
			if(damage < 0) damage = 0;
			
			re.getStats().setCurrentHealth(re.getStats().getCurrenHealth() - damage);
			
			if(re.getStats().getCurrenHealth() <= 0) event.setDamage(2000.0);
			
			((LivingEntity) re.getEntity()).setCustomName("§7Lv. §6" + re.getStats().getLevel() + " §r" + re.getName() + " §c" + re.getStats().getCurrenHealth() + "/" + re.getStats().getMaxHealth());
			
		} else if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			
			DuelManager dm = c.duel.get(c.rPlayer.get(((Player) event.getEntity()).getUniqueId().toString()));
			
			if(dm == null) {
				
				event.setCancelled(true);
				return;
				
			}
			
			
			RPlayer rp = c.rPlayer.get(event.getEntity().getUniqueId().toString());
			RPlayer rpp = c.rPlayer.get(event.getDamager().getUniqueId().toString());
			
			if(rp.isInDuel() && rpp.isInDuel()) {
				
				event.setCancelled(false);
				
				int str = rpp.getStats().getStr();
				int armor = rp.getStats().getArmor();
				
				int damage = str - armor;
				
				if(damage < 0) damage = 0;
				
				rp.getStats().setCurrentHealth(rp.getStats().getCurrenHealth() - damage);
				float dmg = 1F * rp.getStats().getCurrenHealth() / rp.getStats().getMaxHealth();
				if(dmg < 0) dmg = 0;			
				rp.getPlayer().setHealth(dmg * 20.0);
				event.setDamage(0.0);
				
				return;
				
			} else event.setCancelled(true);
			
			return;
			
		}
		
		
	}
	
}
