package ca.ucalgary.main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.Timer;


public class GUIGameInterface extends JPanel {
	
	private JFrame frame;
	private GUIGame game;
	
    public GUIGameInterface(ActionListener a, KeyListener k, GUIGame g) {
    	game = g;
    	frame = new JFrame("Space Invaders");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
        frame.setSize(400, 500);
        frame.setVisible(true);
        
        frame.addKeyListener(k);
        
        setSize(400, 500);

        setBackground(Color.BLACK);
        
        setFocusable(true);
        requestFocus();
        
        frame.getContentPane().add(this);

    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Board board = new Board(g);
        
        game.player.draw(board);
        
        for (Enemy enemy : game.enemies) {
            enemy.draw(board);
        }
                
        for (Projectile projectile : game.projectiles) {
            projectile.draw(board);
        }
        
        for (Collectable collectable : game.collectables) {
            collectable.draw(board);
        }


    }
}



