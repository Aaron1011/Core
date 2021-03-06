package main.java.me.creepsterlgc.core.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.me.creepsterlgc.core.Controller;
import main.java.me.creepsterlgc.core.customized.CoreDatabase;
import main.java.me.creepsterlgc.core.customized.CoreMute;
import main.java.me.creepsterlgc.core.customized.CorePlayer;
import main.java.me.creepsterlgc.core.utils.CommandUtils;
import main.java.me.creepsterlgc.core.utils.PermissionsUtils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;


public class CommandReply implements CommandCallable {
	
	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {
		
		String[] args = arguments.split(" ");
		
		if(sender instanceof Player == false) { sender.sendMessage(Texts.builder("Cannot be run by the console!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		if(!PermissionsUtils.has(sender, "core.msg")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }
		
		if(args.length < 1) { sender.sendMessage(Texts.builder("Usage: /r <message>").color(TextColors.YELLOW).build()); return CommandResult.success(); }
		
		String message = CommandUtils.combineArgs(0, args);
		
		if(sender instanceof Player) {
			
			Player checking = (Player) sender;
			
	    	CoreMute mute = CoreDatabase.getMute(checking.getUniqueId().toString());
	    	
	    	if(mute != null) {
	    		
	    		if(mute.getDuration() != 0 && mute.getDuration() <= System.currentTimeMillis()) {
	    			CoreDatabase.removeMute(checking.getUniqueId().toString());
	    			mute.delete();
	    		}
	    		else {
		    		checking.sendMessage(Texts.of(TextColors.RED, mute.getReason()));
		    		return CommandResult.success();
	    		}
	    	}
		}
		
		Player player = (Player) sender;
		CorePlayer p = CoreDatabase.getPlayer(player.getUniqueId().toString());
		
		if(p.getReply().equalsIgnoreCase("")) {
			sender.sendMessage(Texts.of(TextColors.RED, "No message to reply to!"));
			return CommandResult.success();
		}
		
		if(!Controller.getGame().getServer().getPlayer(p.getReply()).isPresent()) {
			sender.sendMessage(Texts.of(TextColors.RED, "Player is not longer online!"));
			return CommandResult.success();
		}
		
		Player target = Controller.getGame().getServer().getPlayer(p.getReply()).get();
		
		sender.sendMessage(Texts.of(TextColors.YELLOW, "To ", target.getName(), ": ", TextColors.WHITE, message));
		target.sendMessage(Texts.of(TextColors.YELLOW, "From ", sender.getName(), ": ", TextColors.WHITE, message));
		
		return CommandResult.success();
		
	}

	private final Text usage = Texts.builder("Usage: /reply").color(TextColors.YELLOW).build();
	private final Text help = Texts.builder("Help: /reply").color(TextColors.YELLOW).build();
	private final Text description = Texts.builder("Core | Reply Command").color(TextColors.YELLOW).build();
	private List<String> suggestions = new ArrayList<String>();
	private String permission = "";
	
	@Override
	public Text getUsage(CommandSource sender) { return usage; }
	@Override
	public Optional<Text> getHelp(CommandSource sender) { return Optional.of(help); }
	@Override
	public Optional<Text> getShortDescription(CommandSource sender) { return Optional.of(description); }
	@Override
	public List<String> getSuggestions(CommandSource sender, String args) throws CommandException { return suggestions; }
	@Override
	public boolean testPermission(CommandSource sender) { return permission.equals("") ? true : sender.hasPermission(permission); }

}
