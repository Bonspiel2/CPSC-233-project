
package ca.ucalgary.collectable;

import ca.ucalgary.game.GUIGame;

import java.awt.Color;
import java.awt.Graphics;

public class IncreasedFireRate extends Collectable {

    private static final String FIRERATE_SYMBOL = "%";
    
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
     * Draws the collectable to a graphics object, as a yellow "$"
     * @param g the graphics object being drawn to
     */
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawString(FIRERATE_SYMBOL, getX(), getY());
        
    }
    
    /**
     * Collectable not used in text game.
     */
    public void draw(String[][] board) {
    }

    
}

