package ca.ucalgary.main;

public class Projectile {
	
	private int x;
	private int y;
    private boolean collided;
	private boolean edgy;
	
	// detects whether projectile at same coordinate as enemy
	public boolean collidedWith(Enemy enemy) {
        collided = (x == enemy.getX() && (y == enemy.getY() || y == enemy.getY() - 1));
        return collided;

	}
	// constructor
	public Projectile(int newX, int newY){
		x = newX;
		y = newY;
        collided = false;
		
	}
	// moves the projectile up one row and detects whether it has reached
	// the edge of the board
	public boolean move() {
        // projectile stops moving if it has collided with an enemy
        if (collided) {
            y = y;
        }
        
        // detects whether the projectile's current row is the same as the edge of the board
        if (y == 0) {
			edgy = true;
		} else {
			edgy = false;
			y = y - 1;
		}
		return edgy;
	}
	// draws the projectile
	public void draw(String[][] board) {
		board[x][y] = "|";
	}
    
	public int getX() {
		return x;
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

}
