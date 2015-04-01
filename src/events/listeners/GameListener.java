package events.listeners;

import events.GameEvent;

public interface GameListener {
	
	public void scored(GameEvent g);
	public void death(GameEvent g);
	
}
