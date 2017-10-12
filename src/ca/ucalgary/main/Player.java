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
		}
		
		public Projectile shoot() {
			return new Projectile(x, y-1);
		}
		
		public String draw() {
			return ship;
		}
}
