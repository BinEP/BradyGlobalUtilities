package sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BSTestSound {

	public BSTestSound() {
	}
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		 File soundFile = new File("/Users/88713791/git/BradyGlobalUtilities/InfoFiles/sound_files/chu.wav");
//		 File soundFile = new File("/Users/88713791/git/BradyGlobalUtilities/InfoFiles/sound_files/chu.m4a");

		 AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

		    // load the sound into memory (a Clip)
		    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		    Clip clip = (Clip) AudioSystem.getLine(info);
		    clip.open(sound);

		    // due to bug in Java Sound, explicitly exit the VM when
		    // the sound has stopped.
		    clip.addLineListener(new LineListener() {
		      public void update(LineEvent event) {
		        if (event.getType() == LineEvent.Type.STOP) {
		          event.getLine().close();
		          System.exit(0);
		        }
		      }
		    });

		    // play the sound clip
		    clip.start();
		    while (clip.isOpen()) {
		    	Thread.sleep(100);
		    }
		    clip.close();
	}
}
