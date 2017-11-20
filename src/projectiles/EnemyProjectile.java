package projectiles;

import java.awt.Color;
import java.awt.Graphics;

import game.TextGame;

/**
 * EnemyProjectile class allows the game to create enemy projectiles which travel 
 * down the screen with different implementation for GUI or TextGame 
 * instances, where the GUI instance has a velocity parameter
 * @author Matt
 */
public class EnemyProjectile extends Projectile{

	private boolean edgy;
	private String symbol = "*";

	/**
	 * Constructor for the TextGame version
	 * @param x x Coordinate of the enemyProjectile
	 * @param y, y Coordinate of the EnemyProjectile
	 */
	public EnemyProjectile(int x, int y) {
		super(x, y);
	}

	/**
	 * Constructor for the GUI version
	 * @param x, x coordinate of the projectile
	 * @param y, y coordinate of the projectile
	 * @param velocity, velocity of the projectile or how much it moves each 'Tick'
	 */
	public EnemyProjectile(int x, int y, int velocity) {
		super(x, y, velocity);
		
	}
	
	@Override
	/**
	 * Moves the projectile down one row or pixel and detects whether or not it has
	 * reached the edge of the board/ screen.
	 * @return edgy, boolean of whether it is at the edge of the screen or not
	 */
	public boolean move() {
		if (getY() == TextGame.ROWS - 1) {
			edgy = false;
		} else {
			edgy = true;
			setY(getY()+ getVelocity());
		}
		return edgy;
	}
	
	/**
	 * Draws the symbol of the EnemyProjectile on the text board
	 * @param board the 2D array that represents the board
	 */
	@Override
	public void draw(String[][] board) {
		if( (getX() >= 0 && getX() <= getMaxX()) && (getY() >= 0 && getY() <= getMaxY()) ) {
    		board[getY()][getX()] = symbol;
    	}
	}

	/**
	 * Draws the EnemyProjectile as a thin rectangle at its current x and y coordinates
	 * @param g the graphics object which the EnemyProjectile is drawn to
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
