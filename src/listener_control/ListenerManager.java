package listener_control;

import java.util.ArrayList;
import java.util.List;

import custom_listeners.BSAllListeners;
import custom_listeners.BSActionListener;
import custom_listeners.BSDirectionKeyListener;
import custom_listeners.BSFocusListener;
import custom_listeners.BSGameListener;
import custom_listeners.BSKeyListener;
import custom_listeners.BSMouseListener;

public class ListenerManager {

	public static List<BSKeyListener> keyListeners = new ArrayList<BSKeyListener>();
	public static List<BSDirectionKeyListener> directionKeyListeners = new ArrayList<BSDirectionKeyListener>();
	public static List<BSActionListener> actionListeners = new ArrayList<BSActionListener>();
	public static List<BSMouseListener> mouseListeners = new ArrayList<BSMouseListener>();
	public static List<BSFocusListener> focusListeners = new ArrayList<BSFocusListener>();
	public static List<BSGameListener> gameListeners = new ArrayList<BSGameListener>();
	
	public static void addAllListeners(BSAllListeners al) {
		addKeyListener(al);
		addDirectionKeyListener(al);
		addActionListener(al);
		addMouseListener(al);
		addFocusListener(al);
		addGameListener(al);
	}
	
	public static void removeAllListeners(BSAllListeners al) {
		removeKeyListener(al);
		removeDirectionKeyListener(al);
		removeActionListener(al);
		removeMouseListener(al);
		removeFocusListener(al);
		removeGameListener(al);
	}
	
	public static void addKeyListener(BSKeyListener k) {
		synchronized (keyListeners) {
			keyListeners.add(k);
		}
	}
	
	public static void removeKeyListener(BSKeyListener k) {
		synchronized (keyListeners) {
			keyListeners.remove(k);
		}
	}
	
	public static void addDirectionKeyListener(BSDirectionKeyListener d) {
		synchronized (directionKeyListeners) {
			directionKeyListeners.add(d);
		}
	}
	
	public static void removeDirectionKeyListener(BSDirectionKeyListener d) {
		synchronized (directionKeyListeners) {
			directionKeyListeners.remove(d);
		}
	}
	
	public static void addActionListener(BSActionListener a) {
		synchronized (actionListeners) {
			actionListeners.add(a);
		}
	}
	
	public static void removeActionListener(BSActionListener a) {
		synchronized (actionListeners) {
			actionListeners.remove(a);
		}
	}
	
	public static void addMouseListener(BSMouseListener m) {
		synchronized (mouseListeners) {
			mouseListeners.add(m);
		}
	}
	
	public static void removeMouseListener(BSMouseListener m) {
		synchronized (mouseListeners) {
			mouseListeners.remove(m);
		}
	}
	
	public static void addFocusListener(BSFocusListener f) {
		synchronized (focusListeners) {
			focusListeners.add(f);
		}
	}
	
	public static void removeFocusListener(BSFocusListener f) {
		synchronized (focusListeners) {
			focusListeners.remove(f);
		}
	}
	
	public static void addGameListener(BSGameListener g) {
		synchronized (gameListeners) {
			gameListeners.add(g);
		}
	}
	
	public static void removeGameListener(BSGameListener g) {
		synchronized (gameListeners) {
			gameListeners.remove(g);
		}
	}
}
