package de.hexagonsoftware.engine.resources;

import java.awt.image.BufferedImage;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.util.ErrorHandler;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

/**
 * This class is used for creating new Texture resources
 * 
 * @author Felix Eckert
 * */
public class TextureResource implements IResource {
	private String path;
	private BufferedImage img;
	private boolean useGameResourceDir;
	
	public TextureResource(String path) {
		this.path = path;
		this.useGameResourceDir = false;
	}
	
	public TextureResource(String path, boolean useGameResourceDir) {
		this.path = path;
		this.useGameResourceDir = useGameResourceDir;
	}
	
	/**
	 * Loads the texture from the given path.
	 * */
	public void load() {
		try {
			Class<?> cls = useGameResourceDir ? HexagonEngine.getGame().getClass() : this.getClass();
			this.img = ImageLoader.loadImage(cls.getResource(path));
		} catch (HEResourceLoadException e) {
			this.img = ((TextureResource) ResourceManager.getInstance().getResource(ResourceManager.RES_MANAGER_STD_MTNAME)).getImage();
			ErrorHandler.reportException(new HEResourceLoadException());
		}
	}
	
	/**
	 * @return The loaded image.
	 * */
	public BufferedImage getImage() {
		if (img == null)
			load();
		
		return img; 
	}
}
