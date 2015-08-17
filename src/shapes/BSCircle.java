package shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import shapes.interfaces.BSShape;

public class BSCircle extends BSShape {

	public int radius;
	
	public BSCircle() {
		
	}
	public BSCircle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	public BSCircle(int x, int y, int r, int dx, int dy) {
		this (x, y, r);
		deltaX = dx;
		deltaY = dy;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(x, y, radius, radius);
	}
}
