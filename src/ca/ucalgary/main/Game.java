package ca.ucalgary.main;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Game {

	public ArrayList<Enemy> enemies;
	public ArrayList<Collectable> collectables;
	public ArrayList<Projectile> projectiles;

	public Player player;

	public Game() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();
	}
    
    

	/**
	 * Updates the game every iteration as well as creates new objects when needed
	 * If the players health reaches zero, this method will quit the game
	 * 
	 * @author Cole
	 */
	public abstract void run();

	/**
	 * Checks if collisions have occured between enemies and projectiles, 
	 * between collectables and the player, or between enemies and the player.
	 * If a collision occurs, the given object(s) is/are removed from their 
	 * array list (and therefore the board).
	 *
	 * @author Quinn
	 *
	 */
	public void checkCollisions() {

		for (int projIndex = 0; projIndex < projectiles.size(); projIndex++) {
			Projectile p = projectiles.get(projIndex);

			if (p instanceof PlayerProjectile) {
				boolean collided = false;
				for (int enemyIndex = 0; enemyIndex < enemies.size() && !collided; enemyIndex++) {
					Enemy e = enemies.get(enemyIndex);
					
					if (((PlayerProjectile) p).collidedWith(e)) {
						collectables.add(new Collectable(e.getX(),e.getY()));
						projectiles.remove(projIndex);
						projIndex--;
						enemies.remove(enemyIndex);
						enemyIndex--;
						collided = true;
					}
				}
			} else if (p instanceof EnemyProjectile) {
				if (((EnemyProjectile) p).collidedWith(player)) {
					projectiles.remove(projIndex);
					projIndex--;
					int health = player.getHealth() - 1;
					player.setHealth(health);
				}
			}
		}

		// check collisions between collectables and player
		for (Iterator<Collectable> collecItr = collectables.iterator(); collecItr.hasNext();) {
			Collectable collec = collecItr.next();
			if (player.collidedWith(collec)) {
				collecItr.remove();
			}
		}

		// check collisions between enemies and player
		for (Iterator<Enemy> enemyItr = enemies.iterator(); enemyItr.hasNext();) {
			Enemy enemy = enemyItr.next();
			// decrease player health by one if collision occurs
			if (enemy.collidedWith(player)) {
				int health = player.getHealth() - 1;
				player.setHealth(health);
			}
		}
	}

	/**
	 * Draws the enemies, projectiles, collectables, and player on the gameboard.
	 *
	 * @author Quinn
	 * @return board the fully drawn gameboard.
	 */
	public abstract void draw();

	/**
	 * Prints the game board, the player's health 
	 * and the players score for each turn.
	 * @author Lily and Quinn
	 */

	/**
	 * Reads user input with every turn to determine whether 
	 * the player moves the the right or to to the left.
	 * @return String that indicates the direction the player 
	 * desires to move.
	 * 
	 * @author Lily
	 */
	public abstract String getInput();

	/**
	 * Moves each object to its next location on the screen
	 * @param s The string command given as input for the direction 
	 * for the player to move
	 * @author Matt
	 */

	public void move() {

		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();	
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			if(projectile.move()) {
				projectiles.remove(i);
			}
		}

		for(int i = 0; i < collectables.size(); i++) {
			Collectable collectable = collectables.get(i);
			if(!collectable.move()) {
				collectables.remove(i);
			}
		}
	}

	/**
	 * Initializes the board blank and then with enemies up to the 6th
	 * row from the bottom
	 * @return board The text board after being initialized
	 * @author Matt
	 */

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public void playerShoot() {
		Projectile p = player.shoot();

		if (p!=null) {
			projectiles.add(p);
		}
	}

	public void enemiesShoot() {
		for (Enemy enemy : enemies) {
			if (enemy.getHasAShot()) {
				Projectile enemyShot = enemy.shoot();
				projectiles.add(enemyShot);
			}
		}
	}
	
	public boolean playerIsDead() {
		return player.getHealth() <= 0;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Collectable> getCollectables() {
		return collectables;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public Player getPlayer() {
		return player;
	}
    
    public void setPlayer(Player player) {
        this.player = player;
    }

}
