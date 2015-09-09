package utility_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import custom_listeners.BSSpecificKeyListener;

public class BSHashMapKeys extends HashMap<Character, List<BSSpecificKeyListener>> {

	private static final long serialVersionUID = 1L;

	public void put(Character c,  BSSpecificKeyListener l) {
		
		List<BSSpecificKeyListener> listeners = get(c);

	    // if list does not exist create it
	    if(listeners == null) {
	    	listeners = new ArrayList<BSSpecificKeyListener>();
	    	listeners.add(l);
	         super.put(c, listeners);
	    } else {
	        // add if item is not already in list
	        if(!listeners.contains(l)) listeners.add(l);
	    }
	}
}
