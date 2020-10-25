package de.hexagonsoftware.engine.resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import de.hexagonsoftware.engine.HexagonEngine;

public class FontResource implements IResource {
	private Font font;
	private String path;
	private boolean useGameResourceDir;
	
	private float size;
	private int type;
	
	public FontResource(String path, float size, int type) {
		this.path = path;
		this.useGameResourceDir = false;
		this.size = size;
		this.type = type;
	}
	
	public FontResource(String path, float size, int type, boolean useGameResourceDir) {
		this.path = path;
		this.useGameResourceDir = useGameResourceDir;
		this.size = size;
		this.type = type;
	}
	
	/**
	 * Loads the font from the given path.
	 * */
	public void load() {
		Class<?> cls = useGameResourceDir ? HexagonEngine.getGame().getClass() : this.getClass();
		try {
			font = Font.createFont(type, cls.getResourceAsStream(path)).deriveFont(size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return The loaded image.
	 * */
	public Font getFont() {
		if (font == null)
			load();
		
		return font; 
	}
}
