
package ca.ucalgary.main;

import static org.junit.Assert.*;

import java.awt.Graphics;

import org.junit.Test;

public class CollectableTest {

	private class Collect extends Collectable{

		public Collect(int x, int y) {
			super(x, y, TextGame.ROWS);

		}
		
		public Collect(int x, int y, int width, int height) {
			super(x,y,width,height, GUIGame.SCREEN_HEIGHT);
		}
		
		//Unimplemented
		@Override
		public void draw(String[][] board){

		}
		
		//Unimplemented
		@Override
		public void draw(Graphics g){

		}
	}

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
		Collectable collectable = new Collect(1, 1);
		assertEquals("Unexpected x coordinate.", 1, collectable.getX());
		assertEquals("Unexpected y coordinate.", 1, collectable.getY());
	}

	// test main constructor with negative values (text)
	@Test
	public void test_mainTextConstructor_negative() {
		Collectable collectable = new Collect(-1, -1);
		assertEquals("Unexpected x coordinate.", 0, collectable.getX());
		assertEquals("Unexpected y coordinate.", 0, collectable.getY());
	}

	// test main constructor with values too large(text)
	@Test
	public void test_mainTextConstructor_large() {
		Collectable collectable = new Collect(TextGame.COLUMNS+1, TextGame.ROWS + 1);
		assertEquals("Unexpected x coordinate.", TextGame.COLUMNS-1, collectable.getX());
		assertEquals("Unexpected y coordinate.", TextGame.ROWS-1, collectable.getY());
	}

	// test GUI constructor
	@Test
	public void test_mainGUIConstructor() {
		Collectable collectable = new Collect(1, 1, 1, 1);
		assertEquals("Unexpected x coordinate.", 1, collectable.getX());
		assertEquals("Unexpected y coordinate.", 1, collectable.getY());
		assertEquals("Unexpected width value.", 1, collectable.getHeight());
		assertEquals("Unexpected height value.", 1, collectable.getHeight());
	}

	// test GUI constructor with negative values
	@Test
	public void test_mainGUIConstructor_negative() {
		Collectable collectable = new Collect(-1, -1, -1, -1);
		assertEquals("Unexpected x coordinate.", 0, collectable.getX());
		assertEquals("Unexpected y coordinate.", 0, collectable.getY());
		assertEquals("Unexpected width value.", 0, collectable.getHeight());
		assertEquals("Unexpected height value.", 0, collectable.getHeight());
	}

	// test GUI constructor with values too large
	@Test
	public void test_mainGUIConstructor_large() {
		Collectable collectable = new Collect(GUIGame.SCREEN_WIDTH+1, GUIGame.SCREEN_HEIGHT+1, 1, 1);
		assertEquals("Unexpected x coordinate.", GUIGame.SCREEN_WIDTH-1, collectable.getX());
		assertEquals("Unexpected y coordinate.", GUIGame.SCREEN_HEIGHT-1, collectable.getY());
	}
	
	// test regular collectable movement
	@Test
	public void test_Move() {
		Collectable collectable = new Collect(0,0);
		assertTrue("Collectable removed while still on board", collectable.move());
		assertEquals("Collectable did not move the correct amount", 1, collectable.getY());
	}

	// test off board collectable movement
	@Test
	public void test_Move_offBoard() {
		Collectable collectable = new Collect(TextGame.ROWS-1,0);
		assertFalse("Collectable not removed from board", collectable.move());
		assertEquals("Collectable moved over max", TextGame.ROWS-1, collectable.getY());
	}

	// test collision on top
	@Test
	public void test_CollisionOnTop() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(5,5,10,10);
		assertTrue("Created collectable testing collsion on top (5,5)", collectable.collidedWith(collide));
	}

	// test collision top left
	@Test
	public void test_CollisionTopLeft() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(2,2,10,10);
		assertTrue("Created collectable testing collsion with top left corner", collectable.collidedWith(collide));
	}

	// test collision top right
	@Test
	public void test_CollisionTopRight() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(10,2,10,10);
		assertTrue("Created collectable testing collsion with top right corner", collectable.collidedWith(collide));
	}

	// test collision bottom left
	@Test
	public void test_CollisionBottomLeft() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(2, 10,10,10);
		assertTrue("Created collectable testing collsion with bottom left corner", collectable.collidedWith(collide));
	}

	// test collision bottom right
	@Test
	public void test_CollisionBottomRight() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(10, 10,10,10);
		assertTrue("Created collectable testing collsion with bottom right corner", collectable.collidedWith(collide));
	}

	// test collision above
	@Test
	public void test_CollisionAbove() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(5,0,10,4);
		assertFalse("Created collectable testing collsion above", collectable.collidedWith(collide));
	}

	// test collision left
	@Test
	public void test_CollisionLeft() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(0,5,4,10);
		assertFalse("Created collectable testing collsion to the left", collectable.collidedWith(collide));
	}

	// test collision right
	@Test
	public void test_CollisionRight() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(10, 16,10,10);
		assertFalse("Created collectable testing collsion to the right", collectable.collidedWith(collide));
	}

	// test collision below
	@Test
	public void test_CollisionBelow() {
		Collectable collectable = new Collect(5, 5, 10, 10);
		Collidable collide = new Collision(10, 16,10,10);
		assertFalse("Created collectable testing collsion below", collectable.collidedWith(collide));
	}
	//test x getter
	@Test
	public void test_x_getter() {
		Collectable collectable = new Collect(4,3);
		assertEquals("Generated collectable, testing x coordinate", 4, collectable.getX());
	}

	//test y getter
	@Test
	public void test_y_getter() {
		Collectable collectable = new Collect(4,3);
		assertEquals("Generated collectable, testing y coordinate", 3, collectable.getY());
	}

	//test x setter
	@Test
	public void test_x_setter() {
		Collectable collectable = new Collect(4,3);
		collectable.setX(1);
		assertEquals("Generated collectable, changed x value", 1, collectable.getX());
	}

	//test x setter too low
	@Test
	public void test_x_setter_low() {
		Collectable collectable = new Collect(4,3);
		collectable.setX(-1);
		assertEquals("Generated collectable, changed x below 0", 0, collectable.getX());
	}

	//test x setter too high
	@Test
	public void test_x_setter_high() {
		Collectable collectable = new Collect(4,3);
		collectable.setX(TextGame.COLUMNS+1);
		assertEquals("Generated collectable, changed x above board width", TextGame.COLUMNS-1, collectable.getX());
	}

	//test y setter
	@Test
	public void test_y_setter() {
		Collectable collectable = new Collect(4,3);
		collectable.setY(1);
		assertEquals("Generated collectable, changed y value", 1, collectable.getY());
	}

	//test y setter too low
	@Test
	public void test_y_setter_low() {
		Collectable collectable = new Collect(4,3);
		collectable.setY(-1);
		assertEquals("Generated collectable, changed y below 0", 0, collectable.getY());
	}

	//test x setter too high
	@Test
	public void test_y_setter_high() {
		Collectable collectable = new Collect(4,3);
		collectable.setY(TextGame.ROWS+1);
		assertEquals("Generated collectable, changed y above board width", TextGame.ROWS-1, collectable.getY());
	}

	//test width getter
	@Test
	public void test_width_getter() {
		Collectable collectable = new Collect(10,10,10,20);
		assertEquals("Created collectable, testing width getter", 10, collectable.getWidth());
	}

	//test height getter
	@Test
	public void test_height_getter() {
		Collectable collectable = new Collect(10,10,10,20);
		assertEquals("Created collectable, testing height getter", 20, collectable.getHeight());
	}

	//test max y getter gui
	@Test
	public void test_maxY_getter_GUI() {
		Collectable collectable = new Collect(10,10,10,10);
		assertEquals("Created collectable, testing max y getter", GUIGame.SCREEN_HEIGHT, collectable.getMaxY());
	}

	//test max y getter text
	@Test
	public void test_maxY_getter_Text() {
		Collectable collectable = new Collect(5,5);
		assertEquals("Created collectable, testing max y getter", TextGame.ROWS, collectable.getMaxY());

	}
}
