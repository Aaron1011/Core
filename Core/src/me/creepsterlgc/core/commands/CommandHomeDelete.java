package me.creepsterlgc.core.commands;

import java.util.HashMap;

import me.creepsterlgc.core.customized.DATABASE;
import me.creepsterlgc.core.customized.HOME;
import me.creepsterlgc.core.customized.PERMISSIONS;
import me.creepsterlgc.core.customized.PLAYER;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandSource;


public class CommandHomeDelete {

	public CommandHomeDelete(CommandSource sender, String[] args) {
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }
		
		if(!PERMISSIONS.has(sender, "core.home.delete")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		if(args.length < 1 || args.length > 2) { sender.sendMessage(Texts.of(TextColors.YELLOW, "Usage: ", TextColors.GRAY, "/home delete [name]")); return; }
		
		Player player = (Player) sender;
		PLAYER p = DATABASE.getPlayer(player.getUniqueId().toString());
		
		String name = "default"; if(args.length == 2) name = args[1].toLowerCase();
		HOME home = p.getHome(name);
		
		if(home == null) { sender.sendMessage(Texts.builder("Home does not exist!").color(TextColors.RED).build()); return; }
		
		home.delete();
		
		HashMap<String, HOME> homes = p.getHomes();
		homes.remove(name);
		p.setHomes(homes);
		
		sender.sendMessage(Texts.of(TextColors.GRAY, "Home ", TextColors.YELLOW, name, TextColors.GRAY, " has been removed."));
		
	}

}
