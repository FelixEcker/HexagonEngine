package de.hexagonsoftware.engine.util;

import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;

/**
 * This class is used for storing an EngineConfiguration,
 * it is utilized by the engine to shorten the amount of
 * parameters required for initialization, instead it will
 * load these parameters from a given JSON File which this
 * class loads.
 * 
 * @author Felix Eckert
 * */
public class EngineConfig {
	private static Logger logger = LogManager.getLogger("EngineConfig");
	private JsonObject configFile;
	
	/**
	 * @param filePath The path to the Configuration file
	 * */
	public EngineConfig(String filePath) {
		logger.info("Loading engine config from "+filePath);
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(filePath)));
		this.configFile = gson.fromJson(reader, JsonObject.class);
	}
	
	/**
	 * @return The wanted title for the HEWindow instance
	 * */
	public String getWindowTitle() {
		return this.configFile.get("window").getAsJsonObject().get("title").getAsString();
	}
	
	/**
	 * @return The wanted width for the HEWindow instance
	 * */
	public int getWindowWidth() {
		return this.configFile.get("window").getAsJsonObject().get("width").getAsInt();
	}
	
	/**
	 * @return The wanted height for the HEWindow instance
	 * */
	public int getWindowHeight() {
		return this.configFile.get("window").getAsJsonObject().get("height").getAsInt();
	}
	
	/**
	 * @return If the HEWindow instance should be resizable
	 * */
	public boolean getWindowResizable() {
		return this.configFile.get("window").getAsJsonObject().get("resizable").getAsBoolean();
	}
	
	/**
	 * @return The folder in which all assets are stored
	 * */
	public String getAssetsRoot() {
		return this.configFile.get("assetsroot").getAsString();
	}
	
	/**
	 * @return The resources.json for automatic resource loading
	 * */
	public String getResourcesFile() {
		return this.configFile.get("resourcesfile").getAsString();
	}
}
