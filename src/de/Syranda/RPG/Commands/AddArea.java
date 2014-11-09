package de.Syranda.RPG.Commands;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.Plugin.Main;

public class AddArea {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("RPG.Addarea")) {
			
			p.sendMessage("§cYou don't have permission th execute this Command!");
			return true;
			
		}
		
		if(c.x1.get(p) == null && c.x2.get(p) == null) {
			
			p.sendMessage("§cYou have to mark two points to create an area!");
			return true;
			
		}
		
		if(c.x1.get(p).getWorld() != c.x2.get(p).getWorld()) {
			
			p.sendMessage("§cThe two points have to be in the same world!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("§cUsage: /addarea <name>");
			return true;
			
		}
		
		Location x1 = c.x1.get(p);
		Location x2 = c.x2.get(p);
		
		c.ar.aCon.set(args[0] + ".world", x1.getWorld().getName());
		
		c.ar.aCon.set(args[0] + ".x1", x1.getBlockX());
		c.ar.aCon.set(args[0] + ".y1", x1.getBlockY());
		c.ar.aCon.set(args[0] + ".z1", x1.getBlockZ());
		
		c.ar.aCon.set(args[0] + ".x2", x2.getBlockX());
		c.ar.aCon.set(args[0] + ".y2", x2.getBlockY());
		c.ar.aCon.set(args[0] + ".z2", x2.getBlockZ());
		
		try {
			c.ar.aCon.save(c.ar.areaFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.areas.add(new Area(args[0], x1, x2));
		
		p.sendMessage("§6[RPG] §7Area §a" + args[0] + " §7was created.");
		
		return true;
		
	}

}
