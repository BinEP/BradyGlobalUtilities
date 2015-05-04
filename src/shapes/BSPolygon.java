package shapes;

import java.awt.Polygon;

import shapes.interfaces.BSShape;

public class BSPolygon extends BSShape {

	private Polygon shape;

	public BSPolygon() {
	}

	public BSPolygon(int[] xpoints, int[] ypoints, int npoints) {
		shape = new Polygon(xpoints, ypoints, npoints);
		setShape();
	}

	@Override
	public void update() {
		for (int i = 0; i < shape.npoints; i++) {
			shape.xpoints[i] += deltaX;
			shape.ypoints[i] += deltaY;
		}
	}

	@Override
	protected void setShape() {
		setShape(shape);
	}
}
