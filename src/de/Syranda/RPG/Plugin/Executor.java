package de.Syranda.RPG.Plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.Syranda.RPG.Commands.AddArea;
import de.Syranda.RPG.Commands.AddTag;
import de.Syranda.RPG.Commands.AddWarp;
import de.Syranda.RPG.Commands.Arena;
import de.Syranda.RPG.Commands.Duel;
import de.Syranda.RPG.Commands.GiveItem;
import de.Syranda.RPG.Commands.PlayerStats;
import de.Syranda.RPG.Commands.RemoveTag;
import de.Syranda.RPG.Commands.SelectArea;
import de.Syranda.RPG.Commands.ShowStats;
import de.Syranda.RPG.Commands.Statsfor;
import de.Syranda.RPG.Commands.Warp;

public class Executor implements CommandExecutor{

	Main c;
	
	public Executor(Main c) {

		this.c = c;
		c.getCommand("addarea").setExecutor(this);
		c.getCommand("selectarea").setExecutor(this);
		c.getCommand("addtag").setExecutor(this);
		c.getCommand("stats").setExecutor(this);
		c.getCommand("addwarp").setExecutor(this);
		c.getCommand("warp").setExecutor(this);
		c.getCommand("removetag").setExecutor(this);
		c.getCommand("item").setExecutor(this);
		c.getCommand("statsfor").setExecutor(this);
		c.getCommand("playerstats").setExecutor(this);
		c.getCommand("arena").setExecutor(this);
		c.getCommand("duel").setExecutor(this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("addarea")) return new AddArea().execute(sender, args, c);
			
		if(label.equalsIgnoreCase("selectarea")) return new SelectArea().execute(sender, args, c);	
		
		if(label.equalsIgnoreCase("addtag")) return new AddTag().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("stats")) return new ShowStats().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("addwarp")) return new AddWarp().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("warp")) return new Warp().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("removetag")) return new RemoveTag().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("item")) return new GiveItem().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("statsfor")) return new Statsfor().execute(sender, args);
		
		if(label.equalsIgnoreCase("playerstats")) return new PlayerStats().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("arena")) return new Arena().execute(sender, args, c);
		
		if(label.equalsIgnoreCase("duel")) return new Duel().execute(sender, args, c);
		
		return false;
	}

}
