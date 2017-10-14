package ca.ucalgary.main;

public class Collectable {

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean move(String[][] board) {
		boolean onScreen = true;
		if (y == (board.length - 1)) {
			onScreen = false;
		} else {
			y++;
		}
		return onScreen;
	}

	public void draw(String[][] board) {
		board[x][y] = "$";
	}
}
