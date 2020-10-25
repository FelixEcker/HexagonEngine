package de.hexagonsoftware.engine.game;

import java.awt.Graphics;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hexagonsoftware.engine.game.prefabs.BackgroundRendererObject;

/**
 * This class manages all GameObjects in existance.
 * 
 * @author Felix Eckert
 * */
public class GameObjectManager {
	/**
	 * Singleton instance of the GOBJ Manager
	 * */
	private static GameObjectManager GOBJ_MANAGER;
	/**
	 * GOBJ Logger instance
	 * */
	private static Logger logger = LogManager.getLogger("HexagonEngine_GOBJM");
	/**
	 * Sets if actions should be printed to log.
	 * */
	private static boolean log;
	/**
	 * Logging levels
	 * */
	private int LOG_INFO = 0, LOG_DEBUG = -1, LOG_WARN = 2, LOG_ERR = 3;
	/**
	 * A Map containing all existing GameObejcts
	 * */
	private HashMap<String, GameObject> GOBJ_OBJS;
	/**
	 * The name of the Background Renderer in the GOBJ_OBJS Map
	 * */
	public String BACKGROUND_RENDERER_NAME;
	
	private GameObjectManager() {
		this.GOBJ_OBJS = new HashMap<>();
	}
	
	/**
	 * @return Returns an Singleton instance of this class.
	 * */
	public static GameObjectManager getInstance() {
		if (GOBJ_MANAGER == null)
			GOBJ_MANAGER = new GameObjectManager();
		
		return GOBJ_MANAGER;
	}
	
	/**************************************************/
	
	/**
	 * Adds a gameobject to the object-map
	 * 
	 * @param name The Name to be put as key in the Map
	 * @param obj  The Object to be put as value in the Map.
	 * */
	public void addGameObject(String name, GameObject obj) {
		log("Added new GameObject \""+name+"\"", LOG_INFO);
		
		if (obj instanceof BackgroundRendererObject)
			BACKGROUND_RENDERER_NAME = name;
		
		this.GOBJ_OBJS.put(name, obj);
	}
	
	/**
	 * Calls the start function in every Game Object
	 * */
	public void start() {
		for (Object obj : this.GOBJ_OBJS.values()) {
			((GameObject) obj).start();
		}
	}
	
	/**
	 * Calls the update function in every Game Object
	 * */
	public void update() {
		for (Object obj : this.GOBJ_OBJS.values()) {
			((GameObject) obj).update();
		}
	}
	
	/**
	 * Calls the render function in every Game Object
	 * @param g The Graphics object instance used for rendering
	 * */
	public void render(Graphics g) {
		if (BACKGROUND_RENDERER_NAME != null)
			((GameObject) GOBJ_OBJS.get(BACKGROUND_RENDERER_NAME)).render(g);
		
		for (Object obj : this.GOBJ_OBJS.values()) {
			if (obj == GOBJ_OBJS.get(BACKGROUND_RENDERER_NAME))
				continue;
			
			((GameObject) obj).render(g);
		}
	}
	
	/**
	 * Used for internal logging; Only logs if the field "log" is true
	 * 
	 * @param msg The message to be logged
	 * @param level The level for the logger to use.
	 * */
	private static void log(String msg, int level) {
		if (!log)
			return;
		
		if (level == -1)
			logger.debug(msg);
		
		if (level == 0)
			logger.info(msg);

		if (level == 2)
			logger.warn(msg);

		if (level == 3)
			logger.error(msg);
	}
	
	/**
	 * @param b Sets if this class should log or not.
	 * */
	public void setLogging(boolean b) { this.log = b; }

	public HashMap<String, GameObject> getGOBJ_OBJS() {
		return GOBJ_OBJS;
	}
}
