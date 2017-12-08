package ca.ucalgary.collectable;

import java.awt.Color;
import java.awt.Graphics;

import ca.ucalgary.interfaces.Collidable;

/**
 * This Collectable class allows the game to interact with 
 * the position of collectables spawned within the enemy 
 * class. 
 * The class updates the collectable's position and checks 
 * if it is still within the range of the game board.
 * 
 * @author lilypollreis
 *
 */
public abstract class Collectable implements Collidable{

	private static final int TEXT_DIMENSION = 0;
	
	public static final int DEFAULT_WIDTH = 7;
	public static final int DEFAULT_HEIGHT = 10;
	
	private int x;
	private int y;
	private int maxY;
	private int maxX;
    
    private int width;
    private int height;
	
    /**
     * Collectable constructor for the TextBased version of the game, initializes all variables that are 
	 * needed when a collectable is created
	 * @param x, x coordinate of the collectable
	 * @param y, y coordinate of the collectable
	 * @param maxY, the farthest down a collectable can travel before being removed
     */
	public Collectable(int x, int y, int maxX, int maxY) {
		this.x = Math.min(maxX - 1, Math.max(0, x));
		this.y = Math.min(maxY - 1,Math.max(0, y));
		
		this.width = TEXT_DIMENSION;
		this.height = TEXT_DIMENSION;
		
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	/**
	 * Collectable constructor for the GUI version of the game, initializes all variables that are 
	 * needed when a collectable is created
	 * @param x, x coordinate of the collectable
	 * @param y, y coordinate of the collectable
	 * @param maxY, the farthest down a collectable can travel before being removed
	 * @param width, width of the collectable
	 * @param height, height of the collectable
	 */
	public Collectable(int x, int y, int width, int height, int maxX, int maxY) {
		this.x = Math.min(maxX - 1, Math.max(0, x));
		this.y = Math.min(maxY - 1,Math.max(0, y));
		
		this.width = Math.max(0, width);
		this.height = Math.max(0,height);
		
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	/**
	 * Copy Constructor
	 * @param c Collectable to copy
	 */
	public Collectable(Collectable c) {
		this.x = c.getX();
		this.y = c.getY();
		
		this.width = c.getWidth();
		this.height = c.getHeight();
		
		this.maxX = c.getMaxX();
		this.maxY = c.getMaxY();
	}
	
	/**
	 * Checks whether the collectable and a given collidable have collided
	 * i.e. they have the same x and y values
	 * @param c The collidable object
	 * @return True if it has collided, false if it has not
	 */
	public boolean collidedWith(Collidable c) {
		int cx = c.getX();
		int cy = c.getY();
		int ch = c.getHeight();
		int cw = c.getWidth();
		return (cx + cw >= this.x) && (cy + ch >= this.y) &&
				(cx <= this.x + this.width) && (cy <= this.y + this.height);
	}
	
	
	/**
	 * Gets integer x value of the collectable.
	 * @return x value the collectable.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets integer value of the width of the collectable;
	 * @return width of the collectable
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter for the height of the collectable
	 * @return height of the collectable
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets new integer x value for the collectable.
	 * @param x new column value.
	 */
	public void setX(int x) {
		if (x < maxX && x >= 0) {
			this.x = x;
		} else if (x < 0) {
			this.x = 0;
		} else if (x >= maxX) {
			this.x = maxX - 1;
		}
	}

	/**
	 * Gets integer y value the collectable.
	 * @return integer y value the collectable.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets new integer y value for the collectable.
	 * @param y new row value.
	 */
	public void setY(int y) {
		if (y < maxY && y >= 0) {
			this.y = y;
		} else if (y < 0) {
			this.y = 0;
		} else if (y >= maxY) {
			this.y = maxY - 1;
		}
	}

	/**
	 * Gets max Y value for the collectable
	 * @return maxY maximum y value
	 */
	
	public int getMaxY() {
		return maxY;
	}
	
	/**
	 * Moves collectable down the screen by one row every turn 
	 * until it is no longer within the range of the game board.
	 * @return true if the collectable is still within
	 * the range of the game board.
	 */
	public boolean move() {
		boolean onScreen = true;
		if (y+1 >= maxY) {
			onScreen = false;
		} else {
			y++;
		}
		return onScreen;
	}

	/**
	 * Abstract method indicating subclases must contain a draw method for
     * the TextBased game.
	 * @param board array list that is the game board.
	 */
    public abstract void draw(String[][] board);
    
	/**
	 * Abstract method indicating subclasses must contain a draw method
     * for the GUI game
	 * @param g the graphics object being drawn to
	 */
    public abstract void draw(Graphics g);

	public int getMaxX() {
		return maxX;
	}
}
