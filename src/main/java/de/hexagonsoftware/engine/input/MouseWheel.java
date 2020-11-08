package de.hexagonsoftware.engine.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheel implements MouseWheelListener {
	public MouseWheelEvent lastEvent;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		lastEvent = e;
	}

	public MouseWheelEvent getLastEvent() { return lastEvent; }
}
