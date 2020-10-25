package de.hexagonsoftware.engine.game;

import java.awt.Graphics;

/**
 * IGameObjectComponents are Components which can be addded to
 * a GameObject. These can be components such as "Rigidbodies"
 * or "SpriteRenderers" or simply scripts.
 * 
 * @author Felix Eckert
 * */
public interface IGameObjectComponent {
	/**
	 * This function is called on gameloop startup.
	 * */
	void start();
	/**
	 * This function is called on every execution of the HexagonEngine#tick function.
	 * */
	void update();
	/**
	 * This function is called by the Renderer#render method.
	 * @param g The Graphics object instance used for rendering
	 * */
	void render(Graphics g);
}
