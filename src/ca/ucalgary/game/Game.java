package ca.ucalgary.game;

import java.util.ArrayList;
import java.util.Iterator;

import ca.ucalgary.collectable.Collectable;
import ca.ucalgary.collectable.HealthCollectable;
import ca.ucalgary.collectable.IncreasedFireRate;
import ca.ucalgary.collectable.Money;
import ca.ucalgary.enemy.Enemy;
import ca.ucalgary.player.Player;
import ca.ucalgary.projectiles.EnemyProjectile;
import ca.ucalgary.projectiles.PlayerProjectile;
import ca.ucalgary.projectiles.Projectile;

/**
 * Game implements the framework for a space shooter game.
 */

public abstract class Game {

	private ArrayList<Enemy> enemies;
	private ArrayList<Collectable> collectables;
	private ArrayList<Projectile> projectiles;

	private Player player;

	private int currentLevel;

	/**
	 * Default Constructor
	 * initializes lists and current level
	 */
	public Game() {
		enemies = new ArrayList<Enemy>();
		projectiles = new ArrayList<Projectile>();
		collectables = new ArrayList<Collectable>();

		currentLevel = 1;
	}

	/**
	 * Constructor used for testing purposes that takes given lists of enemies,
	 * projectiles, and collectables as parameters. This privacy leak is necessary to alter lists for testing
	 * @param enemies the ArrayList of enemies
	 * @param projectiles the ArrayList of projectiles
	 * @param collectables the ArrayList of collectables
	 * @param player the given player
	 */
	public Game(ArrayList<Enemy> enemies, ArrayList<Collectable> collectables, ArrayList<Projectile> projectiles, Player player) {
		this.enemies = enemies;
		this.collectables = collectables;
		this.projectiles = projectiles;
		this.player = player;
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
	 * Causes enemies to fire projectiles.
	 */
	public void enemiesShoot() {
		for (Enemy enemy : enemies) {
			if (enemy.getHasAShot()) {
				Projectile enemyShot = enemy.shoot();
				if (enemyShot!=null) {
					projectiles.add(enemyShot);
				}
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
	 * Retrieves the ArrayList of enemies. This privacy leak
	 * is necessary so the interface/console can draw the correct set
	 * of objects.
	 * @return list of enemies
	 */
	protected ArrayList<Enemy> getEnemies(){
		return enemies;
	}

	/**
	 * Retrieves the ArrayList of enemies
	 * @return enemies the ArrayList of enemies
	 */
	public ArrayList<Enemy> getNewEnemies() {
		ArrayList<Enemy> returnList = new ArrayList<Enemy>();

		for (Enemy e : enemies) {
			returnList.add(new Enemy(e));
		}

		return returnList;
	}
	
	/**
	 * Retrieves the ArrayList of collectables. This privacy leak
	 * is necessary so the interface/console can draw the correct set
	 * of objects.
	 * @return list of collectables
	 */
	protected ArrayList<Collectable> getCollectables(){
		return collectables;
	}

	/**
	 * Retrieves the ArrayList of collectables
	 * @return collectables the ArrayList of collectables
	 */
	public ArrayList<Collectable> getNewCollectables() {
		ArrayList<Collectable> returnList = new ArrayList<Collectable>();

		for (Collectable c : collectables) {
			if (c instanceof HealthCollectable) {
				returnList.add(new HealthCollectable((HealthCollectable) c));
			} else if (c instanceof IncreasedFireRate) {
				returnList.add(new IncreasedFireRate((IncreasedFireRate) c));
			} else if (c instanceof Money) {
				returnList.add(new Money((Money) c));
			}
		}

		return returnList;
	}
	
	/**
	 * Retrieves the ArrayList of projectiles. This privacy leak
	 * is necessary so the interface/console can draw the correct set
	 * of objects.
	 * @return list of projectiles
	 */
	protected ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}

	/**
	 * Retrieves the ArrayList of projectiles
	 * @return projectiles the ArrayList of projectiles
	 */
	public ArrayList<Projectile> getNewProjectiles() {
		ArrayList<Projectile> returnList = new ArrayList<Projectile>();

		for (Projectile p : projectiles) {
			if (p instanceof PlayerProjectile) {
				returnList.add(new PlayerProjectile((PlayerProjectile) p));
			} else if (p instanceof EnemyProjectile) {
				returnList.add(new EnemyProjectile((EnemyProjectile) p));
			}
		}

		return returnList;
	}

	/** 
	 * Retrieves the player object. This privacy leak is necessary so the motion listener 
	 * can move the current player.
	 * @return player the current player
	 */
	protected Player getPlayer() {
		return player;
	}

	/**
	 * Retrieves a copy of the player object
	 * @return player copy of current player
	 */
	public Player getNewPlayer() {
		return new Player(player);
	}

	/**
	 * Sets the player. This privacy leak is necessary so the subclasses can pass in a specific object
	 * @param player the given player to set
	 */
	protected void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Sets the player to a copy of the player
	 * @param player the given player to set
	 */
	public void setNewPlayer(Player player) {
		this.player = new Player(player);
	}

	/**
	 * @return the currentLevel
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}


}
