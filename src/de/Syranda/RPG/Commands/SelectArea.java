package de.Syranda.RPG.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.Plugin.Main;

public class SelectArea {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("RPG.Selectarea")) {
			
			p.sendMessage("§cYou don't have permission th execute this Command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			if(c.selectedArea.get(p) != null) p.sendMessage("§aSelected area: " + c.selectedArea.get(p).getName());
			p.sendMessage("§cUsage: /selectarea <name>");
			return true;
			
		}
		
		Area a = null;
		
		for(Area as:c.areas) {
			
			if(as.getName().equals(args[0])) a = as;
			
		}
		
		if(a == null) {
			
			p.sendMessage("§cArea not found");
			return true;
			
		}
		
		c.selectedArea.put(p, a);
		
		p.sendMessage("§6[RPG] §7Area §a" + args[0] + " §7selected.");
		
		return true;
		
	}

}
