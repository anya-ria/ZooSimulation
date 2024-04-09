import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectile extends SuperSmoothMover
{
    protected double maxActs = 500;
    protected double acts = 0;
    protected double vx, vy;
    private int downTime = 10;
    public void act()
    {
        setLocation(getX()+vx, getY()+vy);
        acts++;
        if(downTime-- <= 0)
            detectCollision();
        if(acts>maxActs){
            expire();
        }
    }
    protected abstract void detectCollision();
    protected abstract void expire();
}
