package de.Syranda.RPG.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.Plugin.Main;

public class Areas {
	
	public File areaFile;
	public YamlConfiguration aCon;
	
	Main c;
	
	public Areas(Main c) {
		
		this.c = c;
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void load() throws IOException {

		if(!c.getDataFolder().exists())
			c.getDataFolder().mkdir();
		
		areaFile = new File(c.getDataFolder(), "//areas.yml");		
		
		if(!areaFile.exists())
			areaFile.createNewFile();
		
		aCon = YamlConfiguration.loadConfiguration(areaFile);
		
		for(String s:aCon.getConfigurationSection("").getKeys(false)) {
			
			if(aCon.getStringList(s + ".Tags") == null) {
				
				c.areas.add(new Area(s, new Location(Bukkit.getWorld(aCon.getString(s + ".world")), aCon.getDouble(s + ".x1"), aCon.getDouble(s + ".y1"), aCon.getDouble(s + ".z1")), new Location(Bukkit.getWorld(aCon.getString(s + ".world")), aCon.getDouble(s + ".x2"), aCon.getDouble(s + ".y2"), aCon.getDouble(s + ".z2"))));
				
			} else {
				
				c.areas.add(new Area(s, new Location(Bukkit.getWorld(aCon.getString(s + ".world")), aCon.getDouble(s + ".x1"), aCon.getDouble(s + ".y1"), aCon.getDouble(s + ".z1")), new Location(Bukkit.getWorld(aCon.getString(s + ".world")), aCon.getDouble(s + ".x2"), aCon.getDouble(s + ".y2"), aCon.getDouble(s + ".z2")), aCon.getStringList(s + ".Tags")));
				
			}

		}
		
	}

}
