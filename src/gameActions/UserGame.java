package gameActions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.Timer;

import events.ShapeListenerManager;
import events.ShapeListenerManager.Action;
import events.ShapeListenerManager.Movement;
import shapes.BSRectangle;
import utility_classes.*;

public class UserGame extends Game {

	/*
	 * int deltaX int deltaY int movementVar
	 * 
	 * int playerX int playerY
	 * 
	 * boolean startGame boolean playing boolean BooleanManager.endGame() boolean nameEnter
	 * boolean highScores
	 * 
	 * boolean singleDirection sets if only one directin per frame
	 */

	/*
	 * You can override these methods to customize actions
	 * 
	 * drawStart(Graphics2D g)
	 * drawPlaying(Graphics2D g)
	 * drawPaused(Graphics2D g)
	 * drawEnd(Graphics2D g)
	 * up()
	 * down()
	 * left()
	 * right()
	 * upReleased()
	 * downReleased()
	 * rightReleased()
	 * leftReleased()
	 * 
	 */
	
	public BSRectangle player;
	
	public UserGame() {

		super();
		
	}

	/**
	 * Used to draw custom graphics on the screen
	 * Anything in this method will be painted on all screens
	 * unless the painting part is inside of if statements so
	 * that it only drawn when that is true
	 * 
	 */
	public void draw(Graphics2D g) {
		
	}
	/**
	 * Overrides the drawPlaying method in the Control class. So if you want something different than a
	 * box that you can change the size of, change what is in here. Gets called when the screen in repainted
	 */

	public void drawPlaying(Graphics2D g) {
		
		g.setColor(Color.CYAN);
//		g.fillRect(playerX, playerY, 20, 20);
		g.fill(player);
		
		g.drawString(String.valueOf(getTime()), 5, 15);

	}
	/**
	 * Gets called constantly. Put code here that will modify the player or other variables
	 * 
	 */
	public void moves() {
		
//		if (upPressed) {
//			deltaY = -movementVar;
//		} else if (downPressed) {
//			deltaY = movementVar;
//		} else {
//			deltaY = 0;
//		}
//		
//		if (leftPressed) {
//			deltaX = -movementVar;
//		} else if (rightPressed) {
//			deltaX = movementVar;
//		} else {
//			deltaX = 0;
//		}
//		
		player.x += deltaX;
		player.y += deltaY;

		

	}
	/**
	 * This should return a boolean indicating if the player is dead or not. By default returns false
	 */
	public boolean checkIfDead() {
		
		
//		return playerX > 500;
		return false;
	}
	
	/**
	 * Gets called when the game is reset and you start over. Also calls the setup method. Can be used
	 * so make sure everything is cleared so the game starts new
	 */
	public void reset() {

		deltaX = 2;
		deltaY = 2;
		player.x = playerX;
		player.y = playerY;

	}
/**
 	* Sets up the game before it starts. Sets all variables needed to any certain values
 */
	@Override
	public void setup() {

		deltaX = 2;
		deltaY = 2;
		playerX = 100;
		playerY = 100;

		player = new BSRectangle(playerX, playerY, 20, 20);
		ShapeListenerManager.addTrigger(Action.death, Movement.exit, outerbox, "Stuff", player);
//		ListenerManager.addTrigger(Action.score, Movement.exit, outerbox, "Stuff", player);

		
	}

@Override
public String getGameName() {
	// TODO Auto-generated method stub
	return "Game";
}

@Override
public int getScore() {
	// TODO Auto-generated method stub
	return getTime();
}

public static void main(String[] args) {
	new Runner(new UserGame());
	
}

}
