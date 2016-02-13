package listener_control;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import shapes.BSRectangle;
import sounds.BSSound;
import utility_classes.BSHashMap;
import custom_listeners.BSActionListener;
import custom_listeners.BSFocusListener;
import custom_listeners.BSGameListener;
import custom_listeners.BSMouseListener;
import events.GameEvent;
import events.SoundData;
import game_actions.Control;
import game_state.ListenerAutoAdd;

public class ObjectListenerManager implements BSActionListener,
		BSFocusListener, BSGameListener, BSMouseListener {

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

	public static final BSHashMap shapeTriggers = new BSHashMap();
	private static final ArrayList<BSSound> playingSounds = new ArrayList<BSSound>();

	static {
		addListeners();
	}

	private ObjectListenerManager() {
	}

	private static void addListeners() {
		ListenerAutoAdd.addListeners(new ObjectListenerManager());
	}

	public static void addAction(String listener, Object objectToCallMethod,
			String methodName) {
		try {
			Class<?> params[] = { getEventParameter(listener) };
			Method callMethod = objectToCallMethod.getClass().getMethod(methodName, params);
			addMethod(listener, objectToCallMethod, callMethod);
		} catch (NoSuchMethodException e) {
			try {
				Method callMethod = objectToCallMethod.getClass().getMethod(methodName);
				addMethod(listener, objectToCallMethod, callMethod);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void addAction(String listener, BSRectangle bounds, Object objectToCallMethod,
			String methodName) {
		try {
			Class<?> params[] = { getEventParameter(listener) };
			Method callMethod = objectToCallMethod.getClass().getMethod(methodName, params);
			addMethod(listener, objectToCallMethod, callMethod, bounds);
		} catch (NoSuchMethodException e) {
			try {
				Method callMethod = objectToCallMethod.getClass().getMethod(methodName);
				addMethod(listener, objectToCallMethod, callMethod, bounds);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void addMethod(String listener, Object objectToCallMethod,
			Method callMethod) {
		synchronized (shapeTriggers) {
			shapeTriggers.put(listener, new CallMethod(objectToCallMethod, callMethod));
		}
	}
	
	private static void addMethod(String listener, Object objectToCallMethod,
			Method callMethod, BSRectangle bounds) {
		synchronized (shapeTriggers) {
			shapeTriggers.put(listener, new CallMethod(objectToCallMethod, callMethod, bounds));
		}
	}
	
	public static void addSound(BSSound sound) {
		synchronized (playingSounds) {
			playingSounds.add(sound);
		}
	}

	private static Class<?> getEventParameter(String listener)
			throws ClassNotFoundException {
		String className = listenerName(listener) + "Event";
		String classString = (className.equals("GameEvent")) ? "events.GameEvent"
				: "java.awt.event." + className;
		return Class.forName(classString);
	}

	private static String listenerName(String listener) {

		switch (listener) {

		case ACTION:
			return "Action";
		case GAIN_FOCUS:
		case LOST_FOCUS:
			return "Focus";
		case SCORE:
		case DEATH:
			return "Game";
		case MOUSE_CLICKED:
		case MOUSE_PRESSED:
		case MOUSE_RELEASED:
		case MOUSE_ENTERS:
		case MOUSE_EXITS:
			return "Mouse";
		default:
			return "";
		}
	}

	public static class CallMethod {

		private Object theObject;
		private Method theMethod;
		private BSRectangle bounds;

		public CallMethod(Object o, Method m) {
			this.theObject = o;
			this.theMethod = m;
			this.bounds = new BSRectangle(0, 0, Control.WIDTH, Control.HEIGHT);
		}
		
		public CallMethod(Object o, Method m, BSRectangle r) {
			this.theObject = o;
			this.theMethod = m;
			this.bounds = r;
		}

		public Object getTheObject() {
			return theObject;
		}

		public Method getTheMethod() {
			return theMethod;
		}
		
		public BSRectangle getBounds() {
			return bounds;
		}
	}

	@SuppressWarnings("unchecked")
	public synchronized static void runMethod(String key, AWTEvent e) {
		synchronized (shapeTriggers) {

			ArrayList<CallMethod> methods = (ArrayList<CallMethod>) shapeTriggers
					.get(key);
			if (methods == null)
				return;
			try {
				 for (CallMethod cm : (ArrayList<CallMethod>) methods.clone()) {
					 if (!verifyBounds(cm, e)) continue;
					 runAndCatchException(cm, e);
				 }
			} catch (ConcurrentModificationException e1) {
				System.out.println("ConcurrentModError! BS");
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private static boolean verifyBounds(CallMethod cm, AWTEvent e) {
		Object o = e.getClass().cast(e);
		if (!(o instanceof MouseEvent)) return true;
		return cm.getBounds().contains(((MouseEvent) o).getPoint());
	}

	private static void runAndCatchException(CallMethod cm, AWTEvent e)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {
		try {
			cm.theMethod.invoke(cm.theObject, e);
		} catch (IllegalArgumentException e1) {
			cm.theMethod.invoke(cm.theObject);
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

	@Override
	public void playSound(GameEvent g) {
		ObjectListenerManager.addSound(((SoundData) (g.getDataEvent())).triggerEvent());
	}
	
	public static void endSounds() {
		synchronized (playingSounds) {
			for (BSSound sound : playingSounds) {
				sound.end();
				System.out.println("Ended Sound");
			}
		}
	}
}
