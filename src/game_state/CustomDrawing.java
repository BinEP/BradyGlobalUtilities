package game_state;

import game_actions.Control;
import game_actions.Scene;
import shapes.BSString;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility_classes.CenteredText;
import utility_classes.Windows;

public class CustomDrawing {
	
	private Control game;
	
	public CustomDrawing(Control control) {
		game = control;
	}
	
	public void draw(Graphics2D g) {}

	/** Draws the start screen. gets game name from Windows class */
	public void drawStart(Graphics2D g) {

		g.setFont(Control.customFont.getFont(Windows.getTITLE_SIZE()));
		CenteredText.draw(game.getGameName(), Windows.getTITLE_Y(), g);

		g.setFont(Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()));
		CenteredText.draw("Press Enter to", Windows.getENTER_Y(), g);
		CenteredText.draw("Start", Windows.getSTART_Y(), g);

		g.setFont(Control.customFont.getFont(12));
		CenteredText.draw("Press keys Up, Right, Down, Left to map new keys",
				30, g);
	}

	/** Draws the screen when BooleanManager.isPlaying() */
	public void drawPlaying(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(20, 30, game.getPlayerX(), game.getPlayerY());
	}

	/** Draws the word "Paused" in the middle of the screen */
	public void drawPaused(Graphics2D g) {
		g.setFont(Control.customFont.getFont(Windows.getPAUSE_SIZE()));
		g.setColor(Color.WHITE);
		CenteredText.draw("Paused", Windows.getPAUSE_Y(), g);
	}

	/** Draws the end game screen */
	public void drawEnd(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.setFont(Control.customFont.getFont(Windows.getEND_SCORE_SIZE()));
		CenteredText.draw(String.valueOf(game.getScore()), Windows.getEND_SCORE_Y(),
				g);

		g.setFont(Control.customFont.getFont(Windows.getYOU_LOSE_SIZE()));
		CenteredText.draw("You Lose!", Windows.getYOU_LOSE_Y(), g);

		g.setFont(Control.customFont.getFont(Windows.getRESTART_SIZE()));
		CenteredText.draw("Enter to Restart", Windows.getRESTART_Y(), g);
	}

	public void drawBorder(Graphics2D g) {
		drawBorder(g, Color.WHITE, 15);
	}

	public void drawBorder(Graphics2D g, Color c) {
		drawBorder(g, c, 15);
	}

	public void drawBorder(Graphics2D g, int width) {
		drawBorder(g, Color.WHITE, width);
	}

	public void drawBorder(Graphics2D g, Color c, int width) {
		//TODO Change this to BS Shapes
		g.setColor(c);
		g.setStroke(new BasicStroke(width));
		g.drawRect(game.getOuterbox().x, game.getOuterbox().y, game.getOuterbox().width, game.getOuterbox().height);
		g.setStroke(new BasicStroke(2));
	}
	
	public void setBackgroundColor(Color c) {
		game.setBackground(c);
	}
	
	public void setupDrawStart(Graphics2D g) {
		BSString title = new BSString(game.getGameName(), Control.customFont.getFont(Windows.getTITLE_SIZE()),  Windows.getTITLE_Y());
		title.setColor(Color.WHITE);
		BSString enter = new BSString("Press Enter to", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getENTER_Y());
		enter.setColor(Color.WHITE);
		BSString start = new BSString("Start", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getSTART_Y());
		start.setColor(Color.WHITE);
		BSString mapKeys = new BSString("Press keys Up, Right, Down, Left to map new keys", Control.customFont.getFont(12), 30);
		mapKeys.setColor(Color.WHITE);
		
		Scene startScene = new Scene("Start");
		startScene.addShapeToBeDrawn(title);
		startScene.addShapeToBeDrawn(enter);
		startScene.addShapeToBeDrawn(start);
		startScene.addShapeToBeDrawn(mapKeys);

		game.sceneManager.addSceneCustom(startScene);
	}
	
	public void setupDrawPlaying(Graphics2D g) {
		Scene playing = new Scene("Playing");
		
		game.sceneManager.addSceneCustom(playing);
	}
	
	public void setupDrawPause(Graphics2D g) {
		BSString pause = new BSString("Paused", Control.customFont.getFont(Windows.getPAUSE_SIZE()), Windows.getPAUSE_Y());
		pause.setColor(Color.WHITE);
		
		Scene pauseScene = new Scene("Pause");
		pauseScene.addShapeToBeDrawn(pause);
		
		game.sceneManager.addSceneCustom(pauseScene);
	}
	
	public void setupDrawEnd(Graphics2D g) {
		BSString score = new BSString(String.valueOf(game.getScore()), Control.customFont.getFont(Windows.getEND_SCORE_SIZE()), Windows.getEND_SCORE_Y());
		score.setColor(Color.WHITE);
		BSString lose = new BSString("You Lose!", Control.customFont.getFont(Windows.getYOU_LOSE_SIZE()), Windows.getYOU_LOSE_Y());
		lose.setColor(Color.WHITE);
		BSString enter = new BSString("Enter to Restart", Control.customFont.getFont(Windows.getRESTART_SIZE()), Windows.getRESTART_Y());
		enter.setColor(Color.WHITE);
		
		Scene endScene = new Scene("End");
		endScene.addShapeToBeDrawn(score);
		endScene.addShapeToBeDrawn(lose);
		endScene.addShapeToBeDrawn(enter);
		
		game.sceneManager.addSceneCustom(endScene);
	}
}
