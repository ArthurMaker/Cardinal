package de.Syranda.RPG.CustomClasses;

import org.bukkit.Location;

public class DuelManager {
	
	private Area arena;
	private Location l1;
	private Location l2;
	private Location l3;
	private RPlayer p1;
	private RPlayer p2;
	
	public DuelManager(Area arena, Location l1, Location l2, Location l3, RPlayer p1, RPlayer p2) {

		this.arena = arena;
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.p1 = p1;
		this.p2 = p2;
			
	}
	
	public Area getArena()  {
		
		return this.arena;
		
	}
	
	public Location getPlayerOneLocation() {
		
		return this.l1;
		
	}
	
	public Location getPlayerTwoLocation() {
		
		return this.l2;
		
	}
	
	public Location getSpectatorLocation() {
		
		return this.l3;
		
	}
	
	public RPlayer getPlayerOne() {
		
		return this.p1;
		
	}
	
	public RPlayer getPlayerTwo() {
		
		return this.p2;
		
	}
	
}
