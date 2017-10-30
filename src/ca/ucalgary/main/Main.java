package ca.ucalgary.main;

import java.util.Scanner;

/**
 * Main prompts user to choose which game they want to play 
 * and launches either the text or the gooey game based on 
 * user input.
 *
 * @author Group 3
 *
 */

public class Main {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		System.out.print("Type 1 to play the text game or 2 to play the gooey game.");
        int gameChoice = in.nextInt();
        
        
        if(gameChoice == 1){
            TextGame textgame = new TextGame();
        	System.out.println("You chose the text game! Control your ship using WASD.");
        	textgame.run();
        }
        else if (gameChoice == 2){
            GUIGameController guiGame = new GUIGameController();
        	System.out.println("You chose the gooey game! Control your ship using WASD.");
        	GUIGameInterface gui = guiGame.getGUI();
        	gui.setVisible(true);
        	
        }

        
      
	}
}
