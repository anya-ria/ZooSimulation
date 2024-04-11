import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Penguin here.
 * 
 * @author (Luke Xiao) 
 * @version (2024.04.05)
 * 2024.04.05: Class created
 */
public class Penguin extends Animal
{
    /**
     * Act - do whatever the Penguin wants to do. This method is called whenever
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
        if (getX() <= 695 || getX() >= 980)
        {
            turn(180);
        }
        if (getY() <= 510 || getY() >= 760)
        {
            turn(180);
        }
    }
    
    public void slide()
    {
        direction = Greenfoot.getRandomNumber(90) - 45;
        move(3);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(direction);
            setRotation(direction);
        }
        if (getX() <= 695 || getX() >= 980)
        {
            turn(180);
        }
        if (getY() <= 510 || getY() >= 760)
        {
            turn(180);
        }
    }
}
