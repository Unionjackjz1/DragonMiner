package com.unionjackjz11.main;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	boolean particleOn = false;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		Player player = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("pp") && player.hasPermission("particle.view")) {
			if (particleOn) {
				particleOn = false;
				player.sendMessage("Particlez Off");
			}
			else {
				particleOn = true;
				player.sendMessage("Particlez On");
			}
		} 
		Location loc = player.getLocation();
		onMove(loc);
		
		return true;
	}
	
	public void onMove(PlayerMoveEvent e, Location loc){
		for(int i = 0; i < 4; i++) {
			loc.getWorld().playEffect(loc, Effect.FLAME, 165);
		}
	}
	
}
