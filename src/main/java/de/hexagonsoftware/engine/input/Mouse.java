package de.hexagonsoftware.engine.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	public boolean mouseClicked;
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("asd");
		System.out.println(e.getButton());
	}
}
