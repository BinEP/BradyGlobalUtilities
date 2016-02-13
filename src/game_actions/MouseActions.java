package game_actions;

import java.awt.event.MouseEvent;

public interface MouseActions {
	
	/**
	 * When the mouse is clicked, this gets called
	 * @param e
	 */
	public void clicked(MouseEvent e);
	
	/**
	 * When the mouse is pressed, this gets called
	 * @param e
	 */
	public void pressed(MouseEvent e);
	
	/**
	 * When the mouse is released, this gets called
	 * @param e
	 */
	public void released(MouseEvent e);
	
	/**
	 * When the mouse enters the window, this gets called
	 * @param e
	 */
	public void enters(MouseEvent e);
	
	/**
	 * When the mouse exits the window, this gets called
	 * @param e
	 */
	public void exits(MouseEvent e);
	
}
