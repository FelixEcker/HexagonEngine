package de.hexagonsoftware.test.layeredrendering;

import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;

public class LayeredRenderingTest implements IGame {

	@Override
	public void start() {
		HexagonEngine.HE_GOBJ_MANAGER.addGameObject("test", new TestObject());
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}
}
