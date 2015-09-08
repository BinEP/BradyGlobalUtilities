package shapes;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import game_actions.Control;
import shapes.interfaces.BSShape;
import utility_classes.Windows;

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
	
	public BSString(String text, Font font, int y) {
		this.font = font;
		this.text = text;
		setLocation(y);
	}
	
	public BSString(String text, Font font, Point p) {
		this(text, font, p.x, p.y);
	}
	
	public BSString(String text, int x, int y, int dx, int dy) {
		this (text, x, y);
		deltaX = dx;
		deltaY = dy;
	}
	
	public BSString(String text, Font font, int y, int dx, int dy) {
		this.font = font;
		this.text = text;
		setLocation(y);
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
	
	public void setLocation(int yVal) {
		setLocation(0, 0, 0, yVal, false);
	}
	
	public void setLocation(Rectangle r) {		
		setLocation(r.width, r.height, r.x, r.y, true);
	}
	
	private void setLocation(int width, int height, int xVal, int yVal, boolean yCenter) {
		
		if (width == 0) width = Windows.getWidth();
		if (height == 0) height = Windows.getHeight();
		
		FontMetrics fontInfo = new JPanel().getFontMetrics(font);
		int textWidth = fontInfo.stringWidth(text);
		int textHeight = fontInfo.getHeight();
		
		int x = xVal + (width - textWidth) / 2;
		int y = (yCenter) ? yVal + (height - textHeight) / 2 : yVal;
		
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Shape getInnerShape() {
		GlyphVector v = font.createGlyphVector(panel.getFontMetrics(font).getFontRenderContext(), text);
		for (int i = 0; i < v.getNumGlyphs(); i++) {
			Point2D offset = v.getGlyphPosition(i);
			offset.setLocation(offset.getX() + x, offset.getY() + y);
			v.setGlyphPosition(i, offset);
		}		
		return v.getOutline();
	}
}
