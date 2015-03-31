package listenerControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import customListeners.BSDirectionKeyListener;
import customListeners.BSFocusListener;
import customListeners.BSGameListener;
import customListeners.BSKeyListener;
import customListeners.BSMouseListener;
import events.GameEvent;
import events.ShapeListenerManager;
import gameActions.Control;
import gameState.BooleanManager;
import gameState.GameTime;

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
		for (BSGameListener gl : ListenerIndex.gameListeners) {
			gl.scored(g);
		}
	}

	@Override
	public void death(GameEvent g) {
		for (BSGameListener gl : ListenerIndex.gameListeners) {
			gl.death(g);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {

		if (BooleanManager.isResumeOnFocus() && BooleanManager.ifPlaying()) {
			GameTime.startTime();
			BooleanManager.resume();
		}

		for (BSFocusListener f : ListenerIndex.focusListeners)
			f.gotFocus(e);

		game.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (BooleanManager.ifPlaying()) {
			GameTime.stopTime();
			BooleanManager.pause();
		}

		for (BSFocusListener f : ListenerIndex.focusListeners)
			f.lostFocus(e);

		game.repaint();
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
		for (BSKeyListener k : ListenerIndex.keyListeners) {
			k.keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		for (BSKeyListener k : ListenerIndex.keyListeners) {
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
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.right();
		}
	}

	public void left() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.left();
		}
	}

	public void down() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.down();
		}
	}

	public void up() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.up();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (BSKeyListener k : ListenerIndex.keyListeners) {
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
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.leftReleased();
		}
	}

	public void downReleased() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.leftReleased();
		}
	}

	public void leftReleased() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.leftReleased();
		}
	}

	public void rightReleased() {
		for (BSDirectionKeyListener d : ListenerIndex.directionKeyListeners) {
			d.leftReleased();
		}
	}
}
