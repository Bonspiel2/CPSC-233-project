package ca.ucalgary.main;

import java.util.ArrayList;

public class TextGame {
	
	public static final int ROWS = 15;
	public static final int COLUMNS = 7;
	
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
		
		
	}
	
	//Quinn
	public String[][] draw(){
		return null;
		
	}
	
	//Lily
	public void print() {
		
	}
	
	//Matt
	public void move(String moveCommand) {
		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();	
		}
		for(int i = 0; i < enemies.size(); i++) {
			
		}
		for(int i = 0; i < enemies.size(); i++) {
			
		}
		player.move(board, moveCommand);
	}
	
	//Matt
	public String[][] initBoard(){
		return null;
	}

}
