package de.hexagonsoftware.engine.game.components;

import java.awt.Graphics;

import de.hexagonsoftware.engine.game.IGameObjectComponent;
import de.hexagonsoftware.engine.graphics.Animation;

/**
 * Used for rendering an Animation object <br>
 * 
 * @author Felix Eckert
 * */
public class AnimationRenderer implements IGameObjectComponent {
	private Animation animation;
	
	/**
	 * @param animation The animation to be rendered.
	 * */
	public AnimationRenderer(Animation animation) {
		this.animation = animation;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		animation.play(g);
	}
}
