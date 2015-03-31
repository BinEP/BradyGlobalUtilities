package gameActions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import listenerControl.ListenerActivator;
import listenerControl.ListenerIndex;
import gameState.BooleanManager;
import gameState.DirectionExecution;
import gameState.GameTime;
import utilityClasses.*;

/**
 * @author Brady Stoffel
 */
public class Control extends JPanel implements Screen {

	private static final long serialVersionUID = 6238122615986771090L;
	protected boolean showMouseCoords = false;
	public boolean fullscreen = false;

	/** The value for the upKey This can be changed to suit the user of player */
	public int upKey = KeyEvent.VK_UP;
	/** The value for the downKey This can be changed to suit the user of player */
	public int downKey = KeyEvent.VK_DOWN;
	/** The value for the leftKey This can be changed to suit the user of player */
	public int leftKey = KeyEvent.VK_LEFT;
	/**
	 * The value for the rightKey This can be changed to suit the user of player
	 */
	public int rightKey = KeyEvent.VK_RIGHT;

	protected int width = Windows.getWidth();
	protected int height = Windows.getHeight();

	protected static int WIDTH = 800;
	protected static int HEIGHT = 480;

	/** Outside box of Windows */
	protected Rectangle outerbox = new Rectangle(0, 0, width - 1, height);

	protected static CustomFont customFont;

	public enum Direction {
		up, down, left, right, none;
	}

	/**
	 * The positions that scores could be on screen to make it easier to
	 * position the score
	 * 
	 * @author Brady Stoffel
	 */
	protected enum ScoreCoords {

		top_left(10, 10), top_middle(Windows.getWidth() / 2, 10), top_right(
				Windows.getWidth() - 10, 10), middle_left(10, Windows
				.getHeight() / 2), middle_middle(Windows.getWidth() / 2,
				Windows.getHeight() / 2), middle_right(Windows.getWidth() - 10,
				Windows.getHeight() / 2), bottom_left(10,
				Windows.getHeight() - 15), bottom_middle(
				Windows.getWidth() / 2, Windows.getHeight()
						- Windows.getTopBuffer()), bottom_right(Windows
				.getWidth() - 10, Windows.getHeight() - Windows.getTopBuffer());

		protected int x;
		protected int y;

		private ScoreCoords(int x, int y) {
			this.x = x;
			this.y = y;
		}

		protected Point getCoords() {
			return new Point(this.x, this.y);
		}

		/**
		 * Draws text at preset enum position using current font
		 * 
		 * @param text
		 * @param g
		 */
		protected void draw(String text, Graphics2D g) {

			g.setFont(CustomFont.makeCustomFont(getFONT_FILE(),
					Windows.getSCORE_SIZE()));

			FontMetrics fontInfo = g.getFontMetrics();
			int textWidth = fontInfo.stringWidth(text);
			int textHeight = fontInfo.getHeight();

			if (x == Windows.getWidth() / 2) {
				CenteredText.draw(text, y, g);

			} else if (x == 10) {
				g.drawString(text, x, y + textHeight / 2);

			} else if (x == Windows.getWidth() - 10) {
				g.drawString(text, x - textWidth, y + textHeight / 2);
			}
		}
	}

	/**
	 * keyMap - modify this to change key locations Gets modified when on the
	 * start screen and keys are pressed Assigned in order of when pressed then
	 * the key are mapped when the game starts
	 */
	protected int[] keyMap = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
			KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };

	protected int keyIndex = 0;

	/** The value that deltaX and deltaY will change player position by */
	protected int movementVar = 10;
	/** How much a player moves in the x direction */
	protected int deltaX = movementVar;
	/** How much a player moves in the y direction */
	protected int deltaY = 0;

	protected String pName = "";

	/** player x position */
	protected int playerX;
	/** player y position */
	protected int playerY;

	protected Timer timer;
	protected int origSpeed = 60;
	protected double speed = origSpeed;
	/** If you want to game to speed up as the score gets higher */
	protected boolean speedUp = false;

	public int score;
	protected Character letter;

	private ListenerActivator listenerActivator;
	private DirectionExecution directionExecution;

	protected Control() {

		FileDependencies.checkFolder("InfoFiles");
		setBackground(Color.BLACK);
		setFocusable(true);

		listenerActivator = new ListenerActivator(this);
		directionExecution = new DirectionExecution(this);
		addListeners();

		setup();
		customFont = new CustomFont(getFONT_FILE(), Font.BOLD, 18);

		timer = new Timer((int) (1000 / speed), listenerActivator);
		timer.start();
	}

	private void addListeners() {
		ListenerIndex.addActionListener(this);
		ListenerIndex.addKeyListener(this);
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
		timer.setDelay(1000 / speed);
	}

	protected void setBackgroundColor(Color c) {
		this.setBackground(c);
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

		scale(g2);
		g.setColor(Color.WHITE);
		draw(g2);

		if (BooleanManager.isStartGame()) {
			drawStart(g2);

		} else if (BooleanManager.isPlaying() || BooleanManager.isPaused()) {

			drawPlaying(g2);

			showMouseCoords(g);
			if (BooleanManager.isPaused()) {
				drawPaused(g2);
			}
		} else if (BooleanManager.isEndGame()) {
			drawEnd(g2);

		} else if (BooleanManager.isNameEnter()) {
			ScoreInfo.enterName(g2, getScore(), pName);

		} else if (BooleanManager.isHighScores()) {
			ScoreInfo.drawScores(g2, getTXT_FILE(), getFOLDER_PATH());
		}
	}

	protected void showMouseCoords(Graphics g) {
		if (showMouseCoords) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			Point screen = this.getLocationOnScreen();

			g.drawString("" + (mouse.x - screen.x) + "  "
					+ (mouse.y - screen.y), 20, 80);
		}
	}

	protected void scale(Graphics2D g) {
		g.scale((double) getWidth() / (double) Windows.getWidth(),
				(double) (getHeight()) / (double) Windows.getHeight());
	}

	protected void draw(Graphics2D g) {
	}

	/** Draws the start screen. gets game name from Windows class */
	protected void drawStart(Graphics2D g) {

		g.setFont(customFont.getFont(Windows.getTITLE_SIZE()));
		CenteredText.draw(getGameName(), Windows.getTITLE_Y(), g);

		g.setFont(customFont.getFont(Windows.getENTER_TO_START_SIZE()));
		CenteredText.draw("Press Enter to", Windows.getENTER_Y(), g);
		CenteredText.draw("Start", Windows.getSTART_Y(), g);

		g.setFont(customFont.getFont(12));
		CenteredText.draw("Press keys Up, Right, Down, Left to map new keys",
				30, g);
	}

	/** Draws the screen when BooleanManager.isPlaying() */
	protected void drawPlaying(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(20, 30, playerX, playerY);
	}

	/** Draws the word "Paused" in the middle of the screen */
	protected void drawPaused(Graphics2D g) {
		g.setFont(customFont.getFont(Windows.getPAUSE_SIZE()));
		g.setColor(Color.WHITE);
		CenteredText.draw("Paused", Windows.getPAUSE_Y(), g);
	}

	/** Draws the end game screen */
	protected void drawEnd(Graphics2D g) {

		g.setColor(Color.WHITE);

		g.setFont(customFont.getFont(Windows.getEND_SCORE_SIZE()));
		CenteredText.draw(String.valueOf(getScore()), Windows.getEND_SCORE_Y(),
				g);

		g.setFont(customFont.getFont(Windows.getYOU_LOSE_SIZE()));
		CenteredText.draw("You Lose!", Windows.getYOU_LOSE_Y(), g);

		g.setFont(customFont.getFont(Windows.getRESTART_SIZE()));
		CenteredText.draw("Enter to Restart", Windows.getRESTART_Y(), g);
	}

	protected void drawBorder(Graphics2D g) {
		drawBorder(g, Color.WHITE, 15);
	}

	protected void drawBorder(Graphics2D g, Color c) {
		drawBorder(g, c, 15);
	}

	protected void drawBorder(Graphics2D g, int width) {
		drawBorder(g, Color.WHITE, width);
	}

	protected void drawBorder(Graphics2D g, Color c, int width) {
		g.setColor(c);
		g.setStroke(new BasicStroke(width));
		g.drawRect(outerbox.x, outerbox.y, outerbox.width, outerbox.height);
		g.setStroke(new BasicStroke(2));
	}

	protected void setup() {
	}

	protected void reset() {
	}

	protected int getTime() {
		return GameTime.getTime();
	}

	public ListenerActivator getListenerActivator() {
		return listenerActivator;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (BooleanManager.isStartGame() && e.getKeyCode() != KeyEvent.VK_ENTER) {

			keyMap[keyIndex] = e.getKeyCode();
			keyIndex++;
			if (keyIndex > 3)
				keyIndex = 0;

		} else if (BooleanManager.isSingleDirection()
				&& (directionExecution.getDirection(e.getKeyCode()) != Direction.none)) {
			directionExecution.addDirection(directionExecution.getDirection(e.getKeyCode()));

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& !(BooleanManager.isPaused() || BooleanManager.isPlaying())) {

			if (BooleanManager.isStartGame()) {
				setKeys();
				GameTime.resetTime();
				setup();
				BooleanManager.toPlayingBooleans();

			} else if (BooleanManager.isEndGame()) {

				speed = origSpeed;
				reset();
				GameTime.resetTime();
				BooleanManager.resetBooleans();
				pName = "";
				speed = 10;
				score = 0;

			} else if (BooleanManager.isNameEnter()) {
				BooleanManager.toHighscoreBooleans();
				ScoreInfo.setScores(getScore(), pName, getTXT_FILE(),
						getFOLDER_PATH());
			} else if (BooleanManager.isHighScores()) {
				BooleanManager.toEndGameBooleans();
			} else {
				BooleanManager.toPlayingBooleans();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE
				&& (BooleanManager.isPlaying() || BooleanManager.isPaused())) {
			GameTime.pauseTime();
			repaint();

		} else if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_STANDARD
				&& BooleanManager.isNameEnter()) {

			if (pName.length() < 10)
				addLetterToName(e);
		}
	}

	protected void addLetterToName(KeyEvent e) {
		letter = Character.toUpperCase(e.getKeyChar());
		pName = pName.concat(letter.toString());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Gets called when timer activates an action, and the timer fires very
	 * quickly. Calls the moves method in the UserGame class if the
	 * BooleanManager.isPlaying() variable is true
	 */
	@Override
	public final void actionPerformed(ActionEvent e) {

		alwaysExecute();
		if (BooleanManager.isPlaying()) {

			if (directionExecution.getNextDirection().size() > 0
					&& BooleanManager.isSingleDirection())
				directionExecution.executeDirection();

			moves();

			if (speedUp)
				timer.setDelay(1000 / (int) (speed + getScore() / 2));

			if (checkIfDeadSuper()) {
				BooleanManager.toNameEnter();
				GameTime.stopTime();
			}
		}
		repaint();
	}

	private final boolean checkIfDeadSuper() {
		return checkifDead() || BooleanManager.isDead();
	}

	protected boolean checkifDead() {
		return false;
	}

	protected void moves() {
	}

	/** Sets the graphics font at the given size */
	protected Font getFont(int size) {
		return customFont.getFont(size);
	}

	protected void setNewFont(String name) {
		customFont = new CustomFont(name, Font.BOLD, 18);
	}

	protected void alwaysExecute() {
	}

	protected String getGameName() {
		return null;
	}

	protected String getFolderPath() {
		return "InfoFiles/";
	}

	protected int getScore() {
		return score;
	}

	/** Sets a custom size of the Window and scaling behavior. Default 800x480 */
	protected void setWindowSize(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
	}

	public String getTXT_FILE() {
		return (getGameName() != null) ? getGameName().toLowerCase()
				.replaceAll("\\s", "") : "";
	}

	public static String getFOLDER_PATH() {
		return "InfoFiles/";
	}

	public static String getFONT_FILE() {
		return Windows.getFONT_NAME();
	}
}