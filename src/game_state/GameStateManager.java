package game_state;

import listener_control.SceneManager;
import listener_control.ShapeListenerManager;

public class GameStateManager {

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
	
	private static GameState currentState = GameState.start;
	
	public enum GameState {
		start, playing, paused, end, name, scores
	}
	
	private static boolean dead = false;
	private static boolean resumeOnFocusGain = false;
	
	/**
	 * Set to true if only one direction per frame
	 */
	private static boolean singleDirection = false;
	
	public static GameState getState() {
		return currentState;
	}
	
	public static void toPlayingBooleans() {
		dead = false;
		startGame = false;
		playing = true;
		currentState = GameState.playing;
		SceneManager.setScene("Playing");
		ShapeListenerManager.startThread();
	}

	public static void toEndGameBooleans() {
		highScores = false;
		endGame = true;
		currentState = GameState.end;
		SceneManager.setScene("End");
	}

	public static void toHighscoreBooleans() {
		nameEnter = false;
		highScores = true;
		currentState = GameState.scores;
		SceneManager.setScene("Scores");
	}
	
	public static void toNameEnter() {
		playing = false;
		paused = false;
		nameEnter = true;
		currentState = GameState.name;
		SceneManager.setScene("NameEnter");
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
		currentState = GameState.paused;
		SceneManager.setScene("Pause");

	}
	
	public static void resume() {
		paused = false;
		playing = true;
		currentState = GameState.playing;
		SceneManager.setScene("Playing");

	}
	
	public static void togglePlaying() {
		paused = !paused;
		playing = !playing;
		currentState = (playing) ? GameState.playing : GameState.paused;
		SceneManager.setScene((playing) ? "Playing" : "Pause");

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
	
	public static boolean ifPlaying() {
		return playing || paused;
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
	
	public static boolean isResumeOnFocus() {
		return resumeOnFocusGain;
	}
	
	public static void setResumeOnFocus(boolean resumeOnFocus) {
		resumeOnFocusGain = resumeOnFocus;
	}

	public static boolean isSingleDirection() {
		return singleDirection;
	}
	
	public static void setSingleDirection(boolean single) {
		singleDirection = single;
	}
}
