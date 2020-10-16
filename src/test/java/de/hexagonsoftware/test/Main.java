package de.hexagonsoftware.test;
 
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.resources.JSONResourceLoader;
import de.hexagonsoftware.engine.resources.TextureResource;
import de.hexagonsoftware.engine.scene.Scene;
import de.hexagonsoftware.engine.scene.SceneLoader;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

public class Main {
	public static void main(String[] args) {
		HexagonEngine engine = new HexagonEngine(60);
		engine.initialise(new IGame() {
			@Override
			public void init() {
				HexagonEngine.HE_RES_MANAGER.setLogging(true);
				try {
					JSONResourceLoader.loadResources("/resources.json");
				} catch (HEResourceLoadException e) {
					e.printStackTrace();
				}
				Scene mainScene = SceneLoader.loadScene("/scene.json");
				HexagonEngine.setHE_IGAME_IMP_SCENE(mainScene);
			}
			
			@Override
			public void start() {
				HexagonEngine.HE_GOBJ_MANAGER.setLogging(true);
				HexagonEngine.getHE_IGAME_IMP_SCENE().start();
			}
			
			@Override
			public void update() {
				if (HexagonEngine.HE_MOUSE_INPUT.wasButtonClicked(1)) {
					HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().get("player").setX(0);
					HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().get("player").setY(0);
					System.out.println("A");
				}
			}

			@Override
			public void render(Graphics g) {
			}

		}, "test", 1920, 1080, false);
		engine.start();
	}
}
