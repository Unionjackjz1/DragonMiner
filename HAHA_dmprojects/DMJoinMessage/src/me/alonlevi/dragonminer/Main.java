package me.alonlevi.dragonminer;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public List<String> legend;
	public List<String> CoO;
	public List<String> Operators;
	public List<String> Admins;

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		legend = getConfig().getStringList("legends");
		CoO = getConfig().getStringList("Co-Owners");
		Operators = getConfig().getStringList("Operators");
		Admins = getConfig().getStringList("Admins");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().equalsIgnoreCase("HahaYouFell")) {
			Bukkit.broadcastMessage(ct("&cHahaYouFell &bis the &dHead-&cDev &bof &aDragon&eMiner!"));
		}
		if (legend.contains(e.getPlayer().getName())) {
			Bukkit.broadcastMessage(ct("&c" + e.getPlayer().getName() + "&bis a &dLegend &bof &aDragon&eMiner!"));
		}

		if (CoO.contains(e.getPlayer().getName())) {
			Bukkit.broadcastMessage(ct("&c" + e.getPlayer().getName() + "&bis a &dCo-Owner &bof &aDragon&eMiner!"));
		}

		if (Operators.contains(e.getPlayer().getName())) {
			Bukkit.broadcastMessage(ct("&c" + e.getPlayer().getName() + "&bis an &dOperator &bof &aDragon&eMiner!"));
		}

		if (Admins.contains(e.getPlayer().getName())) {
			Bukkit.broadcastMessage(ct("&c" + e.getPlayer().getName() + "&bis an &dAdmin &bof &aDragon&eMiner!"));
		}

	}

	public String ct(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
