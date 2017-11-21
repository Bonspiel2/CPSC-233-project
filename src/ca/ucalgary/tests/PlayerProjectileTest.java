/**
 * 
 */
package ca.ucalgary.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.ucalgary.game.TextGame;
import ca.ucalgary.projectiles.PlayerProjectile;

/**
 * @author matthew.lee4
 *
 */
public class PlayerProjectileTest {
	// test regular projectile movement
    @Test
    public void test_Move() {
    	PlayerProjectile projectile = new PlayerProjectile(0,1);
    	assertTrue("PlayerProjectile removed while still on board", projectile.move());
    	assertEquals("PlayerProjectile did not move the correct amount", 0, projectile.getY());
    }
    
    // test off board projectile movement
    @Test
    public void test_Move_offBoard() {
    	PlayerProjectile projectile = new PlayerProjectile(0,0);
    	assertFalse("PlayerProjectile not removed from board", projectile.move());
    	assertEquals("PlayerProjectile moved over max", 0, projectile.getY());
    }
}
