package shapes;

import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import shapes.interfaces.BSShape;

public class BSCompound extends BSShape {

	private List<BSShape> shapes = new ArrayList<BSShape>();
	
	public BSCompound() {
	}
	
	public BSCompound(BSShape... toAdd) {
		for (BSShape s : toAdd) {
			shapes.add(s);
		}
	}

	@Override
	public Shape getShape() {
		Area area = new Area();
		for (BSShape shape : shapes)  {
			area.add((Area) shape.getShape());
		}
		return area;
	}
	
	public void addShape(BSShape shape) {
		shapes.add(shape);
	}
	
	public void removeShape(BSShape shape) {
		shapes.remove(shape);
	}
	
	public void getShape(int index) {
		shapes.get(index);
	}

}
