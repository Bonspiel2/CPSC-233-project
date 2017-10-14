package ca.ucalgary.main;

import java.util.ArrayList;

public class TextGame {
	
	public static final int ROWS = 8;
	public static final int COLUMNS = 5;
	public static final int NUMSTARTENEMIES = 6;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Collectable> collectables;
	private Player player;
	
	private String[][] board;
	
	public TextGame() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();
		
        //player = new Player(COLUMNS/2, ROWS - 2, 5); I think rows and columns might be reversed?
        player = new Player(ROWS - 2, COLUMNS/2, 5);
		
		board = initBoard();
	}
	
	//Cole
	public void run() {
		//while(running) {
			//move(getInput) -> make a class for handling input (use to uppercase so that it is "A" and "D"
			//checkCollisions etc maybe with its own class?
			//(generate proj and enemy at top of screen)
			//check for dead things and remove from lists
			//draw
			//print
		
	}
	
	//Quinn + Cole?
	public void checkCollisions() {
		
	}
	
	//Quinn
	public String[][] draw() {
        
        player.draw(board);

        for (Projectile projectile : projectiles) {
            projectile.draw(board);
        }
        
        for (Enemy enemy : enemies) {
            enemy.draw(board);
        }
        
        for (Collectable collectable : collectables) {
            collectables.draw(board);
        }

        return board;
		
	}
	
	//Lily
	public void print() {
        draw();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
	
	//Lily
	public String getInput() {
        return null;
	}
	
	//Matt
	public void move(String s) {
		
		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();	
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			projectile.move(); 
		}
		//@Lily fix your move^^
		
		for(int i = 0; i < collectables.size(); i++) {
			Collectable collectable = collectables.get(i);
			collectable.move();
		}
		
		player.move(board, s);
		//@Cole fix your move^^ (just take string)
	}
	
	//Matt
	public String[][] initBoard(){
		String[][] board = new String[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				board[row][col] = " ";
			}
		}
        // we need to erase the board every time but only want the enemies to be created once
        // maybe separate clearBoard and initGame methods?
		for(int i = 0; i < NUMSTARTENEMIES; i++) {
			Enemy enemy = new Enemy(i);
			enemies.add(enemy);
		}
		
		return board;
	}

}
