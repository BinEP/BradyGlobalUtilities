package sounds;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioStream;

public abstract class BSSound implements BSSoundInterface {

	private String fileName;
	private File soundFile;
	private AudioStream audioStream;
	
	public BSSound(String fileName) {
		this.fileName = fileName;
		this.soundFile = new File(fileName);
	}

	@Override
	public void playSound() {

	}

	@Override
	public void setupSound(String fileName) {

	}
	
	public String getFileName() {
		return fileName;
	}

}
