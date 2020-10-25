package de.hexagonsoftware.engine.input;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class Mouse extends MouseAdapter {
	/**
	 * A list of all currently pressed keys.
	 * */
	public static List<Integer> buttons = new LinkedList<>();
	public MouseEvent lastMouseEvent;
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (!buttons.contains(e.getButton()))
			buttons.add(e.getButton());
		
		lastMouseEvent = e;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		buttons.remove(buttons.indexOf(e.getButton()));
		lastMouseEvent = e;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		lastMouseEvent = e;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastMouseEvent = e;
	}

	public boolean wasButtonClicked(int button) {
		return buttons.contains(button);
	}
	
	public MouseEvent getLastMouseEvent() {
		return lastMouseEvent != null ? lastMouseEvent : new MouseEvent(new Canvas(), 0, 0, 0, 0, 0, 0, false);
	}
}
