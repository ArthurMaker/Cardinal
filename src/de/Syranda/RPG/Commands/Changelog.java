package de.Syranda.RPG.Commands;

import org.bukkit.command.CommandSender;

import de.Syranda.RPG.Plugin.Main;

public class Changelog {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(args.length != 0) {
			
			sender.sendMessage("§cUsage: /changelog");
			return true;
			
		}
		
		sender.sendMessage("§7-------------Changelog-------------");
		sender.sendMessage("§6RPG §7v" + c.getDescription().getVersion());
		sender.sendMessage("§7- Footer and header on tab");
		sender.sendMessage("§7- Working duel system");
		sender.sendMessage("§7- Deleted some messages");
		sender.sendMessage("§7-----------------------------------");
		
		return true;
		
	}

}
