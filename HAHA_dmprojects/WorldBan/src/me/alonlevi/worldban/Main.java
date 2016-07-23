package me.alonlevi.worldban;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	ArrayList<String> session_ban = new ArrayList<String>();

	public void onEnable() {
		System.out.println("[DMWorldBan] Done loading, enabled!");
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onLoad() {
		System.out.println("[DMWorldBan] Loading, please wait.");
	}

	public void onDisable() {
		System.out.println("[DMWorldBan] Disabling, please wait.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("worldban")) {
			if (args.length == 1) {
				sender.sendMessage(ChatColor.GREEN + "Successfully banned player " + args[0] + " from world Wild");
				final Player targetplayer = Bukkit.getPlayer(args[0]);
				session_ban.add(targetplayer.getName());
				Bukkit.broadcastMessage(
						ChatColor.BLUE + "Player " + targetplayer.getName() + " has been banned from world Wild");
				Location spawn = new Location(Bukkit.getWorld("DragonSpawn"), 105, 72, 278);
				if (targetplayer.getLocation().getWorld().getName().equalsIgnoreCase("Wild")) {
					targetplayer.teleport(spawn);
					targetplayer.sendMessage(ChatColor.DARK_RED + "You have been banned from this world!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

						@Override
						public void run() {
							session_ban.remove(targetplayer.getName());
							Bukkit.broadcastMessage(ChatColor.BLUE + "Player " + targetplayer.getName()
									+ " has been unbanned from world Wild");

						}

					}, 20 * 21600);
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid Arguements!");
			}
		}

		if (label.equalsIgnoreCase("worldunban")) {
			if (args.length == 1) {
				sender.sendMessage(ChatColor.GREEN + "Successfully unbanned player " + args[0] + " from world Wild");
				final Player targetplayer = Bukkit.getPlayer(args[0]);
				session_ban.remove(targetplayer.getName());
				Bukkit.broadcastMessage(
						ChatColor.BLUE + "Player " + targetplayer.getName() + " has been unbanned from world Wild");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Invalid Arguements!");
		}
		return false;

	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Location spawn = new Location(Bukkit.getWorld("DragonSpawn"), -105, 72, 278);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (session_ban.contains(p.getName())) {
				if (p.getWorld().getName().equalsIgnoreCase("Wild")) {
					p.sendMessage(ChatColor.DARK_RED + "You have been banned from this world!");
					p.teleport(spawn);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Location spawn = new Location(Bukkit.getWorld("DragonSpawn"), -105, 72, 278);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (session_ban.contains(p.getName())) {
				if (p.getWorld().getName().equalsIgnoreCase("Wild")) {
					p.sendMessage(ChatColor.DARK_RED + "You have been banned from this world!");
					p.teleport(spawn);
				}
			}
		}
	}

}
