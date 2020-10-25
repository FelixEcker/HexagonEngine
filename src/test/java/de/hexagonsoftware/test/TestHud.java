package de.hexagonsoftware.test;

import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.hud.HUD;
import de.hexagonsoftware.engine.hud.widgets.ImageButton;
import de.hexagonsoftware.engine.hud.widgets.TextBox;

public class TestHud extends HUD {
	private Font font;
	private TextBox tb;
	private ImageButton ib;
	
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
		ib = new ImageButton("CharacterFRONT", 100, 100);
		ib.setHeightScaleFactor(20);
		ib.setWidthScaleFactor(20);
	}
	
	public void render(Graphics g) {
		tb.render(g);
		ib.render(g);
	}
	
	public void update() {
		ib.update();
	}
}
