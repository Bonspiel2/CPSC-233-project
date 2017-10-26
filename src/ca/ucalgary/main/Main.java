package ca.ucalgary.main;

import java.util.Scanner;

/**
 * Main launches the game after prompting the user to play.
 *
 * @author Group 3
 *
 */

public class Main {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		System.out.print("Type 1 to play the text game or 2 to play the gooey game.");
        int gameChoice = in.nextInt();
        TextGame g = new TextGame();
        
        
        if(gameChoice == 1){
        	System.out.println("You chose the text game! Control your ship using WASD.");
        	g.run();
        }
        else if (gameChoice == 2){
        	System.out.println("You chose the gooey game! Control your ship using WASD.");
        	GooeyInterface gui = new GooeyInterface(g);
        }

        
      
	}
}
