package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;

/**
 * The HUDWidget Superclass is used
 * for creating Widgets for a HUD (e.g. buttons, labels)
 * 
 * @author Felix Eckert
 * */
public class HUDWidget {
	protected int x, y;
	protected int width, height;
	
	public HUDWidget(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public HUDWidget(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void update() {
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
