package union.haha.minigames;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public Game status;

	public File minigame;
	public FileConfiguration mgconf;

	public int countdown = mgconf.getInt("countdown");

	public List<String> playerswaiting = new ArrayList<String>();

	int amount_playerswaiting;
	int maxplayerswaiting = mgconf.getInt("max-players");

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
		} else {
			mgconf.options().copyDefaults(true);
		}
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location waitingchunk = new Location(getServer().getWorld("flatroom"), mgconf.getInt("waitingroom_x"),
				mgconf.getInt("waitingroom_y"), mgconf.getInt("waitingroom_z"));
		if (e.getPlayer().getLocation().getChunk() == waitingchunk.getChunk()) {
			amount_playerswaiting++;
			if (amount_playerswaiting == maxplayerswaiting) {
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

					status = Game.started;
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (playerswaiting.contains(p.getName())) {
							p.teleport(new Location(getServer().getWorld(mgconf.getString("game_world")), mgconf.getInt("game_x"), mgconf.getInt("game_y"), mgconf.getInt("game_z")));
						}
					}
					getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

						@Override
						public void run() {
							for  (Player p : Bukkit.getOnlinePlayers()) {
								if (playerswaiting.contains(p.getName())) {
									p.teleport(new Location(getServer().getWorld(mgconf.getString("end_world")), mgconf.getInt("end_x"), mgconf.getInt("end_x"), mgconf.getInt("end_x")));
								}
							}
							status = Game.ended;
							
						}
						
					}, 0, mgconf.getLong("game_time"));

				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("game")) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("join")) {
					if (args[1].equalsIgnoreCase("battles")) {
						if (amount_playerswaiting == maxplayerswaiting) {
							sender.sendMessage(ct("&cGame is full! Please wait for another game to start."));
						} else if (amount_playerswaiting < maxplayerswaiting) {
							if (status == Game.ended) {
								Player p = (Player) sender;
								p.teleport(new Location(getServer().getWorld(mgconf.getString("waitingroom_world")),
										mgconf.getInt("waitingroom_x"), mgconf.getInt("waitingroom_y"),
										mgconf.getInt("waitingroom_z")));
								p.sendMessage(ct("&aYou have been teleported to the game!"));
								playerswaiting.add(p.getName());
							}
						} else if (status == Game.started) {
							sender.sendMessage(ct("&cGame has already started! Please wait for another game to start."));
							}
					}
				} else {
					sender.sendMessage(ct("&3Error: &1Invalid arguements, commands: /game join <game>(battles)"));
				}
			}
		}
		return false;
	}

	public String ct(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
