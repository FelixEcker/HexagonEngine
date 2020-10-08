package de.hexagonsoftware.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	public int buttonClicked;
	
	public Mouse() {
		buttonClicked = -1;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		buttonClicked = e.getButton();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		buttonClicked = -1;
	}
	
	public boolean wasButtonClicked(int button) {
		return button == buttonClicked;
	}
}
