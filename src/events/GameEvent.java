package events;

import java.awt.Rectangle;

import events.ShapeListenerManager.Action;
import events.ShapeListenerManager.Movement;
import events.ShapeListenerManager.Movement.Direction;

public class GameEvent {

	private Action action;
	private Movement movement;
	private Direction direction;
	private Rectangle rectangle;
	private String message;
	
	public GameEvent(Action a, Movement m, Direction d, Rectangle r, String message) {
		this.action = a;
		this.movement = m;
		this.direction = d;
		this.rectangle = r;
		this.message = message;
	}

	public Action getAction() {
		return action;
	}

	public Movement getMovement() {
		return movement;
	}

	public Direction getDirection() {
		return direction;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public String getMessage() {
		return message;
	}
}
