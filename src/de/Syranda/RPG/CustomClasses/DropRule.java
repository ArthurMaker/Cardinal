package de.Syranda.RPG.CustomClasses;

import java.util.List;

import org.bukkit.entity.EntityType;

public class DropRule {

	private String lvlCap;
	private EntityType entity;
	private List<Item> drops;
	private int chance;
	
	public DropRule(String lvlCap, EntityType entity, List<Item> drops, int chance) {
		
		this.lvlCap = lvlCap;
		this.entity = entity;
		this.drops = drops;
		this.chance = chance;

	}
	
	public String getLvlCap() {
		
		return this.lvlCap;	
	
	}
	
	public EntityType getEnitiyType() {
			
		return this.entity;
			
	}
	
	public List<Item> getDrops() {
		
		return this.drops;
		
	}
	
	public int getChance() {
		
		return this.chance;
		
	}
	
}
