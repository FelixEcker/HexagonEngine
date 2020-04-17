package com.hexagonstudios.hexagonengine;

import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.sound.HexagonSoundEngine;

public interface IGameObject {
	void init(HexagonSoundEngine engine);
	void render(Graphics g);
	void update(HexagonEngine engine);
	
	String getObjectName();
	boolean isPhysicsObject();
}
