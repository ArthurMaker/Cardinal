package de.Syranda.RPG.CustomClasses;

import org.bukkit.inventory.ItemStack;

public class Armor extends Item{

	private Stats stats;
	
	public Armor(int id, String name, ItemStack item, int onUse, Stats stats ) {
		
		super(id, name, item, onUse);
		this.stats = stats;
		
	}
	
	public Stats getStats() {
		
		return this.stats;
		
	}
		
	public void updateItem() {
		
		
		
	}
	
}
