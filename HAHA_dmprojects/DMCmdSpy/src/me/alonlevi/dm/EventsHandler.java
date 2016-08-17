package me.alonlevi.dm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventsHandler implements Listener {
	Main plugin;

	public EventsHandler(Main CommandSpy) {
		plugin = CommandSpy;
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player ep = e.getPlayer();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (plugin.users.contains(p.getName())) {
				String msg = String.format(ChatColor.BLUE + "Player: " + ChatColor.GREEN + ep.getName()
						+ ChatColor.DARK_RED + " Command:'" + ChatColor.RED + e.getMessage() + ChatColor.DARK_RED + "' "
						+ ChatColor.LIGHT_PURPLE + "World: " + ChatColor.DARK_PURPLE + ep.getWorld().getName());
				if (ep.getWorld().getName().equalsIgnoreCase("Wild")) {
					if (e.getMessage().contains("/gm")) {
						p.sendMessage(creativeMessage(ep.getName(), e.getMessage()));
					} else if (e.getMessage().contains("/gamemode")) {
						p.sendMessage(creativeMessage(ep.getName(), e.getMessage()));
					} else if (e.getMessage().contains("/creative")) {
						p.sendMessage(creativeMessage(ep.getName(), e.getMessage()));
					} else if (e.getMessage().contains("/i")) {
						p.sendMessage(creativeMessage(ep.getName(), e.getMessage()));
					} else if (e.getMessage().contains("/item")) {
						p.sendMessage(creativeMessage(ep.getName(), e.getMessage()));
					}
				}
				if (!e.getMessage().contains("/cs_triggerinterface")) {
					p.sendMessage(trans(
							"(" + ep.getName() + ")(" + e.getMessage() + ")(" + ep.getWorld().getName() + ")"));
				}
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (plugin.users.contains(e.getPlayer().getName())) {
			plugin.users.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if (plugin.users.contains(e.getPlayer().getName())) {
			plugin.users.remove(e.getPlayer().getName());
		}
	}

	public String creativeMessage(String playerName, String commandMessage) {
		String msg = trans("&4&l(&9&l" + playerName + "&4&l)(&e&l" + commandMessage + "&4&l)(&a&lWILD&4&l)");
		return msg;
	}

	public String trans(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
