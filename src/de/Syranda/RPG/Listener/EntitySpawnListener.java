package de.Syranda.RPG.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.CustomClasses.DropRule;
import de.Syranda.RPG.CustomClasses.Item;
import de.Syranda.RPG.CustomClasses.REntity;
import de.Syranda.RPG.Plugin.Main;

public class EntitySpawnListener implements Listener{

	Main c;
		
	public EntitySpawnListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);

	}
	
	@EventHandler
	public void onEvent(CreatureSpawnEvent event) {
		
		if(event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.WITCH) {
			event.setCancelled(true);
			return;
		}
		
		Location spawn = event.getLocation();
		
		boolean allow = false;
		Area spawnArea = null;
		
		for(Area a:c.areas) {
			
			if(a.isInArea(spawn) && a.hasTag("allowcreatures")) {
				allow = true;
				spawnArea = a;
			}
				
		}
		
		if(!allow) {
			
			event.setCancelled(true);
			return;
			
		}
		
		String lvlCap = null;
			
		if(spawnArea == null) {
			
			System.out.println("FATAL ERROR (PLUGIN ERROR) : PLEASE DELETE PLUGIN 'CARDINAL' !");
			return;
			
		}
		
		for(String tag:spawnArea.getTags()) {
			
			if(tag.startsWith("lvlCap")) {
				
				lvlCap = tag;
				
			}
			
		}		
		
		int minLvl = Integer.valueOf(lvlCap.split(":")[1].split("-")[0]);
		int maxLvl = Integer.valueOf(lvlCap.split(":")[1].split("-")[1]);
			
		int actualLvl = 0;
		
		if(minLvl == maxLvl) actualLvl = minLvl;
		else actualLvl = new Random().nextInt(maxLvl-minLvl) + minLvl;
			
		String mob = "Changed later";
		
		List<Item> itemDrop = new ArrayList<Item>();
		
		for(DropRule dr:c.dRules) {
			
			int i = new Random().nextInt(100);
			
			if(i > dr.getChance()) continue;
			
			if(event.getEntityType() != dr.getEnitiyType()) continue;
			
			int miLvl = Integer.valueOf(dr.getLvlCap().split("-")[0]);
			int maLvl = Integer.valueOf(dr.getLvlCap().split("-")[1]);
			
			if(actualLvl <= maLvl && actualLvl >= miLvl) {
				
				itemDrop.addAll(dr.getDrops());
				
			}
			
		}
		
		REntity re = new REntity(mob, event.getEntity(), Main.getStats(actualLvl), itemDrop);
		
		c.rEntity.put(event.getEntity().getEntityId(), re);
		
		event.getEntity().setCustomName("§7Lv. §6" + actualLvl + " §r" + re.getName() + " §c" + re.getStats().getMaxHealth() + "/" + re.getStats().getMaxHealth());
		event.getEntity().setCustomNameVisible(true);
		
	}
	
}
