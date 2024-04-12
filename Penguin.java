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
    private int animCounter;
    
    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;
    
    public Penguin(){
        super(100);
        animCounter = 0;
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
    
    public void animateSlideRight() {
        for(int i = 0; i < 3; i++) {
            slideRight[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
            setImage(slideRight[animCounter++ % 3]);
        }
    }
    public void animateSlideLeft() {
        for(int i = 0; i < 3; i++) {
            slideLeft[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
            slideLeft[i].mirrorHorizontally();
            setImage(slideLeft[animCounter++ % 3]);
        }
    }
    public void animateSlideAway() {
        for(int i = 0; i < 3; i++) {
            slideAway[i] = new GreenfootImage("penguinSlideAway/slideAway" + i + ".png");
            setImage(slideAway[animCounter++ % 3]);
        }
    }
    public void animateSlideToward() {
        for(int i = 0; i < 3; i++) {
            slideToward[i] = new GreenfootImage("penguinSlideToward/slideToward" + i + ".png");
            setImage(slideToward[animCounter++ % 3]);
        }
    }
    
    public void animateRight() {
        for(int i = 0; i < 3; i++) {
            walkRight[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            setImage(walkRight[animCounter++ % 3]);
        }
    }
    public void animateLeft() {
        for(int i = 0; i < 3; i++) {
            walkLeft[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
            setImage(walkLeft[animCounter++ % 3]);
        }
    }
    public void animateAway() {
        for(int i = 0; i < 3; i++) {
            walkAway[i] = new GreenfootImage("penguinWalkAway/walkAway"+ i + ".png");
            setImage(walkAway[animCounter++ % 3]);
        }
    }
    public void animateToward() {
        for(int i = 0; i < 3; i++) {
            walkToward[i] = new GreenfootImage("penguinWalkToward/walkToward" + i + ".png");
            setImage(walkToward[animCounter++ % 3]);
        }
    }
}
