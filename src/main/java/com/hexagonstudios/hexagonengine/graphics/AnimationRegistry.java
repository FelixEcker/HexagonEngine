package com.hexagonstudios.hexagonengine.graphics;

import java.util.HashMap;

public class AnimationRegistry {
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();
	
	public void addAnimation(Animation animation, String animationName) {
		animations.put(animationName, animation);
	}
	
	public Animation getAnimation(String name) { return (Animation) animations.get(name); }
}
