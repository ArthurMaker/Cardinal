package de.Syranda.RPG.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.Plugin.Main;

public class Arena {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("Cardinal.Dev")) {
			
			p.sendMessage("§cThis Command is under development.");
			return true;
			
		}
		
		if(args.length != 0) {
			
			p.sendMessage("§cUsage: /arena");
			return true;
		}
		
		p.sendMessage("§7Following arenas are available:");
		
		for(Area a:c.areas) {
			
			if(a.hasTag("Arena") && !c.usedArenas.contains(a)) {
				
				p.sendMessage("§7- §6" + a.getName());
				
			}
			
		}
		
		return true;
		
	}

}
