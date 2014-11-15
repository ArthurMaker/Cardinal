package de.Syranda.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class ShowStats {
	
	@SuppressWarnings("null")
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			System.out.println("This command is only for players");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(args.length > 1) {
			
			if(p.hasPermission("RPG.ViewStats")) p.sendMessage("§cUsage: /stats [player]");
			else p.sendMessage("§cUsage: /stats");
			return true;
		}
		
		if(!p.hasPermission("Cardinal.ViewStats") && args.length == 1) {
			
			p.sendMessage("§cUsage: /stats");
			return true;
			
		} else if(args.length == 1) {
			
			Player ps = Bukkit.getPlayer(args[0]);
			
			if(ps == null) {
				
				p.sendMessage("§c" + ps.getName() + " is not online");
				return true;
				
			} else {
				
				RPlayer rp = c.rPlayer.get(ps.getUniqueId().toString());
				
				for(int i = 0; i < 3; i++) {
					p.sendMessage(" ");
				}				
				p.sendMessage("§7-------------§6STATS: §a" + args[0] + "§7-------------");
				p.sendMessage("§7UUID: §6" + ps.getUniqueId());
				p.sendMessage("§7Level: §6" + rp.getStats().getLevel());
				p.sendMessage("§7Exp: §6" + rp.getStats().getExp() + "§7/§6" + rp.getStats().getExpToLvl());
				p.sendMessage("§7Health: §6" +rp.getStats().getCurrenHealth() + "§7/§6" + rp.getStats().getMaxHealth());
				p.sendMessage("§7Luk: §6" + rp.getStats().getLuk());
				p.sendMessage("§7Vit: §6" + rp.getStats().getVit());
				p.sendMessage("§7Str: §6" + rp.getStats().getStr());
				p.sendMessage("§7Armor: §6" + rp.getStats().getArmor());
				if(rp.isGM()) p.sendMessage("§7Status: §6Gamemaster");
				else p.sendMessage("§7Status: User");
				
			}
			
			
		} else {
			
			RPlayer rp = c.rPlayer.get(p.getUniqueId().toString());
			
			for(int i = 0; i < 3; i++) {
				p.sendMessage(" ");
			}				
			p.sendMessage("§7-------------§6STATS: §a" + p.getName() + "§7-------------");
			p.sendMessage("§7UUID: §6" + p.getUniqueId());
			p.sendMessage("§7Level: §6" + rp.getStats().getLevel());
			p.sendMessage("§7Exp: §6" + rp.getStats().getExp() + "§7/§6" + rp.getStats().getExpToLvl());
			p.sendMessage("§7Health: §6" +rp.getStats().getCurrenHealth() + "§7/§6" + rp.getStats().getMaxHealth());
			p.sendMessage("§7Luk: §6" + rp.getStats().getLuk());
			p.sendMessage("§7Vit: §6" + rp.getStats().getVit());
			p.sendMessage("§7Str: §6" + rp.getStats().getStr());
			p.sendMessage("§7Armor: §6" + rp.getStats().getArmor());
			if(rp.isGM()) p.sendMessage("§7Status: §6Gamemaster");
			else p.sendMessage("§7Status: User");
			
		}
		
		
		return true;
		
	}

}
