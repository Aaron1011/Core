package me.creepsterlgc.core.commands;

import me.creepsterlgc.core.customized.PERMISSIONS;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandSource;


public class CommandTimeSunrise {

	public CommandTimeSunrise(CommandSource sender, String[] args, Game game) {
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }
		
		if(!PERMISSIONS.has(sender, "core.time.sunrise")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		game.getCommandDispatcher().process(game.getServer().getConsole(), "minecraft:time set 23000");
		
		game.getServer().getBroadcastSink().sendMessage(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has changed the time to sunrise."));
		
	}

}
