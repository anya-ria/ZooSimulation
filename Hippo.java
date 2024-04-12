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
    // Hippo sprites
    private GreenfootImage[] hippoWalkRight = new GreenfootImage[3];
    private GreenfootImage[] hippoWalkLeft = new GreenfootImage[3];
    private GreenfootImage[] hippoWalkToward = new GreenfootImage[3];
    private GreenfootImage[] hippoWalkAway = new GreenfootImage[3];
    private int animCounter; 
    
    private boolean isInfected;
    private int direction;
    //private double speed;
    //private double maxSpeed;
    //private int direction;
    
    public Hippo() 
    {
        animCounter = 0;
    }
    
    /**
     * Act - do whatever the Hippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(200) < 10)
        {
            turn(direction);
            if (direction >= 315 && direction <= 45)
            {
                animateAway();
            }
            else if (direction > 45 && direction <= 135)
            {
                animateRight();
            }
            else if (direction > 135 && direction <= 225)
            {
                animateLeft();
            }
            else if (direction > 225 && direction <= 315)
            {
                animateToward();
            }
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
    
    public void animateRight() 
    {
        for(int i = 0; i < 3; i++) 
        {
            hippoWalkRight[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
            setImage(hippoWalkRight[animCounter++ % 3]);
        }
    }
    public void animateLeft() 
    {
        for(int i = 0; i < 3; i++) 
        {
            hippoWalkLeft[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
            hippoWalkLeft[i].mirrorHorizontally();
            setImage(hippoWalkLeft[animCounter++ % 3]);
        }
    }
    public void animateToward() 
    {
        for(int i = 0; i < 3; i++) 
        {
            hippoWalkToward[i] = new GreenfootImage("hippoWalkToward/hippoWalkToward" + i + ".png");
            setImage(hippoWalkToward[animCounter++ % 3]);
        }
    }
    public void animateAway() 
    {
        for(int i = 0; i < 3; i++) 
        {
            hippoWalkAway[i] = new GreenfootImage("hippoWalkAway/hippoWalkAway" + i + ".png");
            setImage(hippoWalkAway[animCounter++ % 3]);
        }
    }
}
