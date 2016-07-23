package dm.alonlevi.spells;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze implements Listener {
	
	public Main m;
	
	public Freeze(Main ma) {
		this.m = ma;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (m.frozen.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}

}
