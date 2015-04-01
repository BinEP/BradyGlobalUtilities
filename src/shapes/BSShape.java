package shapes;

import java.awt.Shape;

import events.ListenerManager.Movement.Direction;
import events.ListenerManager.Trigger;

public interface BSShape extends Shape, Trigger {
	
	public void setMovement(int dx, int dy);
	public void setDirection(Direction d);
	
}
