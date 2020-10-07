package de.hexagonsoftware.engine.game;

import de.hexagonsoftware.engine.HexagonEngine;

public class Camera {
	public int x = HexagonEngine.getWidth() / 2;
	public int y = HexagonEngine.getHeight() / 2;
	public int scrollX;
	public int scrollY;
	
	public void update() {
		x = HexagonEngine.getWidth()/2;
		y = HexagonEngine.getHeight()/2;
		scrollX = 0;
		scrollY = 0;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }

	public int getScrollX() {
		return scrollX;
	}

	public void setScrollX(int scrollX) {
		this.scrollX = scrollX;
	}

	public int getScrollY() {
		return scrollY;
	}

	public void setScrollY(int scrollY) {
		this.scrollY = scrollY;
	}
	
}
