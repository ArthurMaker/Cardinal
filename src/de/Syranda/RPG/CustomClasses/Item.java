package de.Syranda.RPG.CustomClasses;

import org.bukkit.inventory.ItemStack;

public class Item {
	
	private int id;
	private String name;
	private ItemStack item;
	private int onUse;
	
	public Item(int id, String name, ItemStack item, int onUse) {

		this.id = id;
		this.name = name;
		this.item = item;
		this.onUse = onUse;
		
	}
	
	public int getId() {
		
		return this.id;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public ItemStack getItem() {
		
		return this.item;
		
	}
	
	public int getOnUse() {
		
		return this.onUse;
		
	}
	
}
