package me.alonlevi.dragonminer.dragamite;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DragamiteItem {

	@SuppressWarnings("deprecation")
	public DragamiteItem(ItemStack mat, String name, List<String> lore, Player p) {
		if (!(mat.equals(Material.getMaterial(351 / 10)) || mat.equals(Material.getMaterial(351 / 11))
				|| mat.equals(Material.getMaterial(351 / 12)) || mat.equals(Material.getMaterial(351 / 15))
				|| mat.equals(Material.getMaterial(351 / 15)) || mat.equals(Material.getMaterial(351 / 0))
				|| mat.equals(Material.getMaterial(351 / 10)) || mat.equals(Material.getMaterial(351 / 1)))) {
			
			
			System.out.println("Wrong ids in DragamiteItem.java!");
			

		} else {
			if (mat.equals(Material.getMaterial(351/10))) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (pl.getInventory().getItemInHand().getItemMeta().getLore().equals("Green Dragamite §") && pl.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("Green Dragamite §")) {
						PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2, 1);
						PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2, 1);
						PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 1, 1);
						strength.apply(pl);
						resistance.apply(pl);
						speed.apply(pl);
						
					}
				}
			}
		}
	}

}
