package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener{

	private boolean active = false;
	private Timer timer;
	private int delay = 5;
	
	
	// temporary - in next version array of blocks
	private boolean isHit = false;
	
	//Starting positions
	private int paddleX = 250;
	private int ballX = 290;
	private int ballY = 350;
	private int ballSpeedX = 0;
	private int ballSpeedY = -3;
	
	public Game () {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	

	
	public void paint(Graphics g) {
		//Coloring background
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 500);
		//Coloring ball
		g.setColor(Color.red);
		g.fillOval(ballX, ballY, 20, 20);
		//Coloring paddle
		g.setColor(Color.black);
		g.fillRect(paddleX, 450, 100, 10);
		//Coloring block if its not hit
		if(!isHit) {
			g.setColor(Color.cyan);
			g.fillRect(275, 100, 50, 20);
			
		}
		
		
		g.dispose();
	}
	
	public void moveLeft() {
		if (paddleX < 10) {
			paddleX = 0;
		} else {
			paddleX -= 10;
		}
	}
	
	public void moveRight() {
		if (paddleX >= 490) {
			paddleX = 490;
		} else {
			paddleX += 10;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if (active) {
			
			//Checking if ball is hitting the paddle 
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(paddleX, 450, 100, 10))) {
				ballSpeedY = -ballSpeedY; 
			}
			
			//Checking if ball is hitting the block
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(275, 100, 50, 20)) && !isHit) {
				isHit = true;
				if(ballX >= 275 || ballX < 325) {
					ballSpeedY = -ballSpeedY;
					
				} else {
					ballSpeedX = -ballSpeedX;
				
				}
			}
			//Moving ball
			ballX += ballSpeedX;
			ballY += ballSpeedY;
			
			//Checking if ball is not at screen borders
			if(ballX < 0) {
				ballSpeedX = -ballSpeedX;
			
			}
		
			if(ballY < 0) {
				ballSpeedY = -ballSpeedY;
			
			}	
		
			if(ballX >= 560) {
				ballSpeedX = -ballSpeedX;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Checking if navigation keys are pressed
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			active = true;
			moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			active = true;
			moveRight();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
