package events;

import events.ListenerManager.Movement;
import events.ListenerManager.Movement.Direction;

public class MovementEvent {

	private double x;
	private double y;
	private Movement reaction;
	private Direction direction;

	public MovementEvent(double newX, double newY, Movement r, Direction d) {
		x = newX;
		y = newY;
		reaction = r;
		direction = d;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Movement getReaction() {
		return reaction;
	}

	public Direction getDirection() {
		return direction;
	}

}
