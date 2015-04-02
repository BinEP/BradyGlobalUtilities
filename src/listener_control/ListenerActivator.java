package listener_control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import custom_listeners.BSActionListener;
import custom_listeners.BSDirectionKeyListener;
import custom_listeners.BSFocusListener;
import custom_listeners.BSGameListener;
import custom_listeners.BSKeyListener;
import custom_listeners.BSMouseListener;
import events.GameEvent;
import game_actions.Control;
import game_state.GameStateManager;

public class ListenerActivator implements KeyListener, ActionListener,
		MouseListener, FocusListener, BSGameListener {

	private Control game;

	public ListenerActivator(Control control) {
		game = control;
		control.addKeyListener(this);
		control.addMouseListener(this);
		control.addFocusListener(this);
		ShapeListenerManager.addListener(this);
	}

	@Override
	public void scored(GameEvent g) {
		for (BSGameListener gl : ListenerManager.gameListeners) {
			gl.scored(g);
		}
	}

	@Override
	public void death(GameEvent g) {
		for (BSGameListener gl : ListenerManager.gameListeners) {
			gl.death(g);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

		if (GameStateManager.isResumeOnFocus() && GameStateManager.ifPlaying()) {
			game.getGameTime().startTime();
			GameStateManager.resume();
		}

		for (BSFocusListener f : ListenerManager.focusListeners)
			f.gotFocus(e);

		game.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (GameStateManager.ifPlaying()) {
			game.getGameTime().stopTime();
			GameStateManager.pause();
		}

		for (BSFocusListener f : ListenerManager.focusListeners)
			f.lostFocus(e);

		game.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (BSMouseListener m : ListenerManager.mouseListeners) {
			m.clicked(e);
		}
		game.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (BSMouseListener m : ListenerManager.mouseListeners) {
			m.pressed(e);
		}
		game.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (BSMouseListener m : ListenerManager.mouseListeners) {
			m.released(e);
		}
		game.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (BSMouseListener m : ListenerManager.mouseListeners) {
			m.enters(e);
		}
		game.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (BSMouseListener m : ListenerManager.mouseListeners) {
			m.exits(e);
		}
		game.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (BSActionListener a : ListenerManager.actionListeners) {
			a.actionPerformed(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (BSKeyListener k : ListenerManager.keyListeners) {
			k.keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		for (BSKeyListener k : ListenerManager.keyListeners) {
			k.keyPressed(e);
		}

		if (e.getKeyCode() == game.upKey)
			up();

		if (e.getKeyCode() == game.downKey)
			down();

		if (e.getKeyCode() == game.leftKey)
			left();

		if (e.getKeyCode() == game.rightKey)
			right();
	}

	public void right() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.right();
		}
	}

	public void left() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.left();
		}
	}

	public void down() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.down();
		}
	}

	public void up() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.up();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (BSKeyListener k : ListenerManager.keyListeners) {
			k.keyReleased(e);
		}

		if (e.getKeyCode() == game.upKey)
			upReleased();

		if (e.getKeyCode() == game.downKey)
			downReleased();

		if (e.getKeyCode() == game.leftKey)
			leftReleased();

		if (e.getKeyCode() == game.rightKey)
			rightReleased();
	}

	public void upReleased() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.upReleased();
		}
	}

	public void downReleased() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.downReleased();
		}
	}

	public void leftReleased() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.leftReleased();
		}
	}

	public void rightReleased() {
		for (BSDirectionKeyListener d : ListenerManager.directionKeyListeners) {
			d.rightReleased();
		}
	}
}
