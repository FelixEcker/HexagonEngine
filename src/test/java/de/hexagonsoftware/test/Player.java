package de.hexagonsoftware.test;

import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;
import de.hexagonsoftware.engine.game.components.LayeredSpriteRenderer;
import de.hexagonsoftware.engine.game.components.SpriteRenderer;
import de.hexagonsoftware.engine.resources.TextureResource;

public class Player extends GameObject {	
	private float movementSpeed = 5.0f;
	
	@Override
	public void start() {
		super.setX(0);
		super.setY(0);
		SpriteRenderer layer1 = new SpriteRenderer(this, "CharacterFRONT", true);
		SpriteRenderer layer2 = new SpriteRenderer(this, "CharacterLEFT", true);
		layer1.setWidthScaleFactor(5);
		layer1.setHeightScaleFactor(5);
		layer2.setWidthScaleFactor(5);
		layer2.setHeightScaleFactor(5);
		LayeredSpriteRenderer lsr = new LayeredSpriteRenderer();
		lsr.start();
		lsr.addLayer(layer1, false);
		lsr.addLayer(layer2, true);
		addComponent("lsr", lsr);
		super.setWidth(((TextureResource) HexagonEngine.HE_RES_MANAGER.getResource("CharacterFRONT")).getImage().getWidth()*5);
		super.setHeight(((TextureResource) HexagonEngine.HE_RES_MANAGER.getResource("CharacterFRONT")).getImage().getHeight()*5);
		super.start(false);
	}

	@Override
	public void update() {
		super.update();
		if (HexagonEngine.HE_KEY_INPUT.isKeyDown('w')) {
			super.setY((int)(super.getY()-movementSpeed));
			if (super.getY() < HexagonEngine.getHeight()) {
				HexagonEngine.HE_CAMERA.setScrollY(HexagonEngine.HE_CAMERA.getScrollY()-(int)movementSpeed);
			}
		}
		if (HexagonEngine.HE_KEY_INPUT.isKeyDown('a')) {
			super.setX((int)(super.getX()-movementSpeed));
			if (super.getX() < HexagonEngine.getWidth())
				HexagonEngine.HE_CAMERA.setScrollX(HexagonEngine.HE_CAMERA.getScrollX()-(int)movementSpeed);
		}
		if (HexagonEngine.HE_KEY_INPUT.isKeyDown('s')) {
			super.setY((int)(super.getY()+movementSpeed));
			if (super.getY() > HexagonEngine.getHeight())  {
				HexagonEngine.HE_CAMERA.setScrollY(HexagonEngine.HE_CAMERA.getScrollY()+(int)movementSpeed);
			}
		}
		if (HexagonEngine.HE_KEY_INPUT.isKeyDown('d')) {
			super.setX((int)(super.getX()+movementSpeed));
			if (super.getX() > HexagonEngine.getWidth())
				HexagonEngine.HE_CAMERA.setScrollX(HexagonEngine.HE_CAMERA.getScrollX()+(int)movementSpeed);
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}
}
