package ca.ucalgary.game;

import java.util.ArrayList;

import ca.ucalgary.collectable.Collectable;
import ca.ucalgary.enemy.Enemy;
import ca.ucalgary.player.Player;
import ca.ucalgary.projectiles.Projectile;

/**
 * The GUIGame class extends the Game class and handles the set up of
 * game. It initializes the board and player.
 *
 */
public class GUIGame extends Game {
	
	public static final int SCREEN_WIDTH = 350;
	public static final int SCREEN_HEIGHT = 500;

/**
 * Constructor for testing
 */
    public GUIGame(ArrayList<Enemy> enemies, ArrayList<Collectable> collectables, ArrayList<Projectile> projectiles, Player player) {
        super(enemies, collectables, projectiles, player);
    }
/**
 * The main constructor for the GUIGame. Handles initialization 
 * of the board dimensions and the player,
 */
	public GUIGame() {
		super();

		Player player = new Player(SCREEN_WIDTH/2, SCREEN_HEIGHT*7/8, Player.DEFAULT_GUI_WIDTH, Player.DEFAULT_GUI_HEIGHT, Player.DEFAULT_GUI_HEALTH);
        

		player.setMaxX(SCREEN_WIDTH);
		player.setMaxY(SCREEN_HEIGHT);
        
		super.setPlayer(player);
	}
	
	/**
	 * Handles the set-up of the board through spawning
	 * the initial enemies.
	 */
	public void initBoard() {
		for(int i = 0; i < TextGame.ROWS-6; i++) {
			Enemy enemy = new Enemy(i);
			addEnemy(enemy);
		}
	}

	/**
	 * Updates the x and y coordinates of the player.
	 * Called to move the player based on mouse input.
	 * @param x the given x value for the player
	 * @param y the given y value for the player
	 */
	public void movePlayer(int x, int y) {
		Player p = super.getPlayer();
		p.setX(x - p.getWidth()/2);
		p.setY(y - p.getHeight()/2);
	}

}
