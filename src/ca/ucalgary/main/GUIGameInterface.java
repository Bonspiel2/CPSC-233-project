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

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIGameInterface extends JPanel {

	private JFrame frame;
	private GUIGame game;

	public GUIGameInterface(ActionListener a, KeyListener k,
			MouseMotionListener m, GUIGame g) {
		game = g;
		frame = new JFrame("Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);
		frame.setVisible(true);

		frame.getContentPane().addKeyListener(k);
		frame.addKeyListener(k);

		addMouseMotionListener(m);

		setSize(GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT);

		setBackground(Color.BLACK);

		frame.getContentPane().setFocusable(true);
		requestFocus();

		frame.getContentPane().add(this);

		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		frame.getContentPane().setCursor(blankCursor);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

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
