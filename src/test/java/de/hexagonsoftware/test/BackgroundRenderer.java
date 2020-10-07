package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.prefabs.BackgroundRendererObject;

public class BackgroundRenderer extends BackgroundRendererObject {
	@Override
	public void render(Graphics g) {
		Color prevCol = g.getColor();
		g.setColor(Color.black);
		g.fillRect(0, 0, HexagonEngine.getWidth(), HexagonEngine.getHeight());

		g.setColor(prevCol);
	}
}
