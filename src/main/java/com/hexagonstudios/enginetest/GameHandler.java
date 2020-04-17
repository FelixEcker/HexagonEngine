package com.hexagonstudios.enginetest;

import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameHandler;
import com.hexagonstudios.hexagonengine.IGameState;
import com.hexagonstudios.hexagonengine.sound.Sound;

public class GameHandler implements IGameHandler {
	HexagonEngine engine;
	GameStates states = new GameStates();
	IGameState currentState;
	boolean trackPlaying;
	boolean rise;
	float vol = 0.01f;
	int tick;
	
	@Override
	public void init(HexagonEngine engine) {
		this.engine = engine;
		engine.getSoundEngine().getSoundRegistry().registerSound(new Sound(this.getClass().getClassLoader().getResource("testtrack.wav")), "testtrack");
		engine.getObjectHandler().registerObject(new TestObject(), null);
		engine.getObjectHandler().registerObject(null, new SoundObjectTest());
		engine.getObjectHandler().registerObject(new PhysicsObjectTest(), null);
		states.addGameState(new BaseState());
		states.addGameState(new LoadingState());
	}
	
	@Override
	public void update() {
		tick++;
		if (this.getCurrentState() == null) {
			setState(states.getGameState(1));
			return;
		}
		
		getCurrentState().update(engine);
	}

	@Override
	public void render(Graphics g) {
		if (this.getCurrentState() == null) {
			setState(states.getGameState(1));
			return;
		}
		this.getCurrentState().render(g);
	}

	@Override
	public IGameState getCurrentState() {
		return this.currentState;
	}

	@Override
	public void setState(IGameState state) {
		this.currentState = state;
	}

}
