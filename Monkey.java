import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monkey here.
 * 
 * @author Luke Xiao | Functions
 * @author Anya Shah | Animations
 * @version 04/12/2024
 */
public class Monkey extends Animal
{
    // Monkey sprites
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

    public Monkey(){
        super(100);

        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }

    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("monkeyWalkAway/monkeyWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("monkeyWalkToward/monkeyWalkToward" + i + ".png");
        }
        
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    /**
     * Act - do whatever the Monkey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveAround();
        animate();
    }

    private void moveAround()
    {
        move(2);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(90) - 45);
        }
        if (getX() <= 10 || getX() >= 455)
        {
            turn(180);
        }
        if (getY() <= 10 || getY() >= 310)
        {
            turn(180);
        }
    }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex == maxIndex) {
                animIndex = 0;
            }
            if(right) {
                setImage(walkRight[animIndex]);
            }
            else if(left) {
                setImage(walkLeft[animIndex]);
            }
            else if(away) {
                setImage(walkAway[animIndex]);
            }
            else if(toward){
                setImage(walkToward[animIndex]);
            }
        }
        else {
            animCounter--;
        }
    }
    /*
     * if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Animal.class) == null)
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
     */
}
