package custom_listeners;

import events.GameEvent;

public interface BSGameListener extends SuperListener {
	
	public void scored(GameEvent g);
	public void death(GameEvent g);	
}
