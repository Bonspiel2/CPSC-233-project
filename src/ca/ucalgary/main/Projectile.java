package ca.ucalgary.main;

/**
 *
 * The Projectile class controls the behavior of all projectiles in the game 
 * through drawing projectiles as well as detecting whether or not a projectile 
 * has collided with an enemy or reached the end of the board.
 *
 * @author Quinn
 *
 */

public class Projectile {
	
    /**
     * The x coordinate of the projectile (columns)
     */
	private int x;
    
    /**
     * The y coordinate of the projectile (rows)
     */
	private int y;
    
    private boolean collided;
	private boolean edgy;
    
    // velocity of the projectile
    private int velProjec;
    
    private String symbol = "|";
	
    /**
     * Detects whether projectile occupies the same coordinates as a given enemy.
     * @param enemy the enemy whose coordinates are compared to the projectile's.
     * @return collided returns true if the enemy and projectile have the same coordinates
     */
	public boolean collidedWith(Enemy enemy) {
        collided = ((y == enemy.getY() || y == enemy.getY()-1) && x == enemy.getX());
        return collided;

	}
    
    /**
     * Main constructor
     * @param newX new projectile's column value
     * @param newY new projectile's row value
     */
    public Projectile(int newX, int newY){
		x = newX;
		y = newY;
        collided = false;
		
	}
    /**
     * Moves the projectile up one row and detects whether or not it has reached
     * the edge of the board.
     * @return edgy returns true if the projectile has reached the edhe of the board.
     */
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
    
    /**
     * Draws the projectile.
     * @param board the current gameboard.
     */
    public void draw(String[][] board) {
		board[y][x] = "|";
	}
    
    public void draw(Board board) {
        board.draw(symbol, x, y);
    }
    
    /**
     * Gets the projectile's current x (column) coordinate.
     * @return x the current x coordinate.
     */
	public int getX() {
		return x;
	}
    
    /**
     * Sets the projectile's x (column) coordinate to a given value.
     * @param x the new x coordinate.
     */
	public void setX(int x) {
		this.x = x;
	}
    
    /**
     * Gets the projectile's current y (row) coordinate.
     * @return y the current y coordinate.
     */
	public int getY() {
		return y;
	}
    
    /**
     * Sets the projectile's y (row) coordinate to a given value.
     * @param y the new y coordinate.
     */
	public void setY(int y) {
		this.y = y;
	}

}


