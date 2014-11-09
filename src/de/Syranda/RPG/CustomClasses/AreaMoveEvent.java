package de.Syranda.RPG.CustomClasses;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AreaMoveEvent extends PlayerEvent{

	private Area a;
	
	public AreaMoveEvent(Player who, Area a) {
		super(who);
		this.a = a;
	}

	public static HandlerList hl = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return hl;
	}
	
	public static HandlerList getHandlerList() {
		
		return hl;
		
	}
	
	boolean isCancelled = false;
	
	public void setCancelled(boolean isCanelled) {
		
		this.isCancelled = isCanelled;
		
	}

	public boolean isCancelled() {
		
		return this.isCancelled;
		
	}	
	
	public Area getArea() {
		
		return this.a;
		
	}
	
}
