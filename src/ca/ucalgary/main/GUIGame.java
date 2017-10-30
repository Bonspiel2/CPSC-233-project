package ca.ucalgary.main;

import javax.swing.Timer;

public class GUIGame extends Game{

	Timer gameClock;
	GUIGameInterface gui;
	
	public static final int SCREEN_WIDTH = 350;
	public static final int SCREEN_HEIGHT = 500;


	// constructor
	public GUIGame() {
		super();

		Player player = new Player(SCREEN_WIDTH/2, SCREEN_HEIGHT*7/8, 15, 15, 5);

		player.setMaxX(SCREEN_WIDTH);
		player.setMaxY(SCREEN_HEIGHT);
        
        player.setStep(5);

		super.setPlayer(player);
	}

	@Override
	public void run() {
	}

	@Override
	public void draw() {
	}

	@Override
	public String getInput() {
		return null;
	}
	// sets up the board with enemies
	public void initBoard() {
		for(int i = 0; i < TextGame.ROWS-6; i++) {
			Enemy enemy = new Enemy(i);
			addEnemy(enemy);
		}

	}

	public void movePlayer(String string) {
		player.move(string);
	}

	public void movePlayer(int x, int y) {
		player.setX(x- player.getWidth()/2);
		player.setY(y - player.getHeight()/2);
		
	}




}
