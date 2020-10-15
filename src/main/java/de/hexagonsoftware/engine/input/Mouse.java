package de.hexagonsoftware.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	public int buttonClicked;
	public MouseEvent lastMouseEvent;
	
	public Mouse() {
		buttonClicked = -1;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		buttonClicked = e.getButton();
		lastMouseEvent = e;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		buttonClicked = -1;
		lastMouseEvent = e;
	}
	
	public boolean wasButtonClicked(int button) {
		return button == buttonClicked;
	}
	
	public MouseEvent getLastMouseEvent() {
		return lastMouseEvent;
	}
}
