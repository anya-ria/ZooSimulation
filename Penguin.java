import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Penguin here.
 * 
 * @author Luke Xiao, Anya Shah
 * @version (2024.04.05)
 * 2024.04.05: Class created
 */
public class Penguin extends Animal
{
    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;
    
    /**
     * Act - do whatever the Penguin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Animal.class) == null)
        {
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        if (direction == -1 && getY() <= 450)
        {
            direction = direction * -1;
            setLocation (getX(), getY() + (int)(speed*direction));
        }
        else if (direction == 1 && getY() <= 795)
        {
            direction = direction * -1;
            setLocation (getX(), getY() + (int)(speed*direction));
        }
    }
}
