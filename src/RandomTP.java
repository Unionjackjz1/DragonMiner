


import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.Location;
import static org.bukkit.Material.AIR;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerMoveEvent;

public class RandomTP extends JavaPlugin implements Listener
{
    private static RandomTP plugin;
    public FileConfiguration config = this.getConfig();
    @Override
    public void onEnable()
    {
        plugin = this;
        getServer().getPluginManager().registerEvents((Listener) this, this);
        if(!config.contains("blocked"))
        {
            config.set("blocked", Arrays.asList(8,9,10,11,81,18));
            saveConfig();
        }
    }
    
    @Override
    public void onDisable()
    {
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(getCmd(cmd, "randomtp"))
        {
            if(args.length == 1)
            {
                if(!sender.hasPermission("rtp.others"))
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to tp others randomly. (rtp.others)");
                    return true;
                }
                Player player = getPlayer(args[0]);
                player.teleport(getSafeLocation(getRandomLocation(player.getWorld())));
                sender.sendMessage(ChatColor.GREEN + "Teleported " + player.getName() + " to a random location.");
                player.sendMessage(ChatColor.GREEN + "You were teleported to a random location.");
                return true;
            }
            if(args.length < 1)
            {
                ((Player) sender).teleport(getSafeLocation(getRandomLocation(((Player) sender).getWorld())));
                sender.sendMessage(ChatColor.GREEN + "You were teleported to a random location.");
                return true;
            }
            else
            {
                return false;
            }
        }
        if(getCmd(cmd, "rtpportal"))
        {
            if(args[0].equalsIgnoreCase("add"))
            {
                config.set("portals." + args[1].toLowerCase(), ((Player) sender).getLocation().getBlockX() + "," + ((Player) sender).getLocation().getBlockY() + "," + ((Player) sender).getLocation().getBlockZ());
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Created portal");
                return true;
            }
            if(args[0].equalsIgnoreCase("remove"))
            {
                config.set("portals." + args[1].toLowerCase(), null);
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Removed portal");
                return true;
            }
        }
        return true;
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Set<String> keys = config.getConfigurationSection("portals").getKeys(false);
        try
        {
            for(final String key : keys)
            {
                if((config.getString("portals." + key)).equals(e.getPlayer().getLocation().getBlockX() + "," + e.getPlayer().getLocation().getBlockY() + "," + e.getPlayer().getLocation().getBlockZ()))
                {
                    e.getPlayer().teleport(getSafeLocation(getRandomLocation((e.getPlayer().getWorld()))));
                    e.getPlayer().sendMessage(ChatColor.GREEN + "You were teleported to a random location.");
                }
            }
        }
        catch(NullPointerException ex)
        {
            
        }
    }
    
    public boolean getCmd(Command cmd, String string)
    {
        return cmd.getName().equalsIgnoreCase(string);
    }
    
    public Location getRandomLocation(World world)
    {
        Random rand = new Random();
        int x = rand.nextInt(10000000);
        int z = rand.nextInt(10000000);
        return new Location(world,x,60,z);
    }
    
    public Location getSafeLocation(Location loc)
    {
        int safeY = 255;
        int safeX = loc.getBlockX();
        int safeZ = loc.getBlockZ();
        Location l = loc;
        l.setY(safeY);
        while(l.getBlock().getType() == AIR)
        {
            safeY--;
            l.setY(safeY);
        }
        Location ll = l;
        ll.setY(l.getY()+1);
        while(config.getList("blocked").contains(l.getBlock().getTypeId()) || config.getList("blocked").contains(ll.getBlock().getTypeId()))
        {
            l.setX(safeX+1);
            safeX++;
        }
        l.setY(safeY + 1);
        return l;
    }
}

