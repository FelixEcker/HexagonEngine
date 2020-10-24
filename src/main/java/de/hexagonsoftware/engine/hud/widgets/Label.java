package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import de.hexagonsoftware.engine.HexagonEngine;

public class Label extends HUDWidget {
	public static int 
	TOP_LEFT      = 0, 
	TOP_RIGHT     = 1, 
	BOTTOM_LEFT   = 2,
	BOTTOM_RIGHT  = 3, 
	TOP_CENTER 	  = 4, 
	BOTTOM_CENTER = 5, 
	CENTER 		  = 6
	;
	
	private int alignment = -1;
	private Font font;
	private String text;
	
	public Label(int x, int y, String text, Font font) {
		super(x, y);
		this.font = font;
		this.text = text;
	}
	
	public Label(int alignment, String text, Font font) {
		super(0, 0);
		this.alignment = alignment;
		this.font = font;
		this.text = text;
	}
	
	public Point getLabelPosByAlignment(Graphics g, int alignment, String text) {
		Point location = null;
		int stringWidth = 0;
		
		switch (alignment) {
		case 0:
			location = new Point(0, g.getFontMetrics().getHeight());
			break;
		case 1:
			stringWidth = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
			location = new Point(HexagonEngine.getWidth()-stringWidth, g.getFontMetrics().getHeight());
			break;
		case 2:
			location = new Point(0, HexagonEngine.getHeight()-g.getFontMetrics().getHeight());
			break;
		case 3:
			stringWidth = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
			location = new Point(HexagonEngine.getWidth()-stringWidth, HexagonEngine.getHeight()-g.getFontMetrics().getHeight());
			break;
		case 4:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-g.getFontMetrics().getStringBounds(text, g).getWidth()/2), g.getFontMetrics().getHeight());
			break;
		case 5:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-g.getFontMetrics().getStringBounds(text, g).getWidth()/2), HexagonEngine.getHeight()-g.getFontMetrics().getHeight());
			break;
		case 6:
			location = new Point((int) ((HexagonEngine.getWidth()/2)-g.getFontMetrics().getStringBounds(text, g).getWidth()/2), (HexagonEngine.getHeight()/2)-(g.getFontMetrics().getHeight()/2));
			break;
		}
		
		return location;
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		Point location = getLabelPosByAlignment(g, alignment, text);
		g.drawString(text, (int) location.getX(), (int) location.getY());
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() { return text; }
}
