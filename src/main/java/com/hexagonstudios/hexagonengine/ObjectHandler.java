package com.hexagonstudios.hexagonengine;

import java.util.HashMap;

public class ObjectHandler {
	HashMap<String, IGameObject> objects;
	
	public ObjectHandler() {
		this.objects = new HashMap<String, IGameObject>();
	}
	
	public void registerObject(IGameObject object) {
		objects.put(object.getObjectName(), object);
	}
	
	public void deleteObject(String name) {
		objects.remove(name);
	}
	
	public IGameObject getObject(String name) { return (IGameObject) objects.get(name); }
}
