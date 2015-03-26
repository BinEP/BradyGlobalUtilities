package events;

import java.util.ArrayList;
import java.util.List;

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

	public enum State {
		death, creation
	}

	public enum Reaction {
		bounce, teleport//, overlap;
	}
	
	public enum Movement {
		move, enter, exit, none;
		
		public enum Direction {
			right, left, up, down, none;
		}
	}

	public List<ActionSender> onDeathSenders = new ArrayList<ActionSender>();
	public List<ActionSender> onCreateSenders = new ArrayList<ActionSender>();

	public List<StateListener> stateListeners = new ArrayList<StateListener>();
	
	
	public List<ActionSender> onBounceSenders = new ArrayList<ActionSender>();
	public List<ActionSender> onTeleportSenders = new ArrayList<ActionSender>();

	public List<ReactionListener> reactionListeners = new ArrayList<ReactionListener>();
	
	
	public List<ActionSender> movementSenders = new ArrayList<ActionSender>();

	public List<ReactionListener> movementListeners = new ArrayList<ReactionListener>();
	
	
	
	

	public ListenerManager() {
		// TODO Auto-generated constructor stub
	}

}
