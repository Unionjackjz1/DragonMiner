package me.alonlevi.dmranks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("voter")) {
			sender.sendMessage("PS: Usage: /voter <add|remove> suffix");
			if (sender.hasPermission("dm.rank.voter")) {
				if (args.length > 1) {
					if (args[0].equalsIgnoreCase("add")) {
						if (args[1].equalsIgnoreCase("suffix")) {
							sender.sendMessage("Suffix added!");
							Bukkit.dispatchCommand(getServer().getConsoleSender(), "pex user " + sender.getName() + " suffix " + args[2] + " &e[&6Voter&e] " + args[3]);
						}
					}
					if (args[0].equalsIgnoreCase("remove")) {
						if (args[1].equalsIgnoreCase("suffix")) {
							sender.sendMessage("Suffix removed!");
							Bukkit.dispatchCommand(getServer().getConsoleSender(), "pex user " + sender.getName() + " suffix " + args[2] + args[3]);
						}
					}
				}
			} else {
				sender.sendMessage("Insufficient permissions!");
			}
		}
		return false;
	}

}
