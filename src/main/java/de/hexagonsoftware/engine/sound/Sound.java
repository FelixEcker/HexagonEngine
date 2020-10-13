package de.hexagonsoftware.engine.sound;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Hexagon Engine Sound Object
 * 
 * This class creates a sound clip from the given input URL,
 * this clip can then be played back, stopped and get its volume changed
 * (even whilst playing).
 * It also contains some function to get some info about the current state of
 * the clip.
 * 
 * This class is part of the Hexagon Engine!
 * 
 * @author Felix Eckert
 * */
public class Sound {
    private AudioInputStream stream;
    private Clip clip;

    /**
     * @param URL path to the sound file that the Sound-Object instance should correspond to.
     * @author Felix Eckert
     * */
    public Sound(URL url) {
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
    
	public void stopSound() {
		this.clip.stop();
		this.clip.close();
	}

	// The double it returns is kinda strange...
	public double getTimeInSeconds() {
		return TimeUnit.MICROSECONDS.convert(clip.getMicrosecondPosition(), TimeUnit.SECONDS);
	}

	public boolean isPlaying() {
		return clip.isActive();
	}
	
    /**
     * Can be used to set the Volume of the Sound. Doesnt work whilst sound is playing.
     * @param volume Volume of the Sound.
     * @author Felix Eckert
     * */
    public void changeVolume(float volume) {
        FloatControl gain = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        float db = (float) (Math.log(volume) / Math.log(10) * 20);
        if (db > 6.0206f)
            return;
        gain.setValue(db);
    }
}
