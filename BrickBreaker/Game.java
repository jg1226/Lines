package BrickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer time;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		g.dispose();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		time.start();

		if(play) {// if play is true then ball hit detection on borders is on
			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {// left
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {// top 
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {// right
				ballXdir = -ballXdir;
			}
				
		}
		repaint();// repaint needs to be after the ball thing
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 590) {// makes sure it stays inside the panel
				playerX = 590;
			}
			else {
				moveRight();
			}
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 5) {// makes sure it stays inside the panel
				playerX = 5;
			}
			else {
				moveLeft();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
	}
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}
	
}// end of class
