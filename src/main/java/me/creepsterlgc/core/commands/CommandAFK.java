package main.java.me.creepsterlgc.core.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.me.creepsterlgc.core.Controller;
import main.java.me.creepsterlgc.core.customized.CoreDatabase;
import main.java.me.creepsterlgc.core.customized.CorePlayer;
import main.java.me.creepsterlgc.core.files.FileConfig;
import main.java.me.creepsterlgc.core.utils.PermissionsUtils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;


public class CommandAFK implements CommandCallable {
	
	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		if(!PermissionsUtils.has(sender, "core.afk")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		Player player = (Player) sender;
		CorePlayer p = CoreDatabase.getPlayer(player.getUniqueId().toString());
		
		double time = System.currentTimeMillis();
		
		if(p.getAFK()) {
			Controller.broadcast(Texts.of(TextColors.YELLOW, player.getName(), TextColors.GRAY, " is no longer afk."));
			p.setAFK(false);
			p.setLastaction(time);
		}
		else if(!p.getAFK()) {
			Controller.broadcast(Texts.of(TextColors.YELLOW, player.getName(), TextColors.GRAY, " is now afk."));
			p.setAFK(true);
			p.setLastaction(time - FileConfig.AFK_TIMER_IN_SECONDS() * 1000);
		}
		
		CoreDatabase.addPlayer(p.getUUID(), p);
		
		return CommandResult.success();
		
	}

	private final Text usage = Texts.builder("Usage: /afk").color(TextColors.YELLOW).build();
	private final Text help = Texts.builder("Help: /afk").color(TextColors.YELLOW).build();
	private final Text description = Texts.builder("Core | AFK Command").color(TextColors.YELLOW).build();
	private List<String> suggestions = new ArrayList<String>();
	private String permission = "";
	
	@Override public Text getUsage(CommandSource sender) { return usage; }
	@Override public Optional<Text> getHelp(CommandSource sender) { return Optional.of(help); }
	@Override public Optional<Text> getShortDescription(CommandSource sender) { return Optional.of(description); }
	@Override public List<String> getSuggestions(CommandSource sender, String args) throws CommandException { return suggestions; }
	@Override public boolean testPermission(CommandSource sender) { return permission.equals("") ? true : sender.hasPermission(permission); }

}
