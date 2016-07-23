package com.SkateDev.HC;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener {
	public File config;
	public Configuration configz;
	public String maintenance;

	public void onEnable() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		loadCommands();
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
		config = new File(getDataFolder().getPath(), "HahaConnected.yml");
		try {
			if (!config.exists()) {
				config.createNewFile();
			}
			configz = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		checkForMaintenance();

	}

	private void checkForMaintenance() {
		ProxyServer.getInstance().getScheduler().runAsync(this, new Runnable() {

			@Override
			public void run() {
				if (maintenance.equals("true")) {
					configz.set("maintenance", true);

				}
				if (maintenance.equals("false")) {
					configz.set("maintenance", false);

				}

			}

		});
		if (maintenance.equals("true")) {
			configz.set("maintenance", true);

		}
		if (maintenance.equals("false")) {
			configz.set("maintenance", false);

		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPostLogin(PostLoginEvent event) {
		if (configz.get("maintenance", true)) {
			if (event.getPlayer().getName().equalsIgnoreCase("HahaYouFell")
					|| event.getPlayer().getName().equalsIgnoreCase("lolextrami")) {

			} else {
				event.getPlayer().disconnect("We're on maintenance");
			}
		}
		ProxyServer.getInstance()
				.broadcast(new TextComponent(
						ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "HahaNetwork" + ChatColor.DARK_GRAY + "] "
								+ ChatColor.YELLOW + "" + event.getPlayer().getName() + " has joined the network!"));
	}

	private void loadCommands() {
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new servers_list());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new maintenance());

	}

}
