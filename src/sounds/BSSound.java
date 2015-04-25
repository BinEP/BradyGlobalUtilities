package sounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

public abstract class BSSound implements Runnable, BSSoundInterface {

	private final JButton playButton = new JButton("Play");
	private final JButton pauseButton = new JButton("Pause");
	private final JButton endButton = new JButton("End");

	private String fileName;
	private AudioInputStream audioStream;
	private Clip audioClip;
	
	public BSSound(String fileName) {
		setupSound(fileName);
		setupButtons();
		new Thread(this).start();
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
				e.printStackTrace();
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
