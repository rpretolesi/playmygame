/**
 * 
 */
package com.pretolesi.playmygame.droid.weapon;

import com.pretolesi.playmygame.droid.math.Vector2f;

/**
 * This is a light laser cannon whit a quick reload time and high accuracy
 * 
 * @author impaler
 * 
 */
public class LightLaserCanon implements Weapon {

	private float damage = 0.5f; // the damage inflicted

	@Override
	public void useWeapon(Vector2f target) {
		System.out.println("Shooting my laser canon to " + (int)target.getX() + ","
				+ (int)target.getY() + ". Bang on! Done " + damage + " damage.");
	}

	@Override
	public String getDescription() {
		return "First generation laser canon. Street use only!";
	}
}
