import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectile extends SuperSmoothMover
{
    protected double maxDistance = 500;
    protected double vx, vy;
    private int downTime = 10;
    public void act()
    {
        setLocation(getX()+vx, getY()+vy);
        if(downTime-- <= 0)
            detectCollision();
    }
    protected abstract void detectCollision();
}
