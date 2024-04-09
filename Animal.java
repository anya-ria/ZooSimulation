import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Animal extends SuperSmoothMover
{
    /**
     * Act - do whatever the Animal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;
    public void act()
    {
        super.act();
    }
    
    public void zombify()
    {
        speed = 2;
        GreenfootImage zombie = new GreenfootImage("");
    }
}