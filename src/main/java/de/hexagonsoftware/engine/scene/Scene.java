package de.hexagonsoftware.engine.scene;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;

/**
 * The HexagonEngine version of Scenes.
 * 
 * Scenes can be used for creating Levels or static
 * worlds using JSON or programmatically.
 * 
 * @author Felix Eckert
 * */
public class Scene {
    private HashMap<String, GameObject> gameObjects;
    private List<GameObject> gameObjectInstances;
    
    private boolean started = false;
    
	private static Logger logger = LogManager.getLogger("SceneLogger");

    public Scene() {
        gameObjects = new HashMap<>();
        gameObjectInstances = new LinkedList<>();
    }
    
    /**
     * Add a GameObject to the scene. This will not add it to the Object manager,
     * rather its use is to add them for instantiation by name later on using the
     * instantiateGameObject function.
     * 
     * @param name The name of the GameObject for future reference.
     * @param obj  The GameObject to be added.
     * */
    public void addGameObject(String name, GameObject obj) {
    	gameObjects.put(name, obj);
    }

    /**
     * Set the gameObjects HashMap, this function has the same purpose as the
     * addGameObject function but with this function, it can be done with pre-existing
     * HashMaps.
     * 
     * @param object The HashMap of objects to be added
     * */
	public void setGameObjects(HashMap<String, GameObject> objects) {
		this.gameObjects = objects;
	}
	
	/**
	 * Instantiates a GameObject from the gameObjects HashMap by name.
	 * This will add the new instance to the Object Manager automatically,
	 * if the start function of the scene was already called.
	 * 
	 * @param name   The name of the Object to be instantiated
	 * @param x      The x Coordinate of the object
	 * @param y      The y Coordinate of the object
	 * @param width  The width of the object
	 * @param height The height of the object
	 * */
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
			HexagonEngine.HE_GOBJ_MANAGER.addGameObject(name+(HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().size()+1), instance);
		}
	}
	
	/**
	 * This function is used to start all GameObject added pre-start of the Scene.
	 * */
	public void start() {
		for (GameObject obj : gameObjectInstances) {
			HexagonEngine.HE_GOBJ_MANAGER.addGameObject(obj.getClass().getName()+(HexagonEngine.HE_GOBJ_MANAGER.getGOBJ_OBJS().size()+1), obj);
		}
		
		started = true;
	}
}