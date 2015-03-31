package gameState;

import java.util.ArrayList;

import listenerControl.ListenerActivator;
import gameActions.Control;
import gameActions.Control.Direction;

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

		if (BooleanManager.isSingleDirection()) {
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
		}
	}

	public Direction getDirection(int keyCode) {

		if (keyCode == game.upKey) {
			return Direction.up;

		} else if (keyCode == game.downKey) {
			return Direction.down;

		} else if (keyCode == game.leftKey) {
			return Direction.left;

		} else {
			return Direction.right;
		}
	}
	
	public ArrayList<Direction> getNextDirection() {
		return nextDirection;
	}

}
