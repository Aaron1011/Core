package me.creepsterlgc.core.customized;

import me.creepsterlgc.core.Controller;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.sink.MessageSink;

public class SERVER {
	
	public static MessageSink sink;
	
	public static Player getPlayer(String player) {
		
		Player found = null;
		for(Player p : Controller.getServer().getOnlinePlayers()) {
			if(!p.getName().toLowerCase().contains(player.toLowerCase())) continue;
			if(found != null) return null; found = p;
		}
		
		return found;
		
	}
	
	public static void broadcast(Text text) {
		sink.sendMessage(text);
	}
	
	public static void heartbeat() {
		
		if(!CONFIG.AFK_ENABLE_SYSTEM()) return;
		
		for(Player player : Controller.getServer().getOnlinePlayers()) {
			
			PLAYER p = DATABASE.getPlayer(player.getUniqueId().toString());
			if(p == null) continue;
			
			double time = System.currentTimeMillis();
			time -= CONFIG.AFK_TIMER_IN_SECONDS() * 1000;
			time -= CONFIG.AFK_KICK_AFTER() * 1000;
			
			if(p.getAFK() && CONFIG.AFK_KICK_ENABLE() && p.getLastaction() < time) {
				
				player.kick(Texts.of(Texts.of(TextColors.RED, "You have been kicked for being AFK!")));
				
			}
			
			if(p.getAFK()) continue;
			if(p.getLastaction() > System.currentTimeMillis() - CONFIG.AFK_TIMER_IN_SECONDS() * 1000) continue;
			
			p.setAFK(true);
			Controller.broadcast(Texts.of(TextColors.YELLOW, player.getName(), TextColors.GRAY, " is now afk."));
			
		}
		
	}

}