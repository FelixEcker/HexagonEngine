package de.hexagonsoftware.engine.game.components;

import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;
import de.hexagonsoftware.engine.game.IGameObjectComponent;
import de.hexagonsoftware.engine.resources.TextureResource;

/**
 * This GameObjectComponent is used for rendering sprites.
 * 
 * @author Felix Eckert
 * */
public class SpriteRenderer implements IGameObjectComponent {
	/**
	 * The TextureResource used for rendering
	 * */
	private TextureResource texture;
	/**
	 * The GameObject it was added to
	 * */
	private GameObject parentObj;
	/**
	 * The width scaling factor
	 * */
	private int widthScaleFactor = 0;
	/**
	 * The width scaling factor
	 * */
	private int heightScaleFactor = 0;

	private int x, y;
	private boolean useObjectCoords;
	
	/**
	 * @param textureName 	  The name of the TextureResource to be used in rendering
	 * @param parentObj  	  The GameObject this instance was added to
	 * @param useObjectCoords Sets if the renderer should use the Coordinates of the Parent Object for rendering
	 * */
	public SpriteRenderer(GameObject parentObj, String textureName, boolean useObjectCoords) {
		this.texture = HexagonEngine.HE_RES_MANAGER.getTextureResource(textureName);
		this.parentObj = parentObj;
		this.useObjectCoords = useObjectCoords;
		this.x = 0;
		this.y = 0;
	}
	
	@Override
	public void start() {}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {
		int posX = useObjectCoords ? parentObj.getX() : x;
		int posY = useObjectCoords ? parentObj.getY() : y;
		
		/*
		if (posX+(texture.getImage().getWidth()*widthScaleFactor) > HexagonEngine.getWidth()) {
			posX = (posX - HexagonEngine.HE_CAMERA.getScrollX())+texture.getImage().getWidth()*widthScaleFactor;
		} else if (posX+(texture.getImage().getWidth()*widthScaleFactor) < HexagonEngine.getWidth()) {
			posX = (posX + HexagonEngine.HE_CAMERA.getScrollX())+texture.getImage().getWidth()*widthScaleFactor;
		}
		
		if (posY+(texture.getImage().getHeight()*heightScaleFactor)  > HexagonEngine.getHeight()) {
			posY = posY - HexagonEngine.HE_CAMERA.getScrollY();
		} else if (posY+(texture.getImage().getHeight()*heightScaleFactor) < HexagonEngine.getHeight()) {
			posY = posY + HexagonEngine.HE_CAMERA.getScrollY();
		}
		*/
		
		g.drawImage(texture.getImage(),
				posX,
				posY,
				texture.getImage().getWidth()*widthScaleFactor, 
				texture.getImage().getHeight()*heightScaleFactor,
				null);
	}
	
	public int getHeightScaleFactor() {
		return heightScaleFactor;
	}

	public void setHeightScaleFactor(int heightScaleFactor) {
		this.heightScaleFactor = heightScaleFactor;
	}
	
	public int getWidthScaleFactor() {
		return widthScaleFactor;
	}

	public void setWidthScaleFactor(int widthScaleFactor) {
		this.widthScaleFactor = widthScaleFactor;
	}
	
	public TextureResource getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = (TextureResource) HexagonEngine.HE_RES_MANAGER.getResource(texture);
	}

	public void setX(int i) {
		this.x = i;
	}
	
	public void setY(int i) {
		this.y = i;
	}
}
