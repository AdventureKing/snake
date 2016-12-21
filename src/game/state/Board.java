package game.state;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Created by brandon.a.snow on 12/21/2016.
 * Modified by fernando.c.garcia on 12/21/2016.
 */
public class Board extends JPanel implements ActionListener {


	
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    
    
    private final static int PIXELSIZE = 25;
    
    private final static int TOTALPIXELS = (WIDTH * HEIGHT) / (PIXELSIZE * PIXELSIZE);
    
    private final static int INITIALJOINTS = 1;
    
    // set game speed, the lower the # the faster the snake
    // Maybe increment this gradually to make the game harder
    private static int speed = 45;
    
    // Check to see game running
    private boolean inGame = true;
    
    private Timer timer;
    
    private Snake snake = new Snake();
    
    // Refactor name later
    private food food = new food();
    
    

    public Board(){
    	
    	addKeyListener(new Keys());
    	setBackground(Color.BLACK);
    	setFocusable(true);
    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	//requestFocus();
    	initializeGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	draw(g);
    }
    
    public void draw(Graphics g) {
    	if(inGame == true) {
    		g.setColor(Color.green);
    		g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE);
    	
	    	for(int i = 0; i < snake.getJoints(); i++){
	    		// Snake's head
	    		if(i == 0) {
	    			g.setColor(Color.RED);
	    			g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
	    		} else {
	    			g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
	    		}
	    	}
	    	// Sync graphics
	    	Toolkit.getDefaultToolkit().sync();
	    }
    	else {
    		// Snake dies, end game
    		endGame(g);
    	}
    }
    
    public void initializeGame() {
    	snake.setJoints(INITIALJOINTS); // the initialize size of the snake
    	
    	// Create the snake
    	for(int i = 0; i < snake.getJoints(); i++) {
    		snake.setSnakeX(WIDTH / 2);
    		snake.setSnakeY(HEIGHT / 2);
    	}
    	
    	// Initial starting direction for snake to move
    	snake.setDirection(Snake.Direction.RIGHT);
    	
    	// Generate first food
    	food.createFood();
    	
    	// set the timer to record game's speed / make the game move
    	timer = new Timer(speed, this);
    	timer.start();
    	
    }
    
    // If our snake is close to the food
    void checkFoodCollisions() {
    	if((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
    			&& (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {
    		System.out.println("Intersection occurs");
    		// Add a joint to our snake
    		snake.setJoints(snake.getJoints() + 1);
    		// Create new food
    		food.createFood();
    	}
    }
    
    // Check collision against self or the board's edge
    void checkCollision() {
    	// If snake collides with its own joint
    	for(int i = snake.getJoints(); i > 0; i--) {
    		if(snake.getSnakeX(0) == snake.getSnakeX(i) && snake.getSnakeY(0) == snake.getSnakeY(i)) {
    			inGame = false;
    		}
    	}
    	
    	// If the snake intersects with the board edges
    	if(snake.getSnakeY(0) >= HEIGHT || snake.getSnakeY(0) < 0) {
    		inGame = false;
    	}
    	if(snake.getSnakeX(0) >= WIDTH || snake.getSnakeX(0) < 0) {
    		inGame = false;
    	}
    	
    	// if game over, stop the timer
    	if(!inGame) {
    		timer.stop();
    	}
    	
    }
    
    public void endGame(Graphics g) {
    	// Game Over msg
    	String message = "Game Over";
    	
    	// Create font
    	Font font = new Font("Times New Roman", Font.BOLD, 14);
    	FontMetrics metrics = getFontMetrics(font);
    	
    	// Set the color of the text and font
    	g.setColor(Color.red);
    	g.setFont(font);
    	
    	// Draw the message to the board
    	g.drawString(message, (WIDTH - metrics.stringWidth(message)) / 2, HEIGHT / 2);
    	System.out.println("Game Ended");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(inGame == true) {
    		checkFoodCollisions();
    		checkCollisions();
    		snake.move();
    		
    		System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
    				+ " " + food.getFood() + ", " + food.getFoodY());
    	}
    	// Repaint
    	repaint();
    }
    
    private class Keys extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();
    		
    		boolean movingRight = snake.isMovingRight();
    		boolean movingLeft = snake.isMovingLeft();
    		boolean movingUp = snake.isMovingUp();
    		boolean movingDown = snake.isMovingDown();
    		
    		if(key == KeyEvent.VK_LEFT && !movingRight) {
    			snake.setMovingLeft(true);
    			snake.setMovingUp(false);
    			snake.setMovingDown(false);
    		}
    		
    		if(key == KeyEvent.VK_RIGHT && !movingLeft) {
    			snake.setMovingRight(true);
    			snake.setMovingUp(false);
    			snake.setMovingDown(false);
    		}
    		
    		if(key == KeyEvent.VK_UP && !movingDown) {
    			snake.setMovingUp(true);
    			snake.setMovingRight(false);
    			snake.setMovingLeft(false);
    		}
    		
    		if(key == KeyEvent.VK_DOWN && !movingUp) {
    			snake.setMovingDown(true);
    			snake.setMovingRight(false);
    			snake.setMovingLeft(false);
    		}
    		
    		if(key == KeyEvent.VK_ENTER && inGame == false) {
    			inGame = true;
    			snake.setMovingDown(false);
    			snake.setMovingUp(false);
    			snake.setMovingLeft(false);
    			snake.setMovingRight(false);
    			
    			initializeGame();
    		}
    		
    	}
    }
    
    private boolean proximity(int a, int b, int closeness) {
    	return Math.abs((long) a - b) <= closeness;
    }
    
    public static int getAllDots() {
    	return TOTALPIXELS;
    }
    
    public static int getDotSize() {
    	return PIXELSIZE;
    }
    
}
