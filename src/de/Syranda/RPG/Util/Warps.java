package de.Syranda.RPG.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Syranda.RPG.Plugin.Main;

public class Warps {
	
	Main c;
	
	public File warpFile;
	public YamlConfiguration w;	
	
	public Warps(Main c) {
		
		this.c = c;
		try {
			loadWarps();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadWarps() throws IOException {

		if(!c.getDataFolder().exists())
			c.getDataFolder().mkdir();
		
		warpFile = new File(c.getDataFolder(), "//warps.yml");
		
		if(!warpFile.exists())
			warpFile.createNewFile();
		
		w = YamlConfiguration.loadConfiguration(warpFile);
		
		for(String s:w.getConfigurationSection("").getKeys(false)) {
			
			if(s == null) break;
			
			Location l = new Location(Bukkit.getWorld(w.getString(s + ".world")), w.getDouble(s + ".x"), w.getDouble(s + ".y"), w.getDouble(s + ".z"));
			
			l.add(0.5, 0, 0.5);
			l.setYaw(w.getInt(s + ".yaw"));
			
			c.warps.put(s, l);
			
		}
		
		w.save(warpFile);
			
	}

}
