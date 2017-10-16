package ca.ucalgary.main;

/**
 * Main launches the game after prompting the user to play.
 *
 * @author Group 3
 *
 */

public class Main {
	public static void main(String[] args) {
        System.out.print("Text-Based Space Invaders \nHit enter to start. Control your ship using WASD.");
		
        TextGame g = new TextGame();
        
        g.run();
	}
}
