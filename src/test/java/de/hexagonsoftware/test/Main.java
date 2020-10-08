package de.hexagonsoftware.test;
 
import java.awt.Graphics;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.IGame;
import de.hexagonsoftware.engine.resources.TextureResource;

public class Main {
	public static void main(String[] args) {
		HexagonEngine engine = new HexagonEngine(60);
		engine.initialise("test", 1920, 1080, false);
		engine.start(new IGame() {
			@Override
			public void start() {
				HexagonEngine.HE_GOBJ_MANAGER.setLogging(true);
				HexagonEngine.HE_RES_MANAGER.setLogging(true);
				HexagonEngine.HE_RES_MANAGER.addResource("CharacterFRONT", new TextureResource("/textures/Character.png"));
				HexagonEngine.HE_RES_MANAGER.addResource("CharacterBACK", new TextureResource("/textures/Character_Back.png"));
				HexagonEngine.HE_RES_MANAGER.addResource("CharacterLEFT", new TextureResource("/textures/Character_Left.png"));
				HexagonEngine.HE_RES_MANAGER.addResource("CharacterRIGHT", new TextureResource("/textures/Character_Right.png"));
				HexagonEngine.HE_GOBJ_MANAGER.addGameObject("player", new Player());
				HexagonEngine.HE_GOBJ_MANAGER.addGameObject("background", new BackgroundRenderer());
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
		});
	}
}
