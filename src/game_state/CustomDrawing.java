package game_state;

import game_actions.Control;
import game_actions.Scene;
import shapes.BSRectangle;
import shapes.BSString;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import utility_classes.Windows;

public class CustomDrawing {
	
	private Control game;
	
	public CustomDrawing(Control control) {
		game = control;
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
	
	public void setupDrawNetWaiting() {
		
		
		BSString title = new BSString(game.getGameName(), Control.customFont.getFont(Windows.getTITLE_SIZE()),  Windows.getTITLE_Y());
		title.setColor(Color.WHITE);
		title.setFilled(false);
		BSString enter = new BSString("Waiting For", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getENTER_Y());
		enter.setColor(Color.WHITE);
		BSString start = new BSString("Others", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getSTART_Y());
		start.setColor(Color.WHITE);
		BSString mapKeys = new BSString("Press keys Up, Right, Down, Left to map new keys", Control.customFont.getFont(12), 30);
		mapKeys.setColor(Color.WHITE);
		BSRectangle rect = new BSRectangle(10, 10, 50, 100);
		rect.setColor(Color.WHITE);
		
		Scene netScene = new Scene("Netwait");
		netScene.addShapeToBeDrawn(title);
		netScene.addShapeToBeDrawn(enter);
		netScene.addShapeToBeDrawn(start);
		netScene.addShapeToBeDrawn(mapKeys);
		netScene.addShapeToBeDrawn(rect);

		game.sceneManager.addSceneCustom(netScene);
		
	}
	
	public void setupDrawStart() {
		BSString title = new BSString(game.getGameName(), Control.customFont.getFont(Windows.getTITLE_SIZE()),  Windows.getTITLE_Y());
		title.setColor(Color.WHITE);
		title.setFilled(false);
		BSString enter = new BSString("Press Enter to", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getENTER_Y());
		enter.setColor(Color.WHITE);
		BSString start = new BSString("Start", Control.customFont.getFont(Windows.getENTER_TO_START_SIZE()), Windows.getSTART_Y());
		start.setColor(Color.WHITE);
		BSString mapKeys = new BSString("Press keys Up, Right, Down, Left to map new keys", Control.customFont.getFont(12), 30);
		mapKeys.setColor(Color.WHITE);
		BSRectangle rect = new BSRectangle(10, 10, 50, 100);
		rect.setColor(Color.WHITE);
		
		Scene startScene = new Scene("Start");
		startScene.addShapeToBeDrawn(title);
		startScene.addShapeToBeDrawn(enter);
		startScene.addShapeToBeDrawn(start);
		startScene.addShapeToBeDrawn(mapKeys);
		startScene.addShapeToBeDrawn(rect);

		game.sceneManager.addSceneCustom(startScene);
	}
	
	public void setupDrawPlaying() {
		Scene playing = new Scene("Playing");
		
		game.sceneManager.addSceneCustom(playing);
	}
	
	public void setupDrawPause() {
		BSString pause = new BSString("Paused", Control.customFont.getFont(Windows.getPAUSE_SIZE()), Windows.getPAUSE_Y());
		pause.setColor(Color.WHITE);
		
		Scene pauseScene = new Scene("Pause");
		pauseScene.addShapeToBeDrawn(pause);
		
		game.sceneManager.addSceneCustom(pauseScene);
	}
	
	public void setupDrawEnd() {
//		BSString score = new BSString(String.valueOf(game.getScore()), Control.customFont.getFont(Windows.getEND_SCORE_SIZE()), Windows.getEND_SCORE_Y());
//		score.setColor(Color.WHITE);
		BSString lose = new BSString("You Lose!", Control.customFont.getFont(Windows.getYOU_LOSE_SIZE()), Windows.getYOU_LOSE_Y());
		lose.setColor(Color.WHITE);
		BSString enter = new BSString("Enter to Restart", Control.customFont.getFont(Windows.getRESTART_SIZE()), Windows.getRESTART_Y());
		enter.setColor(Color.WHITE);
		
		Scene endScene = new Scene("End");
//		endScene.addShapeToBeDrawn(score);
		endScene.addShapeToBeDrawn(Control.scoreShape);
		endScene.addShapeToBeDrawn(lose);
		endScene.addShapeToBeDrawn(enter);
		
		game.sceneManager.addSceneCustom(endScene);
	}
}
