import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hippo here.
 * 
 * @author Luke Xiao | Functions
 * @author Anya Shah | Animations
 * @version 04/12/2024
 */

public class Hippo extends Animal
{
    // Hippo sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];

    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    private boolean right, left, away, toward;

    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;

    public Hippo() {
        super(200);

        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }

    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("hippoWalkAway/hippoWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("hippoWalkToward/hippoWalkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }

        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    /**
     * Act - do whatever the Hippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!super.update()) return;
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
    protected void animate() {
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            if(animIndex == maxIndex){
                animIndex = 0; 
            }
            if(right){
                setImage(walkRight[animIndex]);
            } 
            else if (left){
                setImage(walkLeft[animIndex]);
            } 
            else if(toward){
                setImage(walkToward[animIndex]); 
            } 
            else if(away){
                setImage(walkAway[animIndex]);
            }
        } 
        else {
            animCounter--; 
        }
    }
}