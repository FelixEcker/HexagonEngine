package de.hexagonsoftware.test;
 
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.resources.JSONResourceLoader;
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
					JSONResourceLoader.loadResources("/assets/resources.json");
				} catch (HEResourceLoadException e) {
					e.printStackTrace();
				}
				
				Scene mainScene = SceneLoader.loadScene("/assets/scene.json");
				HexagonEngine.setHE_IGAME_IMP_SCENE(mainScene);
			}
			
			@Override
			public void start() {
				HexagonEngine.HE_GOBJ_MANAGER.setLogging(true);
				HexagonEngine.getHE_IGAME_IMP_SCENE().start();
				HexagonEngine.getHE_HUD_MANAGER().addHUD("test", new TestHud());
				HexagonEngine.getHE_HUD_MANAGER().setActiveHUD("test");
				HexagonEngine.getHE_HUD_MANAGER().activateHUD();
			}
			
			@Override
			public void update() {
				if (HexagonEngine.HE_MOUSE_INPUT.wasButtonClicked(1)) {
					HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().get("de.hexagonsoftware.test.Player2").setX(0);
					HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().get("de.hexagonsoftware.test.Player2").setY(0);
				}
			}

			@Override
			public void render(Graphics g) {
			}

		}, "/config.json");
		engine.start();
	}
}
