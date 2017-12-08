package ca.ucalgary.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import ca.ucalgary.collectable.Collectable;
import ca.ucalgary.collectable.HealthCollectable;
import ca.ucalgary.collectable.Money;
import ca.ucalgary.collectable.IncreasedFireRate;
import ca.ucalgary.game.GUIGame;
import ca.ucalgary.game.TextGame;
import ca.ucalgary.interfaces.Collidable;
import ca.ucalgary.projectiles.EnemyProjectile;



/**
 * The enemy class allows the game to create and interact with
 * enemy position, create EnemyProjectiles and have enemies 
 * drop Collectables when killed.
 * The class moves the enemies down the screen as the game 
 * progresses and checks for collisions with the player
 * 
 * @author Matt
 */
public class Enemy implements Collidable {

	private static final String ENEMY_SYMBOL = "V";
	private static final int TEXT_DIMENSION = 0;

	private int x;
	private int y;
	private int width;
	private int height;

	private int maxY;
	private int maxX;

	private int shotSpot;

	private boolean hasAShot;

	private BufferedImage enemyImg;

	/**
	 * Enemy Constructor for the TextBased version of the game, calls other constructor
	 * to create an enemy that is alive has the ability to shoot, has a width and height of 0,
	 * and has a max y value of the number of rows in the text game.
	 * @param x x coordinate of the enemy or column in which is resides
	 * @param y y coordinate of the enemy or row in which is resides
	 */
	public Enemy(int x, int y) {
		this(x,y,TEXT_DIMENSION,TEXT_DIMENSION,TextGame.COLUMNS, TextGame.ROWS);
	}

	/**
	 * Enemy constructor for the GUI version of the game, calss other constructor
	 * to create an enemy with the given x, y, width and height as well as the height
	 * of the GUI screen
	 * @param x x coordinate of the enemy
	 * @param y y coordinate of the enemy
	 * @param width Width of the enemy
	 * @param height Height of the enemy
	 */
	public Enemy(int x, int y, int width, int height) {
		this(x,y,width,height,GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
	}


	/**
	 * Main enemy constructor, initializes all required variables
	 * @param x x coordinate of the enemy
	 * @param y y coordinate of the enemy
	 * @param width Width of the enemy
	 * @param height Height of the enemy
	 * @param maxY Last y value before the enemy exits the screen
	 */
	private Enemy(int x, int y, int width, int height, int maxX, int maxY) {
		this.x = Math.min(maxX-1, Math.max(0, x));
		this.y = Math.min(maxY-1, Math.max(0, y));
		this.width = Math.max(0, width);
		this.height = Math.max(0, height);

		this.hasAShot = true;

		this.maxY = maxY;
		this.maxX = maxX;

		this.shotSpot = new Random().nextInt(maxY/4);


		try {
			enemyImg = ImageIO.read(Enemy.class.getResourceAsStream("/ca/ucalgary/lib/EnemyShip.png"));
		} catch (IOException e) {
			System.out.println("Could not load enemy image.");
		}

	}

	/**
	 * Copy constructor
	 * @param e Enemy to copy
	 */
	public Enemy(Enemy e) {
		this(e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getMaxX(), e.getMaxY());
	}

	/**
	 * Moves the enemy down one row towards the players ship and 
	 * if it has reached the edge of the screen returns false
	 * @return	The enemy's alive value to know whether to draw
	 * it or not
	 */
	public boolean move() {
		boolean alive = true;

		if (y+1 >= maxY) {
			alive = false;
		} else {
			y++;
		}

		return alive;
	}

	/**
	 * Checks whether the player and enemy have collided i.e. they have 
	 * the same x and y values
	 * @param c The player object which collision is checked 
	 * for with
	 * @return True if it has collided, false if it has not
	 */
	public boolean collidedWith(Collidable c) {
		boolean collided = false;

		if (x + width >= c.getX() && x <= c.getX() + c.getWidth() && 
				y + height >= c.getY() && y <= c.getY() + c.getHeight()) {
			collided = true;
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
		int decider = new Random().nextInt(6);
		Collectable collectable;
		if(height != 0) {
			if (decider == 2) {
				collectable = new HealthCollectable(x,y,7,10);
			} else if (decider == 3) {
				collectable = new IncreasedFireRate(x,y,7, 10);
			} else {
				collectable = new Money(x,y, 7, 10);
			}
		}
		else {
			if (decider == 2) {
				collectable = new HealthCollectable(x,y);
			} else {
				collectable = new Money(x,y);
			}
		}
		return collectable;
	}

	/**	Draws the enemy on the board at its current x and y coordinates
	 * @param board	The text board on which the enemy is drawn.
	 */
	public void draw(String[][] board) {
		if (board.length > y && board[0].length > x) {
			board[y][x] = new String(ENEMY_SYMBOL);
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
		if (x < maxX && x >= 0) {
			this.x = x;
		} else if (x < 0) {
			this.x = 0;
		} else if (x >= maxX) {
			this.x = maxX - 1;
		}
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
		if (y < maxY && y >= 0) {
			this.y = y;
		} else if (y < 0) {
			this.y = 0;
		} else if (y >= maxY) {
			this.y = maxY - 1;
		}
	}

	/**
	 * Draws a red square as the enemy at its current x,y,width and height
	 * @param g the graphics object being drawn to
	 */
	public void draw(Graphics g) {
		if (enemyImg != null) {
			g.drawImage(enemyImg, x, y, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
	}

	/**
	 * a getter for hasAShot which indicates whether the enemy has shot
	 * @return hasAShot, whether the enemy has shot yet
	 */
	public boolean getHasAShot() {
		return hasAShot;
	}

	public void setHasAShot(boolean shot) {
		this.hasAShot = shot;
	}

	/**
	 * Creates a new EnemyProjectile object at the enemy's location
	 * @return shot, Projectile that was created
	 */
	public EnemyProjectile shoot() {
		EnemyProjectile shot = null;
		if (y == shotSpot) {
			if (width != 0) {
				shot = new EnemyProjectile(x + width / 2, y + 1 + height, 2);
			} else {
				shot = new EnemyProjectile(x + width / 2, y + 1 + height);
			}
			hasAShot = false;
		}
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
	 * getter for enemy maxX
	 * @return the maxX, or how far to the right the enemy can go before being removed
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * setter for enemy width
	 * @param width width of the enemy
	 */
	public void setWidth(int width) {
		if (width >= 0) {
			this.width = width;
		}
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
		if (height >= 0) {
			this.height = height;
		}
	}

}
