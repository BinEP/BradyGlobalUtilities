package shapes;

import java.awt.Shape;

import events.ShapeListenerManager.Movement.Direction;
import events.ShapeListenerManager.Trigger;

public interface BSShape extends Shape, Trigger {
	
	public void setMovement(int dx, int dy);
	public void setDirection(Direction d);
}
