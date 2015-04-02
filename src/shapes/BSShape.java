package shapes;

import java.awt.AWTEvent;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;

import utility_classes.BSHashMap;
import custom_listeners.BSAllListeners;
import listener_control.ListenerManager;
import events.GameEvent;
import events.ShapeListenerManager.Movement.Direction;
import events.ShapeListenerManager.Trigger;

public abstract class BSShape implements Shape, Trigger, BSAllListeners {

	public static BSHashMap shapeTriggers = new BSHashMap();
		
	public BSShape() {
		ListenerManager.addAllListeners(this);
		ListenerManager.removeDirectionKeyListener(this);
	}

	public abstract void setMovement(int dx, int dy);

	public abstract void setDirection(Direction d);

	public static void addMouseAction(String listener, Class<?> classToCallMethod,
			String methodName) {
		try {
			Class<?> params[] = { getEventParameter(listener) };
			Method callMethod = classToCallMethod.getMethod(methodName, params);
			shapeTriggers.put(listener, new CallMethod(classToCallMethod,
					callMethod));
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
			ArrayList<CallMethod> methods = (ArrayList<CallMethod>) shapeTriggers
					.get(key);
			for (CallMethod cm : methods) {
				cm.theMethod.invoke(cm.theClass, e);
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
		runMethod("focus", e);
	}

	@Override
	public void lostFocus(FocusEvent e) {
		runMethod("focus", e);
	}

	@Override
	public void scored(GameEvent g) {
		runMethod("game", g);
	}

	@Override
	public void death(GameEvent g) {
		runMethod("game", g);
	}

	@Override
	public void clicked(MouseEvent e) {
		runMethod("mouse", e);
	}

	@Override
	public void pressed(MouseEvent e) {
		runMethod("mouse", e);
	}

	@Override
	public void released(MouseEvent e) {
		runMethod("mouse", e);
	}

	@Override
	public void enters(MouseEvent e) {
		runMethod("mouse", e);
	}

	@Override
	public void exits(MouseEvent e) {
		runMethod("mouse", e);
	}
}
