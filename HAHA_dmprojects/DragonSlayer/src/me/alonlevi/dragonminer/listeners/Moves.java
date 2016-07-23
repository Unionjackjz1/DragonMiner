package me.alonlevi.dragonminer.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.alonlevi.dragonminer.Main;

public class Moves implements Listener {
	PlayerJoin pj;
	Main m;

	public Moves(Main main) {
		this.m = main;
	}

	@SuppressWarnings("static-access")
	public void onPlayerMove(PlayerMoveEvent e) {
		PlayerJoin a = pj;
		Location l = e.getPlayer().getLocation();
		double x1 = e.getPlayer().getLocation().getX();
		double y1 = e.getPlayer().getLocation().getY();
		double z1 = e.getPlayer().getLocation().getZ();
		double x2 = x1 - 1;
		double y2 = y1 - 1;
		double z2 = z1 - 1;
		double x3 = x1 - 2;
		double y3 = y1 - 2;
		double z3 = z1 - 2;
		double x4 = x1 - 3;
		double y4 = y1 - 3;
		double z4 = z1 - 3;
		Player p = e.getPlayer();
		if (p.getInventory().getHeldItemSlot() == 9) {
			if (a.firebenders.contains(p.getName())) {
				if (!e.getPlayer().getLocation().equals(l)) {
					if (e.getFrom().getX() == x2) {
						Location loc2 = new Location(p.getWorld(), x2, y2, z2);
						Location loc3 = new Location(p.getWorld(), x3, y3, z3);
						Location loc4 = new Location(p.getWorld(), x4, y4, z4);
						if (p.getLocation().getBlock().getType().FIRE != null) {
							p.getLocation().getBlock().breakNaturally();
						}
						if (loc2.getBlock().getType().FIRE != null) {
							loc2.getBlock().breakNaturally();
						}
						if (loc3.getBlock().getType().FIRE != null) {
							loc3.getBlock().breakNaturally();
						}
						if (loc4.getBlock().getType().FIRE != null) {
							loc4.getBlock().breakNaturally();
						}
						if (p.getLocation().getBlock().getType().NETHERRACK != null) {
							p.getLocation().getBlock().breakNaturally();
						}
						if (loc2.getBlock().getType().NETHERRACK != null) {
							loc2.getBlock().breakNaturally();
						}
						if (loc3.getBlock().getType().NETHERRACK != null) {
							loc3.getBlock().breakNaturally();
						}
						if (loc4.getBlock().getType().NETHERRACK != null) {
							loc4.getBlock().breakNaturally();
						}
					}
				}
			}
		}
		if (a.airbenders.contains(p.getName())) {
			if (!e.getPlayer().getLocation().equals(l)) {
				if (e.getFrom().getX() == x2) {
					Location loc2 = new Location(p.getWorld(), x2, y2, z2);
					Location loc3 = new Location(p.getWorld(), x3, y3, z3);
					Location loc4 = new Location(p.getWorld(), x4, y4, z4);
					if (p.getLocation().getBlock().getType().WOOL != null) {
						p.getLocation().getBlock().breakNaturally();
					}
					if (loc2.getBlock().getType().WOOL != null) {
						loc2.getBlock().breakNaturally();
					}
					if (loc3.getBlock().getType().WOOL != null) {
						loc3.getBlock().breakNaturally();
					}
					if (loc4.getBlock().getType().WOOL != null) {
						loc4.getBlock().breakNaturally();
					}
				}
			}
		}
		if (a.waterbenders.contains(p.getName())) {

			if (!e.getPlayer().getLocation().equals(l)) {
				if (e.getFrom().getX() == x2) {
					Location loc2 = new Location(p.getWorld(), x2, y2, z2);
					Location loc3 = new Location(p.getWorld(), x3, y3, z3);
					Location loc4 = new Location(p.getWorld(), x4, y4, z4);
					if (p.getLocation().getBlock().getType().ICE != null) {
						p.getLocation().getBlock().breakNaturally();
					}
					if (loc2.getBlock().getType().ICE != null) {
						loc2.getBlock().breakNaturally();
					}
					if (loc3.getBlock().getType().ICE != null) {
						loc3.getBlock().breakNaturally();
					}
					if (loc4.getBlock().getType().ICE != null) {
						loc4.getBlock().breakNaturally();
					}
					if (p.getLocation().getBlock().getType().WATER != null) {
						p.getLocation().getBlock().breakNaturally();
					}
					if (loc2.getBlock().getType().WATER != null) {
						loc2.getBlock().breakNaturally();
					}
					if (loc3.getBlock().getType().WATER != null) {
						loc3.getBlock().breakNaturally();
					}
					if (loc4.getBlock().getType().WATER != null) {
						loc4.getBlock().breakNaturally();
					}
				}
			}
		}
		if (a.earthbenders.contains(p.getName())) {

			if (!e.getPlayer().getLocation().equals(l)) {
				if (e.getFrom().getX() == x2) {
					Location loc2 = new Location(p.getWorld(), x2, y2, z2);
					Location loc3 = new Location(p.getWorld(), x3, y3, z3);
					Location loc4 = new Location(p.getWorld(), x4, y4, z4);
					if (p.getLocation().getBlock().getType().STONE != null) {
						p.getLocation().getBlock().breakNaturally();
					}
					if (loc2.getBlock().getType().STONE != null) {
						loc2.getBlock().breakNaturally();
					}
					if (loc3.getBlock().getType().STONE != null) {
						loc3.getBlock().breakNaturally();
					}
					if (loc4.getBlock().getType().STONE != null) {
						loc4.getBlock().breakNaturally();
					}
					if (p.getLocation().getBlock().getType().DIRT != null) {
						p.getLocation().getBlock().breakNaturally();
					}
					if (loc2.getBlock().getType().DIRT != null) {
						loc2.getBlock().breakNaturally();
					}
					if (loc3.getBlock().getType().DIRT != null) {
						loc3.getBlock().breakNaturally();
					}
					if (loc4.getBlock().getType().DIRT != null) {
						loc4.getBlock().breakNaturally();
					}
				}
			}
		}

	}

}
