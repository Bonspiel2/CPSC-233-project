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
	
    /**
     * The x coordinate of the projectile (columns)
     */
	protected int x;
    
	public static final int HEIGHT = 10;
	public static final int WIDTH = 2;

    /**
     * The y coordinate of the projectile (rows)
     */
	protected int y;
    
    private int velocity;
    
    private boolean collided;
	private boolean edgy;
        
    protected String symbol = "|";
	
        /**
     * Main constructor
     * @param newX new projectile's column value
     * @param newY new projectile's row value
     */
    public Projectile(int x, int y) {
		this.x = x;
		this.y = y;
        collided = false;
		
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
        this.velocity = vel;
        collided = false;
        
    }

    /**
     * Abstract method that dictates how projectile moves
     */
    
    public abstract boolean move();
    
    /**
     * Draws the projectile.
     * @param board the current gameboard.
     */
    public void draw(String[][] board) {
		board[y][x] = "|";
	}
    /**
     * Draws the projectile for the GUI Game.
     * @param board the current GUI gameboard.
     */
//    public void draw(Board board) {
//        board.draw(symbol, x, y);
//    }
    
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
    
	public void draw(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
    /**
     * Sets the projectile's y (row) coordinate to a given value.
     * @param y the new y coordinate.
     */
	public void setY(int y) {
		this.y = y;
	}

}


