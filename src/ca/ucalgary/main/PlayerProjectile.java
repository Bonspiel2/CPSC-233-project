package ca.ucalgary.main;

public class PlayerProjectile extends Projectile {
    
    private int velocity;
    private boolean collided;
    private boolean edgy;
    
    // Main constructor
    public PlayerProjectile(int x, int y) {
            super(x, y);
            this.velocity = 0;
        }
    
    // GUI constructor
    public PlayerProjectile(int x, int y, int velocity) {
        super(x, y, velocity);
        this.velocity = 0;
    }

    
    public boolean collidedWith(Enemy enemy) {
        collided = (((this.y >= enemy.getY() || this.y == enemy.getY()-1) &&
                     this.y <= enemy.getY() + enemy.getHeight())
                    &&
                    (this.x >= enemy.getX() &&
                    this.x <= enemy.getX() + enemy.getWidth()));

//        collided = ((this.y == enemy.getY() || this.y == enemy.getY()-1)
//                    && this.x == enemy.getX());
        return collided;
        
    }
    
    /**
     * Moves the projectile up one row and detects whether or not it has reached
     * the edge of the board.
     * @return edgy returns true if the projectile has reached the edge of the board.
     */
    @Override
    public boolean move() {
        if (collided) {
            edgy = false;
        }
        if (this.y == 0) {
            edgy = false;
        } else {
            edgy = true;
            this.y = this.y - 1;
        }
        return edgy;
    }

}
