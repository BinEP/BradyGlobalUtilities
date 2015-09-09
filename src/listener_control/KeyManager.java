package listener_control;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import custom_listeners.BSKeyListener;
import listener_control.ObjectListenerManager.CallMethod;
import utility_classes.BSHashMapKeys;

public class KeyManager implements BSKeyListener {
	
	public static BSHashMapKeys keyListTyped = new BSHashMapKeys();
	public static BSHashMapKeys keyListPressed = new BSHashMapKeys();
	public static BSHashMapKeys keyListReleased = new BSHashMapKeys();

	public enum KEY {
		TYPED, PRESSED, RELEASED;
	}

	public static void addKey(Character c, String methodName, Object object, KEY key) {
		try {
			CallMethod callMethod = new CallMethod(object, object.getClass().getMethod(methodName));
			
			switch (key) {
			case TYPED:
				keyListTyped.put(c, callMethod);
				break;
			case PRESSED:
				keyListPressed.put(c, callMethod);
				break;
			case RELEASED:
				keyListReleased.put(c, callMethod);
				break;
			}

		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		loop(keyListTyped, e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		loop(keyListPressed, e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		loop(keyListReleased, e);
	}


	private void loop(BSHashMapKeys list, KeyEvent e) {
		if (list.get(e.getKeyChar()) == null) return;
		
		for (CallMethod m: list.get(e.getKeyChar())) {
			try {
				m.getTheMethod().invoke(m.getTheObject());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// public static void addAction(Character c, Object objectToCallMethod,
	// String methodName) {
	//// try {
	//// Class<?> params[] = { getEventParameter(listener) };
	//// Method callMethod = objectToCallMethod.getClass().getMethod(methodName,
	// params);
	//// addMethod(listener, objectToCallMethod, callMethod);
	//// } catch (NoSuchMethodException e) {
	// try {
	// Method callMethod = objectToCallMethod.getClass().getMethod(methodName);
	// addMethod(c, objectToCallMethod, callMethod);
	// } catch (NoSuchMethodException | SecurityException e1) {
	// e1.printStackTrace();
	// }
	//// } catch (SecurityException | ClassNotFoundException e) {
	//// e.printStackTrace();
	//// }
	// }
	//
	// private static void addMethod(Character c, Object objectToCallMethod,
	// Method callMethod) {
	// synchronized (shapeTriggers) {
	// shapeTriggers.put(listener, new CallMethod(objectToCallMethod,
	// callMethod));
	// }
	// }

}
