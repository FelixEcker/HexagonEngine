package de.hexagonsoftware.engine.scene;

import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.game.GameObject;

/**
 * This class is used for Loading Scenes from JSON files
 * 
 * @author Felix Eckert
 * */
public class SceneLoader {
	private static Logger logger = LogManager.getLogger("SceneLoader");
	
	/**
	 * Load a scene from a JSON file
	 * 
	 * @param scenePath The path to the file to be loaded
	 * @return The loaded scene
	 * */
    public static Scene loadScene(String scenePath) {
    	logger.info("Loading scene "+scenePath);
    	Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(scenePath)));
		JsonObject sceneFile = gson.fromJson(reader, JsonObject.class);
		
		Scene scene = new Scene();
		
		logger.info("Loading GameObjects...");
		HashMap<String, GameObject> objects = new HashMap<>();
		getObjects(sceneFile, objects);
		scene.setGameObjects(objects);
		
		logger.info("Initialising scene...");
		initialise(sceneFile, scene);
		
		logger.info("Finished scene loading...");
		
		return scene;
    }

	private static void getObjects(JsonObject sceneFile, HashMap<String, GameObject> objects) {
    	JsonObject objectsJSON = sceneFile.get("objects").getAsJsonObject();

    	int loadedObjects = 0;
    	// ClassLoader for the GameObject classes
    	ClassLoader cloader = HexagonEngine.getGame().getClass().getClassLoader();
    	for (String name : objectsJSON.keySet()) {
	    	try {
				GameObject object = (GameObject) Class.forName(objectsJSON.get(name).getAsString(), true, cloader).newInstance();
				objects.put(name, object);
				loadedObjects++;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("A critical error occured whilst loading the scene, please read the Stacktrace Above for further information");
				logger.error("Stopping...");
				System.exit(-1);
			}
    	}
    	
    	logger.info("Loaded "+loadedObjects+" objects");
    }
	
    private static void initialise(JsonObject sceneFile, Scene scene) {
		JsonObject sceneJson = sceneFile.get("scene").getAsJsonObject();
		for (String entry : sceneJson.keySet()) {
			if (entry.matches("objectInstances")) {
				JsonArray objectInstances = sceneJson.get("objectInstances").getAsJsonArray();
				for (int i = 0; i < objectInstances.size(); i++) {
					JsonObject object = objectInstances.get(i).getAsJsonObject();
					scene.instantiateGameObject(object.get("object").getAsString(),
							object.get("x").getAsInt(), 
							object.get("y").getAsInt(), 
							object.get("width").getAsInt(), 
							object.get("height").getAsInt());
				}
			}
		}
	}
}