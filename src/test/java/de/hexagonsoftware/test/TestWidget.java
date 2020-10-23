package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Graphics;

import de.hexagonsoftware.engine.hud.widgets.HUDWidget;

public class TestWidget extends HUDWidget {
	public TestWidget(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 100, 100);
	}
}
