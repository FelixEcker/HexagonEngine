package de.hexagonsoftware.test;

import java.awt.Font;
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.hud.HUD;
import de.hexagonsoftware.engine.hud.widgets.ActionHandler;
import de.hexagonsoftware.engine.hud.widgets.ButtonBox;
import de.hexagonsoftware.engine.hud.widgets.ImageButton;
import de.hexagonsoftware.engine.hud.widgets.TextBox;

public class TestHud extends HUD {
	private Font font;
	private TextBox tb;
	private ImageButton ib;
	private ImageButton ib2;
	private ImageButton ib3;
	private ButtonBox bb;
	
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
		ib = new ImageButton("CharacterFRONT", "ButtonHover", 100, 100, new ActionHandler() {
			@Override
			public void actionPerformed() {
				System.out.println("I was hit1");
			}	
		});
		ib.setHeightScaleFactor(20);
		ib.setWidthScaleFactor(20);
		ib2 = new ImageButton("CharacterFRONT", "ButtonHover", 100, 100, new ActionHandler() {
			@Override
			public void actionPerformed() {
				System.out.println("I was hit2");
			}	
		});
		ib2.setHeightScaleFactor(20);
		ib2.setWidthScaleFactor(20);
		ib3 = new ImageButton("CharacterFRONT", "ButtonHover", 100, 100, new ActionHandler() {
			@Override
			public void actionPerformed() {
				System.out.println("I was hit3");
			}	
		});
		ib3.setHeightScaleFactor(20);
		ib3.setWidthScaleFactor(20);
		bb = new ButtonBox(0, 0, ButtonBox.CENTER, new ImageButton[] {
				ib,
				ib2,
				ib3
		});
	}
	
	public void render(Graphics g) {
		tb.render(g);
		bb.render(g);
	}
	
	public void update() {
		ib.update();
		bb.update();
	}
}
