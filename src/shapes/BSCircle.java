package shapes;

import java.awt.geom.Ellipse2D;

import shapes.interfaces.BSShape;

public class BSCircle extends BSShape{

	private Ellipse2D.Double shape;
	
	public BSCircle() {
	}
	
	public BSCircle(int x, int y, int w, int h) {
		shape = new Ellipse2D.Double(x, y, w, h);
		setShape();
	}

	@Override
	protected void setShape() {
		setShape(shape);
	}
}
