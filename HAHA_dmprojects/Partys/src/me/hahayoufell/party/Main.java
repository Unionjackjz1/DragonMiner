package me.hahayoufell.party;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	File partydir;
	File players;

	List<String> pl = YamlConfiguration.loadConfiguration(players).getStringList("players");

	int partysamount;

	public List<String> partychat;

	public void createParty(String name, List<String> players) {
		this.partydir = new File(getDataFolder().getAbsolutePath(), name);
		this.players = new File(partydir, players + ".yml");
		if (partydir.exists()) {

		}

		if (!partydir.exists()) {
			partydir.mkdirs();

			partychat = players;
		}
		if (!this.players.exists()) {
			try {
				this.players.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void loadPartys() {
		this.partysamount = getDataFolder().listFiles().length;
	}

	public void deleteParty(String partyname) {
		partydir = new File(getDataFolder().getAbsolutePath(), partyname);
		if (partydir.isDirectory()) {
			partydir.delete();
		}
	}

	public void onEnable() {
		loadPartys();
		System.out.println("" + partysamount + " partys have been loaded!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		if (label.equalsIgnoreCase("party")) {
			if (args.length == 0) {
				sender.sendMessage(ct("&8Commands: "));
				sender.sendMessage(ct("/party create <party name> <players>"));
			}
			if (args.length > 1) {

				StringBuilder chat = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					chat.append(args[i] + " ");
			}
		
				if (args[0].equalsIgnoreCase("chat")) {
					for (Player pmate : Bukkit.getOnlinePlayers()) {
						if (pl.contains(pmate.getName())) {
							pmate.sendMessage(ct("&8[PARTYCHAT] &4" + sender.getName() + ": &a" + chat.toString().trim()));
						}
					}
				}
			}
			if (args.length > 2) {

				StringBuilder str = new StringBuilder();
				for (int i = 2; i < args.length; i++) {
					str.append(args[i] + " ");
			}
		
				if (args[0].equalsIgnoreCase("create")) {
					createParty(args[1], Arrays.asList(str.toString().trim()));
					pl.add(str.toString().trim());
					reloadConfig();
				}
			}
		}
		return false;
	}

	public String ct(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
