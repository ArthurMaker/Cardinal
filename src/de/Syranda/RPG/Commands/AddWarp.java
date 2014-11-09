package de.Syranda.RPG.Commands;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Syranda.RPG.Plugin.Main;

public class AddWarp {
	
	public boolean execute(CommandSender sender, String[] args, Main c) {
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage("This command is only for players!");
			return true;
			
		}
		
		Player p = (Player) sender; 
		
		if(!p.hasPermission("RPG.Addwarp")) {
			
			p.sendMessage("§cYou don't have permission to use this command!");
			return true;
			
		}
		
		if(args.length != 1) {
			
			p.sendMessage("§cUsage: /addwarp <warpname>");
			return true;
			
		}
		
		if(!c.w.warpFile.exists())
			try {
				c.w.warpFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		c.warps.put(args[0], p.getLocation());
		
		c.w.w.set(args[0] + ".world", p.getWorld().getName());
		c.w.w.set(args[0] + ".x", p.getLocation().getBlockX());
		c.w.w.set(args[0] + ".y", p.getLocation().getBlockY());
		c.w.w.set(args[0] + ".z", p.getLocation().getBlockZ());
		c.w.w.set(args[0] + ".yaw", p.getLocation().getYaw());
		
		try {
			c.w.w.save(c.w.warpFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.sendMessage("§6[RPG] §7Warp §a" + args[0] + " §7was created.");
		
		return true;
		
	}

}
