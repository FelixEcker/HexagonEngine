package de.hexagonsoftware.engine.resources;

import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.util.EngineConfig;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

public class JSONResourceLoader {
	private static Logger logger = LogManager.getLogger("JSONResourceLoader");
	
	public static void loadResources(String file) throws HEResourceLoadException {
		logger.info("Loading Resources from JSON file "+file);
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(file)));
		JsonObject resourceFile = gson.fromJson(reader, JsonObject.class);
		
		if (resourceFile.has("textures")) {
			loadTextures(resourceFile);
		}
		if (resourceFile.has("sounds")) {
			loadSounds(resourceFile);
		}
	}
	
	public static void loadTextures(JsonObject resourceFile) {
		JsonObject textures = resourceFile.get("textures").getAsJsonObject();
		String assetsRoot   = HexagonEngine.getEngineConfig().getAssetsRoot();
		
		if (assetsRoot == null)
			assetsRoot = "";
		
		int loadedTextures = 0;
		
		for (String texture : textures.keySet()) {
			loadedTextures++;
			HexagonEngine.HE_RES_MANAGER.addResource(texture, new TextureResource(assetsRoot+textures.get(texture).getAsString(), true));
		}
		
		logger.info("Loaded "+loadedTextures+" texture(s)");
	}
	
	public static void loadSounds(JsonObject resourceFile) {
		JsonObject sounds = resourceFile.get("sounds").getAsJsonObject();
		String assetsRoot   = HexagonEngine.getEngineConfig().getAssetsRoot();
		
		if (assetsRoot == null)
			assetsRoot = "";
		
		int loadedSounds = 0;
		
		for (String sound : sounds.keySet()) {
			loadedSounds++;
			HexagonEngine.HE_RES_MANAGER.addResource(sound, new SoundResource(assetsRoot+sounds.get(sound).getAsString(), true));
		}
		
		logger.info("Loaded "+loadedSounds+" sound(s)");
	}
}
