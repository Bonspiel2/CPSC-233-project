package ca.ucalgary.main;

import java.util.Random;

public class Enemy {
	
	private int x;
	private int y;
	private static int counter;
	private boolean alive;
	private boolean right;
	private String symbol = "V";
	
	Enemy(int cols) {
		this.x = new Random().nextInt(cols);
		this.y = counter;
		this.right = new Random().nextBoolean();
		this.alive = true;
		counter++;
	}
	
	
	
	
	
	
	
	public void move(String[][] board) {
		int width = board[0].length;
		if(x == 0 || x == width) {
			right = !right;
		}
		
		if(right) {
			x++;
		}
		else if(!right) {
			x--;
		}
	}
	
	public int getX() {
		return x;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String draw() {
		return symbol;
	}
	
	
}
