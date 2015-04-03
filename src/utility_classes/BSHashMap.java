package utility_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import listener_control.ObjectListenerManager.CallMethod;

public class BSHashMap extends HashMap<String, List<CallMethod>> {

	private static final long serialVersionUID = 4533524210418638763L;

	public void put(String listener, CallMethod m) {
		
		if (m == null) return;
		List<CallMethod> currentMethods = get(listener);
		if (currentMethods == null) {
			currentMethods = new ArrayList<CallMethod>();
			super.put(listener, currentMethods);
		}
		if (!currentMethods.contains(m)) currentMethods.add(m);
	}
}
