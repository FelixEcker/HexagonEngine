package com.hexagonstudios.enginetest;

import java.awt.Color;
import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameState;

public class LoadingState implements IGameState {
	boolean soundPlaying;
	int tick = 0;
	
	@Override
	public void update(HexagonEngine engine) {
		tick++;
		if (!soundPlaying) {
			engine.getObjectHandler().getSoundObject("testObject_sound").init(engine.getSoundEngine());
			engine.getObjectHandler().getSoundObject("testObject_sound").activate();
		}
		soundPlaying = true;
		
		if (soundPlaying && tick == 50) {
			engine.getObjectHandler().getSoundObject("testObject_sound").update(engine);; // Change the volume
			tick = 0;
		}
		
		if (engine.getKeyInput().wasKeyPressed('h')) {
			System.out.println("H");
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 59, 111));
		g.fillRect(100, 100, 250, 125);
	}

}
