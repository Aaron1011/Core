package main.java.me.creepsterlgc.core.api;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;

import main.java.me.creepsterlgc.core.Controller;
import main.java.me.creepsterlgc.core.customized.CoreDatabase;
import main.java.me.creepsterlgc.core.customized.CoreSpawn;

public class CoreAPISpawnManager {

	public static CoreAPISpawnManager instance;
	
	public boolean teleport(Player player, String spawn) {
		CoreSpawn s = CoreDatabase.getSpawn(spawn.toLowerCase());
		if(s == null) return false;
		if(!Controller.getServer().getWorld(s.getWorld()).isPresent()) return false;
		player.setLocation(new Location<World>(Controller.getServer().getWorld(s.getWorld()).get(), new Vector3d(s.getX(), s.getY(), s.getZ())));
		return true;
	}
	
	/*
	 * RETURNS: true if the player could be teleported, false if not.
	 * 
	 * player = The target player
	 * spawn = Name of the spawn
	 * 
	 * EXAMPLE: (player, "market")
	 * Would teleport player to spawn "market".
	 * 
	 */
	
}
