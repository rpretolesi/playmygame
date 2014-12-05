package com.pretolesi.playmygame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainThread extends Thread 
{
	private static final String TAG = MainThread.class.getSimpleName();
	
	private SurfaceHolder surfaceHolder; 
	private MainGamePanel gamePanel; 

	// desired fps 
	private final static int    MAX_FPS = 50; 
	// maximum number of frames to be skipped 
	private final static int    MAX_FRAME_SKIPS = 5; 
	// the frame period 
	private final static int    FRAME_PERIOD = 1000 / MAX_FPS;   

	// flag to hold game state 
	private boolean running; 

	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) 
	{ 
		super(); 
		this.surfaceHolder = surfaceHolder; 
		this.gamePanel = gamePanel; 
	} 

	public void setRunning(boolean running) 
	{ 
		this.running = running; 
	} 

	@Override 
	public void run() 
	{ 
		Canvas canvas;

		long beginTime = 0L;     // the time when the cycle begun 
	    long timeDiff = 0L;      // the time it took for the cycle to execute 
	    int sleepTime = 0;      // ms to sleep (<0 if we're behind) 
	    int framesSkipped = 0;  // number of frames being skipped  
		
		Log.d(TAG, "Starting game loop"); 
		
		while (running) 
		{ 
			canvas = null;

			// try locking the canvas for exclusive pixel editing on the surface 
		   try 
		   { 
			   canvas = this.surfaceHolder.lockCanvas(); 
			   synchronized (surfaceHolder) 
			   { 
			     // update game state 
				 this.gamePanel.update();				   
				 // render state to the screen
				 // draws the canvas on the panel 
				 this.gamePanel.render(canvas);

	                // calculate how long did the cycle take 
	                timeDiff = System.currentTimeMillis() - beginTime; 
	                // calculate sleep time 
	                sleepTime = (int)(FRAME_PERIOD - timeDiff); 
	                if (sleepTime > 0) 
	                { 
	                    // if sleepTime > 0 we're OK 
	                    try 
	                    { 
	                        // send the thread to sleep for a short period
	                        // very useful for battery saving 
	                        Thread.sleep(sleepTime); 
	                    } catch (InterruptedException e) 
	                    {
	                    	
	                    } 
	                } 

	                while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) 
	                { 
	                	// we need to catch up 
	                	// update without rendering 
	                	this.gamePanel.update(); 

	                	// add frame period to check if in next frame 
	                	sleepTime += FRAME_PERIOD; 
	                	framesSkipped++; 
	                } 
	                
			   } 

		   } finally 
		   { 
			   	// in case of an exception the surface is not left in 
			    // an inconsistent state 
			    if (canvas != null) 
			    { 
			    	surfaceHolder.unlockCanvasAndPost(canvas); 
			    } 

		   } // end finally 
		} 
		
		Log.d(TAG, "Stopping game loop");
	} 

}
