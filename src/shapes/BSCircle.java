package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import listener_control.ShapeListenerManager.Movement.Direction;
import shapes.interfaces.BSShape;

public class BSCircle extends BSShape {

	private final Ellipse2D.Double circle = new Ellipse2D.Double();
	
	public BSCircle() {
	}
	
	public BSCircle(double x, double y, double r) {
		circle.setFrame(x, y, r * 2, r * 2);
	}
	

	@Override
	public BSRectangle getPosition() {
		return null;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMovement(int dx, int dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDirection(Direction d) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDeltaX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDeltaY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fill(Graphics2D g) {
		// TODO Auto-generated method stub

	}


	@Override
	public Rectangle getBounds() {
		return circle.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return circle.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return circle.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return circle.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return circle.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return circle.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return circle.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return circle.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return circle.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return circle.getPathIterator(at, flatness);
	}

}
