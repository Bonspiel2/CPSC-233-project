package ca.ucalgary.main;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * The GUIGameInterface class controls all aspects of displaying the game graphically.
 * It sets up the JFrame as well as handles the drawing of all enemies, projectiles, 
 * collectables, and the player. 
 *
 */

public class GUIGameInterface extends JPanel {

	private JFrame frame;
	private JButton playAgain;
	private GUIGame game;
	private boolean gameOver;
	private Cursor blankCursor;

	public GUIGameInterface(ActionListener a, KeyListener k,
			MouseMotionListener m, GUIGame g) {
		game = g;
		frame = new JFrame("Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
		frame.setVisible(true);

		frame.getContentPane().addKeyListener(k);
		frame.addKeyListener(k);
		
		playAgain = new JButton("Play Again");
		playAgain.addActionListener(a);
		playAgain.setVisible(false);
		playAgain.setEnabled(false);
		
		add(playAgain);

		addMouseMotionListener(m);

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

			game.player.draw(g);

			for (Enemy enemy : game.enemies) {
				enemy.draw(g);
			}

			for (Projectile projectile : game.projectiles) {
				projectile.draw(g);
			}

			for (Collectable collectable : game.collectables) {
				collectable.draw(g);
			}
		}
	}
	
	public void gameOver() {
		gameOver = true;
		playAgain.setVisible(true);
		playAgain.setEnabled(true);
		frame.getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void newGame() {
		gameOver = false;
		playAgain.setVisible(false);
		playAgain.setEnabled(false);
		frame.getContentPane().setCursor(blankCursor);
	}
	
	public void setGame(GUIGame g) {
		this.game = g;
	}
}
