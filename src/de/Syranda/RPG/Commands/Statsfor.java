package de.Syranda.RPG.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.Stats;
import de.Syranda.RPG.Plugin.Main;

public class Statsfor {
	
	public boolean execute(CommandSender sender, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("Cardinal.Statsfor")) {
			
			p.sendMessage("§cYou don't have permission to use this command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("§cUsage: /statsfor <level>");
			return true;
			
		}
		
		int i;
		
		try {
			
			i = Integer.valueOf(args[0]);
			
		} catch(Exception e) {
			
			p.sendMessage("§cThe first argument has to be a number");
			return true;
			
		}
		
		Stats s = Main.getStats(i);
		
		p.sendMessage("§7-------------§6STATS: §aLvl." + args[0] + "§7-------------");
		p.sendMessage("§7Level: §6" + s.getLevel());
		p.sendMessage("§7Exp: §6" + s.getExp());
		p.sendMessage("§7Health: §6" + s.getMaxHealth());
		p.sendMessage("§7Str: §6" + s.getStr());
		p.sendMessage("§7Armor: §6" + s.getArmor());
		
		return true;
		
	}

}
