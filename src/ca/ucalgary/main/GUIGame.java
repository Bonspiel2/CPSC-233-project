package ca.ucalgary.main;

import javax.swing.Timer;

public class GUIGame extends Game{

	Timer gameClock;
	GUIGameInterface gui;


	// constructor
	public GUIGame() {
		super();

		Player player = new Player(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT*7/8, 5);

		player.setMaxX(Game.SCREEN_WIDTH);
		player.setMaxY(Game.SCREEN_HEIGHT);

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




}
