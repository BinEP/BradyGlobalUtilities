package shapes;

import java.awt.Rectangle;

import events.ListenerManager.Movement.Direction;

public class BSRectangle extends Rectangle implements BSShape {

	public int deltaX = 0;
	public int deltaY = 0;
	private Direction direction = Direction.none;
	
	@Override
	public void setMovement(int dx, int dy) {
		deltaX = dx;
		deltaY = dy;
		
		if (dx != 0) {
			direction = (dx > 0) ? Direction.right : Direction.left;
		} else {
			direction = (dy > 0) ? Direction.down : Direction.up;
		}
	}
	
	@Override
	public Rectangle getPosition() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	@Override
	public void setDirection(Direction d) {
		// TODO Auto-generated method stub
		direction = d;
	}
}
