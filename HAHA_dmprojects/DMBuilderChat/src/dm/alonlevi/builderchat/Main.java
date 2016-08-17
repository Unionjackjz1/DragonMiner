package dm.alonlevi.builderchat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {

	public List<Player> users = new ArrayList<Player>();

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("builderchat")) {
			if (sender.getName().equalsIgnoreCase("Unionjackjz1")) {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("add")) {
						if (!Bukkit.getPlayer(args[1]).hasPlayedBefore()) {
							sender.sendMessage(
									ChatColor.RED + "Hey, the player you specified has never played on thi server!");
						} else {
							users.add(Bukkit.getPlayer(args[1]));
							sender.sendMessage(
									ChatColor.GREEN + "Successfully added player to ArrayList<String> users ! :D");
						}
					} else if (args[0].equalsIgnoreCase("remove")) {
						if (!users.contains(Bukkit.getPlayer(args[1]))) {
							sender.sendMessage(ChatColor.RED + "Hey, the player you specified is not in builder chat!");
						} else {
							users.remove(Bukkit.getPlayer(args[1]));
							sender.sendMessage(
									ChatColor.GREEN + "Successfully removed player from ArrayList<String> users ! :D");
						}
					} else {
						sender.sendMessage("Incorrect syntax!");
					}
				} else {
					sender.sendMessage("Not enough arguements!");
				}
			} else if (sender.getName().equalsIgnoreCase("HahaYouFell")) {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("add")) {
						if (!Bukkit.getPlayer(args[1]).hasPlayedBefore()) {
							sender.sendMessage(
									ChatColor.RED + "Hey, the player you specified has never played on thi server!");
						} else {
							users.add(Bukkit.getPlayer(args[1]));
							sender.sendMessage(
									ChatColor.GREEN + "Successfully added player to ArrayList<String> users ! :D");
						}
					} else if (args[0].equalsIgnoreCase("remove")) {
						if (!users.contains(Bukkit.getPlayer(args[1]))) {
							sender.sendMessage(ChatColor.RED + "Hey, the player you specified is not in builder chat!");
						} else {
							users.remove(Bukkit.getPlayer(args[1]));
							sender.sendMessage(
									ChatColor.GREEN + "Successfully removed player from ArrayList<String> users ! :D");
						}
					} else {
						sender.sendMessage("Incorrect syntax!");
					}
				} else {
					sender.sendMessage("Not enough arguements!");
				}
			}

			if (!((sender.getName().equalsIgnoreCase("HahaYouFell"))
					|| (sender.getName().equalsIgnoreCase("Unionjackjz1")))) {
				sender.sendMessage(ChatColor.RED + "Hey, you can't use this command!");
			}
		}
		return false;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (users.contains(p.getName())) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.GREEN + "[BUILDER] " + e.getPlayer().getName() + ": " + e.getMessage());
			}
		}
	}

}
