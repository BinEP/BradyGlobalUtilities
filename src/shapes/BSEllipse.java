package shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import shapes.interfaces.BSShape;

public class BSEllipse extends BSShape {

	public int width;
	public int height;
	
	public BSEllipse() {
	}
	
	public BSEllipse(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public BSEllipse(int x, int y, int w, int h, int dx, int dy) {
		this(x, y, w, h);
		deltaX = dx;
		deltaY = dy;
	}

	@Override
	public Shape getInnerShape() {
		return new Ellipse2D.Double(x, y, width, height);
	}
}
