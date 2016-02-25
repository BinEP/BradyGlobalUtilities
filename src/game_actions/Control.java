package game_actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import listener_control.ListenerActivator;
import listener_control.ListenerManager;
import listener_control.ObjectListenerManager;
import listener_control.ShapeListenerManager;
import game_state.CustomDrawing;
import game_state.DirectionExecution;
import game_state.GameStateManager;
import game_state.GameTime;
import game_state.ListenerAutoAdd;
import game_state.SceneManager;
import shapes.BSString;
import shapes.interfaces.Updatable;
import sounds.BSSound;
import utility_classes.*;

/**
 * @author Brady Stoffel
 */
public class Control extends JPanel implements Screen {

	private static final long serialVersionUID = 6238122615986771090L;
	public static CustomFont customFont;
	private static final List<Updatable> updatables = new ArrayList<Updatable>();
//	private static final Map<BSShape, GameState> shapesToDraw = new LinkedHashMap<BSShape, GameState>();

	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	
	public boolean fullscreen = false;
	public int score;
	public static BSString scoreShape;
	
	public int upKey = KeyEvent.VK_UP;
	public int downKey = KeyEvent.VK_DOWN;
	public int leftKey = KeyEvent.VK_LEFT;
	public int rightKey = KeyEvent.VK_RIGHT;
	
	public enum Direction {
		up, down, left, right, none;
	}
		
	public boolean showMouseCoords = false;
	public int width = Windows.getWidth();
	public int height = Windows.getHeight();

	/** Outside box of Windows */
	public Rectangle outerbox = new Rectangle(0, 0, width - 1, height);

	/**
	 * keyMap - modify this to change key locations Gets modified when on the
	 * start screen and keys are pressed Assigned in order of when pressed then
	 * the key are mapped when the game starts
	 */
	public int[] keyMap = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
			KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };

	public int keyIndex = 0;
	
	public int movementVar = 10;
	public int deltaX = movementVar;
	public int deltaY = 0;
	public int playerX;
	public int playerY;
	
	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean leftPressed = false;
	public boolean rightPressed = false;

	public String pName = "";
	public Character letter;

	public Timer timer;
	public int origSpeed = 60;
	public double speed = origSpeed;
	/** If you want to game to speed up as the score gets higher */
	public boolean speedUp = false;
	
	private final GameTime actionTimer = new GameTime();
	private final ListenerActivator listenerActivator = new ListenerActivator(this);
	private final DirectionExecution directionExecution = new DirectionExecution(this);
	private final CustomDrawing customDrawing = new CustomDrawing(this);
	private final GameStateManager GAME_STATE_MANAGER = new GameStateManager(); 
	public final SceneManager sceneManager = new SceneManager();
	
	private BSSound backgroundMusic;
	
	public Control() {
		FileDependencies.checkFolder(Windows.getResourceFolder());
		setBackground(Color.BLACK);
		setFocusable(true);

		addListeners();
		
		customFont = new CustomFont(Windows.getFONT_NAME(), Font.BOLD, 18);
		scoreShape = new BSString(String.valueOf(score), Control.customFont.getFont(Windows.getEND_SCORE_SIZE()), Windows.getEND_SCORE_Y());
		setupScenes();

		timer = new Timer((int) (1000 / speed), listenerActivator);
		timer.start();
		
	}

	private void setupScenes() {
		
		customDrawing.setupDrawStart();
		customDrawing.setupDrawPlaying();
		customDrawing.setupDrawPause();
		sceneManager.addSceneCustom(new Scene("NameEnter"));
		sceneManager.addSceneCustom(new Scene("Scores"));
		customDrawing.setupDrawEnd();	
		SceneManager.setScene("Start");
	}

	private void addListeners() {
		ListenerManager.addActionListener(this);
		ListenerManager.addKeyListener(this);
		ListenerManager.addDirectionKeyListener(this);
		ListenerAutoAdd.addListeners((Control) this);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		timer.setDelay(1000 / speed);
	}
	
	public void setBackgroundMusic(String fileName) {
		backgroundMusic = new BSSound(fileName);
		backgroundMusic.setLoopContinuously();
		backgroundMusic.play();
	}

	/**
	 * Can be called to set the direction keys if they have been modified and
	 * sets the keyMap when the game starts
	 */
	public final void setKeys() {
		upKey = keyMap[0];
		rightKey = keyMap[1];
		downKey = keyMap[2];
		leftKey = keyMap[3];
	}

	/**
	 * This paintComponent checks which state the game is in using the
	 * BooleanManager.isStartGame(), BooleanManager.endGame(), etc. to know what
	 * to paint. Attempts to call methods in the UserGame class, which override
	 * methods in this class so that is the user has not defined a custom
	 * method, a default one is drawn
	 */
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		scale(g2);
		g.setColor(Color.WHITE);
		draw(g2);
		drawShapes(g2);

		if (GameStateManager.isStartGame()) {
//			drawStart(g2);

		} else if (GameStateManager.isPlaying() || GameStateManager.isPaused()) {

			drawPlaying(g2);

			showMouseCoords(g2);
			if (GameStateManager.isPaused()) {
//				drawPaused(g2);
			}
		} else if (GameStateManager.isEndGame()) {
//			drawEnd(g2);

		} else if (GameStateManager.isNameEnter()) {
			ScoreInfo.enterName(g2, getScore(), getPlayerName());

		} else if (GameStateManager.isHighScores()) {
			ScoreInfo.drawScores(g2);
		}
	}

	public void showMouseCoords(Graphics g) {
		if (showMouseCoords) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			Point screen = this.getLocationOnScreen();

			g.drawString("" + (mouse.x - screen.x) + "  "
					+ (mouse.y - screen.y), 20, 80);
		}
	}

	public void scale(Graphics2D g) {
		g.scale((double) getWidth() / (double) Windows.getWidth(),
				(double) (getHeight()) / (double) Windows.getHeight());
	}

	public void draw(Graphics2D g) {}
//
//	/** Draws the start screen. gets game name from Windows class */
//	public void drawStart(Graphics2D g) {}
//
	/** Draws the screen when BooleanManager.isPlaying() */
	public void drawPlaying(Graphics2D g) {
//		customDrawing.drawPlaying(g);
	}
//
//	/** Draws the word "Paused" in the middle of the screen */
//	public void drawPaused(Graphics2D g) {
//		customDrawing.drawPaused(g);
//	}
//
//	/** Draws the end game screen */
//	public void drawEnd(Graphics2D g) {
//		customDrawing.drawEnd(g);
//	}

	public void drawBorder(Graphics2D g) {
		customDrawing.drawBorder(g, Color.WHITE, 15);
	}

	public void drawBorder(Graphics2D g, Color c) {
		customDrawing.drawBorder(g, c, 15);
	}

	public void drawBorder(Graphics2D g, int width) {
		customDrawing.drawBorder(g, Color.WHITE, width);
	}

	public void drawBorder(Graphics2D g, Color c, int width) {
		customDrawing.drawBorder(g, c, width);
	}
	
	public void drawShapes(Graphics2D g) {
		SceneManager.getCurrentScene().drawShapes(g);
	}

	public void setup() {}

	public void reset() {}
	
	public void moves() {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {

		if (GameStateManager.isStartGame() && e.getKeyCode() != KeyEvent.VK_ENTER) {
			mapKeys(e);

		} else if (GameStateManager.isSingleDirection()
				&& (directionExecution.getDirection(e.getKeyCode()) != Direction.none)) {
			directionExecution.addDirection(directionExecution.getDirection(e.getKeyCode()));

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& !(GameStateManager.isPaused() || GameStateManager.isPlaying())) {

			if (GameStateManager.isStartGame()) {
				setKeys();
				actionTimer.resetTime();
				GameStateManager.toPlayingBooleans();

			} else if (GameStateManager.isEndGame()) {
				speed = origSpeed;
				reset();
				actionTimer.resetTime();
				GameStateManager.resetBooleans();
				pName = "";
				speed = 10;
				score = 0;

			} else if (GameStateManager.isNameEnter()) {
				GameStateManager.toHighscoreBooleans();
				ScoreInfo.setScores(getScore(), pName);
			} else if (GameStateManager.isHighScores()) {
				GameStateManager.toEndGameBooleans();
			} else {
				GameStateManager.toPlayingBooleans();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE
				&& (GameStateManager.isPlaying() || GameStateManager.isPaused())) {
			actionTimer.pauseTime();
			repaint();

		} else if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_STANDARD
				&& GameStateManager.isNameEnter()) {

			if (pName.length() < 10)
				addLetterToName(e);
		}
	}

	private void mapKeys(KeyEvent e) {
		keyMap[keyIndex] = e.getKeyCode();
		keyIndex++;
		if (keyIndex > 3)
			keyIndex = 0;
	}
	
	public void resetDirectionPressed() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
	}

	public void addLetterToName(KeyEvent e) {
		letter = Character.toUpperCase(e.getKeyChar());
		pName = pName.concat(letter.toString());
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * Gets called when timer activates an action, and the timer fires very
	 * quickly. Calls the moves method in the UserGame class if the
	 * BooleanManager.isPlaying() variable is true
	 */
	@Override
	public final void actionPerformed(ActionEvent e) {

		executeEveryTick();
		if (GameStateManager.isPlaying()) {
			if (directionExecution.getNextDirection().size() > 0
					&& GameStateManager.isSingleDirection())
				directionExecution.executeDirection();
			synchronized(updatables) {
				for (Updatable u : updatables) 
					u.update();
			}
			moves();

			if (speedUp)
				timer.setDelay(1000 / (int) (speed + getScore() / 2));

			if (checkIfDeadSuper()) {
				GameStateManager.toNameEnter();
				ObjectListenerManager.endSounds();
				ShapeListenerManager.resetAllStates();
				actionTimer.stopTime();
				scoreShape.text = String.valueOf(score);
			}
		}
		repaint();
	}

	public static final void addUpdatable(Updatable u) {
		synchronized(updatables) {
			updatables.add(u);
		}
	}
	
	public void executeEveryTick() {}
	
	@Override
	public void up() 			{upPressed = true;}

	@Override
	public void down() 			{downPressed = true;}

	@Override
	public void left() 			{leftPressed = true;}

	@Override
	public void right()			{rightPressed = true;}

	@Override
	public void upReleased() 	{upPressed = false;}

	@Override
	public void downReleased() 	{downPressed = false;}

	@Override
	public void leftReleased() 	{leftPressed = false;}

	@Override
	public void rightReleased() {rightPressed = false;}
	
	/** Sets a custom size of the Window and scaling behavior. Default 800x480 */
	public void setWindowSize(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
	}
	
	public ListenerActivator getListenerActivator() {return listenerActivator;}
	
	private final boolean checkIfDeadSuper() {return checkifDead() || GameStateManager.isDead();}
	
	public int getTime() 				{return actionTimer.getTime();}

	public boolean checkifDead() 		{return false;}
	
	/** Sets the graphics font at the given size */
	public Font getFont(int size) 		{return customFont.getFont(size);}

	public void setNewFont(String name) 	{customFont = new CustomFont(name, Font.BOLD, 18);}

	public String getGameName() 			{return "Game";}

	public int getScore() 					{return score;}

	public GameTime getGameTime() 			{return actionTimer;}

	public int getPlayerX() 				{return playerX;}

	public int getPlayerY() 				{return playerY;}
	
	public Rectangle getOuterbox() 			{return outerbox;}
	
	public String getPlayerName() 			{return pName;}
	
	public GameStateManager getGameStateManager() {return GAME_STATE_MANAGER;}
	
	public BSSound getBackgroundMusic() {return backgroundMusic;}	
}