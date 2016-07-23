package dm.alonlevi.spells;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public ArrayList<String> frozen = new ArrayList<String>();
	public ArrayList<String> blacklistfreezing = new ArrayList<String>();

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Freeze(this), this);
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@EventHandler
	public void onItemHeldEvent(PlayerItemHeldEvent e) {
		final Player p = e.getPlayer();

		final ItemStack is = p.getItemInHand();
		final ItemMeta im = p.getItemInHand().getItemMeta();
		if (im.getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Freezing Wand")) {
			if (im.getLore().contains("§#FRTAG")) {
				if (is.getType().equals(Material.STICK)) {
					if (getServer().getOnlinePlayers().size() > 1) {
						for (Player pl : Bukkit.getOnlinePlayers()) {
							if (pl.getLocation().getChunk() == p.getLocation().getChunk()) {
								frozen.add(pl.getName());
								frozen.remove(p.getName());
								Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

									@Override
									public void run() {
										frozen.clear();
										p.sendMessage(ChatColor.AQUA + "You'll get your wand again in 15 seconds.");
										p.getInventory().clear(p.getInventory().getHeldItemSlot());
										blacklistfreezing.add(p.getName());
									}

								}, 100);
								Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

									@Override
									public void run() {
										blacklistfreezing.remove(p.getName());
										p.performCommand("spell wand freezing");
									}

								}, 400);
							}
						}
					}
				} else {
					frozen.clear();
				}
			}
		}

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("spell")) {
			if (args[0].equalsIgnoreCase("wand")) {
				if (args[1].equalsIgnoreCase("freezing")) {
					if (sender instanceof Player) {
						if (blacklistfreezing.contains(sender.getName())) {

							sender.sendMessage(ChatColor.AQUA
									+ "You can use this command again when the 15 seconds cool down will end");
						} else {
							Player p = (Player) sender;

							ItemStack is = new ItemStack(Material.STICK);
							ItemMeta freezingwand = is.getItemMeta();
							freezingwand.setDisplayName(ChatColor.AQUA + "Freezing Wand");
							freezingwand.setLore(Arrays.asList("§#FRTAG"));
							is.setItemMeta(freezingwand);

							p.getInventory().addItem(is);
							sender.sendMessage(ChatColor.AQUA + "You have been given a freezing wand!");
						}
					}
				}
			}
		}
		return false;
	}

}
