package utility_classes;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 *  this seems like an odd way to go about this.
 *  I'd expect something more like GuiUtil.centerText(String text, Graphics g).
 *  Or just use a JLabel and set its horizontal alignment.
 */
public class CenteredText {

	public static void draw(String text, int yVal, Graphics2D g) {
		draw(g, text, 0, 0, 0, yVal, false);
	}
	
	public static void draw(String text, Rectangle r, Graphics2D g) {		
		draw(g, text, r.width, r.height, r.x, r.y, true);
	}
	
	private static void draw(Graphics2D g, String text, int width, int height, int xVal, int yVal, boolean yCenter) {
		
		if (width == 0) width = Windows.getWidth();
		if (height == 0) height = Windows.getHeight();
		
		FontMetrics fontInfo = g.getFontMetrics();
		int textWidth = fontInfo.stringWidth(text);
		int textHeight = fontInfo.getHeight();
		
		int x = xVal + (width - textWidth) / 2;
		int y = (yCenter) ? yVal + (height - textHeight) / 2 + textHeight - 2 : yVal + textHeight / 2 - 2;
		
		g.drawString(text, x, y);
	}
}
