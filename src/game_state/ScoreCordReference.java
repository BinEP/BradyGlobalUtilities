package game_state;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import utility_classes.CenteredText;
import utility_classes.CustomFont;
import utility_classes.Windows;

public class ScoreCordReference {
	
	/**
	 * The positions that scores could be on screen to make it easier to
	 * position the score
	 * @author Brady Stoffel
	 */
	public enum ScoreCoords {

		top_left					(10, 10), 
		top_middle					(Windows.getWidth() / 2, 10), 
		top_right					(Windows.getWidth() - 10, 10), 
		middle_left					(10, Windows.getHeight() / 2), 
		middle_middle				(Windows.getWidth() / 2, Windows.getHeight() / 2), 
		middle_right				(Windows.getWidth() - 10, Windows.getHeight() / 2), 
		bottom_left					(10, Windows.getHeight() - 15), 
		bottom_middle				(Windows.getWidth() / 2, Windows.getHeight() - Windows.getTopBuffer()), 
		bottom_right				(Windows.getWidth() - 10, Windows.getHeight() - Windows.getTopBuffer());

		protected int x;
		protected int y;

		private ScoreCoords(int x, int y) {
			this.x = x;
			this.y = y;
		}

		protected Point getCoords() {
			return new Point(this.x, this.y);
		}

		/**
		 * Draws text at preset enum position using current font
		 * @param text
		 * @param g
		 */
		protected void draw(String text, Graphics2D g) {

			g.setFont(CustomFont.makeCustomFont(Windows.getFONT_NAME(),
					Windows.getSCORE_SIZE()));

			FontMetrics fontInfo = g.getFontMetrics();
			int textWidth = fontInfo.stringWidth(text);
			int textHeight = fontInfo.getHeight();

			if (x == Windows.getWidth() / 2) {
				CenteredText.draw(text, y, g);

			} else if (x == 10) {
				g.drawString(text, x, y + textHeight / 2);

			} else if (x == Windows.getWidth() - 10) {
				g.drawString(text, x - textWidth, y + textHeight / 2);
			}
		}
	}

}
