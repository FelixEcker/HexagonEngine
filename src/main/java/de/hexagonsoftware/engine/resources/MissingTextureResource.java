package de.hexagonsoftware.engine.resources;

import java.awt.image.BufferedImage;

/**
 * Used incase a TextureResource is requested, which doesn't exist
 * @author Felix Eckert
 */
public class MissingTextureResource extends TextureResource {
	public MissingTextureResource(String path) {
		super(path);
	}

	public BufferedImage getImage() {
		return MissingTextureImage.getMissingTextureImage();
	}
}
