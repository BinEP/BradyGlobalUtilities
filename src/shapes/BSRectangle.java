package shapes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import events.ShapeListenerManager.Movement.Direction;

public class BSRectangle extends Rectangle implements BSShape {

	public int deltaX = 0;
	public int deltaY = 0;
	private Direction direction = Direction.none;
	
	public BSRectangle() {
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(Rectangle r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(Dimension d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(Point p, Dimension d) {
		super(p, d);
		// TODO Auto-generated constructor stub
	}

	public BSRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public BSRectangle(int x, int y, int width, int height, int dx, int dy) {
		super(x, y, width, height);
		setMovement(dx, dy);
		// TODO Auto-generated constructor stub
	}
	
	public BSRectangle(Rectangle r, int dx, int dy) {
		super(r.x, r.y, r.width, r.height);
		setMovement(dx, dy);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub

	

}
