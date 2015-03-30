package events;

import java.awt.Rectangle;

import events.ListenerManager.Action;
import events.ListenerManager.Movement;
import events.ListenerManager.Movement.Direction;

public class GameEvent {

	private Action action;
	private Movement movement;
	private Direction direction;
	private Rectangle rectangle;
	private String message;
	
	public GameEvent(Action a, Movement m, Direction d, Rectangle r, String message) {
		// TODO Auto-generated constructor stub
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
