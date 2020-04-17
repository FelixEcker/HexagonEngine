package com.hexagonstudios.hexagonengine.physics;

public interface IPhysicsObject {
	int[] getBounds();
	boolean collides();
	void stopMovement();
}
