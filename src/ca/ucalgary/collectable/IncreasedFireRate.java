
package ca.ucalgary.collectable;

import ca.ucalgary.game.GUIGame;
import ca.ucalgary.game.TextGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class extends the collectable class. This is separate so 
 * other classes know how to handle this specific collectable based
 * on what collectable subclass the object pertains to.
 *
 */
public class IncreasedFireRate extends Collectable {

    private static final String FIRERATE_SYMBOL = "%";
    
    /**
     * Collectable constructor for the TextBased version of the game, 
     * initializes all variables that are needed when a collectable is created.
     * @param x, x coordinate of the collectable
     * @param y, y coordinate of the collectable
     * @param maxY, the farthest down a collectable can travel before being removed
     */
    public IncreasedFireRate(int x, int y) {
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
    public IncreasedFireRate(int x, int y, int width, int height) {
        super(x, y, width, height, GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
    }

    
    /**
     * Draws the collectable to a graphics object, as an orange "%"
     * @param g the graphics object being drawn to
     */
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawString(FIRERATE_SYMBOL, getX(), getY());
        
    }
    
    /**
     * Prints the character '%' at the current X and Y
     * value on the array list that is the game board.
     * @param board array list that is the game board.
     */
    public void draw(String[][] board) {
        board[getY()][getX()] = FIRERATE_SYMBOL;
    }

    
}

