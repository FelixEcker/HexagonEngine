package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A Text Box for rendering multiple lines of text.
 * 
 * @author Felix Eckert
 * */
public class TextBox extends Label {
	private String[] lines;
	
	public TextBox(int x, int y, String text, Font font) {
		super(x, y, text, font);
	}
	
	public TextBox(int alignment, String text, Font font) {
		super(alignment, text, font);
	}
	
	public TextBox(int alignment, String text[], Font font) {
		super(alignment, text[0], font);
		this.lines = text;
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(font);
		
		int i = 0;
		for (String line : lines) {
			i++;
			Point basePos = getLabelPosByAlignment(g, alignment, line);
			g.drawString(line, basePos.x, basePos.y+(g.getFontMetrics().getHeight()*i));
		}
	}
	
	public void setLines(String[] lines) { this.lines = lines; }
	public String[] getLines() { return this.lines; }
}
