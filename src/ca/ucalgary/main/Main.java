package ca.ucalgary.main;

public class Main {
	public static void main(String[] args) {
        System.out.print("Text-Based Space Invaders \nHit enter to start. Control your ship using WASD.");

		TextGame game = new TextGame();
		game.run();
		
	}
}
