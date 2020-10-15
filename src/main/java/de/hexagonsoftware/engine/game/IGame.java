package de.hexagonsoftware.engine.game;

import java.awt.Graphics;

/**
 * This interface serves for creating a standardized Game-class,
 * with which the engine can work.
 * 
 * @author Felix Eckert
 * */
public interface IGame {
	/**
	 * This function should be used for the Loading of Resources.
	 * */
	void init();
	/**
	 * Is run on gameloop-start, should be used for creating all GameObjects.
	 * */
	void start();
	/**
	 * Is run on every execution of the {@link de.hexagonsoftware.engine.HexagonEngine#tick()} function
	 * */
	void update();
	/**
	 * 
	 * Is run on every execution of the {@link de.hexagonsoftware.engine.HexagonEngine#render()} function
	 * @param g The Graphics object instance used for rendering
	 * */
	void render(Graphics g);
}
