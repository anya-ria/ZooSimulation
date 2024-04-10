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
    private double speed;
    private double maxSpeed;
    private int direction;
    
    public void act()
    {
        moveAround();
    }
    
    private void moveAround()
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }
        if (getX() <= 600 || getX() >= 1000)
        {
            turn(180);
        }
        if (getY() <= 10 || getY() >= 355)
        {
            turn(180);
        }
    }
}
