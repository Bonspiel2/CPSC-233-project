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

	private GUIGameInterface gui;
	private GUIGame game;

	private int enemyCounter = 0;

	private Timer gameClock;
    private Timer levelClock;
    
    static int currentLevel = 1;

	/**
	 * The main constructor for the GUIGameController class.
	 * It instantiates GUIGame and GUIGameInterface as well as starts a timer for the game.
	 */
	public GUIGameController() {

		game = new GUIGame();
		gui = new GUIGameInterface(this, this, game);

		gameClock = new Timer(7, this);
		gameClock.setActionCommand("TIMER");
		gameClock.start();
        
        levelClock = new Timer(30000, this);
        levelClock.setActionCommand("LEVEL");
        levelClock.start();

		game.initBoard();
        
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
            if (currentLevel == 1) {
                if(enemyCounter >= 40) {
                    game.addEnemy(new Enemy(new Random().nextInt(GUIGame.SCREEN_WIDTH),0,32,32));
                    enemyCounter = 0;
                }
            } else if (currentLevel == 2) {
                if(enemyCounter >= 25) {
                    game.addEnemy(new Enemy(new Random().nextInt(GUIGame.SCREEN_WIDTH),0,32,32));
                    enemyCounter = 0;
                }
            } else {
                if(enemyCounter >= 5) {
                    game.addEnemy(new Enemy(new Random().nextInt(GUIGame.SCREEN_WIDTH),0,32,32));
                    enemyCounter = 0;
                 }
            }
			if (game.playerIsDead()) {
				gui.gameOver();
                currentLevel = 1;
                levelClock.stop();
			} else {
				game.move();
				game.playerShoot();
				game.enemiesShoot();
				game.checkCollisions();
			}

			gui.repaint();
            
        } else if (e.getActionCommand().equals("LEVEL")) {
            if (currentLevel <= 5) {
                this.currentLevel ++;
                System.out.println("************************\nLevel " + this.currentLevel + "\n************************");
            }
		} else if (e.getActionCommand().equals("Play Again")) {
			gui.newGame();
			game = new GUIGame();
			gui.setGame(game);
			levelClock = new Timer(30000, this);
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
    
    public int getLevel() {
        return currentLevel;
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