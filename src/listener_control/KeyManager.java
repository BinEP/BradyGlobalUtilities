package listener_control;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import custom_listeners.BSKeyListener;
import custom_listeners.BSSpecificKeyListener;
import utility_classes.BSHashMapKeys;

public class KeyManager implements BSKeyListener {
	
	List<BSSpecificKeyListener> keyListeners = new ArrayList<BSSpecificKeyListener>();
	public static BSHashMapKeys keyList = new BSHashMapKeys();

	public void addKey(Character c, BSSpecificKeyListener l) {
		keyList.put(c, l);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		for (BSSpecificKeyListener l: keyList.get(e.getKeyChar())) {
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
