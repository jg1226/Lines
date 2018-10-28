import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lines extends JPanel implements KeyListener, ActionListener{
	// variables
	private boolean play = false;
	private int score = 0;
	// player variables
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int playerPosX = 350;
	private int playerPosY = 300;
	private int playerXDir = 2;
	private int playerYDir = 2;
	// vertical lines
	private int[] positionY = new int[87];
	private int[] positionX = new int[87];
	//horizontal lines
	private int[] positiony = new int[71];
	private int[] positionx = new int[71];
	// starts moving the lines
	private boolean start = false;
	// keys to selecting lines
	private int pos = 5;
	private int pos2 = 5;
	// timer
	private Timer time;
	private int delay = 8;

	public Lines() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
		for(int i = 0; i < positionY.length; i++) {// Initializes vertical lines
			positionY[i] = 0;
			if(i == 0) {
				positionX[i] = 3;
			}
			else {
				positionX[i] = 8 + positionX[i-1];
			}
		}

		for(int i = 0; i < positiony.length; i++) {// Initializes horizontal lines
			positionx[i] = 0;
			if(i == 0) {
				positiony[i] = 3;
			}
			else {
				positiony[i] = 8 + positiony[i-1];
			}
		}

	}

	public void paint(Graphics g) {

		// background
		g.setColor(Color.pink);
		g.fillRect(1, 1, 692, 570);// background fits screen size

		// borders
		g.setColor(Color.red);
		g.fillRect(0, 0, 3, 592);// left border
		g.fillRect(0, 0, 692, 3);// top border
		g.fillRect(691, 0, 3, 592);// right border
		g.fillRect(0, 568, 692, 4);// bottom border

		g.setColor(Color.white);
		for(int i = 0; i < positionX.length; i++) {// creates vertical lines
			g.fillRect(positionX[i], 0, 8, positionY[i]);
		}
		for(int i = 0; i < positionx.length; i++) {// creates horizontal lines
			g.fillRect(0, positiony[i], positionx[i], 8);
		}

		// Player
		g.setColor(Color.blue);
		g.fillOval(playerPosX, playerPosY, 8, 8);

		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		score++;
		start = true;
		if(play) {
			// keep movin
			if(right) {
				playerPosX += playerXDir;
			}
			if(left) {
				playerPosX -= playerXDir;
			}
			if(up) {
				playerPosY -= playerYDir;
			}
			if(down) {
				playerPosY += playerYDir;
			}

			if(start) {// moves the vertical bars
				Random rand = new Random();
				if(positionY[pos] != 592) {
					positionY[pos] += 6;
				}
				if(positionY[pos] >= 592) {
					pos = rand.nextInt(86);
				}
			}

			if(start) {// moves the horizontal bars
				Random rand2 = new Random();
				if(positionx[pos2] != 692) {
					positionx[pos2] += 6;
				}
				if(positionx[pos2] >= 692) {
					pos2 = rand2.nextInt(71);
				}
			}

			// border collision
			if(playerPosX < 3) {// left border game over
				gameOver();
			}
			if(playerPosY < 3) {// top border game over
				gameOver();
			}
			if(playerPosX > 683) {// right border game over
				gameOver();
			}
			if(playerPosY > 560) {// bot border game over
				gameOver();
			}

			// line collision
			for(int i = 0; i < positionX.length; i++) {// creates the hit collision for vertical the lines
				if(new Rectangle(positionX[i], 0, 8, positionY[i]).intersects(new Rectangle(playerPosX, playerPosY, 8, 8))) {
					gameOver();
				}
			}

			for(int i = 0; i < positionx.length; i++) {// creates the hit collision for horizontal lines
				if(new Rectangle(0, positiony[i], positionx[i], 8).intersects(new Rectangle(playerPosX, playerPosY, 8, 8))) {
					gameOver();
				}
			}

		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {// right button
			if(left) {
				left = true;
			}
			else {
				right = true;
				up = false;
				down = false;
				left = false;
				moveRight();
			}
		}

		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {// left button
			if(right) {
				right = true;
			}
			else {
				right = false;
				up = false;
				down = false;
				left = true;
				moveLeft();
			}
		}

		if(arg0.getKeyCode() == KeyEvent.VK_UP) {// up button
			if(down) {
				down = true;
			}
			else {
				right = false;
				up = true;
				down = false;
				left = false;
				moveUp();
			}
		}

		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {// down button
			if(up) {
				up = true;
			}
			else {
				right = false;
				up = false;
				down = true;
				left = false;
				moveDown();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	// move methods
	public void moveRight() {
		play = true;
		playerPosX += 1;

	}
	public void moveLeft() {
		play = true;
		playerPosX -= 1;
	}
	public void moveDown() {
		play = true;
		playerPosY += 1;
	}
	public void moveUp() {
		play = true;
		playerPosY -= 1;
	}
	// game over method
	public void gameOver() {
		playerXDir = 0;
		playerYDir = 0;
		JOptionPane.showMessageDialog(null, "Game Over");
		JOptionPane.showMessageDialog(null, "Your score was: " + score);
		
		System.exit(0);
	}

}
