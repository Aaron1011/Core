package me.creepsterlgc.core.commands;

import java.util.List;

import me.creepsterlgc.core.Controller;
import me.creepsterlgc.core.customized.BAN;
import me.creepsterlgc.core.customized.COMMAND;
import me.creepsterlgc.core.customized.DATABASE;
import me.creepsterlgc.core.customized.PERMISSIONS;
import me.creepsterlgc.core.customized.PLAYER;
import me.creepsterlgc.core.customized.TIME;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

import com.google.common.base.Optional;


public class CommandOnlinetime implements CommandCallable {
	
	public Game game;
	
	public CommandOnlinetime(Game game) {
		this.game = game;
	}
	
	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {
		
		String[] args = arguments.split(" ");
		
		if(!PERMISSIONS.has(sender, "core.onlinetime")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		if(args.length > 1) { sender.sendMessage(Texts.of(TextColors.YELLOW, "Usage: ", TextColors.GRAY, "/onlinetime [player]")); return CommandResult.success(); }
		
		if(arguments.equalsIgnoreCase("")) {
			
			if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return CommandResult.success(); }
			
			Player player = (Player)sender;
			PLAYER p = DATABASE.getPlayer(player.getUniqueId().toString());
			sender.sendMessage(Texts.of(TextColors.GRAY, "Your onlinetime: ", TextColors.YELLOW, TIME.toString(p.getOnlinetime())));
		
			return CommandResult.success();
			
		}
		
		PLAYER player = DATABASE.getPlayer(DATABASE.getUUID(args[0].toLowerCase()));
		if(player == null) { sender.sendMessage(Texts.builder("Player not found!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		sender.sendMessage(Texts.of(TextColors.GRAY, player.getName(), "'s onlinetime: ", TextColors.YELLOW, TIME.toString(player.getOnlinetime())));
	
		return CommandResult.success();
		
	}
	
	 @Override
	public Text getUsage(CommandSource sender) { return null; }
	 @Override
	public Optional<Text> getHelp(CommandSource sender) { return null; }
	 @Override
	public Optional<Text> getShortDescription(CommandSource sender) { return null; }
	 @Override
	public List<String> getSuggestions(CommandSource sender, String args) throws CommandException { return null; }
	 @Override
	public boolean testPermission(CommandSource sender) { return false; }

}
