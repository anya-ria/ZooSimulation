import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monkey here.
 * 
 * @author (Luke Xiao) 
 * @version (2024.04.05)
 * 2024.04.05: Class created
 */
public class Monkey extends Animal
{
    /**
     * Act - do whatever the Monkey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;
    
    public void act()
    {
        if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Animal.class) == null)
        {
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        if (direction == -1 && getY() >= 350)
        {
            direction = direction * -1;
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        else if (direction == 1 && getY() <= 5)
        {
            direction = direction * -1;
            setLocation (getX(), getY() + (int)(speed*direction));
        }
    }
}
