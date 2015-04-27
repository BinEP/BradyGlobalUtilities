package game_actions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import listener_control.ObjectListenerManager;
import listener_control.ShapeListenerManager;
import listener_control.ShapeListenerManager.Action;
import listener_control.ShapeListenerManager.Movement;
import custom_listeners.BSGameListener;
import events.GameEvent;
import game_state.GameStateManager;
import game_state.ScoreCordReference.ScoreCoords;
import shapes.BSRectangle;
import utility_classes.CenteredText;

public class UserGame extends Game implements BSGameListener {

	private static final long serialVersionUID = 6772560356584757192L;

	public BSRectangle player;
	private BSRectangle goal;

	/**
	 * Used to draw custom graphics on the screen Anything in this method will
	 * be painted on all screens unless the painting part is inside of if
	 * statements so that it only drawn when that is true
	 * 
	 */
	public void draw(Graphics2D g) {
	}

	/**
	 * Overrides the drawPlaying method in the Control class. So if you want
	 * something different than a box that you can change the size of, change
	 * what is in here. Gets called when the screen in repainted
	 */
	@Override
	public void drawPlaying(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fill(player);

		CenteredText.draw("Draw 1", 20, g);
		g.drawRoundRect(0, 0, 800, 40, 5, 5);
		
		CenteredText.draw("Draw 2", new Rectangle(50, 70, 100, 60), g);
		g.drawRoundRect(50, 70, 100, 60, 5, 5);
		
		g.draw(goal);
		
		g.drawString(String.valueOf(getTime()), 5, 15);
		g.drawString("" + score, ScoreCoords.bottom_middle.getCoords().x, ScoreCoords.bottom_middle.getCoords().y);
	}

	/**
	 * Gets called constantly. Put code here that will modify the player or
	 * other variables
	 * 
	 */
	public void moves() {

		 if (upPressed) {
		 deltaY = -movementVar;
		 } else if (downPressed) {
		 deltaY = movementVar;
		 } else {
		 deltaY = 0;
		 }
		
		 if (leftPressed) {
		 deltaX = -movementVar;
		 } else if (rightPressed) {
		 deltaX = movementVar;
		 } else {
		 deltaX = 0;
		 }
		
		player.x += deltaX;
		player.y += deltaY;
	}

	/**
	 * This should return a boolean indicating if the player is dead or not. By
	 * default returns false
	 */
	public boolean checkIfDead() {
		// return playerX > 500;
		return false;
	}

	/**
	 * Gets called when the game is reset and you start over. Also calls the
	 * setup method. Can be used so make sure everything is cleared so the game
	 * starts new
	 */
	public void reset() {

		resetDirectionPressed();
		deltaX = 0;
		deltaY = 0;
		player.x = playerX;
		player.y = playerY;
	}

	/**
	 * Sets up the game before it starts. Sets all variables needed to any
	 * certain values
	 */
	@Override
	public void setup() {

		deltaX = 2;
		deltaY = 2;
		playerX = 100;
		playerY = 100;
		goal = new BSRectangle(30, 190, 60, 100);
		player = new BSRectangle(playerX, playerY, 20, 20);
		ShapeListenerManager.addTrigger(Action.death, Movement.exit, outerbox,
				"Stuff", player);
		ShapeListenerManager.addTrigger(Action.score, Movement.enter, goal.getBounds(),
				"Stuff", player);
		
		ShapeListenerManager.addTrigger(Action.sound, Movement.enter, goal.getBounds(),
				"chu.wav", player);
		
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, this, "mouse");
		
//		setBackgroundMusic("chu.wav");
	}

	@Override
	public String getGameName() {
		return "Game";
	}

	@Override
	public int getScore() {
		return score;
	}

	public static void main(String[] args) {
		new Runner(new UserGame());
	}

	@Override
	public void scored(GameEvent g) {
		score++;
		System.out.println(score);
	}

	@Override
	public void death(GameEvent g) {
		GameStateManager.setDead(true);
	}
	
	public void mouse() {
		System.out.println("Mouse");
	}

	@Override
	public void playSound(GameEvent g) {
		
	}
}
