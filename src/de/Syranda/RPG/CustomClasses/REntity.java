package de.Syranda.RPG.CustomClasses;

import java.util.List;

import org.bukkit.entity.Entity;

public class REntity {
	
	private Entity entity;
	private Stats stats;
	private String name;
	private List<Item> drop;
	
	public REntity(String name, Entity entity, Stats stats, List<Item> drop) {
		
		this.name = name;
		this.entity = entity;
		this.stats = stats;
		this.drop = drop;
		
	}
	
	public void setStats(Stats stats) {
		
		this.stats = stats;
		
	}
	
	public Stats getStats() {
		
		return this.stats;
		
	}
	
	public Entity getEntity() {
		
		return this.entity;
		
	}

	public String getName() {
		
		return this.name;
		
	}
	
	public List<Item> getDrops() {
		
		return this.drop;
		
	}
	
}
