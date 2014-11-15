package de.Syranda.RPG.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.Syranda.RPG.CustomClasses.Armor;
import de.Syranda.RPG.CustomClasses.Item;
import de.Syranda.RPG.CustomClasses.Weapon;
import de.Syranda.RPG.Plugin.Main;

public class GiveItem {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender;
		
		if(!p.hasPermission("Cardinal.Item")) {
			
			p.sendMessage("§cYou don't have permission to use this command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("§cUsage: /item <itemname>");
			return true;
		}
		
		ItemStack is = null;
		
		for(Weapon w:c.weapons.values()) {
			
			if(w.getName().equalsIgnoreCase(args[0])) is = w.getItem();
			
		}
		
		if(is == null) {
			
			for(Armor a:c.armor.values()) {
				
				if(a.getName().equalsIgnoreCase(args[0])) is = a.getItem();
				
			}		
			
		}
		
		if(is == null) {
			
			for(Item i:c.items.values()) {
				
				if(i.getName().equalsIgnoreCase(args[0])) is = i.getItem();
				
			}
					
			
		}
		 			
		if(is == null) {
			
			p.sendMessage("§cThis item does not exist!");
			return true;
			
		}
		
		p.getInventory().addItem(is);
		p.sendMessage("§7Added item §a" + args[0] + " §7.");
		
		return true;
		
	}

}
