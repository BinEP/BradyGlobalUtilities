package game_actions;

import java.awt.event.KeyEvent;

public interface KeyActions {
	/**
	 * Override this when you want to do something specific with keys or whatever when pressed
	 * @param e
	 */
	public void customPressed(KeyEvent e);

	/**
	 * Override this when you want to do something specific with keys or whatever when released
	 * @param e
	 */
	public void customReleased(KeyEvent e);
}
