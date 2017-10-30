package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



/**
 * The enemy class allows the game to create and interact with
 * enemy position, create EnemyProjectiles and have enemies 
 * drop Collectables when killed.
 * The class moves the enemies down the screen as the game 
 * progresses and checks for collisions with the player
 * 
 * @author Matt
 */
public class Enemy {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private int maxY;

	private boolean alive;
    
    private boolean hasAShot;

	private String symbol = "V";

	/**
	 * Enemy Constructor for the TextBased version of the game, creates an enemy that is alive
	 * has the ability to shoot and initializes its width and height as 0 
	 * @param x x coordinate of the enemy or column in which is resides
	 * @param y y coordinate of the enemy or row in which is resides
	 */
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		
		this.alive = true;
        this.hasAShot = true;
        
        this.maxY = TextGame.ROWS;
	}
	
	
	/**
	 * Enemy constructor for the GUI version of the game, initializes all required variables
	 * @param x x coordinate of the enemy
	 * @param y y coordinate of the enemy
	 * @param width Width of the enemy
	 * @param height Height of the enemy
	 */
	public Enemy(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.alive = true;
        this.hasAShot = true;
        
        this.maxY = GUIGame.SCREEN_HEIGHT;
	}
	
	
	
	/**
	 * Board initializer constructor, used to initialize the
	 * enemies on the board on different rows
	 * @param y y Coordinate of the enemy 
	 */
	public Enemy(int y) {
		this.x = new Random().nextInt(TextGame.COLUMNS - 1);
		this.y = y;
		this.alive = true;
        this.hasAShot = true;

	}

	/**
	 * Moves the enemy down one row towards the players ship and 
	 * if it has reached the edge of the screen returns false
	 * @return	The enemy's alive value to know whether to draw
	 * it or not
	 */
	public boolean move() {

		y++;

		if (y >= maxY) {
			alive = false;
		}

		return alive;
	}
	
	/**
	 * Checks whether the player and enemy have collided i.e. they have 
	 * the same x and y values
	 * @param player The player object which collision is checked 
	 * for with
	 * @return True if it has collided, false if it has not
	 */
	public boolean collidedWith(Player player) {
		boolean collided = false;

		if (x >= player.getX() && x <= player.getX() + player.getWidth() && 
				y >= player.getY() && y <= player.getY() + player.getHeight()) {
			collided = true;
			alive = false;
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
		Collectable collectable;
		if(height != 0) {
			collectable = new Collectable(x,y,maxY,7,10);
		}
		else {
			collectable = new Collectable(x,y,maxY);
		}
		return collectable;
	}
	
	/**	Draws the enemy on the board at its current x and y coordinates
	 * @param board	The text board on which the enemy is drawn.
	 */
	public void draw(String[][] board) {

		if (this.alive) {
			board[y][x] = symbol;
		}
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
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * a getter for hasAShot which indicates whether the enemy has shot
	 * @return hasAShot, whether the enemy has shot yet
	 */
    public boolean getHasAShot() {
        return hasAShot;
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
