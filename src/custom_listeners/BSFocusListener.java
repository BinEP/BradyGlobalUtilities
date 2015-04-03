package custom_listeners;

import java.awt.event.FocusEvent;

public interface BSFocusListener extends SuperListener {

	public void gotFocus(FocusEvent e);
	public void lostFocus(FocusEvent e);
}
