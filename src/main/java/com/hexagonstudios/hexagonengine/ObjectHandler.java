package com.hexagonstudios.hexagonengine;

import java.util.HashMap;

import javax.annotation.Nullable;

import com.hexagonstudios.hexagonengine.sound.ISoundObject;

public class ObjectHandler {
	private HashMap<String, IGameObject> objects;
	private HashMap<String, ISoundObject> soundObjects;
	
	public ObjectHandler() {
		this.objects = new HashMap<String, IGameObject>();
		this.soundObjects = new HashMap<String, ISoundObject>();
	}
	
	public void registerObject(@Nullable IGameObject object, @Nullable ISoundObject soundObject) {
		if (object == null) {
			soundObjects.put(soundObject.getObjectName(), soundObject);
		} else {
			objects.put(object.getObjectName(), object);
		}
	}
	
	public void deleteObject(String name) {
		objects.remove(name);
	}
	
	public IGameObject getObject(String name) { return (IGameObject) objects.get(name); }
	public ISoundObject getSoundObject(String name) { return (ISoundObject) soundObjects.get(name); }
}
