package listenerControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import customListeners.BSGameListener;
import customListeners.BSMouseListener;
import events.GameEvent;
import events.ShapeListenerManager;
import gameActions.Control;

public class ListenerActivator implements KeyListener, ActionListener, MouseListener, FocusListener, BSGameListener {

	private Control game;
	
	public ListenerActivator(Control control) {
		// TODO Auto-generated constructor stub
		game = control;
		control.addKeyListener(this);
		control.addMouseListener(this);
		control.addFocusListener(this);
		ShapeListenerManager.addListener(this);
	}

	@Override
	public void scored(GameEvent g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void death(GameEvent g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (BSMouseListener m : ListenerIndex.mouseListeners) {
			m.clicked(e);
		}
		game.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (BSMouseListener m : ListenerIndex.mouseListeners) {
			m.pressed(e);
		}
		game.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (BSMouseListener m : ListenerIndex.mouseListeners) {
			m.released(e);
		}
		game.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (BSMouseListener m : ListenerIndex.mouseListeners) {
			m.enters(e);
		}
		game.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (BSMouseListener m : ListenerIndex.mouseListeners) {
			m.exits(e);
		}
		game.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
