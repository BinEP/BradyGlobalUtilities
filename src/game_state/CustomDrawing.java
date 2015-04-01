package game_state;

import game_actions.Control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility_classes.CenteredText;
import utility_classes.ScoreInfo;
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
		g.setColor(c);
		g.setStroke(new BasicStroke(width));
		g.drawRect(game.getOuterbox().x, game.getOuterbox().y, game.getOuterbox().width, game.getOuterbox().height);
		g.setStroke(new BasicStroke(2));
	}
	
	public void setBackgroundColor(Color c) {
		game.setBackground(c);
	}
	
	public void paintScreen(Graphics2D g) {
		
		game.scale(g);
		g.setColor(Color.WHITE);
		draw(g);

		if (GameStateManager.isStartGame()) {
			drawStart(g);

		} else if (GameStateManager.isPlaying() || GameStateManager.isPaused()) {

			drawPlaying(g);

			game.showMouseCoords(g);
			if (GameStateManager.isPaused()) {
				drawPaused(g);
			}
		} else if (GameStateManager.isEndGame()) {
			drawEnd(g);

		} else if (GameStateManager.isNameEnter()) {
			ScoreInfo.enterName(g, game.getScore(), game.getPlayerName());

		} else if (GameStateManager.isHighScores()) {
			ScoreInfo.drawScores(g);
		}
		
	}


}
