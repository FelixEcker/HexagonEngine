package com.hexagonstudios.hexagonengine.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.hexagonstudios.hexagonengine.*;

public class MainRenderer {
	private BufferStrategy bufferStrat;
	private Graphics g;
	private HexagonEngine engine;
	private GameWindow win;
	
	public MainRenderer(HexagonEngine engine, GameWindow win) {
		this.engine = engine;
		this.win = win;
	}
	
	public void render() {
		bufferStrat = win.getBufferStrategy();
		
		if (bufferStrat == null) {
			win.createBufferStrategy(3);
			return;
		}
		
		g = bufferStrat.getDrawGraphics();
		
		g.clearRect(0, 0, win.getWidth(), win.getHeight());
		
		engine.getGameHandler().render(g);
		
		g.dispose();
		bufferStrat.show();
	}
}
