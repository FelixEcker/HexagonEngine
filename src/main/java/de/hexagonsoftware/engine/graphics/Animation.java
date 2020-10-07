package de.hexagonsoftware.engine.graphics;

import java.awt.Graphics;

import de.hexagonsoftware.engine.resources.TextureResource;

/**
 * A simple and primitive system for animating a GameObject. <br>
 * 
 * @author Felix Eckert
 * */
public class Animation {
	private TextureResource[] frames;
	private long[] timings;
	private int x, y;
	private int currentFrame = 0;
	private boolean playing = false;
	
	private long timer;
	
	/**
	 * Contructor for a Animation <br>
	 * 
	 * @param frames  An array containing all the TextureResources for the Animation (in order)
	 * @param timings An array containing the amount of ms for the corresponding frame.
	 * */	
	public Animation(TextureResource[] frames, long[] timings, int x, int y) {
		this.frames  = frames;
		this.timings = timings;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Plays the animation <br>
	 * 
	 * @param g The Graphics object it should render to.
	 * */
	public void play(Graphics g) {
		if (currentFrame == 0)
			timer = System.currentTimeMillis();
		
		
		g.drawImage(frames[currentFrame].getImage(), x, y, null);
		//System.out.print("Frame: "+currentFrame+"; drew image "+frames[currentFrame].getImage()+" at x: "+x+" y: "+y+"\r"); // DEBUG
		
		if (System.currentTimeMillis() - timer > (currentFrame != 0 ? timings[currentFrame-1] : 0)) {
			timer += 1000;
			if (currentFrame == frames.length-1) {
				currentFrame = 0;
			} else {
				currentFrame++;
			}
		}
	}
}
