package union.haha.minigames;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public File minigame;
	public FileConfiguration mgconf;

	public int countdown = mgconf.getInt("countdown");

	int playerswaiting;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		minigame = new File(getDataFolder().getAbsolutePath(), "minigame.yml");
		mgconf = YamlConfiguration.loadConfiguration(minigame);
		if (!minigame.exists()) {
			try {
				minigame.createNewFile();
				mgconf.options().copyDefaults(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		int x = -360;
		int y = 223;
		int z = 728;
		Location waitingchunk = new Location(getServer().getWorld("flatroom"), mgconf.getInt("waitingroom_x"),
				mgconf.getInt("waitingroom_y"), mgconf.getInt("waitingroom_z"));
		if (e.getPlayer().getLocation().getChunk() == waitingchunk.getChunk()) {
			playerswaiting++;
			if (playerswaiting == 6) {
				getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

					@Override
					public void run() {
						if (countdown != 0) {
							Bukkit.broadcastMessage(ct("&6[&eDMMiniGames&6] &eGame starting in " + countdown));
							countdown--;
						}

					}

				}, 0, 20);
				if (countdown == 0) {
					// -HERE YOU START THE GAME-//
					// -AND DO ALL THOSE-//

				}
			}
		}
	}

	public String ct(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
