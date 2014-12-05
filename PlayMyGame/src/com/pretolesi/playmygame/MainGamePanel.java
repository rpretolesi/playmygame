package com.pretolesi.playmygame;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback  
{
	private static final String TAG = MainGamePanel.class.getSimpleName();
	private MainThread thread;
	private Droid droid;
	
	public MainGamePanel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this); 

		// create droid and load bitmap 
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50); 
		
		// create the game loop thread 
		thread = new MainThread(getHolder(), this);

		setFocusable(true); 
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setRunning(true); 
		thread.start(); 
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		boolean retry = true; 
		while (retry) 
		{ 
		   try 
		   {
			   thread.join(); 
			   retry = false; 
		   } catch (InterruptedException e) 
		   { 
			   // try again shutting down the thread 
		   } 
		} 
	}

	@Override 
	public boolean onTouchEvent(MotionEvent event) 
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{ 
			// delegating event handling to the droid 
			droid.handleActionDown((int)event.getX(), (int)event.getY()); 

			if (event.getY() > getHeight() - 50) 
			{ 
				thread.setRunning(false); 
				((Activity)getContext()).finish(); 
			} else 
			{ 
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			} 
		} 

		if (event.getAction() == MotionEvent.ACTION_MOVE) 
		{ 
			// the gestures 
			if (droid.isTouched()) 
			{ 
				// the droid was picked up and is being dragged 
				droid.setX((int)event.getX()); 
				droid.setY((int)event.getY()); 
			} 
		} 
		
		if (event.getAction() == MotionEvent.ACTION_UP) 
		{ 
			// touch was released 
			if (droid.isTouched()) 
			{ 
				droid.setTouched(false); 
			} 
		} 

		return true;
	} 

	protected void render(Canvas canvas) 
	{ 
		canvas.drawColor(Color.BLACK); 
		droid.draw(canvas); 
	} 


	public void update() 
	{ 
		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT && droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()) 
		{ 
			droid.getSpeed().toggleXDirection(); 
		} 

		// check collision with left wall if heading left 
		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT && droid.getX() - droid.getBitmap().getWidth() / 2 <= 0) 
		{ 
			droid.getSpeed().toggleXDirection(); 
		} 

		// check collision with bottom wall if heading down 
		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN && droid.getY() + droid.getBitmap().getHeight() / 2 >= getHeight()) 
		{ 
			droid.getSpeed().toggleYDirection(); 
		} 

		// check collision with top wall if heading up 
		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_UP && droid.getY() - droid.getBitmap().getHeight() / 2 <= 0) 
		{ 
			droid.getSpeed().toggleYDirection(); 
		} 

		// Update the lone droid 
		droid.update(); 
	} 

}
