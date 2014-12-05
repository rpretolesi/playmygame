/**
 * 
 */
package com.pretolesi.playmygame.droid.weapon;

import com.pretolesi.playmygame.droid.math.Vector2f;

/**
 * This is a heavy assault laser cannon with high accuracy but slow reload time.
 * @author impaler
 *
 */
public class HeavyLaserCanon implements Weapon {

	private boolean loaded 	= true;	// after fire needs to be reloaded
	private float 	damage 	= 1.5f;	// the damage is considerable
	
	@Override
	public void useWeapon(Vector2f target) {
		if (loaded) {
			// we fire the canon
			System.out.println("Eat this! Laser beam hit target (" + (int)target.getX() + "," + (int)target.getY() + ") and dealt " + damage + " damage.");
			// next time needs reloading
			loaded = false;
		} else {
			System.out.println("Darn! Out of ammo! Reloading...");
			loaded = true;
		}
	}

	@Override
	public String getDescription() {
		return "DASS-5000 - The ultimate in siege weaponry provided by Obviam Enterprises.";
	}

}
