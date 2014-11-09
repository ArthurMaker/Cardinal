package de.Syranda.RPG.CustomClasses;

import org.bukkit.inventory.ItemStack;

public class Buff {
	
	private String id;
	private String name;
	
	private int duration;
	private Runnable onActivate;
	
	private ItemStack icon;
	
	public Buff(String id, String name, int duration, Runnable onActivate, ItemStack icon) {

		this.id = id;
		this.name = name;
		this.duration = duration;
		this.onActivate = onActivate;
		this.icon = icon;
		
	}
	
	public String getId() {
		
		return this.id;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public int getDuration() {
		
		return this.duration;
		
	}
	
	public void setDuration(int duration) {
		
		this.duration = duration;
		
	}
	
	public Runnable getRunnable() {
		
		return this.onActivate;
		
	}
	
	public ItemStack getIcon() {
		
		return this.icon;
		
	}
	
}
