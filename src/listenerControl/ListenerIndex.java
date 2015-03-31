package listenerControl;

import java.util.ArrayList;
import java.util.List;

import customListeners.BSActionListener;
import customListeners.BSDirectionKeyListener;
import customListeners.BSFocusListener;
import customListeners.BSKeyListener;
import customListeners.BSMouseListener;
import customListeners.BSGameListener;

public class ListenerIndex {

	public ListenerIndex() {
	}
	
	public static List<BSKeyListener> keyListeners = new ArrayList<BSKeyListener>();
	public static List<BSDirectionKeyListener> directionKeyListeners = new ArrayList<BSDirectionKeyListener>();
	public static List<BSActionListener> actionListeners = new ArrayList<BSActionListener>();
	public static List<BSMouseListener> mouseListeners = new ArrayList<BSMouseListener>();
	public static List<BSFocusListener> focusListeners = new ArrayList<BSFocusListener>();
	public static List<BSGameListener> gameListeners = new ArrayList<BSGameListener>();
	
	public static void addKeyListener(BSKeyListener k) {
		keyListeners.add(k);
	}
	
	public static void removeKeyListener(BSKeyListener k) {
		keyListeners.remove(k);
	}
	
	public static void addDirectionKeyListener(BSDirectionKeyListener d) {
		directionKeyListeners.add(d);
	}
	
	public static void removeDirectionKeyListener(BSDirectionKeyListener d) {
		directionKeyListeners.remove(d);
	}
	
	public static void addActionListener(BSActionListener a) {
		actionListeners.add(a);
	}
	
	public static void removeActionListener(BSActionListener a) {
		actionListeners.remove(a);
	}
	
	public static void addKeyListener(BSMouseListener m) {
		mouseListeners.add(m);
	}
	
	public static void removeKeyListener(BSMouseListener m) {
		mouseListeners.remove(m);
	}
	
	public static void addKeyListener(BSFocusListener f) {
		focusListeners.add(f);
	}
	
	public static void removeKeyListener(BSFocusListener f) {
		focusListeners.remove(f);
	}
	
	public static void addGameListener(BSGameListener g) {
		gameListeners.add(g);
	}
	
	public static void removeGameListener(BSGameListener g) {
		gameListeners.remove(g);
	}
}
