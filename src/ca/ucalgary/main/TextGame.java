package ca.ucalgary.main;

import java.util.ArrayList;

public class TextGame {
	
	public static final int ROWS = 15;
	public static final int COLUMNS = 7;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Collectable> collectables;
	
	private String[][] board;
	
	public TextGame() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();
		
		board = new String[15][7];
	}
	
	public void run() {
		
		
	}
	
	public String[][] draw(){
		return null;
		
	}
	
	public void print() {
		
	}
	
	public void move() {
		
	}

}
