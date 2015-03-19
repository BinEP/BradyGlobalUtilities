package utilityClasses;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class CustomFont {

	private String path;
	
//	public Font customFont;
	public Font customFont;

	public CustomFont(String fontPath) {
		
		makeCustomFont(fontPath);
	}
	
	public CustomFont(String fontPath, int fontThickness, int size) {

		makeCustomFont(fontPath);
		setFont(fontThickness, size);
	}
	
	public static String getFontPath(String fontName) {
		
		return "/Fonts/" + fontName + ".ttf";
	}

	public void makeCustomFont(String fontPath) {

		try {

			fontPath = getFontPath(fontPath);
			this.path = fontPath;

			InputStream fontStream = CustomFont.class.getClass().getResourceAsStream(fontPath);
			System.out.println(CustomFont.class.getClassLoader());
			System.out.println(fontStream.toString());
			
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			ge.registerFont(customFont);
		} catch (Exception e) {
			// Handle exception
			customFont = new Font("Arial", Font.BOLD, 18);
			System.out.println("Special Font");
			e.printStackTrace();
		}

		setFont(Font.BOLD, 18);
		
	}
	
	public static Font makeCustomFont(String fontPath, int size) {

		
		
		try {

			fontPath = getFontPath(fontPath);
			Font customFont;
			InputStream fontStream = CustomFont.class.getResourceAsStream("/InfoFiles/Fonts/" + fontPath + ".ttf");


			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			ge.registerFont(customFont);
			
			customFont = customFont.deriveFont(Font.BOLD, size);
			return customFont;
			
		} catch (Exception e) {
			// Handle exception
			e.printStackTrace();
			return null;
		}

		
		
	}
	
	/**
	 * Gets font at specified thickness and size
	 * 
	 * @param fontThickness
	 * @param size
	 * @return
	 */
	public Font getFont(int fontThickness, int size) {

		return customFont.deriveFont(fontThickness, size);

	}

	/**
	 * Gets Bold font at specified size
	 * @param size
	 * @return
	 */
	public Font getFont(int size) {

		return customFont.deriveFont((float) size);

	}

	/**
	 * Gets current font size at specified weight
	 * 
	 * @param fontThickness
	 * @return
	 */
	public Font getFontWeight(int fontThickness) {

		return customFont.deriveFont(fontThickness);

	}

	/**
	 * Sets the font to thickness and size for later recall
	 * @param fontThickness
	 * @param size
	 * @return
	 */
	public Font setFont(int fontThickness, int size) {

		customFont = customFont.deriveFont(fontThickness, size);
		return customFont;
	}

	/**
	 * Sets size of font
	 * @param size
	 * @return
	 */
	public Font setFontSize(int size) {

		customFont = customFont.deriveFont(size);
		return customFont;
	}

	/**
	 * Sets thickness of font
	 * @param fontThickness
	 * @return
	 */
	public Font setFontWeight(int fontThickness) {

		customFont = customFont.deriveFont(fontThickness);
		return customFont;
	}
	
	/**
	 * Returns the custom font
	 * 
	 * @return
	 */
	public Font getCustomFont() {
		
		
		return customFont;
		
		
	}
	
	/**
	 * Returns the custom font bolded and at the specified size
	 * @param size
	 * @return
	 */
	public Font fontAtSize(int size) {
		
		return customFont.deriveFont(Font.BOLD, size);
		
	}

	public Font getFont() {
		// TODO Auto-generated method stub
		return customFont;
	}

}
