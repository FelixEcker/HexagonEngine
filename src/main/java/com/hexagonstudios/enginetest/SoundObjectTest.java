package com.hexagonstudios.enginetest;

import java.awt.Graphics;

import com.hexagonstudios.hexagonengine.HexagonEngine;
import com.hexagonstudios.hexagonengine.sound.HexagonSoundEngine;
import com.hexagonstudios.hexagonengine.sound.ISoundObject;
import com.hexagonstudios.hexagonengine.sound.Sound;

public class SoundObjectTest implements ISoundObject {
	private HexagonSoundEngine soundEngine;
	private String soundName;
	private Sound sound;
	private float vol = 0.0f;
	
	@Override
	public void init(HexagonSoundEngine soundEngine) {
		this.soundEngine = soundEngine;
		this.soundName   = "testtrack"; 
	}
	
	@Override
	public void activate() {
		sound = soundEngine.getSoundRegistry().getSound(soundName);
		sound.playSound();
	}

	@Override
	public void update(HexagonEngine engine) {
		vol += 0.1f;
		sound.changeVolume(vol); // Change the volume
	}

	@Override
	public void render(Graphics g) {}

	@Override
	public String getObjectName() {
		return "testObject_sound";
	}
	
	@Override
	public boolean isPhysicsObject() {
		return false;
	}
}
