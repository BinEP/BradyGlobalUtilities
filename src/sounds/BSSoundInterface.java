package sounds;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface BSSoundInterface {
	public void play();
	public void pause();
	public void end();
	public void soundDone();
	public void setupSound(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException;
}
