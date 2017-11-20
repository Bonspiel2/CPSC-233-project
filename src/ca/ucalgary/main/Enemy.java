package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 * The enemy class allows the game to create and interact with
 * enemy position, create EnemyProjectiles and have enemies 
 * drop Collectables when killed.
 * The class moves the enemies down the screen as the game 
 * progresses and checks for collisions with the player
 * 
 * @author Matt
 */
public class Enemy implements Collidable{

	private int x;
	private int y;
	private int width;
	private int height;

	private int maxY;

	private boolean hasAShot;

	private String symbol = "V";

	private BufferedImage enemyImg;

	/**
	 * Enemy Constructor for the TextBased version of the game, calls other constructor
	 * to create an enemy that is alive has the ability to shoot, has a width and height of 0,
	 * and has a max y value of the number of rows in the text game.
	 * @param x x coordinate of the enemy or column in which is resides
	 * @param y y coordinate of the enemy or row in which is resides
	 */
	public Enemy(int x, int y) {
		this(x,y,0,0,TextGame.ROWS);
	}

	/**
	 * Enemy constructor for the GUI version of the game, calss other constructor
	 * to create an enemy with the given x, y, width and height as well as the height
	 * of the GUI screen
	 * @param x x coordinate of the enemy
	 * @param y y coordinate of the enemy
	 * @param width Width of the enemy
	 * @param height Height of the enemy
	 */
	public Enemy(int x, int y, int width, int height) {
		this(x,y,width,height,GUIGame.SCREEN_HEIGHT);
	}


	/**
	 * Main enemy constructor, initializes all required variables
	 * @param x x coordinate of the enemy
	 * @param y y coordinate of the enemy
	 * @param width Width of the enemy
	 * @param height Height of the enemy
	 * @param maxY Last y value before the enemy exits the screen
	 */
	private Enemy(int x, int y, int width, int height, int maxY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.hasAShot = true;

		this.maxY = maxY;

		try {
			enemyImg = ImageIO.read(new File("src/ca/ucalgary/main/EnemyShip.png"));
		} catch (IOException e) {
			System.out.println("Could not load enemy image.");
		}

	}

	/**
	 * Copy constructor
	 * @param e Enemy to copy
	 */
	public Enemy(Enemy e) {
		this(e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getMaxY());
	}


	/**
	 * Board initializer constructor, used to initialize the
	 * enemies on the board on different rows
	 * @param y y Coordinate of the enemy 
	 */
	public Enemy(int y) {
		this.x = new Random().nextInt(TextGame.COLUMNS - 1);
		this.y = y;
		this.hasAShot = true;

	}

	/**
	 * Moves the enemy down one row towards the players ship and 
	 * if it has reached the edge of the screen returns false
	 * @return	The enemy's alive value to know whether to draw
	 * it or not
	 */
	public boolean move() {
		boolean alive = true;
		y++;

		if (y >= maxY) {
			alive = false;
		}

		return alive;
	}

	/**
	 * Checks whether the player and enemy have collided i.e. they have 
	 * the same x and y values
	 * @param c The player object which collision is checked 
	 * for with
	 * @return True if it has collided, false if it has not
	 */
	public boolean collidedWith(Collidable c) {
		boolean collided = false;

		if (x + width >= c.getX() && x <= c.getX() + c.getWidth() && 
				y + height >= c.getY() && y <= c.getY() + c.getHeight()) {
			collided = true;
		}

		return collided;
	}

	/**
	 * Creates a collectable when the enemy is killed, at the enemies current location
	 * depending on whether it is a Gui instance or a textgame instance a different 
	 * Collectable constructor is called
	 * @return collectable object that was created
	 */
	public Collectable createCollectable() {
		int decider = new Random().nextInt(6);
		Collectable collectable;
		if(height != 0) {
			if (decider == 2) {
				collectable = new HealthCollectable(x,y,maxY,7,10);
			} else {
				collectable = new Money(x,y,maxY, 7, 10);
			}
		}
		else {
			if (decider == 2) {
				collectable = new HealthCollectable(x,y,maxY);
			} else {
				collectable = new Money(x,y,maxY);
			}
		}
		return collectable;
	}

	/**	Draws the enemy on the board at its current x and y coordinates
	 * @param board	The text board on which the enemy is drawn.
	 */
	public void draw(String[][] board) {
		board[y][x] = new String(symbol);
	}

	/** Gets the current column/ x value
	 * @return x Current column
	 */
	public int getX() {
		return x;
	}

	/** Sets the enemy's current column/ x value
	 * @param x New x value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets enemy's y value/ row
	 * @return y The enemy's y value/ row
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets enemy's y value/ row
	 * @param y New y value
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Draws a red square as the enemy at its current x,y,width and height
	 * @param g the graphics object being drawn to
	 */
	public void draw(Graphics g) {
		if (enemyImg != null) {
			g.drawImage(enemyImg, x, y, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
	}

	/**
	 * a getter for hasAShot which indicates whether the enemy has shot
	 * @return hasAShot, whether the enemy has shot yet
	 */
	public boolean getHasAShot() {
		return hasAShot;
	}

	public void setHasAShot(boolean shot) {
		this.hasAShot = shot;
	}

	/**
	 * Creates a new EnemyProjectile object at the enemy's location
	 * @return shot, Projectile that was created
	 */
	public EnemyProjectile shoot() {
		EnemyProjectile shot;
		if(width != 0) {
			shot = new EnemyProjectile(x + width / 2, y + 1 + height, 2);
		} else {
			shot = new EnemyProjectile(x + width / 2, y + 1 + height);
		}
		hasAShot = false;
		return shot;
	}

	/**
	 * getter for enemy width
	 * @return width of the enemy
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * getter for enemy maxY
	 * @return the maxY, or how far down the enemy can go before being removed
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * setter for enemy width
	 * @param width width of the enemy
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * getter for the height of the enemy
	 * @return height of the enemy
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * setter for the height of the enemy
	 * @param height of the enemy
	 */
	public void setHeight(int height) {
		this.height = height;
	}



}
