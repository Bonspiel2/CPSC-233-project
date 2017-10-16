package ca.ucalgary.main;

import java.util.Random;



/**
 * 
 * @author Matt
 *
 */
public class Enemy {

	private int x;
	private int y;

	private static int counter;

	private boolean alive;

	private String symbol = "V";


	Enemy() {
		this.x = new Random().nextInt(TextGame.COLUMNS - 1);
		this.y = 0;
		this.alive = true;

	}
	
	/**
	 * 
	 * @param y y coordinate of the enemy 
	 */
	Enemy(int y) {
		this.x = new Random().nextInt(TextGame.COLUMNS - 1);
		this.y = y;
		this.alive = true;
	}

	/**
	 * 
	 * @return	the enemy's alive value 
	 */
	public boolean move() {

		y++;

		if (y == TextGame.ROWS - 1) {
			alive = false;
		}

		return alive;
	}
	
	/**
	 * 
	 * @param player	the player object which collision is checked for
	 * @return		true if it has collided, false if it has not
	 */
	public boolean collidedWith(Player player) {
		boolean collided = false;

		if (x == player.getX() && y == player.getY()) {
			collided = true;
			alive = false;
		}

		return collided;
	}
	
	/**	Draws the enemy on the board at its current x and y coordinates
	 * @param board	The board on which the enemy is drawn.
	 */
	public void draw(String[][] board) {

		if (this.alive) {
			board[y][x] = symbol;
		}
	}

	public int getX() {
		return x;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


}
