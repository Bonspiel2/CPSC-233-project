package ca.ucalgary.main;


import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;


public class GooeyInterface extends JPanel {
    
    TextGame game;
    GooeyLogic logic;
    
    // constructor
    public GooeyInterface() {
        JFrame frame = new JFrame();
        frame.setTitle("Space Invaders");
        frame.setSize(400, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.logic = new GooeyLogic(this);
        setSize(400,500);
        this.setBackground(Color.BLACK);
        frame.getContentPane().add(this);
    }
    
    public GooeyInterface(TextGame game) {
        this();
        this.game = game;

        
    }
    
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Board board = new Board(graphics);
        
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
    
    public static void main(String[] args) {
        
        GooeyInterface gui = new GooeyInterface();
        
        
//        gui.pack();
        gui.setVisible(true);
    }


}
