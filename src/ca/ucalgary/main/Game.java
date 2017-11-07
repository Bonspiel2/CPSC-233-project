package ca.ucalgary.main;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Game {

	private ArrayList<Enemy> enemies;
	private ArrayList<Collectable> collectables;
	private ArrayList<Projectile> projectiles;

	private Player player;

	public Game() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();
	}

	/**
	 * Checks if collisions have occured between enemies and projectiles, 
	 * between collectables and the player, or between enemies and the player.
	 * If a collision occurs, the given object(s) is/are removed from their 
	 * array list (and therefore the board).
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
						collectables.add(e.createCollectable());
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
				enemyItr.remove();
			}
		}
	}

	/**
	 * Moves each object to its next location on the screen.
	 */
	public void move() {

		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (!enemy.move()) {
				enemies.remove(i);
				i--;
			}
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			if(!projectile.move()) {
				projectiles.remove(i);
				i--;
			}
		}

		for(int i = 0; i < collectables.size(); i++) {
			Collectable collectable = collectables.get(i);
			if(!collectable.move()) {
				collectables.remove(i);
				i--;
			}
		}
	}

	/**
	 * Adds an enemy.
	 * @param e the given enemy to add
	 */
	public void addEnemy(Enemy e) {
		enemies.add(new Enemy(e));
	}
	
	/**
	 * Causes the player to fire a projectile.
	 */
	public void playerShoot() {
		Projectile p = player.shoot();

		if (p!=null) {
			projectiles.add(p);
		}
	}
	/**
	 * Causes an enemy to fire a projectile.
	 */
	public void enemiesShoot() {
		for (Enemy enemy : enemies) {
			if (enemy.getHasAShot()) {
				Projectile enemyShot = enemy.shoot();
				projectiles.add(enemyShot);
			}
		}
	}
	
	/**
	 * Indicates whether the player is still alive or not.
	 * @return boolean returns true if the player's health is zero (or less)
	 */
	public boolean playerIsDead() {
		return player.getHealth() <= 0;
	}

	/**
	 * Retrieves the ArrayList of enemies
	 * @return enemies the ArrayList of enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * Retrieves the ArrayList of collectables
	 * @return collectables the ArrayList of collectables
	 */
	public ArrayList<Collectable> getCollectables() {
		return collectables;
	}

	/**
	 * Retrieves the ArrayList of projectiles
	 * @return projectiles the ArrayList of projectiles
	 */
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	/** 
	 * Retrieves the player object
	 * @return player the current player
	 */
	protected Player player() {
		return player;
	}
	
	/**
	 * Retrieves a copy of the player object
	 * @return player copy of current player
	 */
	public Player getPlayer() {
		return new Player(player);
	}
    
	/**
	 * Sets the player
	 * @param player the given player to set
	 */
    public void setPlayer(Player player) {
        this.player = new Player(player);
    }

}
