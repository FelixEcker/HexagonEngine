package de.hexagonsoftware.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.hud.HUD;
import de.hexagonsoftware.engine.hud.widgets.TextBox;

public class TestHud extends HUD {
	private Font font;
	private TextBox tb;
	
	public TestHud() {
		super();
		this.font = HexagonEngine.HE_RES_MANAGER.getFontResource("Pixelart").getFont();
		tb = new TextBox(TextBox.CENTER, new String[] {
				"A",
				"B",
				"C",
				"D",
				"E",
				"F"
		}, new Font("Courier New", Font.PLAIN, 25));
	}
	
	public void render(Graphics g) {
		tb.render(g);
	}
}
