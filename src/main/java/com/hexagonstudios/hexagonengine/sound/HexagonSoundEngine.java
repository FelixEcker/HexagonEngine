package com.hexagonstudios.hexagonengine.sound;

public class HexagonSoundEngine {
	private SoundRegistry registry;
	
	public HexagonSoundEngine() {
		this.registry = new SoundRegistry();
	}
	
	public SoundRegistry getSoundRegistry() { return this.registry; }
}
