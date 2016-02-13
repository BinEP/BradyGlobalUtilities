package game_actions;

import java.awt.event.FocusEvent;

public interface FocusActions {

	/**
	 * Gets called when the window regains focus
	 * @param e
	 */
	public void gotFocus(FocusEvent e);
	
	/**
	 * Gets called when the window loses focus
	 * @param e
	 */
	public void lostFocus(FocusEvent e);
	
}
