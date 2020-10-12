package de.hexagonsoftware.test.layeredrendering;

import java.awt.Graphics;
import java.io.FileNotFoundException;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.resources.JSONResourceLoader;

public class LayeredRenderingTest implements IGame {

	@Override
	public void start() {
		try {
			JSONResourceLoader.loadResources("/resources.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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