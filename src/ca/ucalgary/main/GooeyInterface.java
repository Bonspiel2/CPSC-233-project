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


public class GooeyInterface extends JPanel implements ActionListener, KeyListener {
    
    GooeyGame game;
    Timer gameClock;
    
    // constructor
    public GooeyInterface() {
        JFrame frame = new JFrame();
        frame.setTitle("Space Invaders");
        frame.setSize(400, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        this.setBackground(Color.BLACK);
        frame.getContentPane().add(this);
        
        frame.getContentPane().addKeyListener(this);
        frame.getContentPane().setFocusable(true);
        
        gameClock = new Timer(10, this);
        gameClock.setActionCommand("TIMER");
        gameClock.start();

    }
    
    public GooeyInterface(GooeyGame game) {
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
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 'W':
                System.out.println("Up");
                game.player.move("W");
                break;
            case 'A':
                System.out.println("Left");
                game.player.move("A");
                break;
            case 'S':
                System.out.println("Down");
                game.player.move("S");
                break;
            case 'D':
                System.out.println("Right");
                game.player.move("D");
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent event) {
    }
    @Override
    public void keyReleased(KeyEvent event) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("TIMER")) {
            // every time the timer goes off, the enemies should move by a given amount
            this.repaint();
        }
    }
    
    public static void main(String[] args) {
        
        GooeyInterface gui = new GooeyInterface();
        gui.setVisible(true);
        
    }
    
}



