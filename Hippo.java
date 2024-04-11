import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hippo here.
 * 
 * @author (Luke Xiao) 
 * @version (2024.04.05)
 * 2024.04.05: Class created
 */

public class Hippo extends Animal
{
    /**
     * Act - do whatever the Hippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean isInfected;
    //private double speed;
    //private double maxSpeed;
    //private int direction;
    
    public void act()
    {
        moveAround();
        if (isInfected)
        {
            charge();
        }
    }
    
    public void moveAround()
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }
        if (getX() <= 685 || getX() >= 1000)
        {
            turn(180);
        }
        if (getY() <= 30 || getY() >= 290)
        {
            turn(180);
        }
    }
    
    public void charge()
    {
        move(4);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }
        if (getX() <= 685 || getX() >= 1000)
        {
            turn(180);
        }
        if (getY() <= 30 || getY() >= 290)
        {
            turn(180);
        }
    }
}
