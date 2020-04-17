package com.hexagonstudios.hexagonengine.physics;

import java.util.HashMap;

import com.hexagonstudios.hexagonengine.*;

public class HexagonPhysicsEngine {
	private HexagonEngine engine;
	
	public HexagonPhysicsEngine(HexagonEngine engine) {
		this.engine = engine;
	}
	
	public void update() {
		HashMap<String, IGameObject> objects = engine.getObjectHandler().getObjectMap();
		
		// Goes through every Object
		for (IGameObject i : objects.values()) {
			if (i.isPhysicsObject()) { // Checks if Object is PhysicsObject
				for (IGameObject j : objects.values()) { // Goes through Object again
					if (j.isPhysicsObject() && j != i) { // Cehcks if new object is PhysicsObject and not i
						// Convert i and j to IPhysicsObject
						IPhysicsObject obj = (IPhysicsObject) i;
						IPhysicsObject obj2 = (IPhysicsObject) j;
						if (obj.getBounds().intersects(obj2.getBounds())) { // Check if intersecting
							// If intersecting, stop the movement of both
							obj.stopMovement();
							obj2.stopMovement();
							continue;
						}
						continue;
					}
				}
			}
		}
	}
}
