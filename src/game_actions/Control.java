package game_actions;

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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import utility_classes.*;

/**
 * @author Brady Stoffel
 */
public class Control extends JPanel implements Screen {

	protected static String NAME = "Game Name";
	protected static String TXT_FILE = NAME.toLowerCase().replaceAll("\\s", "");
	protected static String FOLDER_PATH = "InfoFiles/";
	protected static String FONT_FILE = Windows.getFONT_NAME();
	
	protected static boolean fullscreen = false;
	protected static boolean scaleRatio = false;
	
	/**
	 * When start screen is showing Paint checks this variable for whether or
	 * not draw the start screen
	 */
	protected boolean startGame = true;
	/**
	 * When game is being played Paint checks this variable for whether or not
	 * draw playing field
	 */
	protected boolean playing = false;
	/**
	 * When game is paused Paint checks this variable for whether or not draw
	 * the pause screen
	 */
	protected boolean paused = false;
	/**
	 * When end screen is showing Paint checks this variable for whether or not
	 * draw the game over screen
	 */
	protected boolean endGame = false;
	/**
	 * When entering name screen is showing Paint checks this variable for
	 * whether or not draw the enter name screen
	 */
	protected boolean nameEnter = false;
	/**
	 * When high scores are listed on screen Paint checks this variable for
	 * whether or not draw the high score screen
	 */
	protected boolean highScores = false;

	protected boolean showMouseCoords = false;
	protected boolean resumeOnFocusGain = false;

	/**
	 * The value for the upKey This can be changed to suit the user of player
	 */
	protected int upKey = KeyEvent.VK_UP;
	/**
	 * The value for the downKey This can be changed to suit the user of player
	 */
	protected int downKey = KeyEvent.VK_DOWN;
	/**
	 * The value for the leftKey This can be changed to suit the user of player
	 */
	protected int leftKey = KeyEvent.VK_LEFT;
	/**
	 * The value for the rightKey This can be changed to suit the user of player
	 */
	protected int rightKey = KeyEvent.VK_RIGHT;

	protected boolean upPressed = false;
	protected boolean downPressed = false;
	protected boolean leftPressed = false;
	protected boolean rightPressed = false;

	protected int width = Windows.getWidth();
	protected int height = Windows.getHeight();
	
	protected static int WIDTH = 800;
	protected static int HEIGHT = 480;

	/**
	 * Outside box of Windows
	 */
	protected Rectangle outerbox = new Rectangle(0, 0, width - 1, height);
	
	protected static CustomFont customFont;
	/**
	 * Set to true if only one direction per frame
	 * 
	 * @author Brady Stoffel
	 */
	protected boolean singleDirection = false;

	protected enum Direction {
		up, down, left, right;
	}

	/**
	 * The positions that scores could be on screen to make it easier to
	 * position the score
	 * 
	 * @author Brady Stoffel
	 */
	protected enum ScoreCoords {

		top_left				(10, 10), 
		top_middle				(Windows.getWidth() / 2, 10), 
		top_right				(Windows.getWidth() - 10, 10), 
		middle_left				(10, Windows.getHeight() / 2), 
		middle_middle			(Windows.getWidth() / 2, Windows.getHeight() / 2), 
		middle_right			(Windows.getWidth() - 10, Windows.getHeight() / 2), 
		bottom_left				(10,Windows.getHeight() - 15), 
		bottom_middle			(Windows.getWidth() / 2, Windows.getHeight() - Windows.getTopBuffer()), 
		bottom_right			(Windows.getWidth() - 10, Windows.getHeight() - Windows.getTopBuffer());

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

			g.setFont(CustomFont.makeCustomFont(FONT_FILE, Windows.getSCORE_SIZE()));

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

	/**
	 * The value that deltaX and deltaY will change player position by
	 */
	protected int movementVar = 10;
	/**
	 * How much a player moves in the x direction
	 */
	protected int deltaX = movementVar;
	/**
	 * How much a player moves in the y direction
	 */
	protected int deltaY = 0;
	protected String pName = "";

	/**
	 * player x position
	 */
	protected int playerX;
	/**
	 * player y position
	 */
	protected int playerY;

	protected final Timer timer;
	protected final Timer actionTimer;
	protected final GameTime gameTimer = new GameTime();
	protected int origSpeed = 60;
	protected double speed = origSpeed;
	/**
	 * If you want to game to speed up as the score gets higher
	 */
	protected boolean speedUp = false;

	protected int score;
	protected Character letter;
	
	protected AffineTransform trans = new AffineTransform();
		
	protected double startTime;
	protected double totalTime = 0;

	protected ArrayList<Direction> nextDirection = new ArrayList<Direction>();

	protected Control() {

		FileDependencies.checkFolder("InfoFiles");
		setBackground(Color.BLACK);
		setFocusable(true);
		addListeners();
//		setup();
		
		NAME = getGameName();
		TXT_FILE = (NAME != null) ? NAME.toLowerCase().replaceAll("\\s", "") : "";
		FOLDER_PATH = getFolderPath();
		FONT_FILE = Windows.getFONT_NAME();
		
		customFont = new CustomFont(FONT_FILE, Font.BOLD, 18);

		timer = new Timer((int) (1000 / speed), this);
		actionTimer = new Timer((int) (1000 / speed), gameTimer);

		timer.start();
	}
	
	protected void addListeners() {
		addKeyListener(this);
		addMouseListener(this);
		addFocusListener(this);
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
		timer.setDelay(1000 / speed);
	}

	protected void setBackgroundColor(Color c) {
		this.setBackground(c);
	}

	/**
	 * can be called to set the direction keys if they have been modified and
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
	 * startGame, endGame, etc. to know what to paint. Attempts to call methods
	 * in the UserGame class, which override methods in this class so that is
	 * the user has not defined a custom method, a default one is drawn
	 */
	protected final void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		scale(g2);
		g.setColor(Color.WHITE);
		draw(g2);

		if (startGame) {
			drawStart(g2);

		} else if (playing || paused) {

			drawPlaying(g2);

			showMouseCoords(g);
			if (paused) {
				drawPaused(g2);
			}
		} else if (endGame) {
			drawEnd(g2);
			
		} else if (nameEnter) {
			ScoreInfo.enterName(g2, getScore(), pName);

		} else if (highScores) {
			ScoreInfo.drawScores(g2, TXT_FILE, FOLDER_PATH);
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

		if (!scaleRatio) {
			g.scale((double) getWidth() / (double) Windows.getWidth(),
					(double) (getHeight()) / (double) Windows.getHeight());
		} else {
			boolean widthLarger = Windows.getWidth() >= Windows.getHeight();
			double scale = (widthLarger) ? (double) getWidth() / (double) Windows.getWidth() : (double) getHeight() / (double) Windows.getHeight();

			g.scale(scale, scale);
		}
	}

	/**
	 * This draw method is called all the time, so it could be 
	 * used for drawing a permanent border around the outside
	 * @param g
	 */
	protected void draw(Graphics2D g) {}

	/**
	 * Draws the start screen. gets game name from Windows class
	 * 
	 * @param g
	 */
	protected void drawStart(Graphics2D g) {

		g.setFont(customFont.getFont(Windows.getTITLE_SIZE()));

		CenteredText.draw(NAME, Windows.getTITLE_Y(), g);
		g.setFont(customFont.getFont(Windows.getENTER_TO_START_SIZE()));

		CenteredText.draw("Press Enter to", Windows.getENTER_Y(), g);
		CenteredText.draw("Start", Windows.getSTART_Y(), g);

		g.setFont(customFont.getFont(12));

		CenteredText.draw("Press keys Up, Right, Down, Left to map new keys",
				30, g);
	}

	/**
	 * Draws the screen when playing
	 * @param g
	 */
	protected void drawPlaying(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(20, 30, playerX, playerY);
	}

	/**
	 * Draws the word "Paused" in the middle of the screen
	 * @param g
	 */
	protected void drawPaused(Graphics2D g) {
		g.setFont(customFont.getFont(Windows.getPAUSE_SIZE()));
		g.setColor(Color.WHITE);
		CenteredText.draw("Paused", Windows.getPAUSE_Y(), g);
	}

	/**
	 * Draws the end game screen
	 * @param g
	 */
	protected void drawEnd(Graphics2D g) {

		g.setColor(Color.WHITE);
		
		g.setFont(customFont.getFont(Windows.getEND_SCORE_SIZE()));
		CenteredText.draw(String.valueOf(getScore()), Windows.getEND_SCORE_Y(), g);

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
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(width));
		g2.drawRect(outerbox.x, outerbox.y, outerbox.width, outerbox.height);
		g2.setStroke(new BasicStroke(2));
	}

	/**
	 * Gets called at the very beginning of everything. Setup any variables here
	 */
	protected void setup() {}

	/**
	 * This is called after player dies and the game restarts. 
	 * So reset variables here. Might be similar to setup
	 */
	protected void reset() {}

	/**
	 * Starts the timer that can be displayed on screen. Use getTime() to get
	 * the number seconds that have passed
	 */
	protected final void startTime() {
		actionTimer.start();
	}

	/**
	 * Pauses the timer
	 */
	protected final void stopTime() {
		actionTimer.stop();
	}

	/**
	 * Gets the number of seconds that have passed since the timer was started
	 * @return int
	 */
	protected final int getTime() {
		return gameTimer.getTime();
	}

	/**
	 * resets the time passed and sets the start time to the current time
	 */
	protected final void resetTime() {
		gameTimer.resetTime();
		actionTimer.restart();
	}
	
	protected void pauseTime() {
		if (playing) {
			stopTime();
		} else {
			startTime();
		}
		
		playing = !playing;
		paused = !paused;
	}
	
	protected void pauseTime(boolean ifOnlyPlaying) {
		if (playing || paused) pauseTime();
	}
	
	protected boolean ifPlaying() {
		return playing || paused;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (startGame && e.getKeyCode() != KeyEvent.VK_ENTER) {

			keyMap[keyIndex] = e.getKeyCode();
			keyIndex++;
			if (keyIndex > 3)
				keyIndex = 0;

		} else if (e.getKeyCode() == upKey) {

			if (singleDirection) {
				addDirection(Direction.up);
			} else {
				up();
			}

		} else if (e.getKeyCode() == downKey) {

			if (singleDirection) {
				addDirection(Direction.down);
			} else {
				down();
			}

		} else if (e.getKeyCode() == leftKey) {

			if (singleDirection) {
				addDirection(Direction.left);
			} else {
				left();
			}

		} else if (e.getKeyCode() == rightKey) {

			if (singleDirection) {
				addDirection(Direction.right);
			} else {
				right();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && !(paused || playing)) {

			if (startGame) {
				toPlayingBooleans();

				setKeys();
				resetTime();
				setup();

			} else if (endGame) {
				
				speed = origSpeed;
				reset();
				resetTime();
				resetBooleans();
				pName = "";
				speed = 10;
				score = 0;

			} else if (nameEnter) {
				toHighscoreBooleans();
				ScoreInfo.setScores(getScore(), pName, TXT_FILE, FOLDER_PATH);
			} else if (highScores) {
				toEndGameBooleans();
			} else {
				toPlayingBooleans();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && (playing || paused)) {
			pauseTime();
			
			repaint();

		} else if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_STANDARD

		&& nameEnter) {

			if (pName.length() < 10) addLetterToName(e);
			
		}
		customPressed(e);
	}

	protected void addLetterToName(KeyEvent e) {
		letter = e.getKeyChar();
		letter = Character.toUpperCase(letter);
		pName = pName.concat(letter.toString());
	}

	protected void toPlayingBooleans() {
		startGame = false;
		playing = true;
	}

	protected void toEndGameBooleans() {
		highScores = false;
		endGame = true;
	}

	protected void toHighscoreBooleans() {
		nameEnter = false;
		highScores = true;
	}
	
	protected void toNameEnter() {
		playing = false;
		paused = false;
		nameEnter = true;
	}

	protected void resetBooleans() {
		toPlayingBooleans();
		nameEnter = false;
		highScores = false;
		endGame = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == upKey) {
			upReleased();

		} else if (e.getKeyCode() == downKey) {
			downReleased();

		} else if (e.getKeyCode() == leftKey) {
			leftReleased();

		} else if (e.getKeyCode() == rightKey) {
			rightReleased();
		}

		customReleased(e);
	}

	/**
	 * Gets called when timer activates an action, and the timer fires very
	 * quickly. Calls the moves method in the UserGame class if the playing
	 * variable is true
	 */
	@Override
	public final void actionPerformed(ActionEvent e) {

		alwaysExecute();

		if (playing) {

			if (nextDirection.size() > 0 && singleDirection) executeDirection();

			moves();

			if (speedUp)
				timer.setDelay(1000 / (int) (speed + getScore() / 2));

			if (checkIfDead()) {
				toNameEnter();
				stopTime();
			}
		}
		repaint();
	}
	
	/**
	 * Return true if the player died and the game will end and 
	 * go to the score screen and then to the end game screen and then it 
	 * will restart by calling the reset method
	 * @return
	 */
	protected boolean checkIfDead() {
		return false;
	}
	/**
	 * Gets called over and over and over. Player movement and stuff should be here
	 */
	protected void moves() {}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed(e);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		released(e);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		enters(e);
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		exits(e);
		repaint();
	}

	public void clicked(MouseEvent e) {}
	public void pressed(MouseEvent e) {}
	public void released(MouseEvent e) {}
	public void enters(MouseEvent e) {}
	public void exits(MouseEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {
		if (resumeOnFocusGain && ifPlaying()) {
			startTime();
			playing = true;
			paused = false;
		}
		gotFocus(e);
		repaint();
	}

	public void gotFocus(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		if (ifPlaying()) {
			stopTime();
			playing = false;
			paused = true;
		}
		lostFocus(e);
		repaint();
	}
	
	public void lostFocus(FocusEvent e) {}

	@Override
	public void up() {
		upPressed = true;
	}

	@Override
	public void down() {
		downPressed = true;
	}

	@Override
	public void left() {
		leftPressed = true;
	}

	@Override
	public void right() {
		rightPressed = true;
	}

	@Override
	public void upReleased() {
		upPressed = false;
	}

	@Override
	public void downReleased() {
		downPressed = false;
	}
	
	@Override
	public void leftReleased() {
		leftPressed = false;
	}

	@Override
	public void rightReleased() {
		rightPressed = false;
	}

	public void customPressed(KeyEvent e) {}

	public void customReleased(KeyEvent e) {}

	/**
	 * Sets the graphics font at the given size
	 * @param g
	 * @param size
	 */
	protected Font getFont(int size) {
		return customFont.getFont(size);
	}
	
	/**
	 * When you want to change the font, use this. You can 
	 * pass in a font name or the name of a font file. No extension
	 * on the font file. Put the font file into the Fonts folder 
	 * inside of the InfoFiles folder
	 * @param name
	 */
	protected void setNewFont(String name) {
		customFont = new CustomFont(name, Font.BOLD, 18);
	}

	/**
	 * Adds the direction to a list of directions to be executed.
	 * @param d
	 */
	protected void addDirection(Direction d) {

		if (singleDirection) {
			if (nextDirection.size() < 2)
				nextDirection.add(d);
		}
	}

	/**
	 * Executes the direction passed into the method
	 */
	protected void executeDirection() {

		Direction d = nextDirection.get(0);
		nextDirection.remove(0);

		switch (d) {

		case up:
			up();
			break;
			
		case down:
			down();
			break;

		case left:
			left();
			break;

		case right:
			right();
		}
	}

	protected Direction getDirection(int keyCode) {

		if (keyCode == upKey) {
			return Direction.up;

		} else if (keyCode == downKey) {
			return Direction.down;

		} else if (keyCode == leftKey) {
			return Direction.left;

		} else {
			return Direction.right;
		}
	}

	/**
	 * This is always run, even when not playing
	 */
	protected void alwaysExecute() {}

	/**
	 * Return the name of your game
	 * @return
	 */
	protected String getGameName() {
		return null;
	}

	protected String getFolderPath() {
		return "InfoFiles/";
	}
	
	/**
	 * Gets the current score of the game
	 * @return
	 */
	protected int getScore() {
		return score;
	}
	/**
	 * Sets a custom size of the Window and scaling behavior. Default 800x480
	 * @param
	 * w
	 * h
	 */
	protected void setWindowSize(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
	}

	protected class GameTime implements ActionListener {

		private int timeSplit = 0;
		private int timeSeconds = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			timeSplit++;
			if (timeSplit % 60 == 0) {
				timeSplit = 0;
				timeSeconds++;
			}
		}

		protected int getTime() {
			return timeSeconds;
		}

		protected void resetTime() {
			timeSplit = 0;
			timeSeconds = 0;
		}
	}

	
}
