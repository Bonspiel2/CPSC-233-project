package ca.ucalgary.main;

public class Player {

		private int x;
		private int y;
		
		private int health;
		private int score;
		
		private String ship;
		
		public Player(int x, int y, int health) {
			this.x = x;
			this.y = y;
			
			this.health = health;
			this.score = 0;
			
			this.ship = "A";
		}
		
		public void move(String[][] board, String s) {
			 if (s.equals("A")) {
				 if (x - 1 >= 0) {
					 x--;
				 }
			 } else if (s.equals("D")) {
				 if (x + 1 >= board[0].length) {
					 x++;
				 }
			 }
		}
		
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
		
		public Projectile shoot() {
			return new Projectile(x, y-1);
		}
		
		public void draw(String[][] board) {
            board[x][y] = ship;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
    
        public void setHealth(int newHealth) {
            this.health = newHealth;
        }
    
        public int getHealth() {
            return health;
        }
}
