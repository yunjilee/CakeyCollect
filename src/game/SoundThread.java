package game;
import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class SoundThread extends Thread implements Serializable{
	public static final long serialVersionUID =1; 
	
	File file;
	
	public SoundThread(File file) {
		this.file = file;
		start();
	}
	
	public void playSoundLoop(File file) {
	    new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
	      public void run() {
	        try {
	          Clip clip = AudioSystem.getClip();
	          AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
	          clip.open(inputStream);
	          clip.start(); 
	        } catch (Exception e) {
	          System.err.println(e.getMessage());
	        }
	      }
	    }).start();
	  }
	
	public void run() {
		try {
	          Clip clip = AudioSystem.getClip();
	          AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
	          clip.open(inputStream);
	          clip.start();
	        } catch (Exception e) {
	          System.err.println(e.getMessage());
	        }
	}
	
}