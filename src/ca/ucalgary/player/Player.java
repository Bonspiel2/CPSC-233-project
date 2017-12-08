package ca.ucalgary.player;

import ca.ucalgary.*;
import ca.ucalgary.collectable.HealthCollectable;
import ca.ucalgary.collectable.Money;
import ca.ucalgary.collectable.IncreasedFireRate;
import ca.ucalgary.game.GUIGame;
import ca.ucalgary.game.TextGame;
import ca.ucalgary.interfaces.Collidable;
import ca.ucalgary.projectiles.PlayerProjectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import ca.ucalgary.game.GUIGameInterface;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The player class allows the game to interact with the 
 * player's position, their health as well as their score.
 * The class updates the players position based on input,
 * and checks for collisions with other objects.
 * 
 * @author Cole
 */
public class Player implements Collidable, ActionListener {

	public static final int DEFAULT_GUI_WIDTH = 32;
	public static final int DEFAULT_GUI_HEIGHT = 32;

	public static final int DEFAULT_TEXT_HEALTH = 5;
	public static final int DEFAULT_GUI_HEALTH = 10;

	private static final int TEXT_DIMENSION = 0;
	private static final double DEFAULT_FIRE_RATE = 0.5;

	private static final double GUI_INCREASED_FIRE_RATE = 0.8;
	private static final double TEXT_INCREASED_FIRE_RATE = 1;

	private static final double DEFAULT_TEXT_FIRE_COUNT = 6;
	private static final double DEFAULT_GUI_FIRE_COUNT = 100;

	private static final String PLAYER_SYMBOL = "A";

	private static final int PLAYER_TEXT_SPEED = 1;

	private int x;
	private int y;
	private int width;
	private int height;

	private int maxX; //columns
	private int maxY; //rows

	private int health;
	private int initialHealth;
	private int score;

	private double firerate;
	private double fireCount;
	private double fireTimer;

	private BufferedImage img;

	private Timer guiFireRateTimer;
	private int textFireRateTimer;


	/**
	 * Main constructor for textgame. Initializes with given x,y and health as well as 0 width/height,
	 * 0.5 firerate, and 6 fireCount
	 * @param x new player's column value
	 * @param y new player's row value
	 * @param health new player's total health
	 */
	public Player(int x, int y, int health) {
		this(x, y, TEXT_DIMENSION,TEXT_DIMENSION, health,
				DEFAULT_FIRE_RATE, DEFAULT_TEXT_FIRE_COUNT,
				TextGame.COLUMNS, TextGame.ROWS);

	}

	/**
	 * Main constructor for GUI game. Initializes with given x, y, width, height, 
	 * and health as well as 0.5 firerate, and 100 fireCount
	 * @param x new player's column value
	 * @param y new player's row value
	 * @param width new player's width
	 * @param height new player's height
	 * @param health new player's total health
	 */
	public Player(int x, int y, int width, int height, int health) {
		this(x, y, width, height, health,
				DEFAULT_FIRE_RATE, DEFAULT_GUI_FIRE_COUNT,
				GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);

	}

	/**
	 * Main constructor
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param health
	 * @param firerate
	 * @param fireCount
	 * @param maxX
	 * @param maxY
	 */
	private Player(int x, int y, int width, int height, int health, 
			double firerate, double fireCount,
			int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.health = health;
		this.initialHealth = health;
		this.score = 0;

		this.maxX = maxX;
		this.maxY = maxY;

		this.firerate = firerate;
		this.fireCount = fireCount;
		fireTimer = fireCount * (1-firerate);

		try {
			img = ImageIO.read(Player.class.getResourceAsStream("/ca/ucalgary/lib/PlayerShip.png"));
		} catch (IOException e) {
			System.out.println("Could not load player image.");
		}

		this.guiFireRateTimer = new Timer(1,this);
		guiFireRateTimer.stop();
		
		this.textFireRateTimer = 0;
	}

	/**
	 * Copy Constructor
	 * @param p Player to copy
	 */
	public Player(Player p) {
		this.x = p.getX();
		this.y = p.getY();
		this.width = p.getWidth();
		this.height = p.getHeight();

		this.maxX = p.getMaxX();
		this.maxY = p.getMaxY();

		this.health = p.getHealth();
		this.initialHealth = p.getInitialHealth();
		this.score = p.getScore();

		this.firerate = p.getFirerate();
		this.fireCount = p.getFireCount();
		this.fireTimer = p.getFireTimer();

	}


	/**
	 * Moves player either left or right while keeping it on the
	 * screen
	 * @param s Either "A" or "D" denoting left or right
	 */
	public void move(String s) {
		if (s.equals("A")) {
			if (x - PLAYER_TEXT_SPEED >= 0) {
				x-= PLAYER_TEXT_SPEED;
			}
		} else if (s.equals("D")) {
			if ((x + PLAYER_TEXT_SPEED) < maxX) {
				x+= PLAYER_TEXT_SPEED;
			}
		} else if (s.equals("W")) {
			if ((y - PLAYER_TEXT_SPEED) >= 0) {
				y-= PLAYER_TEXT_SPEED;
			}
		} else if (s.equals("S")) {
			if ((y + PLAYER_TEXT_SPEED) < maxY) {
				y+= PLAYER_TEXT_SPEED;
			}
		}
		
		textFireRateTimer--;
		
		if (textFireRateTimer <= 0) {
			firerate = DEFAULT_FIRE_RATE;
		}
	}

	/**
	 * Checks to see if the player has collided with a given collidable.
	 * increases score/health if the collidable is also a collectable
	 * @param c Object to check for collision
	 * @return Returns true if the player and the object occupy the
	 * same space
	 */
	public boolean collidedWith(Collidable c) {
		boolean collided = false;
		int cx = c.getX();
		int cy = c.getY();
		int cw = c.getWidth();
		int ch = c.getHeight();

		if (cx + cw >= x && cx <= (x + width) && cy + ch >= y && cy <= y + height ) {
			if (!GUIGameInterface.gameOver) {
				if (c instanceof Money) {
					score++;
				} else if (c instanceof HealthCollectable) {
					score++;
					if (health + 1 <= initialHealth) {
						health++;
					}
				} else if (c instanceof IncreasedFireRate) {
					score++;
					if (width!=0) {
						guiFireRateTimer.stop();
						this.firerate = GUI_INCREASED_FIRE_RATE;
						guiFireRateTimer = new Timer(4000, this);
						guiFireRateTimer.setActionCommand("FIRERATE");
						guiFireRateTimer.start();
					} else {
						this.firerate = TEXT_INCREASED_FIRE_RATE;
						textFireRateTimer = 5;
					}
				}
				collided = true;
			}
		}

		return collided;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("FIRERATE")) {
			guiFireRateTimer.stop();
			this.firerate = DEFAULT_FIRE_RATE;
		}
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
			fireTimer = (1-firerate) * fireCount;

		}

		return newShot;
	}

	/**
	 * Draws ship character on the board
	 * @param board The text board
	 */
	public void draw(String[][] board) {
		board[y][x] = PLAYER_SYMBOL;
	}

	/**
	 * Draws the projectile for the GUI Game.
	 * @param g the current GUI graphics object.
	 */
	public void draw(Graphics g) {
		if (img != null) {
			g.drawImage(img, x, y, null);

		} else {
			g.setColor(Color.CYAN);
			g.fillOval(x, y, width, height);
		}
		g.setColor(Color.RED);
		g.fillRect(x, y + height + 5, width, 5);
		g.setColor(Color.GREEN);
		g.fillRect(x, y + height + 5, (int)(width * ((double)health/(double)initialHealth)), 5);

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
		if (health >= 0) {
			this.health = health;
		}
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

	/**
	 * Gets the player's firerate
	 * @return firerate The player's firerate
	 */
	public double getFirerate() {
		return firerate;
	}

	/**
	 * Gets the player's fireCounter
	 * @return fireCount The player's total fire count
	 */
	public double getFireCount() {
		return fireCount;
	}

	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * @return the maxY
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * @return the initialHealth
	 */
	public int getInitialHealth() {
		return initialHealth;
	}

	/**
	 * @return the fireTimer
	 */
	public double getFireTimer() {
		return fireTimer;
	}
}
