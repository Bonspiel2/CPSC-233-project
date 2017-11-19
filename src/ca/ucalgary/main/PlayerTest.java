
package ca.ucalgary.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

    // test main constructor (text)
    @Test
    public void test_mainTextConstructor() {
        Player player = new Player(0, 0, 5);
        assertEquals("Unexpected x coordinate.", 0, player.getX());
        assertEquals("Unexpected y coordinate.", 0, player.getY());
        assertEquals("Unexpected health value", 5, player.getHealth());
    }
    
    // test health setter (negative)
    @Test
    public void test_healthSetterNegative() {
        Player player = new Player(0, 0, 5);
        player.setHealth(-10);
        assertEquals("Unexpected health value.", 5, player.getHealth());
    }
    
    
    // test main constructor (GUI)
    @Test
    public void test_mainGUIConstructor() {
        Player player = new Player(0, 0, 11, 10, 5);
        assertEquals("Unexpected x coordinate.", 0, player.getX());
        assertEquals("Unexpected y coordinate.", 0, player.getY());
        assertEquals("Unexpected health value", 5, player.getHealth());
        assertEquals("Unexpected width.", 11, player.getWidth());
        assertEquals("Unexpected height.", 10, player.getHeight());
    }
    
    // test copy constructor
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


    // test move (edge)
    @Test
    public void test_moveLeftEdge() {
        Player player = new Player(0, 0, 5);
        player.move("A");
        assertEquals("Unexpected movement", 0, player.getX());
    }
    
    // test move up (edge)
        
    @Test
    public void test_moveUpEdge() {
        Player player = new Player(0, 0, 5);
        player.move("W");
        assertEquals("Unexpected movement", 0, player.getY());
    }
    
    // test collided
    @Test
    public void test_CollisionWithEnemy() {
        Enemy enemy = new Enemy(0, 0);
        Player player = new Player(0, 0, 5);
        assertEquals("Created enemy and player both at (0,0), should collide.", true, player.collidedWith(enemy));
    }
    
    @Test
    public void test_CollisionWithMoneyScoreIncrease() {
        Player player = new Player(0, 0, 5);
        Money money = new Money(0, 0, 20);
        player.collidedWith(money);
        assertEquals(("Created money and player both at (0,0), expected score is 1 since player and money collide"), 1, player.getScore());
    }
        
    @Test
    public void test_CollisionWithHealth() {
        Player player = new Player(0, 0, 5);
        player.setHealth(4);
        HealthCollectable health = new HealthCollectable(0, 0, 20);
        player.collidedWith(health);
        assertEquals("Created money and player both at (0,0), should collide.", 5, player.getHealth());
    }


    

}
