package gameState;

import events.ShapeListenerManager;

public class BooleanManager {

	/**
	 * When start screen is showing Paint checks this variable for whether or
	 * not draw the start screen
	 */
	private static boolean startGame = true;
	/**
	 * When game is being played Paint checks this variable for whether or not
	 * draw playing field
	 */
	private static boolean playing = false;
	/**
	 * When game is paused Paint checks this variable for whether or not draw
	 * the pause screen
	 */
	private static boolean paused = false;
	/**
	 * When end screen is showing Paint checks this variable for whether or not
	 * draw the game over screen
	 */
	private static boolean endGame = false;
	/**
	 * When entering name screen is showing Paint checks this variable for
	 * whether or not draw the enter name screen
	 */
	private static boolean nameEnter = false;
	/**
	 * When high scores are listed on screen Paint checks this variable for
	 * whether or not draw the high score screen
	 */
	private static boolean highScores = false;
	
	private static boolean dead = false;

	
	public BooleanManager() {
		
	}

	public static void toPlayingBooleans() {
		dead = false;
		startGame = false;
		playing = true;
		ShapeListenerManager.startThread();
	}

	public static void toEndGameBooleans() {
		highScores = false;
		endGame = true;
		
	}

	public static void toHighscoreBooleans() {
		nameEnter = false;
		highScores = true;
	}
	
	public static void toNameEnter() {
		playing = false;
		paused = false;
		nameEnter = true;
		ShapeListenerManager.stopThread();
	}

	public static void resetBooleans() {
		toPlayingBooleans();
		nameEnter = false;
		highScores = false;
		endGame = false;
	}
	
	public static void pause() {
		paused = true;
		playing = false;
	}
	
	public static void resume() {
		paused = false;
		playing = true;
	}
	
	public static void togglePlaying() {
		paused = !paused;
		playing = !playing;
	}

	public static boolean isStartGame() {
		return startGame;
	}

	public static boolean isPlaying() {
		return playing;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static boolean isEndGame() {
		return endGame;
	}

	public static boolean isNameEnter() {
		return nameEnter;
	}

	public static boolean isHighScores() {
		return highScores;
	}

	public static boolean isDead() {
		return dead;
	}
	
	public static void setDead(boolean d) {
		dead = d;
	}
	
	
}
