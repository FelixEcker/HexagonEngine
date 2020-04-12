package com.hexagonstudios.enginetest;

import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameObject;
import com.hexagonstudios.hexagonengine.sound.HexagonSoundEngine;

public class TestObject implements IGameObject {

	@Override
	public void init(HexagonSoundEngine soundEngine) {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(HexagonEngine engine) {
		
	}

	@Override
	public String getObjectName() { return "TestObject"; }
	
}
