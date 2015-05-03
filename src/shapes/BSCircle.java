package shapes;

import java.awt.geom.Ellipse2D;

import shapes.interfaces.BSShape;

public class BSCircle extends BSShape{

	private Ellipse2D.Double shape;
	
	public BSCircle() {
	}
	
	public BSCircle(int x) {
		
	}
	
	

	@Override
	protected void setShape() {
		setShape(this);
	}

}
