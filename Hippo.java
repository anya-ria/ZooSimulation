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
    private int direction;
    //private double speed;
    //private double maxSpeed;
    //private int direction;

    public Hippo() 
    {
        super(200);
    
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }
    
    private void initImages() 
    {
        for(int i = 0; i < maxIndex; i++) 
        {
            walkAway[i] = new GreenfootImage("hippoWalkAway/hippoWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkToward[i] = new GreenfootImage("hippoWalkToward/hippoWalkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkRight[i] = new GreenfootImage("hippoWalkRight/hippoWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
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
        super.act();
        moveAround();
        animate();
        if (isInfected)
        {
            charge();
        }
    }
    
    public void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            // The initial orientation of the images are facing RIGHT
            if (direction >= 315 || direction <= 45) // Right
            {
                away = true;
                right = true;
            }
            if (direction > 45 && direction <= 135) // Down
            {   
                right = true;
                away = false;
            }
            if (direction > 135 && direction <= 225) // Left
            {
                right = false;
                away = false;
            }
            if (direction > 225 && direction <= 315) // Up
            {
                right = false;
                away = true;
            }
        }
        if (getX() <= 700 || getX() >= 985)
        {
            turn(180);
        }
        if (getY() <= 30 || getY() >= 280)
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

    private void animate() {
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            if(animIndex == maxIndex){
                animIndex = 0; 
            }
            if(right && away){
                setImage(walkRight[animIndex]);
            } 

            else if (!right && !away){
                setImage(walkLeft[animIndex]);
            } 
            else if(right && !away){

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
