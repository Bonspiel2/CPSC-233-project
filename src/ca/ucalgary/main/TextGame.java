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
		
		player = new Player(COLUMNS/2, ROWS - 2, 5);
		
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
	public String[][] draw(){
		return null;
		
	}
	
	//Lily
	public void print() {

	}
	
	//Lily
	public String getInput{
		
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
		//@Quinn fix your move^^
		
		player.move(s);
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
		for(int i = 0; i < NUMSTARTENEMIES; i++) {
			Enemy enemy = new Enemy(i);
			enemies.add(enemy);
			board[enemy.getY()][enemy.getX()] = enemy.draw();
		}
		board[player.getY()][player.getX()] = player.draw();
		
		return board;
	}

}
