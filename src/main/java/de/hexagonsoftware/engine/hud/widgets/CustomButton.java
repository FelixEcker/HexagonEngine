package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import de.hexagonsoftware.engine.HexagonEngine;

public class CustomButton extends ImageButton {
	private RenderHandler renderHandler;
	public boolean hovered;
	
	public CustomButton(int x, int y) {
		super(x, y);
	}

	public CustomButton(int x, int y, int width, int height) {
		super(x, y);
	}
	
	public CustomButton(int x, int y, RenderHandler renderHandler, ActionHandler actionHandler) {
		super(x, y);
	}
	
	public CustomButton(int x, int y, int width, int height, RenderHandler renderHandler, ActionHandler actionHandler) {
		super(x, y);
	}
	
	@Override
	public void render(Graphics g) {
		renderHandler.render(g);
	}
	
	@Override
	public void update() {
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

	public RenderHandler getRenderHandler() {
		return renderHandler;
	}

	public ActionHandler getActionHandler() {
		return actionHandler;
	}

	public void setRenderHandler(RenderHandler renderHandler) {
		this.renderHandler = renderHandler;
	}

	public void setActionHandler(ActionHandler actionHandler) {
		this.actionHandler = actionHandler;
	}
}
