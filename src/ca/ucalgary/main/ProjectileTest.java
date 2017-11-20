/**
 * 
 */
package ca.ucalgary.main;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



/**
 * @author matthew.lee4
 *
 */
public class ProjectileTest {

	public class Proj extends Projectile {


		public Proj(int x, int y, int vel) {
			super(x, y, vel);
		}

	
		public Proj(int x, int y) {
			super(x, y);
		}
		

		@Override
		public boolean move() {
			return false;
		}
		
		
	}
	
	private class Collision implements Collidable {

		int x;
		int y;
		int width;
		int height;

		public Collision(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public int getWidth() {
			return width;
		}

		@Override
		public int getHeight() {
			return height;
		}

		@Override
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}

		@Override
		public boolean collidedWith(Collidable c) {
			boolean collided = false;
	    	
	    	if (x + width >= c.getX() && x <= c.getX() + c.getWidth() && 
					(y + height >= c.getY() || y + height == c.getY()-1) && y <= c.getY() + c.getHeight()) {
				collided = true;
			}

	        return collided;
		}

	}
	
	// test main constructor (text)
    @Test
    public void test_mainTextConstructor() {
        Proj projectile = new Proj(1, 1);
        assertEquals("Unexpected x coordinate.", 1, projectile.getX());
        assertEquals("Unexpected y coordinate.", 1, projectile.getY());
    }
    
    // test main constructor with negative values (text)
    @Test
    public void test_mainTextConstructor_negative() {
        Proj projectile = new Proj(-1, -1);
        assertEquals("Unexpected x coordinate.", 0, projectile.getX());
        assertEquals("Unexpected y coordinate.", 0, projectile.getY());
    }
    
    // test main constructor with values too large(text)
    @Test
    public void test_mainTextConstructor_large() {
        Proj projectile = new Proj(TextGame.COLUMNS+1, TextGame.ROWS + 1);
        assertEquals("Unexpected x coordinate.", TextGame.COLUMNS-1, projectile.getX());
        assertEquals("Unexpected y coordinate.", TextGame.ROWS-1, projectile.getY());
    }
    
    // test GUI constructor
    @Test
    public void test_mainGUIConstructor() {
        Proj projectile = new Proj(1, 1, 1);
        assertEquals("Unexpected x coordinate.", 1, projectile.getX());
        assertEquals("Unexpected y coordinate.", 1, projectile.getY());
        assertEquals("Unexpected width value.", 2, projectile.getWidth());
        assertEquals("Unexpected height value.", 10, projectile.getHeight());
        assertEquals("Unexpected velocity value.", 1, projectile.getVelocity());

    }
    
    // test GUI constructor with negative values
    @Test
    public void test_mainGUIConstructor_negative() {
        Proj projectile = new Proj(-1, -1, -1);
        assertEquals("Unexpected x coordinate.", 0, projectile.getX());
        assertEquals("Unexpected y coordinate.", 0, projectile.getY());
        assertEquals("Unexpected width value.", 2, projectile.getWidth());
        assertEquals("Unexpected height value.", 10, projectile.getHeight());
        assertEquals("Unexpected velocity value.", 1, projectile.getVelocity());
    }
    
    // test GUI constructor with values too large
    @Test
    public void test_mainGUIConstructor_large() {
        Proj projectile = new Proj(GUIGame.SCREEN_WIDTH+1, GUIGame.SCREEN_HEIGHT+1, 1);
        assertEquals("Unexpected x coordinate.", GUIGame.SCREEN_WIDTH-1, projectile.getX());
        assertEquals("Unexpected y coordinate.", GUIGame.SCREEN_HEIGHT-1, projectile.getY());
    }
   
    
 // test collision on top
    @Test
    public void test_CollisionOnTop() {
        Proj projectile = new Proj(5, 5, 10);
        Collidable collide = new Collision(5,5,10,10);
        assertTrue("Created projectile testing collsion on top (5,5)", projectile.collidedWith(collide));
    }
    
    // test collision top left
    @Test
    public void test_CollisionTopLeft() {
        Proj projectile = new Proj(5, 5, 10);
        Collidable collide = new Collision(2,2,10,10);
        assertTrue("Created projectile testing collsion with top left corner", projectile.collidedWith(collide));
    }
    
 // test collision top right
    @Test
    public void test_CollisionTopRight() {
        Proj projectile = new Proj(5, 5, 10);
        Collidable collide = new Collision(6,9,10,10);
        assertTrue("Created projectile testing collsion with top right corner", projectile.collidedWith(collide));
    }
    
 // test collision bottom left
    @Test
    public void test_CollisionBottomLeft() {
        Proj projectile = new Proj(5, 5, 10);
        Collidable collide = new Collision(6, 10,10,10);
        assertTrue("Created projectile testing collsion with bottom left corner", projectile.collidedWith(collide));
    }
    
    // test collision bottom right
    @Test
    public void test_CollisionBottomRight() {
        Proj projectile = new Proj(10, 9, 10);
        Collidable collide = new Collision(10, 10,10,10);
        assertTrue("Created projectile testing collsion with bottom right corner", projectile.collidedWith(collide));
    }
    
    // test collision above
    @Test
    public void test_CollisionAbove() {
        Proj projectile = new Proj(0, 0, 10);
        Collidable collide = new Collision(5,15,10,4);
        assertFalse("Created projectile testing collsion above", projectile.collidedWith(collide));
    }
    
 // test collision left
    @Test
    public void test_CollisionLeft() {
        Proj projectile = new Proj(0,0, 10);
        Collidable collide = new Collision(5,5,4,10);
        assertFalse("Created projectile testing collsion to the left", projectile.collidedWith(collide));
    }
    
 // test collision right
    @Test
    public void test_CollisionRight() {
        Proj projectile = new Proj(0, 0, 10);
        Collidable collide = new Collision(10, 16,10,10);
        assertFalse("Created projectile testing collsion to the right", projectile.collidedWith(collide));
    }
    
    // test collision below
    @Test
    public void test_CollisionBelow() {
        Proj projectile = new Proj(10, 19, 10);
        Collidable collide = new Collision(10, 16,10,2);
        assertFalse("Created projectile testing collsion below", projectile.collidedWith(collide));
    }
    
    //test x getter
    @Test
    public void test_x_getter() {
    	Proj projectile = new Proj(4,3);
    	assertEquals("Generated projectile, testing x coordinate", 4, projectile.getX());
    }
    
  //test y getter
    @Test
    public void test_y_getter() {
    	Proj projectile = new Proj(4,3);
    	assertEquals("Generated projectile, testing y coordinate", 3, projectile.getY());
    }
    
  //test x setter
    @Test
    public void test_x_setter() {
    	Proj projectile = new Proj(4,3);
    	projectile.setX(1);
    	assertEquals("Generated projectile, changed x value", 1, projectile.getX());
    }
    
    //test x setter too low
    @Test
    public void test_x_setter_low() {
    	Proj projectile = new Proj(4,3);
    	projectile.setX(-1);
    	assertEquals("Generated projectile, changed x below 0", 0, projectile.getX());
    }
    
    //test x setter too high
    @Test
    public void test_x_setter_high() {
    	Proj projectile = new Proj(4,3);
    	projectile.setX(TextGame.COLUMNS+1);
    	assertEquals("Generated projectile, changed x above board width", TextGame.COLUMNS-1, projectile.getX());
    }
    
    //test y setter
    @Test
    public void test_y_setter() {
    	Proj projectile = new Proj(4,3);
    	projectile.setY(1);
    	assertEquals("Generated projectile, changed y value", 1, projectile.getY());
    }
    
    //test y setter too low
    @Test
    public void test_y_setter_low() {
    	Proj projectile = new Proj(4,3);
    	projectile.setY(-1);
    	assertEquals("Generated projectile, changed y below 0", 0, projectile.getY());
    }
    
    //test y setter too high
    @Test
    public void test_y_setter_high() {
    	Proj projectile = new Proj(4,3);
    	projectile.setY(TextGame.ROWS+1);
    	assertEquals("Generated projectile, changed y above board width", TextGame.ROWS-1, projectile.getY());
    }
    
    //test width getter
    @Test
    public void test_width_getter() {
    	Proj projectile = new Proj(10,10,10);
    	assertEquals("Created projectile, testing width getter", 2, projectile.getWidth());
    }
    
  //test height getter
    @Test
    public void test_height_getter() {
    	Proj projectile = new Proj(10,10,10);
    	assertEquals("Created projectile, testing height getter", 10, projectile.getHeight());
    }
    
    @Test
    public void test_velocity_gui() {
    	Proj projectile = new Proj(1,1,7);
    	assertEquals("Created projectile, testing velocity for GUI constructor", 7, projectile.getVelocity());
    }
    
    @Test
    public void test_velocity_text() {
    	Proj projectile = new Proj(1,1);
    	assertEquals("Created projectile, testing velocity for TextGame constructor", 1, projectile.getVelocity());
    }

	
}
