package ca.ucalgary.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.Timer;

public class GUIGameController implements ActionListener, KeyListener, MouseMotionListener {
	
	private GUIGameInterface gui;
	private GUIGame game;
	
	private int enemyCounter = 0;
	
	
	private Timer gameClock;
	
	public GUIGameController() {
		
		game = new GUIGame();
		gui = new GUIGameInterface(this, this, this, game);

		gameClock = new Timer(10, this);
        gameClock.setActionCommand("TIMER");
        gameClock.start();
        
        game.initBoard();
	}
	@Override
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
                System.out.println("Up");
                game.movePlayer("W");
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
             System.out.println("Left");
             game.movePlayer("A");
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
                System.out.println("Down");
                game.movePlayer("S");
        }            
        if (e.getKeyCode() == KeyEvent.VK_D) {
                System.out.println("Right");
                game.movePlayer("D");
        }
    }

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
