package com.unionjackjz1.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ArrayList<String> users = new ArrayList();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("builderchat") && player.hasPermission("chat.builder")) {
			if (args.length == 0){
				if (users.contains(player.getName())){
					users.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "Builder Chat: " + ChatColor.RED + "DISABLED!");
				}else{
					users.add(player.getName());
					player.sendMessage(ChatColor.GOLD + "Builder Chat: " + ChatColor.GREEN + "ENABLED!");
				}
			}else{
				String myString = "";
				for(int i = 0; i < args.length; i++){
					String arg = args[i] + " ";
		             myString = myString + arg;
		        }
				Bukkit.broadcast(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + player.getName() + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + myString, "chat.builder");
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (users.contains(p.getName())) {
				e.setCancelled(true);
				Bukkit.broadcast(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + p.getName() + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + e.getMessage(), "chat.builder");
			}
		}
	}
}
