package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;

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

	private int x;
	private int y;
	private int maxY;
    private String symbol = "$";
    
    private int width;
    private int height;
	
    /**
     * Collectable constructor for the TextBased version of the game, initializes all variables that are 
	 * needed when a collectable is created
	 * @param x, x coordinate of the collectable
	 * @param y, y coordinate of the collectable
	 * @param maxY, the farthest down a collectable can travel before being removed
     */
	Collectable(int x, int y, int maxY) {
		this.x = x;
		this.y = y;
		this.maxY = maxY;
		this.width = 0;
		this.height = 0;
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
	Collectable(int x, int y, int maxY, int width, int height) {
		this.x = x;
		this.y = y;
		this.maxY = maxY;
		this.width = width;
		this.height = height;
	}
	
	
	public boolean collidedWith(Collidable c) {
		int cx = c.getX();
		int cy = c.getY();
		int ch = c.getHeight();
		int cw = c.getWidth();
		return (cx + cw >= this.x) && (cy + ch >= this.y) &&
				(cx <= this.x + this.width) && (cy <= this.y + this.height);
	}
	
	
	/**
	 * Gets integer column value the collectable.
	 * @return integer column value the collectable.
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
	 * Sets new integer column value for the collectable.
	 * @param x new column value.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets integer row value the collectable.
	 * @return integer row value the collectable.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets new integer row value for the collectable.
	 * @param y new row value.
	 */
	public void setY(int y) {
		this.y = y;
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
	 * 
	 * @param board array list that is the game board.
	 * @return true if the collectable is still within
	 * the range of the game board.
	 */
	public boolean move() {
		boolean onScreen = true;
		if (y >= maxY-1) {
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
}
