package shapes;

import java.awt.geom.Ellipse2D;

import shapes.interfaces.BSShape;

public class BSCircle extends BSShape {

	private final Ellipse2D.Double circle = new Ellipse2D.Double();
	
	public BSCircle() {
	}
	
	public BSCircle(double x, double y, double r) {
		circle.setFrame(x, y, r * 2, r * 2);
	}

	@Override
	protected void setShape() {
		setShape(this);		
	}
}
