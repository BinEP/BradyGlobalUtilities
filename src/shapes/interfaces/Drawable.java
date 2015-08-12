package shapes.interfaces;

import java.awt.Color;

public interface Drawable {
	public void setColor(Color c);
	public Color getColor();
	public void setFilled(boolean filled);
	public boolean filledShape();
}
