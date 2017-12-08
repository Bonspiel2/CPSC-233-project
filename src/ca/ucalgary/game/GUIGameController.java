package ca.ucalgary.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.Timer;

import ca.ucalgary.enemy.Enemy;

import java.io.*;

/**
 * The GUIGameController class allows the game to respond to user input.
 * It reacts to user-given mouse events and refreshes the board based on a timer.
 */
public class GUIGameController implements ActionListener, MouseMotionListener {

	private static final int[] LEVEL_ENEMY_SPAWN_RATES = {100, 40, 30, 25, 20, 15, 0};

	private static final int GAME_SPEED = 7;
	private static final int LEVEL_TIME = 30000;

	private GUIGameInterface gui;
	private GUIGame game;

	private int enemyCounter = 0;

	private Timer gameClock;
	private Timer levelClock;

	/**
	 * The main constructor for the GUIGameController class.
	 * It instantiates GUIGame and GUIGameInterface as well as starts a timer for the game.
	 */
	public GUIGameController() {

		game = new GUIGame();
		gui = new GUIGameInterface(this, this, game);

		gameClock = new Timer(GAME_SPEED, this);
		gameClock.setActionCommand("TIMER");
		gameClock.start();

		levelClock = new Timer(LEVEL_TIME, this);
		levelClock.setActionCommand("LEVEL");
		levelClock.start();

		//        try {
		//            FileWriter writer = new FileWriter("src/lib/HighScore.txt");
		//            BufferedWriter buffWriter = new BufferedWriter(writer);
		//            buffWriter.write("0");
		//            buffWriter.close();
		//        } catch (IOException e) {
		//            System.out.println("Could not initialize high score");
		//        }

	}

	@Override
	/**
	 * This method refreshes the game board based on a timer. It checks 
	 * for collisions, causes enemies and the player to shoot, and terminates the game
	 * if the player is no longer alive. Provides the option for the user to instantly play
	 * again upon dying.
	 * 
	 * @param e the given ActionEvent (in this case, always a timer)
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("TIMER")) {
			enemyCounter++;
			int currentLevel = game.getCurrentLevel();
			
			if (currentLevel == 6) {
				gui.gameOver();
				levelClock.stop();
			} else if (game.playerIsDead()) {
				gui.gameOver();
				levelClock.stop();
			} else {
				if(enemyCounter >= LEVEL_ENEMY_SPAWN_RATES[currentLevel]) {
					game.addEnemy(new Enemy(new Random().nextInt(GUIGame.SCREEN_WIDTH),0,32,32));
					enemyCounter = 0;
				}
				game.move();
				game.playerShoot();
				game.enemiesShoot();
				game.checkCollisions();
			}


			gui.repaint();

		} else if (e.getActionCommand().equals("LEVEL")) {
			int curLevel = game.getCurrentLevel();
			if (curLevel <= 5) {
				game.setCurrentLevel(curLevel+1);
			}
		} else if (e.getActionCommand().equals("Play Again")) {
			gui.newGame();
			game = new GUIGame();
			gui.setGame(game);

			levelClock = new Timer(LEVEL_TIME, this);
			levelClock.setActionCommand("LEVEL");
			levelClock.start();

		}



	}

	/**
	 * Retrieves the GUIGameInterface
	 * @return gui the GUIGameInterface currently in use
	 */
	public GUIGameInterface getGUI() {
		return gui;
	}

	@Override
	/**
	 * Overidden method for a mouse dragged event. No functionality.
	 */
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	/**
	 * Updates the x and y coordinates of the player based on the
	 * coordinates of the user's cursor.
	 * @param e the given mouse event
	 */
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		game.movePlayer(x,y);
	}

}
