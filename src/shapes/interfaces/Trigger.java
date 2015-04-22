package shapes.interfaces;

import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.BSRectangle;

public interface Trigger {
	public BSRectangle getPosition();
	public Direction getDirection();
}