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
 *
 */
public class GUIGameController implements ActionListener, MouseMotionListener {
	
	private GUIGameInterface gui;
	private GUIGame game;
	
	private int enemyCounter = 0;
	
	private Timer gameClock;
	
	/**
	 * The main constructor for the GUIGameController class.
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
	
	public GUIGameInterface getGUI() {
		return gui;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		
		game.movePlayer(x,y);
		
	}

}
