package com.hexagonstudios.hexagonengine.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private URL url;
	private AudioInputStream stream;
	private Clip clip;
	
	/**
	 * @param URL path to the sound file that the Sound-Object instance should correspond to.
	 * @author Felix Eckert
	 * */
	public Sound(URL url) {
		this.url = url;
		
		try {
			this.stream = AudioSystem.getAudioInputStream(url);
			this.clip = AudioSystem.getClip();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays the set sound file.
	 * @author Felix Eckert
	 * */
	public void playSound() {
		try {
			this.clip.open(stream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		clip.start();
	}
	
	/**
	 * Can be used to set the Volume of the Sound. Doesnt work whilst sound is playing.
	 * @param volume Volume of the Sound.
	 * @author Felix Eckert
	 * */
	public void changeVolume(float volume) {
		FloatControl gain = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
		float db = (float) (Math.log(volume) / Math.log(10) * 20);
		gain.setValue(db);
	}
}
