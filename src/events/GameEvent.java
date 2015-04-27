	package events;

import java.awt.AWTEvent;
import java.awt.Rectangle;

import listener_control.ShapeListenerManager.Action;
import listener_control.ShapeListenerManager.Movement;
import listener_control.ShapeListenerManager.Movement.Direction;

public class GameEvent extends AWTEvent {

	private static final long serialVersionUID = -5196848646745217367L;
	private Action action;
	private Movement movement;
	private Direction direction;
	private Rectangle rectangle;
	private DataEvent message;
	
	public GameEvent(Action a, Movement m, Direction d, Rectangle r, DataEvent message) {
		super(r, 1);
		this.action = a;
		this.movement = m;
		this.direction = d;
		this.rectangle = r;
		this.message = message;
	}
	
	public GameEvent getThis() {
		return this;
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
		return message.getMessage();
	}
	
	public DataEvent getDataEvent() {
		return message;
	}
	
	public void setDataEvent(DataEvent e) {
		message = e;
	}
}
