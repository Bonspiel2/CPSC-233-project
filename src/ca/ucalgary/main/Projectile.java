package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * The abstract Projectile class controls the behavior of all projectiles in the game
 * through drawing projectiles as well as detecting whether or not a projectile 
 * has collided with an enemy or reached the end of the board.
 *
 * @author Quinn
 *
 */

public abstract class Projectile {
	
	private int x;
	private int y;
    
	private int width;
	private int height;
    
    private int velocity;

        
    protected String symbol = "|";
	
    /**
     * Main constructor for text game. Defaults projectile's velocity to 1
     * @param newX new projectile's column value
     * @param newY new projectile's row value
     */
    public Projectile(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		this.velocity = 1;
	}
    
    /**
     * Main constructor for GUI Game
     * @param newX new projectile's column value
     * @param newY new projectile's row value
     * @param newVelocity new projectile's velocity
     */
    public Projectile(int x, int y, int vel){
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 2;
        this.velocity = vel;
        
    }

    /**
     * Abstract method that dictates how projectile moves
     */
    
    public abstract boolean move();
    
    /**
     * Draws the projectile on a string array.
     * @param board the current gameboard.
     */
    public void draw(String[][] board) {
		board[y][x] = "|";
	}
    
	/**
     * Draws the projectile for the GUI Game.
     * @param g Graphics object that the projectile is drawn to.
     */
    
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
    
    /**
     * Gets the projectile's current x (column) coordinate.
     * @return x the current x coordinate.
     */
	public int getX() {
		return x;
	}
    
    /**
     * Sets the projectile's x (column) coordinate to a given value.
     * @param x the new x coordinate.
     */
	public void setX(int x) {
		this.x = x;
	}
    
    /**
     * Gets the projectile's current y (row) coordinate.
     * @return y the current y coordinate.
     */
	public int getY() {
		return y;
	}
	

	/**
	 * Gets the projectile's width.
	 * @return width the projectile's width value
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the projectile's height.
	 * @return height the projectile's height value
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the projectile's velocity.
	 * @return velocity the projectile's velocity value
	 */
	public int getVelocity() {
		return velocity;
	}
    /**
     * Sets the projectile's y (row) coordinate to a given value.
     * @param y the new y coordinate.
     */
	public void setY(int y) {
		this.y = y;
	}

}


