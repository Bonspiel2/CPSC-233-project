package ca.ucalgary.main;

public class Projectile {
	
	private int x;
	private int y;
	private boolean collided;
	private boolean edgy;
	
	// detects whether projectile at same coordinate as enemy
	public boolean collidedWith(Enemy enemy) {
		if(x == enemy.getX() && y == enemy.getY()) {
			collided = true;
		} else {
			collided = false;
		}
		return collided;
	}
	// constructor
	public Projectile(int newX, int newY){
		x = newX;
		y = newY; 
		
	}
	// moves the projectile up one row and detects whether it has reached
	// the edge of the board
	public boolean move(String[][] board) {
		// detects whether the projectile's current row is the same as the edge of the board
		if(y == 0) {
			edgy = true;
		} else {
			edgy = false;
			y = y - 1;
		}
		return edgy;
	}
	// draws the projectile
	public String draw() {
		return "|";
	}
	
	

}
