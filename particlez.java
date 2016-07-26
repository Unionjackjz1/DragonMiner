package com.unionjackjz11.main;

import java.util.ArrayList;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	public void onEnable(){
		getServer().getPluginManager().registerEvents((Listener) this, this);
	}
	
	
	ArrayList<String> particlePlayers = new ArrayList<String>();
	boolean particleOn = false;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		Player player = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("pp") && player.hasPermission("particle.view")) {
			if (particlePlayers.contains(player.getName())) {
				player.sendMessage("Particlez Off");
				particlePlayers.remove(player.getName());
			}
			else {
				player.sendMessage("Particlez On");
				particlePlayers.add(player.getName());
			}
		} 
		
		return true;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player player = e.getPlayer();
		if (particlePlayers.contains(player.getName())){
	    	Location loc = player.getLocation();	        
	        int radius = 1;
	        for(double y = 0; y <= 50; y+=0.05) {
	            double x = radius * Math.cos(y);
	            double z = radius * Math.sin(y);
	            //Error on the line below, not sure why
	            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(Effect.FIREWORKS_SPARK, true, loc.getX(), loc.getY(), loc.getZ(), 0, 0, 0, 0.001, 1);
	            ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
	            
	        }
		}else{}
	}
	
}
