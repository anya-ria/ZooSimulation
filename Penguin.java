import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Penguin here.
 * 
 * @author Luke Xiao | Functions
 * @author Anya Shah | Animations
 * @version 04/12/2024
 */
public class Penguin extends Animal
{
    // Penguin slide sprites
    private GreenfootImage[] slideRight = new GreenfootImage[3];
    private GreenfootImage[] slideLeft = new GreenfootImage[3];
    private GreenfootImage[] slideAway = new GreenfootImage[3];
    private GreenfootImage[] slideToward = new GreenfootImage[3];

    // Penguin walk sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];

    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxSlideIndex, maxWalkIndex;
    private boolean right, away;

    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;

    public Penguin(){
        super(100);

        animCounter = 0;
        maxSlideIndex = slideRight.length;
        maxWalkIndex = walkRight.length;
        initImages();
    }

    private void initImages() {
        // Initializing sliding images
        for(int i = 0; i < maxSlideIndex; i++) {
            slideRight[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideLeft[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
            slideLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideAway[i] = new GreenfootImage("penguinSlideAway/slideAway" + i + ".png");
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideToward[i] = new GreenfootImage("penguinSlideToward/slideToward" + i + ".png");
        }
        
        // Initializing walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("penguinWalkAway/walkAway"+ i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("penguinWalkToward/walkToward" + i + ".png");
        }
        
        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    }

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
    
    private void animateWalking() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex == maxWalkIndex) {
                animIndex = 0;
            }
            if(right) {
                setImage(walkRight[animIndex]);
            }
            if(!right) {
                setImage(walkLeft[animIndex]);
            }
            if(away) {
                setImage(walkAway[animIndex]);
            }
            if(!away) {
                setImage(walkToward[animIndex]);
            }
        }
        else {
            animCounter--;
        }
    }
    
    private void animateSliding() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex == maxSlideIndex) {
                animIndex = 0;
            }
            if(right) {
                setImage(slideRight[animIndex]);
            }
            if(!right) {
                setImage(slideLeft[animIndex]);
            }
            if(away) {
                setImage(slideAway[animIndex]);
            }
            if(!away) {
                setImage(slideToward[animIndex]);
            }
        }
        else {
            animCounter--;
        }
    }
}
