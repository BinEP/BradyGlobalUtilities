package shapes.interfaces;

import listener_control.ShapeListenerManager.Movement.Direction;

public interface Moveable extends Updatable {
	public void setMovement(int dx, int dy);
	public void setDirection(Direction d);
	public int getDeltaX();
	public int getDeltaY();
	public int getRotation();
	public void rotate(int degrees);
	public void rotateContinuous(int speed);
}
