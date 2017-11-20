/**
 * 
 */
package ca.ucalgary.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author matthew.lee4
 *
 */
public class EnemyProjectileTest {
	
	// test regular projectile movement
    @Test
    public void test_Move() {
    	EnemyProjectile projectile = new EnemyProjectile(0,0);
    	assertTrue("EnemyProjectile removed while still on board", projectile.move());
    	assertEquals("EnemyProjectile did not move the correct amount", 1, projectile.getY());
    }
    
    // test off board projectile movement
    @Test
    public void test_Move_offBoard() {
    	EnemyProjectile projectile = new EnemyProjectile(0,TextGame.ROWS-1);
    	assertFalse("EnemyProjectile not removed from board", projectile.move());
    	assertEquals("EnemyProjectile moved over max", TextGame.ROWS-1, projectile.getY());
    }

}
