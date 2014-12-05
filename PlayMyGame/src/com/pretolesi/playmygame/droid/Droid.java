package com.pretolesi.playmygame.droid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.pretolesi.playmygame.Speed;
import com.pretolesi.playmygame.chassis.Chassis;
import com.pretolesi.playmygame.droid.math.Vector2f;
import com.pretolesi.playmygame.droid.weapon.Weapon;

public abstract class Droid 
{
/*	
	private Bitmap bitmap;	// the actual bitmap
	private int x;			// the X coordinate
	private int y;			// the Y coordinate
	private boolean touched;	// if droid is touched/picked up
	private Speed speed;	// the speed with its directions
	
	public Droid(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.speed = new Speed();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}

	// Method which updates the droid's internal state every tick
	public void update() {
		if (!touched) {
			x += (speed.getXv() * speed.getxDirection()); 
			y += (speed.getYv() * speed.getyDirection());
		}
	}
	
	
	// Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the 
	// bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	// @param eventX - the event's X coordinate
	// @param eventY - the event's Y coordinate
	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}
*/
	
	protected static int nextId	= 0;	// the next available ID

	protected String 	id;			// unique id
	protected Vector2f 	position;	// the droid's position on the map
	protected Weapon 	weapon;		// the weapon which will be used in fights
	protected Chassis	chassis;	// the chassis on which the droid is placed
	
	// the unique ID of the droid in the game
	public String getId() {
		return id;
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public Chassis getChassis() {
		return chassis;
	}
	public void setChassis(Chassis chassis) {
		this.chassis = chassis;
	}
	
	public void moveToPosition(int x, int y) {
		System.out.print(id + " > " );
		chassis.moveTo(x, y);
	}
	
	/**
	 *  Engages the position on the screen whether it is occupied by
	 *  an enemy or not. Each strategy should decide how to do it. 
	 */ 
	public void attackPosition(int x, int y) {
		System.out.print(id + " > ");
		weapon.useWeapon(new Vector2f(x, y));
	}
	
	/**
	 * Displays some info on the droid
	 */
	public abstract void display();
	
}
