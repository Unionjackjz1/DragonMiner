package me.alonlevi.dragonminer;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.alonlevi.dragonminer.listeners.Moves;
import me.alonlevi.dragonminer.listeners.PlayerJoin;

public class Main extends JavaPlugin {
	
	public PlayerJoin pj;
	
	public File bendersDir;
	public File errorsDir;
	public File errors;
	public File bendersFile;
	public FileConfiguration bendersConf;

	public void onEnable() {
		PlayerJoin join = pj;
		bendersDir = new File("DragonSlayers", "benders");
		errorsDir = new File("DragonSlayers", "errors");
		errors = new File(errorsDir, "errors.yml");
		bendersFile = new File(bendersDir, "benders.yml");
		if (!bendersDir.exists()) {
			bendersDir.mkdir();
			bendersDir.mkdirs();
		}
		if (!errorsDir.exists()) {
			errorsDir.mkdir();
			errorsDir.mkdirs();
		}
		if (!bendersFile.exists()) {
			try {
				bendersFile.createNewFile();
				bendersConf = YamlConfiguration.loadConfiguration(bendersFile);
				bendersConf.options().copyDefaults(true);
			} catch (IOException e12323) {
				System.out.println("Error generating benders.yml please contact HahaYouFell");
				e12323.printStackTrace();
			}
		}
		if (!errors.exists()) {
			try {
				errors.createNewFile();
			} catch (IOException e23123) {
				System.out.println("Error generating errors.yml please contact HahaYouFell");
				e23123.printStackTrace();
			}
		}
		join.airbenders.add("HahaYouFell");
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Moves(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EffectChecker(this), this);
	}

}
