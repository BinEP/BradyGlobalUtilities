package events;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import events.ListenerManager.Movement.Direction;
import events.listeners.GameListener;
import events.listeners.ReactionListener;
import events.listeners.StateListener;
import events.sender.ActionSender;

public class ListenerManager {

	/*
	 * Types of actions
	 * 
	 * Movement Trigger Death Created
	 * 
	 * Object Reactions
	 * 
	 * Bounce Teleport Hit other shape
	 * 
	 * Movement Actions
	 * 
	 * Move Enter Exit None
	 */

	public enum Action {
		score, death;
	}

	// public enum State {
	// death, creation;
	// }

	// public enum Reaction {
	// bounce, teleport;//, overlap;
	// }

	public enum Movement {
		enter, exit, intersect, none;

		public enum Direction {
			right, left, up, down, any, none;
		}
	}

	// public List<ActionSender> onDeathSenders = new ArrayList<ActionSender>();
	// public List<ActionSender> onCreateSenders = new
	// ArrayList<ActionSender>();
	//
	// public List<StateListener> stateListeners = new
	// ArrayList<StateListener>();
	//
	//
	// public List<ActionSender> onBounceSenders = new
	// ArrayList<ActionSender>();
	// public List<ActionSender> onTeleportSenders = new
	// ArrayList<ActionSender>();
	//
	// public List<ReactionListener> reactionListeners = new
	// ArrayList<ReactionListener>();
	//
	//
	// public List<ActionSender> movementSenders = new
	// ArrayList<ActionSender>();
	//
	// public List<ReactionListener> movementListeners = new
	// ArrayList<ReactionListener>();
	//

	public List<TriggerInfo> triggers = new ArrayList<TriggerInfo>();
	public List<GameListener> listeners = new ArrayList<GameListener>();

	public void addTrigger(Action a, Movement m, Direction d, Rectangle r,
			String message, Trigger t) {
		triggers.add(new TriggerInfo(a, m, d, r, message, t));
	}

	public void addTrigger(Action a, Movement m, Rectangle r, String message,
			Trigger t) {
		addTrigger(a, m, Direction.any, r, message, t);
	}

	public void removeTrigger(Trigger t) {

		for (Iterator<TriggerInfo> iterator = triggers.iterator(); iterator
				.hasNext();) {
			TriggerInfo ti = iterator.next();
			if (ti.trigger.equals(t))
				triggers.remove(ti);
		}
	}

	public void addListener(GameListener g) {
		listeners.add(g);
	}

	public void removeListener(GameListener g) {
		listeners.remove(g);
	}

	public void triggerTheStuff() {

		for (TriggerInfo ti : triggers) {
			Trigger t = ti.trigger;

			boolean entered = ti.rectangle.contains(t.getPosition());
			if ((ti.movement == Movement.enter && entered)
			|| (ti.movement == Movement.exit && !entered))
				
				if (ti.direction == Direction.any || ti.direction == t.getDirection())
				sendEvent(ti);
		}
	}

	public void sendEvent(TriggerInfo ti) {

		GameEvent g = new GameEvent(ti.action, ti.movement, ti.direction,
				ti.rectangle, ti.message);
		switch (ti.action) {

		case score:
			sendScore(g);

			break;
		case death:
			sendDeath(g);

			break;
		}

	}

	public void sendScore(GameEvent e) {
		for (GameListener l : listeners) {
			l.scored(e);
		}
	}

	public void sendDeath(GameEvent e) {
		for (GameListener l : listeners) {
			l.death(e);
		}
	}

	public ListenerManager() {
		// TODO Auto-generated constructor stub
	}

	// public interface TriggerUpdater {
	//
	// public void addTrigger(Action a, Movement m, Rectangle r, Trigger t);
	//
	//
	// }

	public interface Trigger {

		// public void makeTrigger(Action a, Movement m, Rectangle r);
		public Rectangle getPosition();

		public Direction getDirection();

	}

	private class TriggerInfo {

		public Action action;
		public Movement movement;
		public Direction direction;
		public Rectangle rectangle;
		public String message;
		public Trigger trigger;

		public TriggerInfo(Action a, Movement m, Direction d, Rectangle r,
				String message, Trigger t) {
			// TODO Auto-generated constructor stub
			this.action = a;
			this.movement = m;
			this.direction = d;
			this.rectangle = r;
			this.message = message;
			this.trigger = t;
		}
	}

}
