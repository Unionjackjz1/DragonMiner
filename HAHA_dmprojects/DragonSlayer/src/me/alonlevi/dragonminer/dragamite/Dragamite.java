package me.alonlevi.dragonminer.dragamite;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Dragamite {
	
	ArrayList<Player> pred = new ArrayList<Player>();
	ArrayList<Player> pgreen = new ArrayList<Player>();
	ArrayList<Player> pblue = new ArrayList<Player>();
	ArrayList<Player> pyellow = new ArrayList<Player>();
	ArrayList<Player> ppurple = new ArrayList<Player>();
	ArrayList<Player> pwhite = new ArrayList<Player>();
	ArrayList<Player> pblack = new ArrayList<Player>();
	
	
	public static int black() {
		return 8000;
		
	}
	
	public static int white() {
		return 15000;
		
	}
	
	public static int purple() {
		return 20000;
		
	}
	
	public static int green() {
		return 40000;
		
	}
	
	public static int red() {
		return 10000;
		
	}
	
	public static int yellow() {
		return 9000;
		
	}
	
	public static int blue() {
		return 23000;
		
	}
	
	public void effectPlayer(Player p, int dragamite) {
		
		int red = 1;
		int yellow = 2;
		int blue = 3;
		int purple = 4;
		int green = 5;
		int white = 6;
		int black = 7;
		
		
		if (dragamite == red) {
			pred.add(p);

		}
		if (dragamite == green) {
			pgreen.add(p);

		}
		if (dragamite == white) {
			pwhite.add(p);

		}
		if (dragamite == black) {
			pblack.add(p);

		}
		if (dragamite == yellow) {
			pyellow.add(p);

		}
		if (dragamite == blue) {
			pblue.add(p);

		}
		if (dragamite == purple) {
			ppurple.add(p);

		}
		
		
	}
	
	

}
