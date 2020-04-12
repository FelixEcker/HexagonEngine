package com.hexagonstudios.hexagonengine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyInput extends KeyAdapter {
	private HashMap<Character, Character> lastKeyPress = new HashMap<Character, Character>();
	
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		this.lastKeyPress.put(key, key);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		this.lastKeyPress.remove(key);
	}
	
	public boolean wasKeyPressed(char key) {
		if (lastKeyPress.containsKey(key)) {
			return true;
		} else {
			return false;
		}
	}
}
