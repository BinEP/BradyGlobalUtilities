package events;

import events.ListenerManager.Reaction;

public class ReactionEvent {

	private double x;
	private double y;
	private Reaction reaction;

	public ReactionEvent(double newX, double newY, Reaction r) {
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
	
	public Reaction getReaction() {
		return reaction;
	}

}
