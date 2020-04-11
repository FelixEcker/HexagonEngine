package com.hexagonstudios.hexagonengine;

import java.awt.Graphics;

public interface IGameState {
	void update(HexagonEngine engine);
	void render(Graphics g);
}
