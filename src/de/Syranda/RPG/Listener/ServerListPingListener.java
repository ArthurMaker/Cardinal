package de.Syranda.RPG.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import de.Syranda.RPG.Plugin.Main;

public class ServerListPingListener implements Listener{

	Main c;
	
	public ServerListPingListener(Main c) {

		this.c = c;
		c.getServer().getPluginManager().registerEvents(this, c);
		
	}
	
	@EventHandler
	public void onEvent(ServerListPingEvent event) {
		
		if(!c.getServer().hasWhitelist()) event.setMotd("§6RPG §7v" + c.getDescription().getVersion() + "\n§7/changelog for changelog");
		else event.setMotd("§4Server maintenance\n§7We will be back soon");
		
	}
	
}
