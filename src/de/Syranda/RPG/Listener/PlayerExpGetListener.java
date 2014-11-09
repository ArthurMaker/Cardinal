package de.Syranda.RPG.Listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class PlayerExpGetListener implements Listener{

	Main c;
	
	public PlayerExpGetListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}	
	
	@EventHandler
	public void onEvent(PlayerExpChangeEvent event) throws SQLException {
				
		RPlayer rp = c.rPlayer.get(event.getPlayer().getUniqueId().toString());
				
		if(rp.getStats().getLevel() == 99) {
			event.setAmount(0);
			return;
		}
		
		rp.getStats().setExp(rp.getStats().getExp() + event.getAmount());
		
		if(rp.getStats().getExp() >= rp.getStats().getExpToLvl()) {
			
			Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lvl" + rp.getStats().getLevel()).removePlayer(rp.getPlayer());
			
			rp.getStats().setLevel(rp.getStats().getLevel() + 1);
			rp.getStats().setExp(rp.getStats().getExp() - rp.getStats().getExpToLvl());
			
			Statement s = Main.conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT exp FROM `leveldata` WHERE level=" + rp.getStats().getLevel());
						
			if(!rs.next()) rp.getPlayer().kickPlayer("§cMySQL-Error");
			
			rp.getStats().setExpToLvl(rs.getInt(1));			
			rp.saveData();			
			
			rp.getPlayer().sendMessage("§6[RPG] §aLevel §6" + rp.getStats().getLevel() + " §areached!");
			
			rp.getPlayer().setLevel(rp.getStats().getLevel());
			
			Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lvl" + rp.getStats().getLevel()).addPlayer(rp.getPlayer());
			
			rp.setDefaultStats(Main.lvlUp(1, rp.getDefaultStats()));
			rp.calculateStats();
			
		}
		
		float exp = 1F * rp.getStats().getExp() / rp.getStats().getExpToLvl();
		
		rp.getPlayer().setExp(exp);
		
		event.setAmount(0);
		
		rp.reloadTab();
		
	}
	
}
