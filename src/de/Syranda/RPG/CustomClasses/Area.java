package de.Syranda.RPG.CustomClasses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Area {
	
	private Location x1;
	private Location x2;
	private String name;
	private List<String> tags;
	
	public Area(String name, Location x1, Location x2) {

		this.name = name;
		this.x1 = x1;
		this.x2 = x2;
	
	}

	public Area(String name, Location x1, Location x2, List<String> tags) {

		this.name = name;
		this.x1 = x1;
		this.x2 = x2;
		this.tags = tags;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public boolean isInArea(Location l) {
		
		if(l.getBlockX() >= Math.min(x1.getBlockX(), x2.getBlockX()) &&  l.getBlockX() <= Math.max(x1.getBlockX(), x2.getBlockX())) {
			
			if(l.getBlockY() >= Math.min(x1.getBlockY(), x2.getBlockY()) && l.getBlockY() <= Math.max(x1.getBlockY(), x2.getBlockY())) {
				
				if(l.getBlockZ() >= Math.min(x1.getBlockZ(), x2.getBlockZ()) && l.getBlockZ() <= Math.max(x1.getBlockZ(), x2.getBlockZ())) {
					
					return true;
					
				}
				
			}
			
		}
		return false;
		
	}
	
	public List<String> getTags() {
		
		return this.tags;
		
	}
	
	public boolean hasTag(String tag) {
		
		if(tags == null || tags.isEmpty()) return false;
		
		if(tags.contains(tag)) return true;
		else return false;
		
	}
	
	public void removeTag(String tag) {
		
		if(tags == null)  {
			tags = new ArrayList<String>();
			return;
		}
		
		tags.remove(tag);
		
	}
	
	public void addTag(String tag) {
		
		if(tags == null) tags = new ArrayList<String>();
		
		tags.add(tag);		
		
	}
	
}
