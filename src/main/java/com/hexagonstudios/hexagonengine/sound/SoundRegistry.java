package com.hexagonstudios.hexagonengine.sound;

import java.util.HashMap;

public class SoundRegistry {
	private HashMap<String, Sound> sounds;
	
	public SoundRegistry() {
		this.sounds = new HashMap<String, Sound>();
	}
	
	public void registerSound(Sound sound, String name) {
		sounds.put(name, sound);
	}
	
	public Sound getSound(String name) { return (Sound) sounds.get(name); }
}
