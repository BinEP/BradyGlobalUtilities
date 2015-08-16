package shapes.interfaces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.BSRectangle;

public abstract class BSShape implements Shape, Trigger, Moveable, Drawable {	
	
	private int deltaX = 0;
	private int deltaY = 0;
	private Direction direction = Direction.none;
	private Color color = Color.WHITE;
	private boolean fill = true;
	
	public int x;
	public int y;
	
	private Shape shape;
	
	public BSShape() {
		setShape();
	}
	
	public abstract void setShape();
	
	@Override
	public void setColor(Color c) {
		color = c;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setFilled(boolean filled) {
		fill = filled;		
	}

	@Override
	public boolean filledShape() {
		return fill;
	}

	public void autoDraw(Graphics2D g) {
		if (fill) fill(g); 
		else draw(g);
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
		return new BSRectangle(shape.getBounds());
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
	
	public void draw(Graphics2D g) {
		g.draw(this);
	}
	
	public void fill(Graphics2D g) {
		g.fill(this);
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
