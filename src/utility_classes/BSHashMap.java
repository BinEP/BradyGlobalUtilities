package utility_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import listener_control.ObjectListenerManager.CallMethod;

public class BSHashMap extends HashMap<String, List<CallMethod>> {

	private static final long serialVersionUID = 4533524210418638763L;

	public void put(String listener, CallMethod m) {
		
		List<CallMethod> methods = get(listener);

	    // if list does not exist create it
	    if(methods == null) {
	    	methods = new ArrayList<CallMethod>();
	    	methods.add(m);
	         super.put(listener, methods);
	    } else {
	        // add if item is not already in list
	        if(!methods.contains(m)) methods.add(m);
	    }
	}
}
