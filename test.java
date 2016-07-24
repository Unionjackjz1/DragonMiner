import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class test extends JavaPlugin {
	public int number = 5;
	
	//-SAMPLE COUNTDOWN-//
	@SuppressWarnings("deprecation")
	public void startCountdown() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (number != 0) {
					Bukkit.broadcastMessage("" + number);
					number--;
				}
			}
			
		}, 0, 20);
	}

}
