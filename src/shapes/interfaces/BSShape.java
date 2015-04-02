package shapes.interfaces;

import java.awt.Graphics2D;
import java.awt.Shape;

public interface BSShape extends Shape, Trigger, Moveable {	
	public void draw(Graphics2D g);
	public void fill(Graphics2D g);
}
