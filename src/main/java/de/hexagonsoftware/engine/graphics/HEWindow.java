package de.hexagonsoftware.engine.graphics;

import java.awt.Canvas;

import javax.swing.JFrame;

/**
 * A simple extension of a java swing window
 * used for the Engine.
 * 
 * @author Felix Eckert
 * */
public class HEWindow extends JFrame {
	private static final long serialVersionUID = 5365168035195716170L;
	private Canvas cvs;
	
	public HEWindow(String title, int width, int height) {
		super(title);
		
		setSize(width, height);
		
		cvs = new Canvas();
		cvs.setSize(width, height);
		add(cvs);
	}
	
	public Canvas getCVS() { return cvs; }
}
