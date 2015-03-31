package gameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTime implements ActionListener {

	private static int timeSeconds = 0;

	private static Timer actionTimer;

	public GameTime() {
		if (actionTimer == null) actionTimer = new Timer((int) (1000), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timeSeconds++;
	}

	/**
	 * Gets the number of seconds that have passed since the timer was started
	 * 
	 * @return int
	 */
	public int getTime() {
		return timeSeconds;
	}

	/**
	 * Starts the timer that can be displayed on screen. Use getTime() to get
	 * the number seconds that have passed
	 */
	public static final void startTime() {
		actionTimer.start();
	}

	/**
	 * Pauses the timer
	 */
	public static final void stopTime() {
		actionTimer.stop();
	}

	/**
	 * resets the time passed and sets the start time to the current time
	 */
	public void resetTime() {
		timeSeconds = 0;
		actionTimer.restart();
	}

	public void pauseTime() {
		if (BooleanManager.isPlaying()) {
			stopTime();
		} else {
			startTime();
		}

		BooleanManager.togglePlaying();
	}

	public void pauseTime(boolean ifOnlyPlaying) {
		if (BooleanManager.isPlaying() || BooleanManager.isPaused())
			pauseTime();
	}

}