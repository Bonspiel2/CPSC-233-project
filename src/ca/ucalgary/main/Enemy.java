package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



/**
 * The enemy class allows the game to create and interact with
 * enemy position
 * The class moves the enemies down the screen as the game 
 * progresses and checks for collisions with the player
 * 
 * @author Matt
 */
public class Enemy {

	private int x;
	private int y;
	
	private int guiX;
	
	private static final int SIZE = 20;

	private boolean alive;
    
    private boolean hasAShot;

	private String symbol = "V";

	/**
	 * Default constructor
	 * Creates an enemy at the top of the screen in a
	 * (psuedo)random column, and sets it to alive
	 */
	Enemy() {
		this.x = new Random().nextInt(TextGame.COLUMNS - 1);
		this.guiX = new Random().nextInt(Game.SCREEN_WIDTH - 1);
		this.y = 0;
		this.alive = true;
        this.hasAShot = true;

	}
	
	/**
	 * Board initializer constructor, used to initialize the
	 * enemies on the board on different rows
	 * @param y y Coordinate of the enemy 
	 */
	Enemy(int y) {
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

		if (y == TextGame.ROWS - 1) {
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

		if (x == player.getX() && y == player.getY()) {
			collided = true;
			alive = false;
		}

		return collided;
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
    
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(guiX, y, SIZE, SIZE);
	}
	
	
    public boolean getHasAShot() {
        return hasAShot;
    }

    
    public EnemyProjectile shoot() {
        EnemyProjectile shot = new EnemyProjectile(x, y+1);
        hasAShot = false;
        return shot;
    }


}
