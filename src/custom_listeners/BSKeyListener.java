package custom_listeners;

import java.awt.event.KeyEvent;

public interface BSKeyListener extends SuperListener {
	
	public void keyTyped(KeyEvent e);
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
}
