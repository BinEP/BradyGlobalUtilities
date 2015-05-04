package sounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

public class BSSound implements Runnable, BSSoundInterface {

	private final JButton playButton = new JButton("Play");
	private final JButton pauseButton = new JButton("Pause");
	private final JButton endButton = new JButton("End");

	private String fileName;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private Thread audioThread;
	private FloatControl volume;

	
	public BSSound(String fileName) {
		setupSound(fileName);
		setupButtons();
		audioThread = new Thread(this);
		audioThread.start();
//		printSoundInfo();
	}
	
	@Override
	public void setupSound(String fileName) {
		try {
			fileName = "/sound_files/" + fileName;
			InputStream soundFile2 = BSSound.class.getResourceAsStream(fileName);
			audioStream = AudioSystem.getAudioInputStream(soundFile2);

			DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			volume = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);

		} catch (LineUnavailableException | IOException
				| UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	private void setupButtons() {
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				end();
			}
		});
	}

	@Override
	public void run() {
		while (audioClip.isOpen()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void play() {
		audioClip.start();
	}

	@Override
	public void pause() {
		audioClip.stop();
	}

	@Override
	public void end() {
		audioClip.close();
		try {
			audioStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		audioThread.interrupt();
	}

	public boolean isPlaying() {
		return audioClip.isOpen();
	}

	public String getFileName() {
		return fileName;
	}

	public void resetAudio() {
		audioClip.stop();
		audioClip.flush();
	}
	
	public void setStartPlace(int position) {
		audioClip.setFramePosition(position);
	}
	
	public void setStartPlace(double position) {
		audioClip.setMicrosecondPosition((long) (position * 1000000));
	}
	
	public void setLoopSegment(int start, int end) {
		audioClip.setLoopPoints(start, end);
	}
	
	public void setLoopSetting(int count) {
		audioClip.loop(count);
	}
	
	public void setLoopContinuously() {
		setLoopSetting(Clip.LOOP_CONTINUOUSLY);
	}
	
	public Clip getAudioClip() {
		return audioClip;
	}
	
	public AudioInputStream getStream() {
		return audioStream;
	}
	
	public void changeSoundLevel(float adjust) {
		volume.setValue(volume.getValue() - 10);
		printSoundInfo();
	}

	private void printSoundInfo() {
		System.out.println("Maximum: " + volume.getMaximum());
		System.out.println("Minimum: " + volume.getMinimum());
		System.out.println("Current Value: " + volume.getValue());
	}
	
	public void changeLevelOverTime(float time, int soundAdjust) {
		volume.shift(volume.getValue(), volume.getValue() + soundAdjust, soundAdjust * 1000000);
	}
	
	@Override
 	public void soundDone() {}
	
	public JButton getPlayButton() {
		return playButton;
	}
	
	public JButton getPauseButton() {
		return pauseButton;
	}
	
	public JButton getEndButton() {
		return endButton;
	}
}
