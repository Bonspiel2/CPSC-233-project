package ca.ucalgary.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.Timer;

/**
 * The GUIGameController class allows the game to respond to user input.
 * It reacts to user-given mouse events and refreshes the board based on a timer.
 */
public class GUIGameController implements ActionListener, MouseMotionListener {
	
	private GUIGameInterface gui;
	private GUIGame game;
	
	private int enemyCounter = 0;
	
	private Timer gameClock;
	
	/**
	 * The main constructor for the GUIGameController class.
	 * It instantiates GUIGame and GUIGameInterface as well as starts a timer for the game.
	 */
	public GUIGameController() {
		
		game = new GUIGame();
		gui = new GUIGameInterface(this, this, game);

		gameClock = new Timer(10, this);
        gameClock.setActionCommand("TIMER");
        gameClock.start();
        
        game.initBoard();
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
			if(enemyCounter >= 20) {
				game.addEnemy(new Enemy(new Random().nextInt(GUIGame.SCREEN_WIDTH),0,10,10));
				enemyCounter = 0;
			}
			game.move();
			game.playerShoot();
			game.enemiesShoot();
			game.checkCollisions();
			gui.repaint();
			
			if (game.playerIsDead()) {
				gui.gameOver();
			}

		} else if (e.getActionCommand().equals("Play Again")) {
			gui.newGame();
			game = new GUIGame();
			gui.setGame(game);
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
