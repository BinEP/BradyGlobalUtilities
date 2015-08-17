package shapes;

import java.awt.Font;
import java.awt.Point;
import java.awt.Shape;
import java.awt.font.GlyphVector;

import javax.swing.JPanel;

import game_actions.Control;
import shapes.interfaces.BSShape;

public class BSString extends BSShape {

	public Font font;
	public String text;
	private JPanel panel = new JPanel();
	
	public BSString() {
	}
	
	public BSString(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		font = Control.customFont.getCustomFont();
	}
	
	public BSString(String text, Point p) {
		this(text, p.x, p.y);
	}
	
	public BSString(String text, Font font, int x, int y) {
		this(text, x, y);
		this.font = font;
	}
	
	public BSString(String text, Font font, Point p) {
		this(text, font, p.x, p.y);
	}
	
	public BSString(String text, int x, int y, int dx, int dy) {
		this (text, x, y);
		deltaX = dx;
		deltaY = dy;
	}
	
	public BSString(String text, Point p, int dx, int dy) {
		this(text, p.x, p.y, dx, dy);
	}
	
	public BSString(String text, Font font, int x, int y, int dx, int dy) {
		this(text, x, y, dx, dy);
		this.font = font;
	}
	
	public BSString(String text, Font font, Point p, int dx, int dy) {
		this(text, font, p.x, p.y, dx, dy);
	}
	
	@Override
	public Shape getShape() {
		GlyphVector v = font.createGlyphVector(panel.getFontMetrics(font).getFontRenderContext(), text);
		return v.getOutline();
	}
}
