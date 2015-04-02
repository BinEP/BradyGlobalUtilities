package shapes.interfaces;

import listener_control.ShapeListenerManager;
import listener_control.ShapeListenerManager.Movement;
import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.BSRectangle;

public interface Trigger {

	public abstract BSRectangle getPosition();

	public abstract Direction getDirection();

}