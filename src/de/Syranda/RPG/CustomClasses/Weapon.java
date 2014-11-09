package de.Syranda.RPG.CustomClasses;

import org.bukkit.inventory.ItemStack;

public class Weapon extends Item{

	private Stats stats;
	
	public Weapon(int id, String name, ItemStack item, int onUse, Stats stats ) {
		
		super(id, name, item, onUse);
		this.stats = stats;
		
	}
	
	public Stats getStats() {
		
		return this.stats;
		
	}
	
}
