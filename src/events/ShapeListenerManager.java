package events;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shapes.BSRectangle;
import custom_listeners.BSGameListener;
import events.ShapeListenerManager.Movement.Direction;

public class ShapeListenerManager {

	public enum Action {
		score, death;
	}

	public enum Movement {
		enter, exit, intersect, none;

		public enum Direction {
			right, left, up, down, any, none;
		}
	}

	private static ShapeListenerManager lm = new ShapeListenerManager();
	public static List<TriggerInfo> triggers = new ArrayList<TriggerInfo>();
	public static List<BSGameListener> listeners = new ArrayList<BSGameListener>();

	private static ListenerThread t = new ListenerThread();
	
	public static void addTrigger(Action a, Movement m, Direction d,
			Rectangle r, String message, Trigger t) {
		triggers.add(lm.new TriggerInfo(a, m, d, r, message, t));
	}

	public static void addTrigger(Action a, Movement m, Rectangle r,
			String message, Trigger t) {
		addTrigger(a, m, Direction.any, r, message, t);
	}

	public static void removeTrigger(Trigger t) {

		for (Iterator<TriggerInfo> iterator = triggers.iterator(); iterator
				.hasNext();) {
			TriggerInfo ti = iterator.next();
			if (ti.trigger.equals(t))
				triggers.remove(ti);
		}
	}

	public static void addListener(BSGameListener g) {
		listeners.add(g);
	}

	public static void removeListener(BSGameListener g) {
		listeners.remove(g);
	}

	public static void triggerTheStuff() {

		for (TriggerInfo ti : triggers) {
			Trigger t = ti.trigger;

			boolean entered = t.getPosition().contains(ti.rectangle);
			
			if ((ti.movement == Movement.enter && entered) 
			 || (ti.movement == Movement.exit && !entered))

				if (ti.direction == Direction.any
						|| ti.direction == t.getDirection())
					sendEvent(ti);
		}
	}

	public static void sendEvent(TriggerInfo ti) {

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

	public static void sendScore(GameEvent e) {
		for (BSGameListener l : listeners) {
			l.scored(e);
		}
	}

	public static void sendDeath(GameEvent e) {
		for (BSGameListener l : listeners) {
			l.death(e);
		}
	}
	
	public static void startThread() {
		t.startThread();
	}
	
	public static void stopThread() {
		t.stopThread();
	}

	public interface Trigger {
		public BSRectangle getPosition();
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
			this.action = a;
			this.movement = m;
			this.direction = d;
			this.rectangle = r;
			this.message = message;
			this.trigger = t;
		}
	}

	private static class ListenerThread implements Runnable {
		
		public static boolean running = false;
		private Thread t;
		
		public void startThread() {
			
			if (running) {
				System.out.println("Already Running");
				return;
			}
			System.out.println("Starting Thread");
			
			running = true;
			if (t != null) 
				t.interrupt();
			
			t = new Thread(this);
			t.start();
		}

		public void stopThread() {
			System.out.println("Stopping Thread");
			running = false;
		}
		
		@Override
		public void run() {
			while (running) {
				triggerTheStuff();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				 System.out.println("ListenerThread");
			}
		}
	}
}
