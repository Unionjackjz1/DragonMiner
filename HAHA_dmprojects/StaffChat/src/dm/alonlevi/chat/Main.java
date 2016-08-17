package dm.alonlevi.chat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {
    private static Main Chat;
    private static FileConfiguration config;
	private static Plugin plugin;
	
	public ArrayList<String> toggled = new ArrayList<String>();


    @Override
    public void onEnable() {
        Chat = this;
        config = this.getConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        getLogger().info("Enabled");
    }

    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        command.getName().equalsIgnoreCase("staffchat");
        {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("Staffchat.admin"))
                    this.reloadConfig();
                sender.sendMessage(ChatColor.YELLOW + "Config Reloaded");

            } else if (args.length > 0) {

                StringBuilder stringBuilder = new StringBuilder();
                for (String value : args) {
                    stringBuilder.append(value).append(" ");
                }


                String message = stringBuilder.toString();
                message = ChatColor.translateAlternateColorCodes('&', message);

                String prefix = this.getConfig().getString("prefix");
                String StaffMessage = this.getConfig().getString("StaffMessage");

                prefix = ChatColor.translateAlternateColorCodes('&', prefix);
                StaffMessage = ChatColor.translateAlternateColorCodes('&', StaffMessage);
                StaffMessage = StaffMessage.replace("{message}", message);

                StaffMessage = ChatColor.translateAlternateColorCodes('&', StaffMessage);
                StaffMessage = StaffMessage.replace("{Sender}", sender.getName());


                Bukkit.broadcast(prefix + StaffMessage, "Staffchat.use");

                return true;
            } else if (args.length < 1) {
            	toggled.add(sender.getName());
            	sender.sendMessage(ChatColor.GREEN + "Your staff chat has been toggled!");
            }

        }
        return true;
    }
    
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
    	if (toggled.contains(e.getPlayer().getName())) {
    		
    		e.setCancelled(true);
    		
    		String message = e.getMessage();
    		Player sender = e.getPlayer();
    		
            String prefix = this.getConfig().getString("prefix");
            String StaffMessage = this.getConfig().getString("StaffMessage");

            prefix = ChatColor.translateAlternateColorCodes('&', prefix);
            StaffMessage = ChatColor.translateAlternateColorCodes('&', StaffMessage);
            StaffMessage = StaffMessage.replace("{message}", message);

            StaffMessage = ChatColor.translateAlternateColorCodes('&', StaffMessage);
            StaffMessage = StaffMessage.replace("{Sender}", sender.getName());


            Bukkit.broadcast(prefix + StaffMessage, "Staffchat.use");
    	}
    }
}



