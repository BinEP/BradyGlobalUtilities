package shapes;

import game_actions.Control;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.interfaces.BSShape;

public class BSPolygon extends BSShape {

	private static final long serialVersionUID = 2375247181030188951L;
	private int deltaX = 0;
	private int deltaY = 0;
	private Direction direction = Direction.none;
	
	public BSPolygon() {
		Control.addUpdatable(this);
	}

	public BSPolygon(int[] xpoints, int[] ypoints, int npoints) {
		super(xpoints, ypoints, npoints);
		Control.addUpdatable(this);
	}

	@Override
	public BSRectangle getPosition() {
		return new BSRectangle(bounds);
	}

	@Override
	public Direction getDirection() {
		return direction;
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
	public void setDirection(Direction d) {
		direction = d;
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
	public void update() {
		for (int i = 0; i < npoints; i++) {
			xpoints[i] += deltaX;
			ypoints[i] += deltaY;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.draw(this);
	}

	@Override
	public void fill(Graphics2D g) {
		g.fill(this);
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}

}
