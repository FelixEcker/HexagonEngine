package de.hexagonsoftware.engine.resources;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.sound.Sound;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

/**
 * A basic sound Resource used for playing back
 * sounds.
 * 
 * {@link de.hexagonsoftware.engine.sound.Sound}
 * 
 * @author Felix Eckert
 * */
public class SoundResource implements IResource {
	private String path;
	private Sound sound;
	private boolean useGameResourceDir;
	
	public SoundResource(String path) {
		this.path = path;
		this.useGameResourceDir = false;
	}
	
	public SoundResource(String path, boolean useGameResourceDir) {
		this.path = path;
		this.useGameResourceDir = useGameResourceDir;
	}
	
	/**
	 * Loads the sound from the given path.
	 * @throws HEResourceLoadException
	 * */
	public void load() throws HEResourceLoadException {
		Class cls = useGameResourceDir ? HexagonEngine.getGame().getClass() : this.getClass();
		this.sound = new Sound(cls.getResource(path));
	}
	
	/**
	 * @return The loaded sound.
	 * */
	public Sound getSound() {
		if (sound == null) {
			try {
				load();
			} catch (HEResourceLoadException e) {
				e.printStackTrace();
			}
		}
		
		return sound; 
	}
	
	/**
     * Plays the set sound file.
     * @author Felix Eckert
     * */
    public void playSound() {
    	getSound().playSound();
    }
    
	public void stopSound() {
		getSound().stopSound();
	}

	// The double it returns is kinda strange...
	public double getTimeInSeconds() {
		return getSound().getTimeInSeconds();
	}

	public boolean isPlaying() {
		return getSound().isPlaying();
	}
	
    /**
     * Can be used to set the Volume of the Sound. Doesnt work whilst sound is playing.
     * @param volume Volume of the Sound.
     * @author Felix Eckert
     * */
    public void changeVolume(float volume) {
    	getSound().changeVolume(volume);
    }
}
