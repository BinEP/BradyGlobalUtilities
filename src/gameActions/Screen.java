package gameActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.peer.MouseInfoPeer;

import customListeners.BSGameListener;

public interface Screen extends KeyListener, ActionListener, MouseListener, FocusListener, BSGameListener {

	
//	public void moves();
//	public void reset();
//	public void draw();
//	
	public void up();
	public void down();
	public void left();
	public void right();
	
	public void upReleased();
	public void downReleased();
	public void leftReleased();
	public void rightReleased();

	
	
}
