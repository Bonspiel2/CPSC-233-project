package ca.ucalgary.main;

/**
 * The PlayerProjectile class controls all the behaviour of projectiles friendly to the player 
 * by checking for collisions with enemies as well as moving in the correct direction
 * 
 */
public class PlayerProjectile extends Projectile {
    
    /**
     * Main constructor for the text game
     * @param x Projectile's initial x value
     * @param y Projectile's initial y value
     */
    public PlayerProjectile(int x, int y) {
            super(x, y);
        }
    
    /**
     * Main constructor for the GUI game
     * @param x Projectile's initial x value
     * @param y Projectile's initial y value
     * @param velocity 
     */
    public PlayerProjectile(int x, int y, int velocity) {
        super(x, y, velocity);
    }

    /**
     * Checks if the projectile and a given enemy have collided
     * @param enemy Potential enemy collision
     * @return true if the two objects have collided
     */
    public boolean collidedWith(Enemy enemy) {
        boolean collided = (((getY() >= enemy.getY() || getY() == enemy.getY()-1) &&
                     getY() <= enemy.getY() + enemy.getHeight())
                    &&
                    (getX() >= enemy.getX() &&
                    getX() <= enemy.getX() + enemy.getWidth()));

        return collided;
        
    }
    
    /**
     * Moves the projectile up one row and detects whether or not it has reached
     * the edge of the board.
     * @return edgy returns true if the projectile has reached the edge of the board.
     */
    @Override
    public boolean move() {
    	boolean edgy;
        if (getY() == 0) {
            edgy = false;
        } else {
            edgy = true;
            setY(getY() - getVelocity());
        }
        return edgy;
    }

}
