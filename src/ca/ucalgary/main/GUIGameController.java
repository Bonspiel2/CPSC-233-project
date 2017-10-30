package ca.ucalgary.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class GUIGameController implements ActionListener, KeyListener {
	
	private GUIGameInterface gui;
	private GUIGame game;
	
	private int enemyCounter = 0;
	
	
	private Timer gameClock;
	
	public GUIGameController() {
		
		game = new GUIGame();
		gui = new GUIGameInterface(this, this, game);
		
		gameClock = new Timer(50, this);
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
				game.addEnemy(new Enemy());
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

}
