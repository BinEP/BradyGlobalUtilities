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

import javax.swing.JPanel;
import javax.swing.Timer;

import listener_control.ListenerActivator;
import listener_control.ListenerManager;
import game_state.CustomDrawing;
import game_state.DirectionExecution;
import game_state.GameStateManager;
import game_state.GameTime;
import utility_classes.*;

/**
 * @author Brady Stoffel
 */
public class Control extends JPanel implements Screen {

	private static final long serialVersionUID = 6238122615986771090L;
	public static CustomFont customFont;
	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	
	public boolean fullscreen = false;
	public int score;
	
	public int upKey = KeyEvent.VK_UP;
	public int downKey = KeyEvent.VK_DOWN;
	public int leftKey = KeyEvent.VK_LEFT;
	public int rightKey = KeyEvent.VK_RIGHT;
	
	public enum Direction {
		up, down, left, right, none;
	}
		
	protected boolean showMouseCoords = false;
	protected int width = Windows.getWidth();
	protected int height = Windows.getHeight();

	/** Outside box of Windows */
	protected Rectangle outerbox = new Rectangle(0, 0, width - 1, height);

	/**
	 * keyMap - modify this to change key locations Gets modified when on the
	 * start screen and keys are pressed Assigned in order of when pressed then
	 * the key are mapped when the game starts
	 */
	protected int[] keyMap = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
			KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };

	protected int keyIndex = 0;
	
	protected int movementVar = 10;
	protected int deltaX = movementVar;
	protected int deltaY = 0;
	protected int playerX;
	protected int playerY;
	
	protected boolean upPressed = false;
	protected boolean downPressed = false;
	protected boolean leftPressed = false;
	protected boolean rightPressed = false;

	protected String pName = "";
	protected Character letter;

	protected Timer timer;
	protected int origSpeed = 60;
	protected double speed = origSpeed;
	/** If you want to game to speed up as the score gets higher */
	protected boolean speedUp = false;

	private GameTime actionTimer;
	private ListenerActivator listenerActivator;
	private DirectionExecution directionExecution;
	private CustomDrawing customDrawing;
	
	protected Control() {

		FileDependencies.checkFolder("InfoFiles");
		setBackground(Color.BLACK);
		setFocusable(true);

		listenerActivator = new ListenerActivator(this);
		directionExecution = new DirectionExecution(this);
		customDrawing = new CustomDrawing(this);
		addListeners();

		setup();
		customFont = new CustomFont(Windows.getFONT_NAME(), Font.BOLD, 18);
		actionTimer = new GameTime();
		timer = new Timer((int) (1000 / speed), listenerActivator);
		timer.start();
	}

	private void addListeners() {
		ListenerManager.addActionListener(this);
		ListenerManager.addKeyListener(this);
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
		timer.setDelay(1000 / speed);
	}

	/**
	 * Can be called to set the direction keys if they have been modified and
	 * sets the keyMap when the game starts
	 */
	protected final void setKeys() {
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
	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		customDrawing.paintScreen(g2);
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

	protected void draw(Graphics2D g) {
		customDrawing.draw(g);
	}

	/** Draws the start screen. gets game name from Windows class */
	protected void drawStart(Graphics2D g) {
		customDrawing.drawStart(g);
	}

	/** Draws the screen when BooleanManager.isPlaying() */
	protected void drawPlaying(Graphics2D g) {
		customDrawing.drawPlaying(g);
	}

	/** Draws the word "Paused" in the middle of the screen */
	protected void drawPaused(Graphics2D g) {
		customDrawing.drawPaused(g);
	}

	/** Draws the end game screen */
	protected void drawEnd(Graphics2D g) {
		customDrawing.drawEnd(g);
	}

	protected void drawBorder(Graphics2D g) {
		customDrawing.drawBorder(g, Color.WHITE, 15);
	}

	protected void drawBorder(Graphics2D g, Color c) {
		customDrawing.drawBorder(g, c, 15);
	}

	protected void drawBorder(Graphics2D g, int width) {
		customDrawing.drawBorder(g, Color.WHITE, width);
	}

	protected void drawBorder(Graphics2D g, Color c, int width) {
		customDrawing.drawBorder(g, c, width);
	}

	protected void setup() {}

	protected void reset() {}

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
				setup();
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

	protected void addLetterToName(KeyEvent e) {
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

			moves();

			if (speedUp)
				timer.setDelay(1000 / (int) (speed + getScore() / 2));

			if (checkIfDeadSuper()) {
				GameStateManager.toNameEnter();
				actionTimer.stopTime();
			}
		}
		repaint();
	}

	protected void executeEveryTick() {}
	
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
	protected void setWindowSize(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
	}

	protected void moves() {}
	
	public ListenerActivator getListenerActivator() {return listenerActivator;}
	
	private final boolean checkIfDeadSuper() {return checkifDead() || GameStateManager.isDead();}
	
	protected int getTime() 				{return actionTimer.getTime();}

	protected boolean checkifDead() 		{return false;}
	
	/** Sets the graphics font at the given size */
	protected Font getFont(int size) 		{return customFont.getFont(size);}

	protected void setNewFont(String name) 	{customFont = new CustomFont(name, Font.BOLD, 18);}

	public String getGameName() 			{return "Game";}

	public int getScore() 					{return score;}

	public GameTime getGameTime() 			{return actionTimer;}

	public int getPlayerX() 				{return playerX;}

	public int getPlayerY() 				{return playerY;}
	
	public Rectangle getOuterbox() 			{return outerbox;}
	
	public String getPlayerName() 			{return pName;}
}