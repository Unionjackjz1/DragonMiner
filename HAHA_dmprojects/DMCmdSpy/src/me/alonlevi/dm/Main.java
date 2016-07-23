package me.alonlevi.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
	public List<String> users = new ArrayList<String>();
	public List<String> owners = new ArrayList<String>();
	public List<String> staff = new ArrayList<String>();
	public List<String> veterans = new ArrayList<String>();

	Logger log;

	public void onEnable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.isOp()) {
				if (p.hasPermission("chat.owner")) {
					owners.add(p.getName());
				}
			}
			if (p.hasPermission("chat.staff")) {
				staff.add(p.getName());
			}
		}
		this.log = getLogger();
		getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
	}

	public void onDisable() {
		owners.clear();
		users.clear();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("chat")) {
			Player p = (Player) sender;
			if (sender instanceof Player) {
				if (args.length < 1) {

					p.sendMessage(trans("&aError! You need to do /chat <chat> <message>"));

				}

				if (args.length > 0) {
					if (args.length > 1) {
						StringBuilder str = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							str.append(args[i] + " ");
						}
						if (args[0].equalsIgnoreCase("Op")) {

						} else if (args[0].equalsIgnoreCase("Staff")) {

						} else if (args[0].equalsIgnoreCase("Owner")) {
							if (owners.contains(p.getName())) {
								for (Player owners : Bukkit.getOnlinePlayers()) {
									if (this.owners.contains(owners.getName())) {
										owners.sendMessage(
												trans("&e[OWNER] &r" + p.getName() + ": " + str.toString().trim()));
									}
								}
							}

						} else if (args[0].equalsIgnoreCase("Veteran")) {

						}
						if (!args[0].equalsIgnoreCase("Op")) {
							if (!args[0].equalsIgnoreCase("Veteran")) {
								if (!args[0].equalsIgnoreCase("Owner")) {
									if (!args[0].equalsIgnoreCase("Staff")) {
										p.sendMessage(trans("&cCould not find chat &9" + args[0]));
									}
								}
							}

						}
					}
				}
			}

		}

		if (cmd.getName().equalsIgnoreCase("cmdspy")) {
			Bukkit.getServer().getWorld(this.getServer().getWorldContainer().getName()).getFullTime();
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("commandspy.toggle")) {
					if (users.contains(p.getName())) {
						users.remove(p.getName());
						p.sendMessage(ChatColor.YELLOW + "[DMCS] " + ChatColor.WHITE + "You" + ChatColor.RED
								+ " disabled " + ChatColor.WHITE + "CommandSpy");
						return true;
					} else {
						users.add(p.getName());
						p.sendMessage(ChatColor.YELLOW + "[DMCS] " + ChatColor.WHITE + "You" + ChatColor.GREEN
								+ " enabled " + ChatColor.WHITE + "CommandSpy");
						return true;
					}
				} else {
					p.sendMessage(ChatColor.RED + "Error: You don't have permission to do that!");
					return true;
				}
			} else {
				sender.sendMessage("sorry, you need to be a player to use this command");
				return true;
			}
		}
		return false;
	}

	private String trans(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}