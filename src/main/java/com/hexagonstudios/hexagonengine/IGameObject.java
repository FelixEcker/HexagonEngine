package com.hexagonstudios.hexagonengine;

import java.awt.Graphics;

public interface IGameObject {
	void init();
	void render(Graphics g);
	void update(HexagonEngine engine);
	
	String getObjectName();
}
