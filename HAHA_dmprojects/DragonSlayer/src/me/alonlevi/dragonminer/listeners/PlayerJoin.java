package me.alonlevi.dragonminer.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.alonlevi.dragonminer.Main;

public class PlayerJoin implements Listener {
	Main m;

	public ArrayList<String> firebenders = new ArrayList<String>();
	public ArrayList<String> airbenders = new ArrayList<String>();;
	public ArrayList<String> waterbenders = new ArrayList<String>();;
	public ArrayList<String> earthbenders = new ArrayList<String>();;

	public PlayerJoin(Main main) {
		this.m = main;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Main main = this.m;
		Player p = e.getPlayer();
		if (p.getName().equalsIgnoreCase("HahaYouFell")) {
			p.getInventory().addItem(new ItemStack(Material.getMaterial(351/10)));
			ItemMeta im = p.getInventory().getItemInHand().getItemMeta();
			im.setLore(Arrays.asList("Green Dragamite §"));
			im.setDisplayName("Green Dragamite §");
			p.getInventory().getItemInHand().setItemMeta(im);
		}
		if (p.hasPermission("dragonminer.rank.firedragon")) {
			main.bendersConf = YamlConfiguration.loadConfiguration(main.bendersFile);
			FileConfiguration bendersConf = main.bendersConf;
			firebenders.add(e.getPlayer().getName());
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.BLUE + "is a "
					+ ChatColor.RED + "Fire Dragon!");
		}
		if (p.hasPermission("dragonminer.rank.airdragon")) {
			main.bendersConf = YamlConfiguration.loadConfiguration(main.bendersFile);
			FileConfiguration bendersConf = main.bendersConf;
			airbenders.add(e.getPlayer().getName());
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.BLUE + "is an "
					+ ChatColor.GRAY + "Air Dragon!");
		}
		if (p.hasPermission("dragonminer.rank.waterdragon")) {
			main.bendersConf = YamlConfiguration.loadConfiguration(main.bendersFile);
			FileConfiguration bendersConf = main.bendersConf;
			waterbenders.add(e.getPlayer().getName());
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.BLUE + "is a "
					+ ChatColor.DARK_BLUE + "Water Dragon!");
		}
		if (p.hasPermission("dragonminer.rank.earthdragon")) {
			main.bendersConf = YamlConfiguration.loadConfiguration(main.bendersFile);
			FileConfiguration bendersConf = main.bendersConf;
			earthbenders.add(e.getPlayer().getName());
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.BLUE + "is an "
					+ ChatColor.GREEN + "Earth Dragon!");
		}

	}
}