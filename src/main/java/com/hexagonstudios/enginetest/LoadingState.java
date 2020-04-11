package com.hexagonstudios.enginetest;

import java.awt.Color;
import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameState;

public class LoadingState implements IGameState {

	@Override
	public void update(HexagonEngine engine) {
		engine.getObjectHandler().getObject("TestObject").update(engine);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 59, 111));
		g.fillRect(100, 100, 250, 125);
	}

}
