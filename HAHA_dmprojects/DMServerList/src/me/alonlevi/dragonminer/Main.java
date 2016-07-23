package me.alonlevi.dragonminer;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_10_R1.ServerPing;

public class Main extends JavaPlugin {

	public static Main m1;
	
	public String motd = getCurrentMOTD();

	public File dir = new File("DragonMiner", "MOTD");

	public File file = new File(dir, "motd.yml");
	public FileConfiguration fileconf = YamlConfiguration.loadConfiguration(file);

	public void onEnable() {
		if (!dir.exists()) {
			dir.mkdir();
			dir.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
				fileconf.options().copyDefaults(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			motd = fileconf.getString("MOTD");
		}
	}
	
	public static <liveMOTD> String getCurrentLiveMOTD() {
		Main m = m1;
		m.reloadConfig();
		String liveMOTD = m.fileconf.getString("MOTD");
		return liveMOTD;
	}
	
	public <setMOTD> void onPing(ServerListPingEvent e) {
		e.setMotd(motd);
	}
	
	public static <getMOTD> String getCurrentMOTD() {
		Main m = m1;
		String currentMotd = m.getServer().getMotd();
		return currentMotd;
	}
	
	
	

}
