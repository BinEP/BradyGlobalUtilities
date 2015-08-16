package shapes.interfaces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import game_actions.Control;
import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.BSRectangle;

public abstract class BSShape implements Shape, Trigger, Moveable, Drawable {	
	
	public int deltaX = 0;
	public int deltaY = 0;
	private Direction direction = Direction.none;
	private Color color = Color.WHITE;
	private boolean fill = true;
	
	public int x;
	public int y;
		
	public BSShape() {
		Control.addUpdatable(this);
	}
	
	public abstract Shape getShape();
	
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
		return new BSRectangle(getShape().getBounds());
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
		return getShape().getBounds();
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		return getShape().getBounds2D();
	}
	
	@Override
	public boolean contains(double x, double y) {
		return getShape().contains(x, y);
	}
	
	@Override
	public boolean contains(Point2D p) {
		return getShape().contains(p);
	}
	
	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return getShape().intersects(x, y, w, h);
	}
	
	@Override
	public boolean intersects(Rectangle2D r) {
		return getShape().intersects(r);
	}
	
	@Override
	public boolean contains(double x, double y, double w, double h) {
		return getShape().contains(x, y, w, h);
	}
	
	@Override
	public boolean contains(Rectangle2D r) {
		return getShape().contains(r);
	}
	
	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return getShape().getPathIterator(at);
	}
	
	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return getShape().getPathIterator(at, flatness);
	}
}
