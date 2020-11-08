package de.hexagonsoftware.test.layeredrendering;

import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.resources.JSONResourceLoader;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

public class LayeredRenderingTest implements IGame {
	@Override
	public void init() {
		JSONResourceLoader.loadResources("/resources.json");
	}
	
	@Override
	public void start() {
		HexagonEngine.HE_GOBJ_MANAGER.addGameObject("test", new TestObject());
		System.out.println(HexagonEngine.HE_RES_MANAGER.getTextureResource("CharacterFRONT").getImage());
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}
}
