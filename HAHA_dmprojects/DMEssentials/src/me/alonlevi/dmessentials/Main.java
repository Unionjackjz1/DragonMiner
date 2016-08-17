
package me.alonlevi.dmessentials;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public File playerFile;
	public File directory;

	public FileConfiguration playerConf;

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		
		directory = new File(this.getDataFolder().getAbsolutePath(), "players");

		if (!directory.exists()) {
			directory.mkdirs();
			directory.mkdir();
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		playerFile = new File(directory, e.getPlayer().getName() + ".yml");
		if (!playerFile.exists()) {
			System.out.println("New player joining!");
			Bukkit.broadcastMessage("New player is joining, he won't see this message but you would, greet him! :)");
			try {
				playerFile.createNewFile();
				while (playerFile.createNewFile()) {
					System.out.println("Attempting to create a player file!");
				}
			} catch (IOException e1) {
				System.out.println("Failed creating config for player!");
				e1.printStackTrace();
			}
		}
		playerConf = YamlConfiguration.loadConfiguration(playerFile);
		playerConf.set("path", playerFile.getAbsolutePath());
	}

}
