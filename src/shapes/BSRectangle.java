package shapes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import events.ShapeListenerManager.Movement.Direction;

public class BSRectangle extends Rectangle implements BSShape {

	private static final long serialVersionUID = 3023130375242283368L;
	public int deltaX = 0;
	public int deltaY = 0;
	private Direction direction = Direction.none;
	
	public BSRectangle() {
	}

	public BSRectangle(Rectangle r) {
		super(r);
	}

	public BSRectangle(Point p) {
		super(p);
	}

	public BSRectangle(Dimension d) {
		super(d);
	}

	public BSRectangle(int width, int height) {
		super(width, height);
	}

	public BSRectangle(Point p, Dimension d) {
		super(p, d);
	}

	public BSRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public BSRectangle(int x, int y, int width, int height, int dx, int dy) {
		super(x, y, width, height);
		setMovement(dx, dy);
	}
	
	public BSRectangle(Rectangle r, int dx, int dy) {
		super(r.x, r.y, r.width, r.height);
		setMovement(dx, dy);
	}
	
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
		return this;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction d) {
		direction = d;
	}
}
