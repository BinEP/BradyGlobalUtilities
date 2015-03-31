package custom_listeners;

import java.awt.event.MouseEvent;

public interface BSMouseListener {

	public void clicked(MouseEvent e);
	public void pressed(MouseEvent e);
	public void released(MouseEvent e);
	public void enters(MouseEvent e);
	public void exits(MouseEvent e);
}
