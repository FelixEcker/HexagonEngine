package de.hexagonsoftware.engine.scene;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;

public class Scene {
    private HashMap<String, GameObject> gameObjects;
    private List<GameObject> gameObjectInstances;
    
    private boolean started = false;
    
	private static Logger logger = LogManager.getLogger("SceneLogger");

    public Scene() {
        gameObjects = new HashMap<>();
        gameObjectInstances = new LinkedList<>();
    }
    
    public void addGameObject(String name, GameObject obj) {
    	gameObjects.put(name, obj);
    }

	public void setGameObjects(HashMap<String, GameObject> objects) {
		this.gameObjects = objects;
	}
	
	public void instantiateGameObject(String name, int x, int y, int width, int height) {
		GameObject instance = null;
		try {
			instance = gameObjects.get(name).getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			logger.error("A critical error occured whilst Instantiating a new GameObject! Please read the stacktrace above for further information.");
			System.exit(-1);
		}
		instance.setX(x);
		instance.setY(y);
		instance.setWidth(width);
		instance.setHeight(height);
		gameObjectInstances.add(instance);
		
		if (started) {
			HexagonEngine.HE_GOBJ_MANAGER.addGameObject(name+HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().size()+1, instance);
		}
	}
	
	public void start() {
		for (GameObject obj : gameObjectInstances) {
			HexagonEngine.HE_GOBJ_MANAGER.addGameObject(obj.getClass().getName()+HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().size()+1, obj);
		}
		
		started = true;
	}
}