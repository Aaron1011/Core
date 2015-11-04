package me.creepsterlgc.core.commands;

import me.creepsterlgc.core.Controller;
import me.creepsterlgc.core.utils.PermissionsUtils;
import me.creepsterlgc.core.utils.ServerUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.weather.Weathers;


public class CommandWeatherRain {

	public CommandWeatherRain(CommandSource sender, String[] args, Game game) {
		
		if(!PermissionsUtils.has(sender, "core.weather.rain")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		if(args.length == 2) {
			
			if(args[1].equalsIgnoreCase("*")) {
				for(World world : Controller.getServer().getWorlds()) world.forecast(Weathers.RAIN);
				ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the weather to rain."));
				return;
			}
			
			if(!Controller.getServer().getWorld(args[1]).isPresent()) {
				sender.sendMessage(Texts.of(TextColors.RED, "World not found!"));
				return;
			}
			
			World world = Controller.getServer().getWorld(args[1]).get();
			world.forecast(Weathers.RAIN);
			
			ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the weather to rain on ", TextColors.YELLOW, world.getName()));
			
			return;
			
		}
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }
		
		Player player = (Player) sender;
		World world = player.getWorld();
		world.forecast(Weathers.RAIN);
		
		ServerUtils.broadcast(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the weather to rain on ", TextColors.YELLOW, world.getName()));
		
	}

}
