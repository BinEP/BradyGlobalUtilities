package listener_control;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utility_classes.BSHashMap;
import custom_listeners.BSAllListeners;
import events.GameEvent;

public abstract class ObjectListenerManager implements BSAllListeners {
	
	public static final String ACTION = "action";
	public static final String GAIN_FOCUS = "gotFocus";
	public static final String LOST_FOCUS = "lostFocus";
	public static final String SCORE = "scored";
	public static final String DEATH = "death";
	public static final String MOUSE_CLICKED = "clicked";
	public static final String MOUSE_PRESSED = "pressed";
	public static final String MOUSE_RELEASED = "released";
	public static final String MOUSE_ENTERS = "enters";
	public static final String MOUSE_EXITS = "exits";
	
	private static final BSHashMap shapeTriggers = new BSHashMap();
	
	private ObjectListenerManager() {
		ListenerManager.addAllListeners(this);
		ListenerManager.removeDirectionKeyListener(this);
	}

	public static void addAction(String listener,
			Class<?> classToCallMethod, String methodName) {
		try {
			Class<?> params[] = { getEventParameter(listener) };
			Method callMethod = classToCallMethod.getMethod(methodName, params);
			synchronized (shapeTriggers) {
				shapeTriggers.put(listener, new CallMethod(classToCallMethod,
						callMethod));
			}
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Class<?> getEventParameter(String listener)
			throws ClassNotFoundException {

		int endIndex = listener.indexOf("Listener");
		int beginIndex = (listener.indexOf("Direction") == -1) ? 3 : 11;

		String className = listener.substring(beginIndex, endIndex) + "Event";
		return Class.forName("custom_listeners." + className);
	}

	public static class CallMethod {

		private Class<?> theClass;
		private Method theMethod;

		public CallMethod(Class<?> c, Method m) {
			this.theClass = c;
			this.theMethod = m;
		}

		public Class<?> getTheClass() {
			return theClass;
		}

		public Method getTheMethod() {
			return theMethod;
		}
	}

	public static void runMethod(String key, AWTEvent e) {
		try {
			synchronized (shapeTriggers) {

				ArrayList<CallMethod> methods = (ArrayList<CallMethod>) shapeTriggers.get(key);

				for (CallMethod cm : methods) {
					cm.theMethod.invoke(cm.theClass, e);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		runMethod("action", e);
	}

	@Override
	public void gotFocus(FocusEvent e) {
		runMethod("gotFocus", e);
	}

	@Override
	public void lostFocus(FocusEvent e) {
		runMethod("lostFocus", e);
	}

	@Override
	public void scored(GameEvent g) {
		runMethod("scored", g);
	}

	@Override
	public void death(GameEvent g) {
		runMethod("death", g);
	}

	@Override
	public void clicked(MouseEvent e) {
		runMethod("clicked", e);
	}

	@Override
	public void pressed(MouseEvent e) {
		runMethod("pressed", e);
	}

	@Override
	public void released(MouseEvent e) {
		runMethod("released", e);
	}

	@Override
	public void enters(MouseEvent e) {
		runMethod("enters", e);
	}

	@Override
	public void exits(MouseEvent e) {
		runMethod("exits", e);
	}
}
