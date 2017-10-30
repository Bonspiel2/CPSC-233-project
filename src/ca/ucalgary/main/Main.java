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
		System.out.print("Type 1 to play the text game or 2 to play the gui game.");
        int gameChoice = in.nextInt();
        
        
        if(gameChoice == 1){
        	boolean running = true;
        	
        	while (running) {
        		TextGame textgame = new TextGame();
            	System.out.println("Welcome to the Text Game! Control your ship using WASD.");
            	textgame.run();
            	System.out.println("Press A to play again");
            	if (!in.next().toUpperCase().equals("A")) {
            		running = false;
            	}
        	}
            
        }
        else if (gameChoice == 2){
            GUIGameController guiGame = new GUIGameController();
        	System.out.println("You chose the gooey game! Control your ship using WASD.");
        	GUIGameInterface gui = guiGame.getGUI();
        	gui.setVisible(true);
        	
        }

        
      
	}
}
