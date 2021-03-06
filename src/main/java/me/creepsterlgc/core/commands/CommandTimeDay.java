package main.java.me.creepsterlgc.core.commands;

import main.java.me.creepsterlgc.core.Controller;
import main.java.me.creepsterlgc.core.utils.PermissionsUtils;
import main.java.me.creepsterlgc.core.utils.ServerUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.world.World;


public class CommandTimeDay {

	public CommandTimeDay(CommandSource sender, String[] args, Game game) {
		
		if(!PermissionsUtils.has(sender, "core.time.day")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		if(args.length == 2) {
			
			if(args[1].equalsIgnoreCase("*")) {
				for(World world : Controller.getServer().getWorlds()) world.getProperties().setWorldTime(0);
				ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the time to day."));
				return;
			}
			
			if(!Controller.getServer().getWorld(args[1]).isPresent()) {
				sender.sendMessage(Texts.of(TextColors.RED, "World not found!"));
				return;
			}
			
			World world = Controller.getServer().getWorld(args[1]).get();
			world.getProperties().setWorldTime(0);
			
			ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the time to day on ", TextColors.YELLOW, world.getName()));
			
			return;
			
		}
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }
		
		Player player = (Player) sender;
		World world = player.getWorld();
		world.getProperties().setWorldTime(0);
		
		ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the time to day on ", TextColors.YELLOW, world.getName()));
		
	}

}
