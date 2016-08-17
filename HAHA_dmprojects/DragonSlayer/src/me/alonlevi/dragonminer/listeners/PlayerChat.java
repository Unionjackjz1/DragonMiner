package me.alonlevi.dragonminer.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class PlayerChat implements Listener {
	PlayerJoin pj;

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		PlayerJoin a = pj;
		if (a.airbenders.contains(p.getName())) {
			e.setFormat(ChatColor.BLACK + "[" + ChatColor.GRAY + "Air Dragon" + ChatColor.BLACK + "]" + ChatColor.RESET
					+ e.getFormat());
		} else if (a.firebenders.contains(p.getName())) {
			e.setFormat(ChatColor.BLACK + "[" + ChatColor.RED + "Fire Dragon" + ChatColor.BLACK + "]" + ChatColor.RESET
					+ e.getFormat());
		} else if (a.earthbenders.contains(p.getName())) {
			e.setFormat(ChatColor.BLACK + "[" + ChatColor.GREEN + "Earth Dragon" + ChatColor.BLACK + "]"
					+ ChatColor.RESET + e.getFormat());
		} else if (a.waterbenders.contains(p.getName())) {
			e.setFormat(ChatColor.BLACK + "[" + ChatColor.BLUE + "Water Dragon" + ChatColor.BLACK + "]"
					+ ChatColor.RESET + e.getFormat());
		} else {
			e.setFormat(e.getFormat());
		}
	}

}
