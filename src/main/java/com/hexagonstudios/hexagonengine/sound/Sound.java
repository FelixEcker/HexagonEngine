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
	
	public Sound(URL url) {
		this.url = url;
		
		try {
			this.stream = AudioSystem.getAudioInputStream(url);
			this.clip = AudioSystem.getClip();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playSound() {
		try {
			clip.open(stream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		clip.start();
	}
	
	public void changeVolume(float volume) {
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float db = (float) (Math.log(volume) / Math.log(10) * 20);
		gain.setValue(db);
	}
}
