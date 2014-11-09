package de.Syranda.RPG.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.Syranda.RPG.CustomClasses.Item;
import de.Syranda.RPG.CustomClasses.REntity;
import de.Syranda.RPG.Plugin.Main;

public class EntityDeathListener implements Listener{
	
	Main c;
	
	public EntityDeathListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}
	
	@EventHandler
	public void onEvent(EntityDeathEvent event) {
		
		if(event.getEntity() instanceof Player) return;
		
		REntity re = c.rEntity.get(event.getEntity().getEntityId());
		int exp = re.getStats().getExp();
		event.setDroppedExp(exp);
		event.getDrops().clear();
		List<ItemStack> drops = new ArrayList<ItemStack>();
		
		for(Item is:re.getDrops()) {
			
			drops.add(is.getItem());
			
		}
		
		event.getDrops().addAll(drops);
		
	}

}
