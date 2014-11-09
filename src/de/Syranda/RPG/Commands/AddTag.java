package de.Syranda.RPG.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.Plugin.Main;

public class AddTag {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("RPG.AddTag")) {
			
			p.sendMessage("�cYou don't have permission th execute this Command!");
			return true;
			
		}
				
		if(c.selectedArea.get(p) == null) {
			
			p.sendMessage("�cYou have to select an area to use this command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("�cUsage: /addtag <tag>");
			return true;
			
		}
		
		if(c.selectedArea.get(p).hasTag(args[0])) {
			
			p.sendMessage("�cArea " + c.selectedArea.get(p).getName() + " has already the tag " + args[0] + ".");
			return true;
			
		}
		
		List<String> newList = c.ar.aCon.getStringList(c.selectedArea.get(p).getName() + ".Tags");
	
		if(newList == null) {
			
			newList = new ArrayList<String>();
			
		} else {
			
			newList.add(args[0]);
			
		}
		
		c.ar.aCon.set(c.selectedArea.get(p).getName() + ".Tags", newList);		
		
		try {
			c.ar.aCon.save(c.ar.areaFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.selectedArea.get(p).addTag(args[0]);
		
		p.sendMessage("�6[RPG] �7Added tag �a" + args[0] + " �7to area �a" + c.selectedArea.get(p).getName() + " �7.");
		
		return true;
		
	}

}