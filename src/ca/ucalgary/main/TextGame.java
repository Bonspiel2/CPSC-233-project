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
public class TextGame {
	
	public static final int ROWS = 10;
	public static final int COLUMNS = 7;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Collectable> collectables;
	private Player player;
	
	private String[][] board;
	
	private boolean running;
	
	public TextGame() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();
		
        player = new Player(COLUMNS/2, ROWS - 2, 5);		
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
			enemies.add(new Enemy(0));
			projectiles.add(new Projectile(player.getX(), player.getY() - 1));
			draw();
			print();
			if(player.getHealth() == 0) {
				running = false;
			}
		}
		System.out.println("GAME OVER!");

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
	public void checkCollisions() {
        // check collisions between enemies and projectiles
        for (Iterator<Enemy> enemyItr = enemies.iterator(); enemyItr.hasNext();) {
            Enemy enemy = enemyItr.next();
            for (Iterator<Projectile> projecItr = projectiles.iterator(); projecItr.hasNext();) {
                Projectile projec = projecItr.next();
                // if enemy and projectile collide, remove each from respective arraylists
                if (projec.collidedWith(enemy)) {
                	collectables.add(new Collectable(enemy.getX(),enemy.getY()));
                    projecItr.remove();
                    enemyItr.remove();
                    break;
                }
            }
            //should do the player/enemy collision check here for efficency
        }
        // check collisions between collectables and player
        for (Iterator<Collectable> collecItr = collectables.iterator(); collecItr.hasNext();) {
            Collectable collec = collecItr.next();
            if (player.collidedWith(collec)) {
                collecItr.remove();
            }
        }
        
        // check collisions between enemies and player
        for (Iterator<Enemy> enemyItr = enemies.iterator(); enemyItr.hasNext();) {
            Enemy enemy = enemyItr.next();
            // decrease player health by one if collision occurs
            if (enemy.collidedWith(player)) {
                int health = player.getHealth() - 1;
                player.setHealth(health);
            }
        }
    }
	
    /**
     * Draws the enemies, projectiles, collectables, and player on the gameboard.
     *
     * @author Quinn
     * @return board the fully drawn gameboard.
     */
	public String[][] draw() {
        
        clearBoard();
        
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

        return board;
		
	}
	
	/**
	 * Prints the game board, the player's health 
	 * and the players score for each turn.
	 * @author Lily and Quinn
	 */
	public void print() {
        //draw();		//why does this and run call draw?
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
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();	
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			if(projectile.move()) {
				projectiles.remove(i);
			}; 
		}
		
		for(int i = 0; i < collectables.size(); i++) {
			Collectable collectable = collectables.get(i);
			if(!collectable.move(board)) {;
			collectables.remove(i);
			}
		}
		
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
			enemies.add(enemy);
		}
		
		return board;
	}

}
