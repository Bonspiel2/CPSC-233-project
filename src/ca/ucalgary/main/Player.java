package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The player class allows the game to interact with the 
 * player's position, their health as well as their score.
 * The class updates the players position based on input,
 * and checks for collisions with other objects.
 * 
 * @author Cole
 */
public class Player {

	private int x;
	private int y;
	private int width;
	private int height;

	private int step;


	private int maxX; //columns
	private int maxY; //rows

	private int health;
	private int initialHealth;
	private int score;

	private String ship;

	private double firerate;
	private double fireCount;
	private double fireTimer;


	/**
	 * Main constructor for textgame
	 * @param x new player's column value
	 * @param y new player's row value
	 * @param health new player's total health
	 */
	public Player(int x, int y, int health) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;

		this.health = health;
		this.initialHealth = health;
		this.score = 0;

		this.ship = "A";

		firerate = 0.5;
		fireCount = 6;
		fireTimer = fireCount * firerate;
	}

	/**
	 * Main constructor for GUI game
	 * @param x new player's column value
	 * @param y new player's row value
	 * @param width new player's width
	 * @param height new player's height
	 * @param health new player's total health
	 */
	public Player(int x, int y, int width, int height, int health) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.health = health;
		this.initialHealth = health;
		this.score = 0;

		this.ship = "A";

		firerate = 0.5;
		fireCount = 100;
		fireTimer = fireCount * firerate;
	}


	/**
	 * Moves player either left or right while keeping it on the
	 * screen
	 * @param s Either "A" or "D" denoting left or right
	 */
	public void move(String s) {
		if (s.equals("A")) {
			if (x - step >= 0) {
				x-= step;
			}
		} else if (s.equals("D")) {
			if ((x + step) < maxX) {
				x+= step;
			}
		} else if (s.equals("W")) {
			if ((y - step) >= 0) {
				y-= step;
			}
		} else if (s.equals("S")) {
			if ((y + step) < maxY) {
				y+= step;
			}
		}
	}

	/**
	 * Checks to see if the player has collided with a given collectable.
	 * @param c Object to check for collision
	 * @return Returns true if the player and the collectable occupy the
	 * same space
	 */
	public boolean collidedWith(Collectable c) {
		boolean collided = false;
		int cx = c.getX();
		int cy = c.getY();
		int cw = c.getW();
		int ch = c.getH();

		if (cx + cw >= x && cx <= (x + width) && cy >= y && cy <= y + height ) {
            if (c instanceof Money) {
                score++;
            } else if (c instanceof HealthCollectable) {
                score++;
                if (health + 1 < initialHealth) {
                    health++;
                }
            }
			collided = true;
		}

		return collided;
	}

	/**
	 * Creates a projectile when the player's cooldown is at 0
	 * @return the projectile object fired or null when nothing fired
	 */
	public PlayerProjectile shoot() {
		PlayerProjectile newShot = null;
		fireTimer--;
		if (fireTimer <= 0) {
			if(width != 0) {
				newShot = new PlayerProjectile(x+(width/2), y-1, 2);
			} else {
				newShot = new PlayerProjectile(x+(width/2), y-1);
			}
			fireTimer = firerate * fireCount;

		}

		return newShot;
	}

	/**
	 * Draws ship character on the board
	 * @param board The text board
	 */
	public void draw(String[][] board) {
		board[y][x] = ship;
	}

	/**
	 * Draws the projectile for the GUI Game.
	 * @param g the current GUI graphics object.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(x, y, width, height);
		g.setColor(Color.RED);
		g.fillRect(x, y + height + 5, width, 5);
		g.setColor(Color.GREEN);
		g.fillRect(x, y + height + 5, (int) (width * ((double)health/(double)initialHealth)), 5);
	}

	/**
	 * Gets the player's column value
	 * @return player's column value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the player's column value
	 * @param x New column value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the player's row value
	 * @return Player's row value
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the player's row value
	 * @param y New row value
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the player's health value
	 * @return player's health value
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the player's health value
	 * @param health New health value
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Gets the player's score value
	 * @return player's score value
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the player's maxX value
	 * @param max the new maxX value for the player
	 */
	public void setMaxX(int max) {
		this.maxX = max;
	}
	
	/**
	 * Sets the player's maxY value
	 * @param max the new maxY value
	 */
	public void setMaxY(int max) {
		this.maxY = max;
	}
	
	/**
	 * Sets the players step or distance moved per button press 
	 * @param step the amount moved 
	 */
	public void setStep(int step) {
		this.step = step;
	}
	
	/**
	 * Gets the player's width 
	 * @return width the player's width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the player's width 
	 * @param width the player's new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Gets the player's height 
	 * @return height the players height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the player's height value
	 * @param height the player's height value
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
