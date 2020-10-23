package de.hexagonsoftware.engine.resources;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hexagonsoftware.engine.util.HEMissingResourceException;

/**
 * This class manages game-resources such as Sounds and Images.
 * 
 * @author Felix Eckert
 * */
public class ResourceManager {
	/**
	 * Singleton instance of the Resource Manager
	 * */
	private static ResourceManager RES_MANAGER;
	/**
	 * GOBJ Logger instance
	 * */
	private static Logger logger = LogManager.getLogger("HexagonEngine_RESM");
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
	private HashMap<String, IResource> RESOURCES;
	/**
	 * The name for the Engine-Standard Missing Texture Resource
	 * */
	public static final String RES_MANAGER_STD_MTNAME = "HEGE_STD_MissingTexture";
	
	private ResourceManager() {
		this.RESOURCES = new HashMap<>();
		this.RESOURCES.put(RES_MANAGER_STD_MTNAME, new MissingTextureResource(""));
	}
	
	/**
	 * @return Returns an Singleton instance of this class.
	 * */
	public static ResourceManager getInstance() {
		if (RES_MANAGER == null)
			RES_MANAGER = new ResourceManager();
		
		return RES_MANAGER;
	}
	
	/***************************************************/
	
	/**
	 * Adds a IResource instance to the resource-map
	 * 
	 * @param name The Name to be put as key in the Map
	 * @param res  The Resource to be put as value in the Map.
	 * */
	public void addResource(String name, IResource res) {
		log("Added new Resource \""+name+"\"", LOG_INFO);
		this.RESOURCES.put(name, res);
	}
	
	/**
	 * @param name The name of the resource.
	 * @return The requested resource.
	 * @author Felix Eckert
	 * */
	public IResource getResource(String name) {
		if (this.RESOURCES.get(name) == null)
			throw new HEMissingResourceException();
		
		return this.RESOURCES.get(name);
	}
	
	/**
	 * @param name The name of the resource.
	 * @return The requested resource.
	 * @author Felix Eckert
	 * */
	public TextureResource getTextureResource(String name) {
		if (this.RESOURCES.get(name) == null)  {
			logger.warn("The resource \""+name+"\" is missing!");
			return getTextureResource(RES_MANAGER_STD_MTNAME);
		}
		
		return (TextureResource) this.RESOURCES.get(name);
	}
	
	/**
	 * @param name The name of the resource.
	 * @return The requested resource.
	 * @author Felix Eckert
	 * */
	public SoundResource getSoundResource(String name) {
		if (this.RESOURCES.get(name) == null)  {
			logger.warn("The resource \""+name+"\" is missing!");
			return null;
		}
		
		IResource res = this.RESOURCES.get(name);
		
		if (!(res instanceof SoundResource)) {
			logger.error("The requested resource is not a SoundResource!");
		}
		
		return (SoundResource) res;
	}
	
	/**
	 * @param name The name of the resource.
	 * @return The requested resource.
	 * @author Felix Eckert
	 * */
	public FontResource getFontResource(String name) {
		if (this.RESOURCES.get(name) == null)  {
			logger.warn("The resource \""+name+"\" is missing!");
			return null;
		}
		
		IResource res = this.RESOURCES.get(name);
		
		if (!(res instanceof FontResource)) {
			logger.error("The requested resource is not a FontResource!");
		}
		
		return (FontResource) res;
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
	 * Sets if this class should log or not.
	 * */
	public void setLogging(boolean b) { this.log = b; }
}
