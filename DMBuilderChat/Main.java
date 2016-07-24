package com.unionjackjz1.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements CommandExecutor, Listener{
	public void onEnable(){
		users.clear();
	}
	
	public void onDisable(){
		users.clear();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ArrayList<String> users = new ArrayList();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("builderchat") && player.hasPermission("chat.builder")) {
			if (args.length == 0){
				if (users.contains(player.getName())){
					users.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Builder Chat: " + ChatColor.RED + "Disabled!");
				}else{
					users.add(player.getName());
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
		
		return true;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void adminToggle(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (users.contains(player.getName())) {
			if (player.hasPermission("chat.builder")){
				Bukkit.broadcast(ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "B " + player.getName() + ChatColor.DARK_BLUE + "] " + ChatColor.WHITE + e.getMessage(), "chat.builder");
				e.setCancelled(true);
			}
		}
	}
}
