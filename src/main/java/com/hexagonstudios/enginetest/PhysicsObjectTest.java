package com.hexagonstudios.enginetest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.IGameObject;
import com.hexagonstudios.hexagonengine.physics.IPhysicsObject;
import com.hexagonstudios.hexagonengine.sound.HexagonSoundEngine;

public class PhysicsObjectTest implements IPhysicsObject {
	int x;
	int y;
	int width;
	int height;
	
	@Override
	public void init(HexagonSoundEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(111, 123, 255));
		g.fillRect(200, 300, 100, 100);
	}

	@Override
	public void update(HexagonEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getObjectName() {
		return "physicsObject_test";
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public boolean collides() {
		return true;
	}

	@Override
	public void stopMovement() {}

	@Override
	public boolean isPhysicsObject() {
		return true;
	}
}
