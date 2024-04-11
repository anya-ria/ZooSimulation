import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hippo here.
 * 
 * @author Luke Xiao, Anya Shah
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
    private double speed;
    private double maxSpeed;
    private int direction;
    
    public Hippo() {
        animCounter = 0;
    }
    
    /**
     * Act - do whatever the Hippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        if (getX() <= 610 || getX() >= 1000)
        {
            turn(180);
        }
        if (getY() <= 10 || getY() >= 310)
        {
            turn(180);
        }
    }
    
    public void animateRight() {
        for(int i = 0; i < 3; i++) {
            hippoWalkRight[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
            setImage(hippoWalkRight[animCounter++ % 3]);
        }
    }
    public void animateLeft() {
        for(int i = 0; i < 3; i++) {
            hippoWalkLeft[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
            hippoWalkLeft[i].mirrorHorizontally();
            setImage(hippoWalkLeft[animCounter++ % 3]);
        }
    }
    public void animateToward() {
        for(int i = 0; i < 3; i++) {
            hippoWalkToward[i] = new GreenfootImage("hippoWalkToward/hippoWalkToward" + i + ".png");
            setImage(hippoWalkToward[animCounter++ % 3]);
        }
    }
    public void animateAway() {
        for(int i = 0; i < 3; i++) {
            hippoWalkAway[i] = new GreenfootImage("hippoWalkAway/hippoWalkAway" + i + ".png");
            setImage(hippoWalkAway[animCounter++ % 3]);
        }
    }
}
