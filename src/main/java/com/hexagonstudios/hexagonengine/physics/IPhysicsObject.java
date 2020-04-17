package com.hexagonstudios.hexagonengine.physics;

import java.awt.Rectangle;

import com.hexagonstudios.hexagonengine.IGameObject;

public interface IPhysicsObject extends IGameObject {
	Rectangle getBounds();
	boolean collides();
	void stopMovement();
}
