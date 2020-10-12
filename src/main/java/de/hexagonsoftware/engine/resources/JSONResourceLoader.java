package de.hexagonsoftware.engine.resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;

public class JSONResourceLoader {
	private static Logger logger = LogManager.getLogger("JSONResourceLoader");
	
	public static void loadResources(String file) throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(file)));
		JsonObject resourceFile = gson.fromJson(reader, JsonObject.class);
		
		if (resourceFile.has("textures")) {
			loadTextures(resourceFile);
		}
	}
	
	public static void loadTextures(JsonObject resourceFile) {
		JsonObject textures = resourceFile.get("textures").getAsJsonObject();
		
		int loadedTextures = 0;
		
		for (String texture : textures.keySet()) {
			loadedTextures++;
			HexagonEngine.HE_RES_MANAGER.addResource(texture, new TextureResource(textures.get(texture).getAsString(), true));
		}
		
		logger.info("Loaded "+loadedTextures+" textures");
	}
}
