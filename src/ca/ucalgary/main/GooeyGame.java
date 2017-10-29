package ca.ucalgary.main;

import javax.swing.Timer;

public class GooeyGame extends Game{
    
    Timer gameClock;
    GooeyInterface gui;
    

    // constructor
    public GooeyGame() {
        super();
        
        Player player = new Player(3, 8, 5);

        player.setMaxX(7);
        player.setMaxY(9);
        
        super.setPlayer(player);
    }
    
    @Override
    public void run() {
        
        initBoard();
        checkCollisions();
        addEnemy(new Enemy(0));
        playerShoot();
        enemiesShoot();

    }
    
    @Override
    public void draw() {
        // this is drawn by the paintComponent in GooeyInterface
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


    

}
