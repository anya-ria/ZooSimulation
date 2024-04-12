import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * General class of projectiles
 * 
 * @author Lucas
 * @version 2024/4/9
 */
public abstract class Projectile extends SuperSmoothMover
{
    protected double maxActs = 500;
    protected double acts = 0;
    protected double vx, vy;
    protected boolean expired = false;
    private int downTime = 10;
    public Projectile(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }
    public void act()
    {
        setLocation(getX()+vx, getY()+vy);
        acts++;
        if(downTime-- <= 0)
            detectCollision();
        if(acts>maxActs){
            expired = true;
        }
        if(isAtEdge()){
            expired = true;
        }
        if(expired){
            expire();
            return;
        }
    }
    protected abstract void detectCollision();
    protected abstract void expire();
}
