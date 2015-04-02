package shapes.interfaces;

import listener_control.ShapeListenerManager.Movement.Direction;

public interface Moveable extends Updateable {
	public void setMovement(int dx, int dy);
	public void setDirection(Direction d);
	public int getDeltaX();
	public int getDeltaY();
}
