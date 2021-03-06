package ca.ucalgary.collectable;

import java.awt.Color;
import java.awt.Graphics;

import ca.ucalgary.game.GUIGame;
import ca.ucalgary.game.TextGame;

/**
 * This class extends the collectable class. This is separate so 
 * other classes know how to handle this specific collectable based
 * on what collectable subclass the object pertains to.
 *
 */

public class HealthCollectable extends Collectable {

    private static final String HEALTH_SYMBOL = "+";
    
    /**
     * Collectable constructor for the TextBased version of the game, 
     * initializes all variables that are needed when a collectable is created.
     * @param x, x coordinate of the collectable
     * @param y, y coordinate of the collectable
     * @param maxY, the farthest down a collectable can travel before being removed
     */
    public HealthCollectable(int x, int y) {
        super(x, y, TextGame.COLUMNS, TextGame.ROWS);
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
    public HealthCollectable(int x, int y, int width, int height) {
        super(x, y, width, height, GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
    }
    
    /**
     * Copy Constructor
	 * @param c Object to copy
	 */
	public HealthCollectable(HealthCollectable c) {
		super(c);
	}
    
    /**
     * Prints the character '+' at the current X and Y
     * value on the array list that is the game board.
     * @param board array list that is the game board.
     */
    public void draw(String[][] board) {
        board[getY()][getX()] = HEALTH_SYMBOL;
        
    }
    
    /**
     * Draws the collectable to a graphics object, as a red "+"
     * @param g the graphics object being drawn to
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString(HEALTH_SYMBOL, getX(), getY());
        
    }

}
