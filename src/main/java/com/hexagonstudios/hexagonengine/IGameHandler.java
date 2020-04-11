package com.hexagonstudios.hexagonengine;

import java.awt.Graphics;

public interface IGameHandler {
	void init(HexagonEngine engine);
	void update();
	void render(Graphics g);
	IGameState getCurrentState();
	void setState(IGameState state);
}
