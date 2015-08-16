package shapes;

import game_actions.Control;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import shapes.interfaces.BSShape;

public class BSRectangle extends BSShape {

	public int width;
	public int height;

	public BSRectangle() {
	}

	public BSRectangle(Rectangle r) {
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;
	}

	public BSRectangle(Point p) {
		this(p.x, p.y, 0, 0);
	}

	public BSRectangle(Dimension d) {
		this(0, 0, d.width, d.height);
	}

	public BSRectangle(int width, int height) {
		this(0, 0, width, height);
	}

	public BSRectangle(Point p, Dimension d) {
		this(p.x, p.y, d.width, d.height);
	}

	public BSRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Control.addUpdatable(this);
	}

	public BSRectangle(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height);
		setMovement(dx, dy);
	}

	public BSRectangle(Rectangle r, int dx, int dy) {
		this(r.x, r.y, r.width, r.height);
		setMovement(dx, dy);
	}

	@Override
	public void setShape() {
		shape = new Rectangle(x, y, width, height);
	}

}
