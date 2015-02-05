package gameActions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import utilityClasses.*;

public class Control extends JPanel implements Screen {

	public boolean startGame = true;
	public boolean playing = false;
	public boolean paused = false;
	public boolean endGame = false;
	public boolean nameEnter = false;
	public boolean highScores = false;
	
	
	public int upKey = KeyEvent.VK_UP;
	public int downKey = KeyEvent.VK_DOWN;
	public int leftKey = KeyEvent.VK_LEFT;
	public int rightKey = KeyEvent.VK_RIGHT;
	
	public int[] keyMap = { KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
			KeyEvent.VK_DOWN, KeyEvent.VK_LEFT };

	public int keyIndex = 0;
	
	public int movementVar = 10;
	public int deltaX = movementVar;
	public int deltaY = 0;
	public String pName;
	
	public int playerX;
	public int playerY;
	
	public Timer timer;
	public int origSpeed = movementVar;
	public double speed = origSpeed;
	
	public int score;
	public Character letter;
	
	public UserGame sub = (UserGame) this;

	public Control() {
		
		
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		
		sub.setup();

		timer = new Timer((int) (1000 / speed), this);
//		reset();
		timer.start();

	
	}
	
	public void setBackgroundColor(Color c) {
		
		this.setBackground(c);
		
		
	}
	
	public void setKeys() {

		upKey = keyMap[0];
		rightKey = keyMap[1];
		downKey = keyMap[2];
		leftKey = keyMap[3];

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (startGame && e.getKeyCode() != KeyEvent.VK_ENTER) {

			keyMap[keyIndex] = e.getKeyCode();
			keyIndex++;
			if (keyIndex > 3)
				keyIndex = 0;

		} else if (e.getKeyCode() == upKey) {

			up();

		} else if (e.getKeyCode() == downKey) {

			down();

		} else if (e.getKeyCode() == leftKey) {

			left();

		} else if (e.getKeyCode() == rightKey) {

			right();

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (startGame) {

				playing = true;
				startGame = false;
				setKeys();

			} else if (endGame) {

				sub.reset();
				startGame = false;
				playing = true;
				nameEnter = false;
				highScores = false;
				endGame = false;
				pName = "";
				speed = 10;
				score = 0;

			} else if (nameEnter) {
				nameEnter = false;
				highScores = true;
				ScoreInfo.setScores(score, pName, Window.FOLDER_PATH + Window.GAME_NAME);
			} else if (highScores) {

				highScores = false;
				endGame = true;
			} else {
				startGame = false;
				playing = true;

			}

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && !nameEnter) {

			playing = !playing;
			paused = !paused;
		} else if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_STANDARD
				&& nameEnter) {

			if (pName.length() < 10) {
				letter = e.getKeyChar();

				letter = Character.toUpperCase(letter);
				pName = pName.concat(letter.toString());
			}

		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		sub.draw(g);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sub.moves();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void up() {
		// TODO Auto-generated method stub
		if (deltaY != movementVar) {
			deltaX = 0;
			deltaY = -movementVar;
		}
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		if (deltaY != -movementVar) {
			deltaX = 0;
			deltaY = movementVar;
		}
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		if (deltaX != movementVar) {
			deltaX = -movementVar;
			deltaY = 0;
		}
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		if (deltaX != -movementVar) {
			deltaX = movementVar;
			deltaY = 0;
		}
	}

}