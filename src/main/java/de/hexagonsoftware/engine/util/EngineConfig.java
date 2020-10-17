package de.hexagonsoftware.engine.util;

import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;

public class EngineConfig {
	private static Logger logger = LogManager.getLogger("EngineConfig");
	private JsonObject configFile;
	
	public EngineConfig(String filePath) {
		logger.info("Loading engine config from "+filePath);
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(filePath)));
		this.configFile = gson.fromJson(reader, JsonObject.class);
	}
	
	public String getWindowTitle() {
		return this.configFile.get("window").getAsJsonObject().get("title").getAsString();
	}
	
	public int getWindowWidth() {
		return this.configFile.get("window").getAsJsonObject().get("width").getAsInt();
	}
	
	public int getWindowHeight() {
		return this.configFile.get("window").getAsJsonObject().get("height").getAsInt();
	}
	
	public boolean getWindowResizable() {
		return this.configFile.get("window").getAsJsonObject().get("resizable").getAsBoolean();
	}
	
	public String getAssetsRoot() {
		return this.configFile.get("assetsroot").getAsString();
	}
	
	public String getResourcesFile() {
		return this.configFile.get("resourcesfile").getAsString();
	}
}
