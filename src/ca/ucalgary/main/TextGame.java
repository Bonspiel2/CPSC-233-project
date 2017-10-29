package ca.ucalgary.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/**
 * The TextGame program implements a space invaders game which can be
 * played on the console.
 * 
 * @author Group 3
 */
public class TextGame extends Game {

	public static final int ROWS = 10;
	public static final int COLUMNS = 7;

	private String[][] board;

	private boolean running;

	public TextGame() {
		super();
        
        Player player = new Player(COLUMNS/2, ROWS - 2, 5);
        player.setMaxX(COLUMNS);
        player.setMaxY(ROWS);

        super.setPlayer(player);
        
		board = initBoard();
	}

	/**
	 * Updates the game every iteration as well as creates new objects when needed
	 * If the players health reaches zero, this method will quit the game
	 * 
	 * @author Cole
	 */
	public void run() {
		running = true;


		while (running) {
			String input = getInput();
			move(input);
			checkCollisions();
			addEnemy(new Enemy(0));
			playerShoot();
            enemiesShoot();

			draw();
			print();
			if (playerIsDead()) {
				System.out.println("GAME OVER!");
				running = false;
			}
		}


	}

	/**
	 * Checks if collisions have occured between enemies and projectiles, 
	 * between collectables and the player, or between enemies and the player.
	 * If a collision occurs, the given object(s) is/are removed from their 
	 * array list (and therefore the board).
	 *
	 * @author Quinn
	 *
	 */

	/**
	 * Draws the enemies, projectiles, collectables, and player on the gameboard.
	 *
	 * @author Quinn
	 * @return board the fully drawn gameboard.
	 */
	public void draw() {

		clearBoard();
		ArrayList<Enemy> enemies = getEnemies();
		ArrayList<Projectile> projectiles = getProjectiles();
		ArrayList<Collectable> collectables = getCollectables();
		Player player = getPlayer();

		player.draw(board);

		for (Enemy enemy : enemies) {
			enemy.draw(board);
		}

		for (Projectile projectile : projectiles) {
			projectile.draw(board);
		}

		for (Collectable collectable : collectables) {
			collectable.draw(board);
		}

	}

	/**
	 * Prints the game board, the player's health 
	 * and the players score for each turn.
	 * @author Lily and Quinn
	 */
	public void print() {
		Player player = getPlayer();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("Health: " + player.getHealth());
		System.out.println("Score: " + player.getScore());
	}

	/**
	 * Reads user input with every turn to determine whether 
	 * the player moves the the right or to to the left.
	 * @return String that indicates the direction the player 
	 * desires to move.
	 * 
	 * @author Lily
	 */
	public String getInput() {
		Scanner in = new Scanner(System.in);
		String com = in.nextLine().toUpperCase();
		return com;
	}

	/**
	 * Moves each object to its next location on the screen
	 * @param s The string command given as input for the direction 
	 * for the player to move
	 * @author Matt
	 */

	public void move(String s) {
		super.move();
		Player player = getPlayer();
		player.move(s);
	}

	public void clearBoard() {
		board = new String[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				board[row][col] = "-";
			}
		}
	}

	/**
	 * Initializes the board blank and then with enemies up to the 6th
	 * row from the bottom
	 * @return board The text board after being initialized
	 * @author Matt
	 */
	public String[][] initBoard(){
		clearBoard();
		for(int i = 0; i < ROWS-6; i++) {
			Enemy enemy = new Enemy(i);
			addEnemy(enemy);
		}

		return board;
	}

}
