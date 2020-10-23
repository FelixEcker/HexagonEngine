package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.hud.HUD;

public class TestHud extends HUD {
	private Font font;
	
	public TestHud() {
		super();
		this.font = HexagonEngine.HE_RES_MANAGER.getFontResource("Pixelart").getFont();
		addWidget(new TestWidget(1, 1));
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Test", 100, 200);
	}
}
