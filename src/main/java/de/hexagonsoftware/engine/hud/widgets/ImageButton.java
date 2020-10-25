package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hexagonsoftware.engine.HexagonEngine;

public class ImageButton extends HUDWidget {
	private BufferedImage texture;
	private int widthScaleFactor;
	private int heightScaleFactor;
	
	public ImageButton(String texture, int x, int y) {
		super(x, y);
		
		this.texture = HexagonEngine.HE_RES_MANAGER.getTextureResource(texture).getImage();
		this.widthScaleFactor = HexagonEngine.getWidth()/this.texture.getWidth();
		this.heightScaleFactor = HexagonEngine.getHeight()/this.texture.getHeight();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, x, y, width, height, null);
	}
	
	@Override
	public void update() {
		width = (HexagonEngine.getWidth()/widthScaleFactor);
		height = (HexagonEngine.getWidth()/heightScaleFactor);
		
		if (HexagonEngine.HE_MOUSE_INPUT.wasButtonClicked(1)) {
			Rectangle buttonBounds = new Rectangle(x, y, width, height);
			Rectangle clickBounds  = new Rectangle(HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getX(), HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getY(), 1, 1);
			
			if (clickBounds.intersects(buttonBounds)) {
				System.out.println("ButtonWasHit");
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
