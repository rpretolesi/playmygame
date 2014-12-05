/**
 * 
 */
package com.pretolesi.playmygame.chassis;

/**
 * This is the chassis strategy interface.
 * Every entity (droid) requires one. Some give the ability to
 * move some don't but a droid needs to be supported by a chassis.
 * 
 * 
 * @author impaler
 *
 */
public interface Chassis {

	/**
	 * Delegates the movement to the supporting chassis and
	 * tries to move the unit to <code>x</code>,<code>y</code>
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y);
	
	/**
	 * Returns the description of the chassis
	 */
	public String getDescription();
}
