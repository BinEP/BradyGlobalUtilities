package gameActions;

import java.awt.BorderLayout;
import java.awt.Window;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import utility_classes.Windows;

/**
 * Runs the game. Makes a new JFrame and adds the UserGame to the frame
 */
public class Runner extends JFrame {
	
	private static final long serialVersionUID = 7320121689295681465L;
	
	public static void tryFullScreen(Window window) {
		
		if (isMacOSX()) {
            enableFullScreenMode(window);
            enterFullScreen(window);
        }
	}
	
	public static void enableFullScreenMode(Window window) {
        try {
            Class<?> FSUClass = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class<?> params[] = new Class[]{Window.class, Boolean.TYPE};
            Method allowFullScreen = FSUClass.getMethod("setWindowCanFullScreen", params);
            
            allowFullScreen.invoke(FSUClass, window, true);
        } catch (Throwable t) {
            System.err.println("Full screen mode is not supported");
            t.printStackTrace();
        }
    }
	
	public static void enterFullScreen(Window window) {
		
		try {
			Class<?> appClass = Class.forName("com.apple.eawt.Application");
			Class<?> params[] = new Class[]{};
			
			Method getApplication = appClass.getMethod("getApplication", params);
			Object application = getApplication.invoke(appClass);
			
			Method toggleFullScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);
         
			toggleFullScreen.invoke(application, window);
		} catch (Exception e) {
			System.out.println("An exception occurred while trying to toggle full screen mode");
			e.printStackTrace();
		}
	}
	
	private static boolean isMacOSX() {
        return System.getProperty("os.name").indexOf("Mac OS X") >= 0;
    }

	public Runner() {
		enableFullScreenMode(this);
		setVisible(true);
	}
	
	public Runner(Control game) {
		super(game.getGameName() + "!");
		boolean fullscreen = game.fullscreen;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setResizable(Windows.isRESIZEABLE());
		setAlwaysOnTop(Windows.isALWAYS_ON_TOP());
		
		add(game, BorderLayout.CENTER);
		setSize(Windows.getWidth(), Windows.getREAL_HEIGHT());
		
		enableFullScreenMode(this);
		if (fullscreen) enterFullScreen(this);
		
		setVisible(true);
	}

}
