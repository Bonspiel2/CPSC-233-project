package ca.ucalgary.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ca.ucalgary.collectable.Collectable;
import ca.ucalgary.enemy.Enemy;
import ca.ucalgary.player.Player;
import ca.ucalgary.projectiles.Projectile;


/**
 * The TextGame program implements a text based game similar to 
 * space invaders which can be played on the console.
 * 
 * @author Group 3
 */
public class TextGame extends Game {

	public static final int ROWS = 10;
	public static final int COLUMNS = 7;
	
	private static final String EMPTY_SPACE_SYMBOL = "-";

	private String[][] board;

	private boolean running;
	
	/**
	 * Default Constructor for TextGame
	 * Creates a player, and sets its maxY and maxX to ROWS and COLUMNS
	 * Initializes the board and the player on it
	 */
	public TextGame() {
		super();
        
        Player player = new Player(COLUMNS/2, ROWS - 2, Player.DEFAULT_TEXT_HEALTH);

        super.setPlayer(player);
        
		board = initBoard();
	}
    
    /**
     * Constructor for testing
     */
    public TextGame(ArrayList<Enemy> enemies, ArrayList<Collectable> collectables, ArrayList<Projectile> projectiles, Player player) {
        super(enemies, collectables, projectiles, player);
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
			addEnemy(new Enemy(new Random().nextInt(COLUMNS), 0));
			playerShoot();
			enemiesShoot();
            

			draw();
			print();
			
			if (playerIsDead()) {
				System.out.println("GAME OVER!");
				int highScore = 0;
				int finalScore = getPlayer().getScore();
				int displayScore;
				try {
		            FileReader reader = new FileReader("HighScore.txt");
		            BufferedReader buffReader = new BufferedReader(reader);
		            String line = buffReader.readLine();
		            highScore = Integer.parseInt(line);
		            buffReader.close();
		        } catch (FileNotFoundException e) {
		            System.out.println("Could not find high score");
		        } catch (IOException e) {
		            System.out.println("Error reading high score");
		        }
		        
		        if (finalScore > highScore) {
		            displayScore = finalScore;
		            try {
		                FileWriter writer = new FileWriter("HighScore.txt", false);
		                BufferedWriter buffWriter = new BufferedWriter(writer);
		                buffWriter.write(Integer.toString(finalScore));
		                buffWriter.close();
		            } catch (IOException e) {
		                System.out.println("Could not record high score.");
		            }
		        } else {
		            displayScore = highScore;
		        }
		        
		        System.out.println("HIGH SCORE: " + displayScore);
				running = false;
			}
		}


	}

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
		Player player = getNewPlayer();

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

	/**
	 * Clears the board before each time the board is redrawn
	 */
	public void clearBoard() {
		board = new String[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				board[row][col] = EMPTY_SPACE_SYMBOL;
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
			Enemy enemy = new Enemy(i, new Random().nextInt(TextGame.COLUMNS-1));
			addEnemy(enemy);
		}

		return board;
	}

}
