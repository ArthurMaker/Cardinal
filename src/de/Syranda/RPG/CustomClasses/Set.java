package de.Syranda.RPG.CustomClasses;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Set {

	private int id;
	private String name;
	private List<ItemStack> items;
	
	public Set(int id, String name, List<ItemStack> items) {
		
		this.id = id;
		this.name = name;
		this.items = items;
		
	}
	
	public int getId() {
		
		return this.id;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
		
	public List<ItemStack> getItems() {
		
		return this.items;
		
	}
	
}
