package de.hexagonsoftware.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used for handling input by the user. <br>
 * 
 * @author Felix Eckert
 * */
public class Keyboard extends KeyAdapter {
	/**
	 * A list of all currently pressed keys.
	 * */
	public static List<Character> keys = new LinkedList<>();
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (keys.contains(e.getKeyChar()))
			return;
		
		keys.add(e.getKeyChar());
	}	
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (!keys.contains(e.getKeyChar()))
			return;
		
		keys.remove((Character)e.getKeyChar());
	}
	
	/**
	 * @param key      The key to be checked if pressed.
	 * @return Returns true if the given key is pressed
	 * */
	public boolean isKeyDown(char key) {	
		return keys.contains(key);
	}
}
