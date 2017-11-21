package ca.ucalgary.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import ca.ucalgary.game.GUIGame;
import ca.ucalgary.game.TextGame;
import ca.ucalgary.interfaces.Collidable;

/**
 *
 * The abstract Projectile class controls the behavior of all projectiles in the game
 * through drawing projectiles as well as detecting whether or not a projectile 
 * has collided with an enemy or reached the end of the board.
 *
 * @author Quinn
 *
 */

public abstract class Projectile implements Collidable {
	
	private int x;
	private int y;
	
	private int maxY;
	private int maxX;
    
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
    	maxY = TextGame.ROWS-1;
    	maxX = TextGame.COLUMNS-1;
    	setX(x);
    	setY(y);		
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
    	maxX = GUIGame.SCREEN_WIDTH-1;
    	maxY = GUIGame.SCREEN_HEIGHT-1;
    	setX(x);
    	setY(y);
        this.height = 10;
        this.width = 2;
        
        if(vel >= 0) {
        	this.velocity = vel;
        } else {
        	this.velocity = 1;
        }
        
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
    	if( (x >= 0 && x <= maxX) && (y >= 0 && y <= maxY) ) {
    		board[y][x] = "|";
    	}
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
     * Checks if the projectile and a given collidable have collided
     * @param c Potential enemy collision
     * @return true if the two objects have collided
     */
    public boolean collidedWith(Collidable c) {
      
        boolean collided = false;
    	
    	if (x + width >= c.getX() && x <= c.getX() + c.getWidth() && 
				(y + height >= c.getY() || y + height == c.getY()-1) && y <= c.getY() + c.getHeight()) {
			collided = true;
		}

        return collided;
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
		if(x > maxX) {
			this.x = maxX;
		} else if(x <= 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
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
		if(y > maxY) {
			this.y = maxY;
		} else if(y <= 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}
	/**
	 * Gets the projectile's maxY value
	 * @return maxY the projectile's maximum Y
	 */
	public int getMaxY() {
		return maxY;
	}
	/**
	 * Gets the projectile's maxX value
	 * @return maxX the projectile's maximum X
	 */
	public int getMaxX() {
		return maxX;
	}
}


