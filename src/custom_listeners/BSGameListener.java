package custom_listeners;

import events.GameEvent;

public interface BSGameListener {
	
	public void scored(GameEvent g);
	public void death(GameEvent g);	
}
