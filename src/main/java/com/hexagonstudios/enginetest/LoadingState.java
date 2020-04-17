package com.hexagonstudios.enginetest;

import java.awt.Color;
import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameState;
import com.hexagonstudios.hexagonengine.ObjectHandler;

public class LoadingState implements IGameState {
	boolean soundPlaying;
	int tick = 0;
	ObjectHandler obj;
	
	@Override
	public void update(HexagonEngine engine) {
		obj = engine.getObjectHandler();
		tick++;
		if (!soundPlaying) {
			obj.getSoundObject("testObject_sound").init(engine.getSoundEngine());
			obj.getSoundObject("testObject_sound").activate();
		}
		soundPlaying = true;
		
		if (soundPlaying && tick == 50) {
			obj.getSoundObject("testObject_sound").update(engine);; // Change the volume
			tick = 0;
		}
		
		if (engine.getKeyInput().wasKeyPressed('h')) {
			System.out.println("H");
		}
		
		obj.getObject("physicsObject_test").update(engine);
		engine.getPhysicsEngine().update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 59, 111));
		g.fillRect(100, 100, 250, 125);
		obj.getObject("physicsObject_test").render(g);
	}

}
