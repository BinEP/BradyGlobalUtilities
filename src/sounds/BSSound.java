package sounds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import utility_classes.Windows;

public abstract class BSSound implements BSSoundInterface {

	private String fileName;
	private File soundFile;
	private InputStream soundInputStream;
	private AudioStream audioStream;
	
	public BSSound(String fileName) {
		setupSound(fileName);
	}

	@Override
	public void playSound() {
		AudioPlayer.player.start(audioStream);
	}

	@Override
	public void setupSound(String fileName) {
		try {
			this.fileName = fileName;
			this.soundFile = new File(Windows.getResourceFolder() + "/sound_files/" + fileName);
			this.soundInputStream = new FileInputStream(soundFile);
			this.audioStream = new AudioStream(soundInputStream);
		} catch (IOException e) {
			System.out.println("Could not locate sound file at ");
			System.out.println(Windows.getResourceFolder() + "/sound_files/" + fileName);
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}

}
