package events;

import events.ListenerManager.State;

public class StateEvent {
	
	private double x;
	private double y;
	private State reaction;

	public StateEvent(double newX, double newY, State r) {
		x = newX;
		y = newY;
		reaction = r;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public State getReaction() {
		return reaction;
	}

}
