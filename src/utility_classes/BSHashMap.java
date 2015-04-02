package utility_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shapes.BSShape.CallMethod;

public class BSHashMap extends HashMap<String, List<CallMethod>> {

	private static final long serialVersionUID = 4533524210418638763L;

	public void put(String listener, CallMethod m) {
		
		List<CallMethod> currentMethods = get(listener);
		if (currentMethods == null) {
			currentMethods = new ArrayList<CallMethod>();
			super.put(listener, currentMethods);
		}
		currentMethods.add(m);
	}
}
