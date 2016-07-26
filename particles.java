package com.unionjackjz11.main;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	boolean particleOn = false;

	public Location loctrans;

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("pp") && player.hasPermission("particle.view")) {
			if (particleOn) {
				particleOn = false;
				player.sendMessage("Particlez Off");
			} else {
				particleOn = true;
				player.sendMessage("Particlez On");
				Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						if (particleOn = true) {
							for (Player player : Bukkit.getOnlinePlayers()) {
								if (player.getDisplayName() == "Unionjackjz1") {
									for (int i = 0; i < 4; i++) {
										loctrans.getWorld().playEffect(loctrans, Effect.FLAME, 165);
									}
								}
							}
						} else {
							
						}
					}
				}, 0, 10);
			}
		}
		Location loc = player.getLocation();
		this.loctrans = loc;

		return true;
	}

}
