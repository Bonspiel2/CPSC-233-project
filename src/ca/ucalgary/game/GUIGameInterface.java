package ca.ucalgary.game;

import java.awt.Color;
import java.awt.Font;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.ucalgary.collectable.Collectable;
import ca.ucalgary.enemy.Enemy;
import ca.ucalgary.projectiles.Projectile;

import java.io.*;
import java.util.ConcurrentModificationException;

/**
 * The GUIGameInterface class handles all the user interaction and controls all aspects 
 * of displaying the game graphically.
 * It sets up the JFrame as well as handles the drawing of all enemies, projectiles, 
 * collectables, and the player. 
 *
 */

public class GUIGameInterface extends JPanel {
	
	private static final int SCORE_LABEL_X = 10;
	private static final int SCORE_LABEL_Y = 15;
	
	private static final int LEVEL_LABEL_X = 290;
	private static final int LEVEL_LABEL_Y = 15;
    
    public static boolean gameOver;

	private JFrame frame;
	private JButton playAgain;
	private GUIGame game;
	private Cursor blankCursor;
	private int finalScore;
	private int highScore;
	private int displayScore;

	
	/**
	 * The main constructor for GUIGameInterface.
	 * Initializes the frame and allows the player to click 'play again' when they lose etc.
	 * There is a privacy leak for all three parameters in the constructor for GUIGameInterface
	 * The privacy leak is neccasary here so that the GUIGame interface is interacting with the 
	 * same objects as in the GUIGame controller.
	 * 
	 * @param actionListener Action Listener for player actions 
	 * @param mouseMotionListener MouseMotionListener for mouse motion events
	 * @param guiGame the GUIGame instance that is being handled/ drawn by 
	 * 
	 */
	public GUIGameInterface(ActionListener actionListener,
			MouseMotionListener mouseMotionListener, GUIGame guiGame) {
		game = guiGame;
		frame = new JFrame("Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
		frame.setVisible(true);

		playAgain = new JButton("Play Again");
		playAgain.addActionListener(actionListener);
		playAgain.setVisible(false);
		playAgain.setEnabled(false);

		add(playAgain);

		addMouseMotionListener(mouseMotionListener);

		setSize(GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);

		setBackground(Color.BLACK);

		frame.getContentPane().setFocusable(true);
		requestFocus();

		frame.getContentPane().add(this);

		//https://stackoverflow.com/questions/1984071/how-to-hide-cursor-in-a-swing-application
		//github link for invisible cursor
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);

		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");

		frame.getContentPane().setCursor(blankCursor);

	}

	/**
	 * The paintComponent method draws all projectiles, enemies, collectables, and the player
	 * by drawing to a graphics object.
	 * @param g the given graphics object.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!gameOver) {
			
			int curLevel = game.getCurrentLevel();

			game.getPlayer().draw(g);

			try {
				for (Enemy enemy : game.getEnemies()) {
					enemy.draw(g);
				}
			} catch (ConcurrentModificationException cme) {
				//this error does nothing its just annoying so we swept it under the rug
			}

			for (Projectile projectile : game.getProjectiles()) {
				projectile.draw(g);
			}

			for (Collectable collectable : game.getCollectables()) {
				collectable.draw(g);
			}
			g.setColor(Color.WHITE);
			g.drawString("Score: " + game.getPlayer().getScore(), SCORE_LABEL_X, SCORE_LABEL_Y);
            
            g.setColor(Color.WHITE);
            g.drawString("Level: " + curLevel, LEVEL_LABEL_X, LEVEL_LABEL_Y);
            
            if(curLevel == 1) {
                setBackground(Color.BLACK);
            } else if (curLevel == 2)  {
                setBackground(new Color(8, 71, 173));
            } else if (curLevel == 3)  {
                setBackground(new Color(96, 5, 99));
            } else if (curLevel == 4)  {
                setBackground(new Color(209, 4, 103).darker());
            } else if (curLevel == 5)  {
                setBackground(new Color(165, 17, 6));
            }
		}
		else {
            if (game.getCurrentLevel() == 6 && !game.playerIsDead()) {
                game.getPlayer().setHealth(100);
                g.setColor(Color.GREEN);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 35));
                g.drawString("You Win!", 100, 150);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Helvetica", Font.BOLD, 30));
                g.drawString("Score: " + finalScore, 100, 200);
                g.drawString("High Score: " + displayScore, 70, 300);

                
            } else {
                g.setColor(Color.RED);
                g.setFont(new Font("Times New Roman", Font.PLAIN, 35));
                g.drawString("You Died", 100, 150);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Helvetica", Font.BOLD, 30));
                g.drawString("Score: " + finalScore, 100, 200);
                g.drawString("High Score: " + displayScore, 70, 300);
            }

		}
	}

	
	/**
	 * gameOver is run when the player dies
	 * brings up the players score, current highscore and updates the highscore
	 * if higher than the current. also allows the player to play again by clicking
	 * the 'play again' button.
	 */
	public void gameOver() {
        gameOver = true;
        finalScore = game.getPlayer().getScore();
        setBackground(Color.BLACK);
        if (game.getCurrentLevel() != 6) {
            playAgain.setVisible(true);
            playAgain.setEnabled(true);
        }
		frame.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		try {
			FileReader reader = new FileReader("HighScore.txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			highScore = Integer.parseInt(line);
			buffReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find high score");
		} catch (IOException e) {
			System.out.println("Error reading high score");
		}

		if (finalScore > highScore) {
			displayScore = finalScore;
			try {
				FileWriter writer = new FileWriter("HighScore.txt", false);
				BufferedWriter buffWriter = new BufferedWriter(writer);
				buffWriter.write(Integer.toString(finalScore));
				buffWriter.close();
			} catch (IOException e) {
				System.out.println("Could not record high score.");
			}
		} else {
			displayScore = highScore;
		}
	}

	/**
	 * starts a new game if the player selects play again.
	 */
	public void newGame() {
		gameOver = false;
		playAgain.setVisible(false);
		playAgain.setEnabled(false);
		frame.getContentPane().setCursor(blankCursor);
	}
	
	/**
	 * when a new game is run through the play again button
	 * a new game is created and set to a new instance of the GUIGame
	 * @param g the GUIGame that is being set as the latest instance of game.
	 * This method has a privacy leak that is required as this class need to be
	 * working with the same instance of GUIGame as Game
	 */
	public void setGame(GUIGame g) {
		this.game = g;
	}

    
}
