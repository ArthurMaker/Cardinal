package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.Plugin.Main;

public class BlockBreakListener implements Listener{
	
	Main c;
	
	public BlockBreakListener(Main c) {
		
		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}
	
	@EventHandler
	public void onEvent(BlockBreakEvent event) {
		
		event.setExpToDrop(0);
		
		for(Area a:c.areas) {
			
			if(a.isInArea(event.getBlock().getState().getLocation()) && a.hasTag("nobreak")) event.setCancelled(true);
			
		}

	}

}
