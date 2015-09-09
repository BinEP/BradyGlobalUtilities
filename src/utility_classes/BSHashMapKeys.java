package utility_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import listener_control.ObjectListenerManager.CallMethod;

public class BSHashMapKeys extends HashMap<Character, List<CallMethod>> {

	private static final long serialVersionUID = 1L;

	public void put(Character c,  CallMethod m) {
		
		List<CallMethod> listeners = get(c);

	    // if list does not exist create it
	    if(listeners == null) {
	    	listeners = new ArrayList<CallMethod>();
	    	listeners.add(m);
	         super.put(c, listeners);
	    } else {
	        // add if item is not already in list
	        if(!listeners.contains(m)) listeners.add(m);
	    }
	}
}
