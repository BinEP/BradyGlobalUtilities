package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import shapes.interfaces.BSShape;
import listener_control.ShapeListenerManager.Movement.Direction;

public class BSRectangle implements BSShape {

	private int deltaX = 0;
	private int deltaY = 0;
	private Direction direction = Direction.none;
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public BSRectangle() {
	}

	public BSRectangle(Rectangle r) {
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;
	}

	public BSRectangle(Point p) {
		this(p.x, p.y, 0, 0);
	}

	public BSRectangle(Dimension d) {
		this(0, 0, d.width, d.height);
	}

	public BSRectangle(int width, int height) {
		this(0, 0, width, height);
	}

	public BSRectangle(Point p, Dimension d) {
		this(p.x, p.y, d.width, d.height);
	}

	public BSRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public BSRectangle(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height);
		setMovement(dx, dy);
	}
	
	public BSRectangle(Rectangle r, int dx, int dy) {
		this(r.x, r.y, r.width, r.height);
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
	public BSRectangle getPosition() {
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
	public void draw(Graphics2D g) {
		g.draw(this);
	}
	
	@Override
	public void fill(Graphics2D g) {
		g.fill(this);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public Rectangle2D getBounds2D() {
		return getBounds();
	}

	@Override
	public boolean contains(double x, double y) {
		int tx = this.x;
		int ty = this.y;
		
		boolean yGood = y > ty && y < ty + height;
		boolean xGood = x > tx && x < tx + width;
		
		return yGood && xGood;
	}

	@Override
	public boolean contains(Point2D p) {
		return contains(p.getX(), p.getY());
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
