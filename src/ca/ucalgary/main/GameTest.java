
package ca.ucalgary.main;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * Tests functionality of Game class
 */

public class GameTest {

    /**
     * Tests to ensure arraylists of enemies, projectiles, and collectables are being created.
     */
    @Test
    public void test_mainConstructorGUI() {
        GUIGame game = new GUIGame();
        assertEquals("Game does not have ArrayList of enemies", 0, game.getEnemies().size());
        assertEquals("Game does not have ArrayList of projectiles", 0, game.getProjectiles().size());
        assertEquals("Game does not have ArrayList of collectables", 0, game.getCollectables().size());
    }
    
    /**
     * Tests to ensure checkCollisions is working properly between enemy and player.
     */
    @Test
    public void test_GUICollisionsEnemyPlayer() {
        Player player = new Player(0, 0, 5);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(0,0)));
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>(Arrays.asList(new PlayerProjectile(1,1)));
        ArrayList<Collectable> collectables = new ArrayList<Collectable>(Arrays.asList(new HealthCollectable(1,1,4)));

        GUIGame game = new GUIGame(enemies, collectables, projectiles, player);
        game.checkCollisions();
        assertEquals("Player and enemy should collide", 4, player.getHealth());
    }
    
    /**
     * Tests to ensure checkCollisions is working properly between enemy projectile and player.
     */
    @Test
    public void test_GUICollisionsProjectilePlayer() {
        Player player = new Player(0, 0, 5);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(1,1)));
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>(Arrays.asList(new EnemyProjectile(0,0)));
        ArrayList<Collectable> collectables = new ArrayList<Collectable>(Arrays.asList(new HealthCollectable(2,2,4)));
        
        GUIGame game = new GUIGame(enemies, collectables, projectiles, player);
        game.checkCollisions();
        assertEquals("Enemy projectile and player should collide", 4, player.getHealth());
        assertEquals("Enemy projectile should be removed", 0, projectiles.size());

    }
    
    /**
     * Tests to ensure checkCollisions is working properly between enemy projectile, health, and player.
     */
    @Test
    public void test_GUIThreeCollisionsHealthPlayerEnemy() {
        Player player = new Player(0, 0, 5);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(1,1)));
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>(Arrays.asList(new EnemyProjectile(0,0)));
        ArrayList<Collectable> collectables = new ArrayList<Collectable>(Arrays.asList(new HealthCollectable(0,0,4)));
        
        GUIGame game = new GUIGame(enemies, collectables, projectiles, player);
        game.checkCollisions();
        assertEquals("Enemy projectile, player, and health should collide, player health should be unchanged", 5, player.getHealth());
        assertEquals("Enemy projectile should be removed", 0, projectiles.size());
        assertEquals("Health should be removed", 0, collectables.size());


    }
    
    /**
     * Tests to ensure checkCollisions is working properly between enemy projectile, money, and player.
     */
    @Test
    public void test_GUIThreeCollisionsMoneyPlayerEnemy() {
        Player player = new Player(0, 0, 5);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(1,1)));
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>(Arrays.asList(new EnemyProjectile(0,0)));
        ArrayList<Collectable> collectables = new ArrayList<Collectable>(Arrays.asList(new Money(0,0,4)));
        
        GUIGame game = new GUIGame(enemies, collectables, projectiles, player);
        game.checkCollisions();
        assertEquals("Enemy projectile, player, and money should collide, player health should be decrease by one", 4, player.getHealth());
        assertEquals("Enemy projectile, player, and money should collide, player score should be increase by one", 1, player.getScore());
        assertEquals("Money should be removed", 0, collectables.size());
        assertEquals("Enemy projectile should be removed", 0, projectiles.size());

    }
    
    /**
     * Tests to ensure checkCollisions is working properly player projectile and enemy.
     */
    @Test
    public void test_GUICollisionsProjectileEnemy() {
        Player player = new Player(1, 1, 5);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(Arrays.asList(new Enemy(0,0)));
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>(Arrays.asList(new PlayerProjectile(0,0)));
        ArrayList<Collectable> collectables = new ArrayList<Collectable>(Arrays.asList(new Money(1,2,4)));
        
        GUIGame game = new GUIGame(enemies, collectables, projectiles, player);
        game.checkCollisions();
        assertEquals("Player projectile should kill enemy", 0, enemies.size());
        assertEquals("Player projectile should be removed", 0, projectiles.size());

    }





}
