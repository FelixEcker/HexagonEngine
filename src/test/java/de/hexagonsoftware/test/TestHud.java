package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.hud.HUD;

public class TestHud extends HUD {
	private Font font;
	
	public TestHud(Font font) {
		this.font = font;
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Test", 100, 200);
	}
}
