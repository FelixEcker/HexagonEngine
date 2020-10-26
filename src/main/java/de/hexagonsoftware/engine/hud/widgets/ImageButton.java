package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hexagonsoftware.engine.HexagonEngine;

public class ImageButton extends HUDWidget {
	private BufferedImage texture;
	private BufferedImage hoverOverlay;
	private int widthScaleFactor;
	private int heightScaleFactor;
	private boolean hovered;
	protected ActionHandler actionHandler;
	
	public ImageButton(int x, int y) {
		super(x, y);
	}
	
	public ImageButton(String texture, int x, int y, ActionHandler actionHandler) {
		super(x, y);
		
		this.texture = HexagonEngine.HE_RES_MANAGER.getTextureResource(texture).getImage();
		this.widthScaleFactor = HexagonEngine.getWidth()/this.texture.getWidth();
		this.heightScaleFactor = HexagonEngine.getHeight()/this.texture.getHeight();
		this.actionHandler = actionHandler;
	}
	
	public ImageButton(String texture, String hoverOverlay, int x, int y, ActionHandler actionHandler) {
		super(x, y);
		
		this.texture = HexagonEngine.HE_RES_MANAGER.getTextureResource(texture).getImage();
		this.hoverOverlay = HexagonEngine.HE_RES_MANAGER.getTextureResource(hoverOverlay).getImage();
		this.widthScaleFactor = HexagonEngine.getWidth()/this.texture.getWidth();
		this.heightScaleFactor = HexagonEngine.getHeight()/this.texture.getHeight();
		this.actionHandler = actionHandler;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, x, y, width, height, null);
		if (hovered && hoverOverlay != null)
			g.drawImage(hoverOverlay, x, y, width, height, null);
	}
	
	@Override
	public void update() {
		width = (HexagonEngine.getWidth()/widthScaleFactor);
		height = (HexagonEngine.getWidth()/heightScaleFactor);
		Rectangle buttonBounds = new Rectangle(x, y, width, height);
		Point     hoverPoint   = new Point(HexagonEngine.HE_MOUSE_INPUT.getLastMouseEvent().getX(), HexagonEngine.HE_MOUSE_INPUT.getLastMouseEvent().getY());
		
		if (buttonBounds.contains(hoverPoint)) {
			hovered = true;
		} else {
			hovered = false;
		}
		
		if (HexagonEngine.HE_MOUSE_INPUT.wasButtonClicked(1)) {
			Rectangle clickBounds  = new Rectangle(HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getX(), HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getY(), 1, 1);
			
			if (clickBounds.intersects(buttonBounds)) {
				actionHandler.actionPerformed();
			}
		}
	}

	public int getWidthScaleFactor() {
		return widthScaleFactor;
	}

	public void setWidthScaleFactor(int widthScaleFactor) {
		this.widthScaleFactor = widthScaleFactor;
	}

	public int getHeightScaleFactor() {
		return heightScaleFactor;
	}

	public void setHeightScaleFactor(int heightScaleFactor) {
		this.heightScaleFactor = heightScaleFactor;
	}
}
