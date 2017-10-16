package ca.ucalgary.main;
/**
 * The player class allows the game to interact with the 
 * player's position, their health as well as their score.
 * The class updates the players position based on input,
 * and checks for collisions with other objects.
 * 
 * @author Cole
 */
public class Player {

		private int x;
		private int y;
		
		private int health;
		private int score;
		
		private String ship;
		
		
		/**
		 * Main constructor
		 * @param x new player's column value
		 * @param y new player's row value
		 * @param health new player's total health
		 */
		public Player(int x, int y, int health) {
			this.x = x;
			this.y = y;
			
			this.health = health;
			this.score = 0;
			
			this.ship = "A";
		}
		
		
		/**
		 * Moves player either left or right while keeping it on the
		 * screen
		 * @param s Either "A" or "D" denoting left or right
		 */
		public void move(String s) {
			 if (s.equals("A")) {
				 if (x - 1 >= 0) {
					 x--;
				 }
			 } else if (s.equals("D")) {
				 if ((x + 1) < TextGame.COLUMNS) {
					 x++;
				 }
			 }
		}
		
		/**
		 * Checks to see if the player has collided with a given collectable.
		 * @param c Object to check for collision
		 * @return Returns true if the player and the collectable occupy the
		 * same space
		 */
		public boolean collidedWith(Collectable c) {
			boolean collided = false;
			int cx = c.getX();
			int cy = c.getY();
			
			if (cx == x && cy == y) {
				score++;
				collided = true;
			}
			
			return collided;
		}
		
		/**
		 * Creates a projectile when the player shoots.
		 * @return the projectile object fired
		 */
		public Projectile shoot() {
			return new Projectile(x, y-1);
		}
		
		/**
		 * Draws ship character on the board
		 * @param board The text board
		 */
		public void draw(String[][] board) {
            board[y][x] = ship;
		}
		
		/**
		 * Gets the player's column value
		 * @return player's column value
		 */
		public int getX() {
			return x;
		}
		
		/**
		 * Sets the player's column value
		 * @param x New column value
		 */
		public void setX(int x) {
			this.x = x;
		}
		
		/**
		 * Gets the player's row value
		 * @return Player's row value
		 */
		public int getY() {
			return y;
		}

		/**
		 * Sets the player's row value
		 * @param y New row value
		 */
		public void setY(int y) {
			this.y = y;
		}
    
		/**
		 * Gets the player's health value
		 * @return player's health value
		 */
        public int getHealth() {
            return health;
        }
        
        /**
         * Sets the player's health value
         * @param health New health value
         */
        public void setHealth(int health) {
        	this.health = health;
        }

        /**
         * Gets the player's score value
         * @return player's score value
         */
		public int getScore() {
			return score;
		}
}
