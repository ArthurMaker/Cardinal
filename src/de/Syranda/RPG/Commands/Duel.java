package de.Syranda.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.CustomClasses.Area;
import de.Syranda.RPG.CustomClasses.DuelManager;
import de.Syranda.RPG.CustomClasses.RPlayer;
import de.Syranda.RPG.Plugin.Main;

public class Duel {

	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("DEV")) {
			
			p.sendMessage("§cThis Command is under development.");
			return true;
			
		}
		
		if(args.length != 2) {
			
			p.sendMessage("§cUsage: /duell <player> <arena>");
			return true;
		}
		
		Player ps = Bukkit.getPlayer(args[0]);
		
		if(ps == null) {
			
			p.sendMessage("§c" + args[0] + " is not online!");
			return true;
			
		}
		
		if(!p.getNearbyEntities(5, 5, 5).contains(ps)) {
			
			p.sendMessage("§cThe other player has to be within 5 block to be challenged to a duel!");
			return true;
			
		}
		
		if(c.inDuell.contains(ps.getUniqueId().toString())) {
			
			p.sendMessage("§c" + args[0] + " is already in a duel");
			return true;
			
		}
		
		Area a = null;
		
		for(Area area:c.areas) {
			
			if(args[1].equals(area.getName())) a = area;
			
		}
		 
		if(a == null || !a.hasTag("Arena") || c.usedArenas.contains(a)) {
			
			p.sendMessage("§cArena " + args[1] + " is not avaiable.");
			return true;
			
		}
		
		RPlayer rpp = c.rPlayer.get(ps.getUniqueId().toString());
		RPlayer rp = c.rPlayer.get(p.getUniqueId().toString());
		
		Location l1 = null;
		Location l2 = null;
		Location l3 = null;
		
		for(String tag : a.getTags()) {
			
			if(tag.startsWith("dpos1")) l1 = c.warps.get(tag.split(":")[1]);
			else if(tag.startsWith("dpos2")) l2 = c.warps.get(tag.split(":")[1]);
			else if(tag.startsWith("dpos3")) l3 = c.warps.get(tag.split(":")[1]);
			
		}
		
		DuelManager duel = new DuelManager(a, l1, l2, l3, rp, rpp);
		
		c.duel.put(c.rPlayer.get(ps.getUniqueId().toString()), duel);		
		c.duel.put(c.rPlayer.get(p.getUniqueId().toString()), duel);	
		rpp.challange(rp, a);
		
		return true;
		
	}
	
}
