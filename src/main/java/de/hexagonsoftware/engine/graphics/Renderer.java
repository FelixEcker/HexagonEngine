package de.hexagonsoftware.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;

public class Renderer {
	private static BufferStrategy bs;
	private static Graphics g;
	
	/**
	 * This functions is used for rendering.
	 * 
	 * @param window The HEWindow instance required for rendering
	 * @param game   The IGame instance.
	 * */
	public static void render(HEWindow window, IGame game) {
		bs = window.getCVS().getBufferStrategy();
		
		if (bs == null) {
			window.getCVS().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, window.getWidth(), window.getWidth());
		
		HexagonEngine.HE_GOBJ_MANAGER.render(g);
		game.render(g);
		
		g.dispose();
		bs.show();
	}
}
