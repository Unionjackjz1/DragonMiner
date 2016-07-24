package com.unionjackjz1.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener{
	public void onEnable(){
		builder.clear();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){
		builder.clear();
	}
	
	ArrayList<String> builder = new ArrayList<String>();
	ArrayList<String> dev = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("builderchat") && player.hasPermission("chat.builder")) {
			if (args.length == 0){
				if (builder.contains(player.getName())){
					builder.remove(player.getName());
					dev.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Builder Chat: " + ChatColor.RED + "Disabled!");
				}else{
					builder.add(player.getName());
					dev.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Builder Chat: " + ChatColor.GREEN + "Enabled!");
				}
			}else{
				String myString = "";
				for(int i = 0; i < args.length; i++){
					String arg = args[i] + " ";
		             myString = myString + arg;
		        }
				Bukkit.broadcast(ChatColor.DARK_BLUE+ "[" + ChatColor.BLUE + "B " + player.getName() + ChatColor.DARK_BLUE+ "] " + ChatColor.WHITE + myString, "chat.builder");
			}
		}else if (!(player.hasPermission("chat.builder"))){
			player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
		}
		
		if (cmd.getName().equalsIgnoreCase("devchat") && player.hasPermission("chat.dev")){
			if (args.length == 0){
				if (dev.contains(player.getName())){
					dev.remove(player.getName());
					builder.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Dev Chat: " + ChatColor.RED + "Disabled!");
				}else{
					dev.add(player.getName());
					builder.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Dev Chat: " + ChatColor.GREEN + "Enabled!");
				}
				
			}else{
				String myString = "";
				for(int i = 0; i < args.length; i++){
					String arg = args[i] + " ";
		             myString = myString + arg;
		        }
				Bukkit.broadcast(ChatColor.GOLD + "[" + ChatColor.YELLOW + "D " + player.getName() + ChatColor.GOLD+ "] " + ChatColor.DARK_GRAY + "<" + ChatColor.RED + myString + ChatColor.DARK_GRAY + ">", "chat.dev");
			}
		}else if (!(player.hasPermission("chat.dev"))){
			player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
		}
		
		return true;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (builder.contains(player.getName())) {
			if (player.hasPermission("chat.builder")){
				Bukkit.broadcast(ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "B " + player.getName() + ChatColor.DARK_BLUE + "] " + ChatColor.WHITE + e.getMessage(), "chat.builder");
				e.setCancelled(true);
			}
		}
		
		if (dev.contains(player.getName())) {
			if (player.hasPermission("chat.dev")){
				Bukkit.broadcast(ChatColor.GOLD + "[" + ChatColor.YELLOW + "D " + player.getName() + ChatColor.GOLD+ "] " + ChatColor.DARK_GRAY + "<" + ChatColor.RED + e.getMessage() + ChatColor.DARK_GRAY + ">", "chat.dev");
				e.setCancelled(true);
			}
		}
	}
}
