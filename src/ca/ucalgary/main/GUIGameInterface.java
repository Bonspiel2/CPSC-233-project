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
    	
        frame.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        frame.setVisible(true);
        
        frame.addKeyListener(k);
        
        setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        setBackground(Color.BLACK);
        
        setFocusable(true);
        requestFocus();
        
        frame.getContentPane().add(this);

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



