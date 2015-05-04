package shapes.interfaces;

import game_actions.Control;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.BSRectangle;

public abstract class BSShape implements Trigger, Moveable, Shape {	
	
	public int deltaX = 0;
	public int deltaY = 0;
	protected Direction direction = Direction.none;
	
	public int x;
	public int y;
	
	protected Shape shape;
	
	public BSShape() {
		Control.addUpdatable(this);
		setShape();
	}
	
	protected abstract void setShape();
	
	protected void setShape(Shape s) {
		this.shape = s;
	}
		
	public void draw(Graphics2D g) {
		g.draw(this);
	};
	
	public void fill(Graphics2D g) {
		g.fill(this);
	};
	
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
	public BSRectangle getPosition() {
		return new BSRectangle(this.getBounds());
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Direction d) {
		direction = d;
	}
	
	@Override
	public void update() {
		x += deltaX;
		y += deltaY;
	}

	@Override
	public int getDeltaX() {
		return deltaX;
	}

	@Override
	public int getDeltaY() {
		return deltaY;
	}

	@Override
	public Rectangle getBounds() {
		return shape.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return shape.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return shape.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return shape.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return shape.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return shape.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return shape.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return shape.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return shape.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return shape.getPathIterator(at, flatness);
	}
}
