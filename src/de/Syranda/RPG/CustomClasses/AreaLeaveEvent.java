package de.Syranda.RPG.CustomClasses;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AreaLeaveEvent extends PlayerEvent{

	Area a;
	boolean isCancelled = false;
	
	public AreaLeaveEvent(Player who, Area a) {
		super(who);
		this.a = a;
	}

	public static HandlerList hl = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return hl;
	}
	
	public static HandlerList getHandlerList() {
		
		return hl;
		
	}

	public Area getArea() {
		
		return a;
		
	}
	
	public void setCancelled(boolean b) {
		
		isCancelled = b;
		
	}
	
	public boolean isCancelled() {
		
		return isCancelled;
		
	}
	
}
