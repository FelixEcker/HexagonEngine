package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.hud.HUD;

public class TestHud extends HUD {
	private Font font;
	
	public TestHud(Font font) {
		super();
		this.font = font;
		addWidget(new TestWidget(1, 1));
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Test", 100, 200);
	}
}
