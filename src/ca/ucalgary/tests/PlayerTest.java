
package ca.ucalgary.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ucalgary.collectable.HealthCollectable;
import ca.ucalgary.collectable.Money;
import ca.ucalgary.enemy.Enemy;
import ca.ucalgary.game.GUIGame;
import ca.ucalgary.player.Player;

/**
 * Tests functionality of the player class (movement, constructors, and collisions).
 */
public class PlayerTest {

    /**
     * Tests whether main constructor is properly initializing instance variables.
     */
    @Test
    public void test_mainTextConstructor() {
        Player player = new Player(0, 0, 5);
        assertEquals("Unexpected x coordinate.", 0, player.getX());
        assertEquals("Unexpected y coordinate.", 0, player.getY());
        assertEquals("Unexpected health value", 5, player.getHealth());
    }
    
    /**
     * Tests that player's health cannot be set to negative value.
     */
    @Test
    public void test_healthSetterNegative() {
        Player player = new Player(0, 0, 5);
        player.setHealth(-10);
        assertEquals("Unexpected health value.", 5, player.getHealth());
    }
    
    
    /**
     * Tests whether main constructor for GUI is properly initializing instance variables.
     */
    @Test
    public void test_mainGUIConstructor() {
        Player player = new Player(0, 0, 11, 10, 5);
        assertEquals("Unexpected x coordinate.", 0, player.getX());
        assertEquals("Unexpected y coordinate.", 0, player.getY());
        assertEquals("Unexpected health value", 5, player.getHealth());
        assertEquals("Unexpected width.", 11, player.getWidth());
        assertEquals("Unexpected height.", 10, player.getHeight());
    }
    
    /**
     * Tests whether copy constructor is correctly copying variable values.
     */
    @Test
    public void test_CopyConstructor() {
        Player player = new Player(50, 20, 15, 13, 2);
        Player playerCopy = new Player(player);
        assertEquals("Unexpected x coordinate.", 50, playerCopy.getX());
        assertEquals("Unexpected y coordinate.", 20, playerCopy.getY());
        assertEquals("Unexpected health value", 2, playerCopy.getHealth());
        assertEquals("Unexpected width.", 15, playerCopy.getWidth());
        assertEquals("Unexpected height.", 13, playerCopy.getHeight());
    }


    /**
     * Tests that player cannot move off left edge of board.
     */
    @Test
    public void test_moveLeftEdge() {
        Player player = new Player(0, 0, 5);
        player.move("A");
        assertEquals("Moved off edge of game board (left)", 0, player.getX());
    }
    
    /**
     * Tests that player cannot move off right edge of board.
     */
    @Test
    public void test_moveRightEdge() {
        Player player = new Player(GUIGame.SCREEN_WIDTH, 0, 5);
        player.move("D");
        assertEquals("Moved off edge of game board (right)", 350, player.getX());
    }
    
    /**
     * Tests that player cannot move off top edge of board.
     */
    @Test
    public void test_moveUpEdge() {
        Player player = new Player(0, 0, 5);
        player.move("W");
        assertEquals("Moved off edge of game board (up)", 0, player.getY());
    }
    
    /**
     * Tests that player cannot move off bottom edge of board.
     */
    @Test
    public void test_moveDownEdge() {
        Player player = new Player(0, GUIGame.SCREEN_HEIGHT, 5);
        player.move("S");
        assertEquals("Moved off edge of game board (up)", 500, player.getY());
    }

    
    /**
     * Tests that player and enemy with same coordinates collide.
     */
    @Test
    public void test_CollisionWithEnemy() {
        Enemy enemy = new Enemy(0, 0);
        Player player = new Player(0, 0, 5);
        assertEquals("Created enemy and player both at (0,0), should collide.", true, player.collidedWith(enemy));
    }
    
    /**
     * Tests that player moving to same coordinates as enemy collides with the enemy.
     */
    @Test
    public void test_CollisionPlayerMoveTowardsEnemy() {
        Enemy enemy = new Enemy(1, 0);
        Player player = new Player(0, 0, 5);
        player.move("D");
        assertEquals("Created enemy and player, moved player, the two should collide.", true, player.collidedWith(enemy));
    }

    /**
     * Tests that player and enemy with different coordinates don't collide.
     */
    @Test
    public void test_NoCollisionWithEnemy() {
        Player player = new Player(0, 0, 5);
        Enemy enemy = new Enemy(1, 0);
        assertEquals(("Created enemy at (1,0) and player both at (0,0), should not collide"), false, player.collidedWith(enemy));
    }

    /**
     * Tests that score increases when player has same coordinates as money.
     */
    @Test
    public void test_CollisionWithMoneyScoreIncrease() {
        Player player = new Player(0, 0, 5);
        Money money = new Money(0, 0);
        player.collidedWith(money);
        assertEquals(("Created money and player both at (0,0), expected score is 1 since player and money collide"), 1, player.getScore());
    }
    
    /**
     * Tests that health increases when player has same coordinates as health.
     */
    @Test
    public void test_CollisionWithHealth() {
        Player player = new Player(0, 0, 5);
        player.setHealth(4);
        HealthCollectable health = new HealthCollectable(0, 0);
        player.collidedWith(health);
        assertEquals("Created money and player both at (0,0), should collide.", 5, player.getHealth());
    }


}
