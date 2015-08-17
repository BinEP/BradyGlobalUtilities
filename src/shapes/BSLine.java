package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import shapes.interfaces.BSShape;

public class BSLine extends BSShape {

	public int x2;
	public int y2;
	public int lineWidth = 1;
	
	public BSLine() {
	}
	
	public BSLine(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public BSLine(Point p1, Point p2) {
		this(p1.x, p1.y, p2.x, p2.y);
	}
	
	public BSLine(Point p1, Point p2, int dx, int dy) {
		this(p1.x, p1.y, p2.x, p2.y);
		deltaX = dx;
		deltaY = dy;
	}
	
	public BSLine(int x, int y, int x2, int y2, int dx, int dy) {
		this(x, y, x2, y2);
		deltaX = dx;
		deltaY = dy;
	}

	@Override
	public Shape getShape() {
		return new Line2D.Double(new Point2D.Double(x, y), new Point2D.Double(x2, y2));
	}
	
	public double getSlope() {
		return (double) (y2 - y) / (double) (x2 - x);
	}
	
	public void setWidthOfLine(int width) {
		//TODO have to do thing method
	}
}
