package shapes;

import java.awt.Polygon;

import shapes.interfaces.BSShape;

public class BSPolygon extends BSShape {

	private Polygon poly = (Polygon) shape;

	public BSPolygon() {
	}

	public BSPolygon(int[] xpoints, int[] ypoints, int npoints) {
		setShape(new Polygon(xpoints, ypoints, npoints));
	}

	@Override
	public void update() {
		for (int i = 0; i < poly.npoints; i++) {
			poly.xpoints[i] += deltaX;
			poly.ypoints[i] += deltaY;
		}
	}

	@Override
	protected void setShape() {
		setShape(this);
	}

}
