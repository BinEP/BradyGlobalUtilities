package game_state;

import java.util.ArrayList;

import listener_control.ListenerActivator;
import game_actions.Control;
import game_actions.Control.Direction;

public class DirectionExecution {

	private ListenerActivator listenerActivator;
	private ArrayList<Direction> nextDirection = new ArrayList<Direction>();
	private Control game;
	
	public DirectionExecution(Control control) {
		game = control;
		listenerActivator = game.getListenerActivator();
	}
	
	/**
	 * Adds the direction to a list of directions to be executed.
	 * @param d
	 */
	public void addDirection(Direction d) {

		if (GameStateManager.isSingleDirection()) {
			if (nextDirection.size() < 2)
				nextDirection.add(d);
		}
	}

	/**
	 * Executes the direction passed into the method
	 */
	public void executeDirection() {

		Direction d = nextDirection.get(0);
		nextDirection.remove(0);

		switch (d) {

		case up:
			listenerActivator.up();
			break;
			
		case down:
			listenerActivator.down();
			break;

		case left:
			listenerActivator.left();
			break;

		case right:
			listenerActivator.right();
			break;
		case none:
			break;
		}
	}

	public Direction getDirection(int keyCode) {

		if (keyCode == game.upKey) {
			return Direction.up;

		} else if (keyCode == game.downKey) {
			return Direction.down;

		} else if (keyCode == game.leftKey) {
			return Direction.left;

		} else if (keyCode == game.rightKey) {
			return Direction.right;
		}
		
		return Direction.none;
	}
	
	public ArrayList<Direction> getNextDirection() {
		return nextDirection;
	}
}