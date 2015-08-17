package shapes;

import java.awt.Polygon;
import java.awt.Shape;

import shapes.interfaces.BSShape;

public class BSPolygon extends BSShape {

	public int[] xpoints;
	public int[] ypoints;
	public int npoints;

	public BSPolygon() {
	}

	public BSPolygon(int[] xpoints, int[] ypoints, int npoints) {
		this.xpoints = xpoints;
		this.ypoints = ypoints;
		this.npoints = npoints;
		x = xpoints[0];
		y = ypoints[0];
	}

	public BSPolygon(int[] xpoints, int[] ypoints, int npoints, int dx, int dy) {
		this(xpoints, ypoints, npoints);
		deltaX = dx;
		deltaY = dy;
	}

	@Override
	public Shape getShape() {
		return new Polygon(xpoints, ypoints, npoints);
	}
}
