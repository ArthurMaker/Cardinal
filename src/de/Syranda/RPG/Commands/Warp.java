package de.Syranda.RPG.Commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.Plugin.Main;

public class Warp {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender; 
		
		if(!p.hasPermission("RPG.Warp")) {
			
			p.sendMessage("§cYou don't have permission to use this command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("§cUsage: /warp <warpname>");
			return true;
			
		}
		
		Location l = c.warps.get(args[0]);
		
		if(l == null) {
			
			p.sendMessage("§cWarp not found!");
			return true;
			
		}
		
		p.teleport(l);
		
		p.sendMessage("§6[RPG] §7Warped to §a" + args[0] + "§7.");
		
		return true;
		
	}

}
