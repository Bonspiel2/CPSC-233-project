package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This Collectable class allows the game to interact with 
 * the position of collectables spawned within the enemy 
 * class. 
 * The class updates the collectable's position and checks 
 * if it is still within the range of the game board.
 * 
 * @author lilypollreis
 *
 */
public class Collectable {

	private int x;
	private int y;
	private int maxY;
    private String symbol = "$";
    
    private int width;
    private int height;
	
	Collectable(int x, int y, int maxY) {
		this.x = x;
		this.y = y;
		this.maxY = maxY;
	}
	
	Collectable(int x, int y, int maxY, int lol) {
		this.x = x;
		this.y = y;
		this.maxY = maxY;
		this.width = 7;
		this.height = 12;
	}
	
	
	/**
	 * Gets integer column value the collectable.
	 * @return integer column value the collectable.
	 */
	public int getX() {
		return x;
	}
	
	public int getW() {
		return width;
	}

	public int getH() {
		return height;
	}
	
	/**
	 * Sets new integer column value for the collectable.
	 * @param x new column value.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets integer row value the collectable.
	 * @return integer row value the collectable.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets new integer row value for the collectable.
	 * @param y new row value.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Moves collectable down the screen by one row every turn 
	 * until it is no longer within the range of the game board.
	 * 
	 * @param board array list that is the game board.
	 * @return true if the collectable is still within
	 * the range of the game board.
	 */
	public boolean move() {
		boolean onScreen = true;
		if (y == TextGame.ROWS - 1) {
			onScreen = false;
		} else {
			y++;
		}
		return onScreen;
	}

	/**
	 * Prints the character '$' at the current X and Y 
	 * value on the array list that is the game board.
	 * @param board array list that is the game board.
	 */
	public void draw(String[][] board) {
		board[y][x] = "$";
        
	}
//    
//    public void draw(Board board) {
//        board.draw(symbol, x, y);
//    }
    
    public void draw(Graphics g) {
 	
    	g.setColor(Color.YELLOW);
    	g.drawString("$", x, y);
    	
    }

}
