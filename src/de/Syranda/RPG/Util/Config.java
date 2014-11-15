package de.Syranda.RPG.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import de.Syranda.RPG.Plugin.Customizer;
import de.Syranda.RPG.Plugin.Main;

public class Config {
	
	File conFile;
	YamlConfiguration con;

	Main c;
	
	public Config(Main c) {
		
		this.c = c;
		try {
			load();
			loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void load() throws IOException {

		if(!c.getDataFolder().exists())
			c.getDataFolder().mkdir();
				
		conFile = new File(c.getDataFolder(), "//config.yml");
		
		if(!conFile.exists())
			conFile.createNewFile();
		
		con = YamlConfiguration.loadConfiguration(conFile);
		
		con.addDefault("MySQL.Host", "localhost");
		con.addDefault("MySQL.Port", 3306);
		con.addDefault("MySQL.Database", "database");
		con.addDefault("MySQL.User", "root");
		con.addDefault("MySQL.Pass", "123456");
		con.addDefault("MySQL.TablePrefix", "Cardinal_");
		
		con.addDefault("Config.StarterSet", "starterset");
		con.addDefault("Config.StarterSpawn", "spawn");
		con.addDefault("Config.EditMode", true);
		
		con.options().copyDefaults(true);
		con.save(conFile);
		
	}
	
	private void loadValues() {
		
		Customizer.host = con.getString("MySQL.Host");
		Customizer.port = con.getInt("MySQL.Port");
		Customizer.database = con.getString("MySQL.Database");
		Customizer.user = con.getString("MySQL.User");
		Customizer.pass = con.getString("MySQL.Pass");
		Customizer.TablePrefix = con.getString("MySQL.TablePrefix");
		
		Customizer.StarterSet = con.getString("Config.StarterSet");
		Customizer.StarterSpawn = con.getString("Config.StarterSpawn");
		Customizer.EditMode = con.getBoolean("Config.EditMode");
		
	}
	
	
}
