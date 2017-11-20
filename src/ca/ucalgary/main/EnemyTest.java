
package ca.ucalgary.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {
	
	private class Collision implements Collidable{
		
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

		//Not implemented
		@Override
		public boolean collidedWith(Collidable c) {
			return false;
		}
		
	}

    // test main constructor (text)
    @Test
    public void test_mainTextConstructor() {
        Enemy enemy = new Enemy(1, 1);
        assertEquals("Unexpected x coordinate.", 1, enemy.getX());
        assertEquals("Unexpected y coordinate.", 1, enemy.getY());
    }
    
    // test main constructor with negative values (text)
    @Test
    public void test_mainTextConstructor_negative() {
        Enemy enemy = new Enemy(-1, -1);
        assertEquals("Unexpected x coordinate.", 0, enemy.getX());
        assertEquals("Unexpected y coordinate.", 0, enemy.getY());
    }
    
    // test main constructor with values too large(text)
    @Test
    public void test_mainTextConstructor_large() {
        Enemy enemy = new Enemy(TextGame.COLUMNS+1, TextGame.ROWS + 1);
        assertEquals("Unexpected x coordinate.", TextGame.COLUMNS-1, enemy.getX());
        assertEquals("Unexpected y coordinate.", TextGame.ROWS-1, enemy.getY());
    }
    
    // test GUI constructor
    @Test
    public void test_mainGUIConstructor() {
        Enemy enemy = new Enemy(1, 1, 1, 1);
        assertEquals("Unexpected x coordinate.", 1, enemy.getX());
        assertEquals("Unexpected y coordinate.", 1, enemy.getY());
        assertEquals("Unexpected width value.", 1, enemy.getHeight());
        assertEquals("Unexpected height value.", 1, enemy.getHeight());
    }
    
    // test GUI constructor with negative values
    @Test
    public void test_mainGUIConstructor_negative() {
        Enemy enemy = new Enemy(-1, -1, -1, -1);
        assertEquals("Unexpected x coordinate.", 0, enemy.getX());
        assertEquals("Unexpected y coordinate.", 0, enemy.getY());
        assertEquals("Unexpected width value.", 0, enemy.getHeight());
        assertEquals("Unexpected height value.", 0, enemy.getHeight());
    }
    
    // test GUI constructor with values too large
    @Test
    public void test_mainGUIConstructor_large() {
        Enemy enemy = new Enemy(GUIGame.SCREEN_WIDTH+1, GUIGame.SCREEN_HEIGHT+1, 1, 1);
        assertEquals("Unexpected x coordinate.", GUIGame.SCREEN_WIDTH-1, enemy.getX());
        assertEquals("Unexpected y coordinate.", GUIGame.SCREEN_HEIGHT-1, enemy.getY());
    }
    
 // test copy constructor
    @Test
    public void test_CopyConstructor() {
        Enemy enemy = new Enemy(50, 20, 15, 13);
        Enemy enemyCopy = new Enemy(enemy);
        assertEquals("Unexpected x coordinate.", 50, enemyCopy.getX());
        assertEquals("Unexpected y coordinate.", 20, enemyCopy.getY());
        assertEquals("Unexpected width.", 15, enemyCopy.getWidth());
        assertEquals("Unexpected height.", 13, enemyCopy.getHeight());
        assertTrue("Copy is a reference to the original object", enemy != enemyCopy);
    }
    
    // test regular enemy movement
    @Test
    public void test_Move() {
    	Enemy enemy = new Enemy(0,0);
    	assertTrue("Enemy removed while still on board", enemy.move());
    	assertEquals("Enemy did not move the correct amount", 1, enemy.getY());
    }
    
    // test off board enemy movement
    @Test
    public void test_Move_offBoard() {
    	Enemy enemy = new Enemy(0,TextGame.ROWS-1);
    	assertFalse("Enemy not removed from board", enemy.move());
    	assertEquals("Enemy moved over max", TextGame.ROWS-1, enemy.getY());
    }
    
 // test collision on top
    @Test
    public void test_CollisionOnTop() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(5,5,10,10);
        assertTrue("Created enemy testing collsion on top (5,5)", enemy.collidedWith(collide));
    }
    
    // test collision top left
    @Test
    public void test_CollisionTopLeft() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(2,2,10,10);
        assertTrue("Created enemy testing collsion with top left corner", enemy.collidedWith(collide));
    }
    
 // test collision top right
    @Test
    public void test_CollisionTopRight() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(10,2,10,10);
        assertTrue("Created enemy testing collsion with top right corner", enemy.collidedWith(collide));
    }
    
 // test collision bottom left
    @Test
    public void test_CollisionBottomLeft() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(2, 10,10,10);
        assertTrue("Created enemy testing collsion with bottom left corner", enemy.collidedWith(collide));
    }
    
    // test collision bottom right
    @Test
    public void test_CollisionBottomRight() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(10, 10,10,10);
        assertTrue("Created enemy testing collsion with bottom right corner", enemy.collidedWith(collide));
    }
    
    // test collision above
    @Test
    public void test_CollisionAbove() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(5,0,10,4);
        assertFalse("Created enemy testing collsion above", enemy.collidedWith(collide));
    }
    
 // test collision left
    @Test
    public void test_CollisionLeft() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(0,5,4,10);
        assertFalse("Created enemy testing collsion to the left", enemy.collidedWith(collide));
    }
    
 // test collision right
    @Test
    public void test_CollisionRight() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(10, 16,10,10);
        assertFalse("Created enemy testing collsion to the right", enemy.collidedWith(collide));
    }
    
    // test collision below
    @Test
    public void test_CollisionBelow() {
        Enemy enemy = new Enemy(5, 5, 10, 10);
        Collidable collide = new Collision(10, 16,10,10);
        assertFalse("Created enemy testing collsion below", enemy.collidedWith(collide));
    }
    
    //test collectable creation
    @Test
    public void test_newCollectable() {
    	Enemy enemy = new Enemy(5,6);
    	Collectable collectable = enemy.createCollectable();
    	assertEquals("Generated collectable using enemy testing x value", 5, collectable.getX());
    	assertEquals("Generated collectable using testing y value", 6, collectable.getY());
    }
    
    //test text draw method
    @Test
    public void test_DrawText() {
    	Enemy enemy = new Enemy(1,2);
    	String[][] board = new String[3][3];
    	enemy.draw(board);
    	assertEquals("Generated enemy, drew to text board. Testing placement", "V", board[2][1]);
    }
    
  //test text draw method board too small
    @Test
    public void test_DrawTextSmallBoard() {
    	Enemy enemy = new Enemy(5,6);
    	String[][] board = new String[3][3];
    	enemy.draw(board);
    	assertArrayEquals("Generated enemy, drew to small text board. Testing placement", new String[3][3], board);
    }
    
    //test x getter
    @Test
    public void test_x_getter() {
    	Enemy enemy = new Enemy(4,3);
    	assertEquals("Generated enemy, testing x coordinate", 4, enemy.getX());
    }
    
  //test y getter
    @Test
    public void test_y_getter() {
    	Enemy enemy = new Enemy(4,3);
    	assertEquals("Generated enemy, testing y coordinate", 3, enemy.getY());
    }
    
  //test x setter
    @Test
    public void test_x_setter() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setX(1);
    	assertEquals("Generated enemy, changed x value", 1, enemy.getX());
    }
    
    //test x setter too low
    @Test
    public void test_x_setter_low() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setX(-1);
    	assertEquals("Generated enemy, changed x below 0", 0, enemy.getX());
    }
    
  //test x setter too high
    @Test
    public void test_x_setter_high() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setX(TextGame.COLUMNS+1);
    	assertEquals("Generated enemy, changed x above board width", TextGame.COLUMNS-1, enemy.getX());
    }
    
  //test y setter
    @Test
    public void test_y_setter() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setY(1);
    	assertEquals("Generated enemy, changed y value", 1, enemy.getY());
    }
    
    //test y setter too low
    @Test
    public void test_y_setter_low() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setY(-1);
    	assertEquals("Generated enemy, changed y below 0", 0, enemy.getY());
    }
    
  //test x setter too high
    @Test
    public void test_y_setter_high() {
    	Enemy enemy = new Enemy(4,3);
    	enemy.setY(TextGame.ROWS+1);
    	assertEquals("Generated enemy, changed y above board width", TextGame.ROWS-1, enemy.getY());
    }
    
    //test has a shot getter
    @Test
    public void test_hasShot_getter() {
    	Enemy enemy = new Enemy(0,0);
    	assertTrue("Generated enemy, testing initial shot", enemy.getHasAShot());
    }
    
  //test has a shot getter after shot
    @Test
    public void test_hasShot_getter_shot() {
    	Enemy enemy = new Enemy(0,0);
    	enemy.shoot();
    	assertFalse("Generated enemy, allowed shot, testing has shot", enemy.getHasAShot());
    }
    
    //test shot produced
    @Test
    public void test_shoot() {
    	Enemy enemy = new Enemy(10,10,10,20);
    	EnemyProjectile projectile = enemy.shoot();
    	assertEquals("Created enemy, produced shot, testing x value", 15, projectile.getX());
    	assertEquals("Created enemy, produced shot, testing y value", 31, projectile.getY());
    }
    
    //test width getter
    @Test
    public void test_width_getter() {
    	Enemy enemy = new Enemy(10,10,10,20);
    	assertEquals("Created enemy, testing width getter", 10, enemy.getWidth());
    }
    
  //test height getter
    @Test
    public void test_height_getter() {
    	Enemy enemy = new Enemy(10,10,10,20);
    	assertEquals("Created enemy, testing height getter", 20, enemy.getHeight());
    }
    
  //test width setter
    @Test
    public void test_width_setter() {
    	Enemy enemy = new Enemy(10,10,10,10);
    	enemy.setWidth(5);
    	assertEquals("Created enemy, changed width. Testing value", 5, enemy.getWidth());
    }
    
  //test width setter
    @Test
    public void test_width_setter_low() {
    	Enemy enemy = new Enemy(10,10,10,10);
    	enemy.setWidth(-1);
    	assertEquals("Created enemy, changed width too low. Testing value", 10, enemy.getWidth());
    }
    
  //test height setter
    @Test
    public void test_height_setter() {
    	Enemy enemy = new Enemy(10,10,10,20);
    	enemy.setHeight(5);
    	assertEquals("Created enemy, changed height. Testing value", 5, enemy.getHeight());
    }
    
  //test height setter
    @Test
    public void test_height_setter_low() {
    	Enemy enemy = new Enemy(10,10,10,20);
    	enemy.setHeight(-1);
    	assertEquals("Created enemy, changed height too low. Testing value", 20, enemy.getHeight());
    }
    
  //test max y getter gui
    @Test
    public void test_maxY_getter_GUI() {
    	Enemy enemy = new Enemy(10,10,10,10);
    	assertEquals("Created enemy, testing max y getter", GUIGame.SCREEN_HEIGHT, enemy.getMaxY());
    }
    
    //test max y getter text
    @Test
    public void test_maxY_getter_Text() {
    	Enemy enemy = new Enemy(5,5);
    	assertEquals("Created enemy, testing max y getter", TextGame.ROWS, enemy.getMaxY());
    }
    
  //test max x getter gui
    @Test
    public void test_maxX_getter_GUI() {
    	Enemy enemy = new Enemy(10,10,10,10);
    	assertEquals("Created enemy, testing max x getter", GUIGame.SCREEN_WIDTH, enemy.getMaxX());
    }
    
    //test max x getter text
    @Test
    public void test_maxX_getter_Text() {
    	Enemy enemy = new Enemy(5,5);
    	assertEquals("Created enemy, testing max x getter", TextGame.COLUMNS, enemy.getMaxX());
    }
}
