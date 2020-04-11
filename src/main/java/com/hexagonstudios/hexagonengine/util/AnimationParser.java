package com.hexagonstudios.hexagonengine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.*;
import com.hexagonstudios.hexagonengine.graphics.Animation;
import com.hexagonstudios.hexagonengine.graphics.HexagonGraphicsEngine;

public class AnimationParser {
	private HexagonGraphicsEngine gEngine;
	
	public AnimationParser(HexagonGraphicsEngine gEngine) {
		this.gEngine = gEngine;
	}
	
	public void parseAnimation(File animationFile) {
		JsonObject data = null;
		try {
			data = new Gson().fromJson(new FileReader(animationFile.getPath()), JsonObject.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("[ERROR][CRITICAL] HAP: An error occured whilst parsing an Animation! Exiting!");
			System.exit(1);
		}
		
		registerAnimation(new Animation(data.get("frameResX").getAsInt(), data.get("frameResY").getAsInt(), data.get("frames").getAsInt(), data.get("speed").getAsInt()),
				data.get("name").getAsString());
	}
	
	public void registerAnimation(Animation animation, String animationName) {
		gEngine.getAnimationRegistry().addAnimation(animation, animationName);
	}
}
