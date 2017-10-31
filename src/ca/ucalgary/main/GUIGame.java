package ca.ucalgary.main;

/**
 * The GUIGame class extends the Game class and handles the set up of
 * game. It initializes the board and player.
 *
 */
public class GUIGame extends Game {

	GUIGameInterface gui;
	
	public static final int SCREEN_WIDTH = 350;
	public static final int SCREEN_HEIGHT = 500;

/**
 * The main constructor for the GUIGame. Handles initialization 
 * of the board dimensions and the player,
 */
	public GUIGame() {
		super();

		Player player = new Player(SCREEN_WIDTH/2, SCREEN_HEIGHT*7/8, 15, 15, 10);

		player.setMaxX(SCREEN_WIDTH);
		player.setMaxY(SCREEN_HEIGHT);
        
        player.setStep(5);

		super.setPlayer(player);
	}

	@Override
	/**
	 * Overriden run method. No functionality.
	 */
	public void run() {
	}

	@Override
	/**
	 * Overriden draw method. No functionality.
	 */
	public void draw() {
	}

	@Override
	/**
	 * Overriden method to get input. No functionality.
	 * @return null returns nothing.
	 */
	public String getInput() {
		return null;
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
		super.getPlayer().setX(x- super.getPlayer().getWidth()/2);
		super.getPlayer().setY(y - super.getPlayer().getHeight()/2);
	}
}
