import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * General class of projectiles, objects that fly at a constant speed to hit something
 * 
 * @author Lucas
 * @version 2024/4/9
 */
public abstract class Projectile extends SuperSmoothMover
{
    // lifespan
    private double maxActs = 500;
    private double acts = 0;
    // speeds
    protected double vx, vy;
    // expiration flag, to be able to trigger expire() in the act method
    protected boolean expired = false;
    // number of acts in the beginning where it cannot trigger collision
    private int downTime = 12;
    /**
     * Creates a new projectile
     * @param vx    the x-velocity
     * @param vy    the y-velocity
     */
    public Projectile(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }
    public void act()
    {
        // move
        setLocation(getX()+vx, getY()+vy);
        // increases acts passed
        acts++;
        // if enough acts have passed since creation, run the collision function
        if(downTime-- <= 0)
            detectCollision();
        // if it ran out of lifespan, or if it reached the edge, expire it
        if(acts>maxActs || isAtEdge())
            expired = true;
        // if the expired flag is on, run the expire function
        if(expired){
            expire();
            return;
        }
    }
    protected abstract void detectCollision();
    protected abstract void expire();
}
