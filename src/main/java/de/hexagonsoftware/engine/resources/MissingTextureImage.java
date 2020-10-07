package de.hexagonsoftware.engine.resources;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MissingTextureImage {
	/**
	 * @return A programatically generated MissingTextureImage
	 * @author Felix Eckert
	 * */
	public static BufferedImage getMissingTextureImage() {
		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, 8, 8);

		g.setColor(Color.RED);
		g.fillRect(8, 8, 8, 8);
		
		g.setColor(Color.BLUE);
		g.fillRect(8, 0, 8, 8);

		g.setColor(Color.BLUE);
		g.fillRect(0, 8, 8, 8);
		
		g.dispose();
		return img;
	}
}
