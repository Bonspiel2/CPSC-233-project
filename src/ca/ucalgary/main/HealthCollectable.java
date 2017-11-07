package ca.ucalgary.main;

import java.awt.Color;
import java.awt.Graphics;

public class HealthCollectable extends Collectable {

    private String symbol = "+";
    
    /**
     * Collectable constructor for the TextBased version of the game, 
     * initializes all variables that are needed when a collectable is created.
     * @param x, x coordinate of the collectable
     * @param y, y coordinate of the collectable
     * @param maxY, the farthest down a collectable can travel before being removed
     */
    HealthCollectable(int x, int y, int maxY) {
        super(x, y, maxY);
    }
    
    
    /**
     * Collectable constructor for the GUI version of the game, 
     * initializes all variables that are needed when a collectable is created.
     * @param x, x coordinate of the collectable
     * @param y, y coordinate of the collectable
     * @param maxY, the farthest down a collectable can travel before being removed
     * @param width, width of the collectable
     * @param height, height of the collectable
     */
    HealthCollectable(int x, int y, int maxY, int width, int height) {
        super(x, y, maxY, width, height);
    }
    
    /**
     * Prints the character '+' at the current X and Y
     * value on the array list that is the game board.
     * @param board array list that is the game board.
     */
    public void draw(String[][] board) {
        board[getY()][getX()] = symbol;
        
    }
    
    /**
     * Draws the collectable to a graphics object, as a yellow "$"
     * @param g the graphics object being drawn to
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString(symbol, getX(), getY());
        
    }

}
