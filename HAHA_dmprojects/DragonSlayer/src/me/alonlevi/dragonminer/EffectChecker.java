package me.alonlevi.dragonminer;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import me.alonlevi.dragonminer.dragamite.DragamiteItem;

public class EffectChecker implements Listener {

	Main m;

	public EffectChecker(Main main) {
		this.m = main;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		new DragamiteItem(new ItemStack(Material.getMaterial(351 / 10)), "Green Dragamite §", Arrays.asList("Green Dragamite §"), p);
	}

}
