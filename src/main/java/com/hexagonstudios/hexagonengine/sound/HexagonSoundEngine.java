package com.hexagonstudios.hexagonengine.sound;

public class HexagonSoundEngine {
	private SoundRegistry registry;
	
	public HexagonSoundEngine() {
		this.registry = new SoundRegistry();
	}
	
	/**
	 * Plays a registered sound by name
	 * 
	 * @param soundName The set name of the sound to be played
	 * @author Felix Eckert
	 * */
	public void playSound(String soundName) {
		Sound sound = this.registry.getSound(soundName);
		sound.playSound();
	}
	
	/**
	 * Changes the volume of a sound by Name. (Works whilst sound is playing)
	 * 
	 * @param soundName Name of the sound of which the Volume should be changed
	 * @param volume The new sound volume
	 * @author Felix Eckert
	 * */
	public void changeSoundVolume(String soundName, float volume) {
		Sound sound = this.registry.getSound(soundName);
		sound.changeVolume(volume);
	}
	
	public SoundRegistry getSoundRegistry() { return this.registry; }
}
