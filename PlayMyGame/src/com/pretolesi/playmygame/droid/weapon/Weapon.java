/**
 * 
 */
package com.pretolesi.playmygame.droid.weapon;

import com.pretolesi.playmygame.droid.math.Vector2f;

/**
 * This is the Weapon strategy interface.
 * Every entity with the ability to carry a weapon will make use 
 * of this strategy to use it.
 * 
 * @author impaler
 *
 */
public interface Weapon {

	/**
	 * The trigger to use the weapon.
	 * @param target - where on the map is the target
	 */
	public void useWeapon(Vector2f target);
	
	/**
	 * Returns the description of the weapon
	 */
	public String getDescription();
	
}
