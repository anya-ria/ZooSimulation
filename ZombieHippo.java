import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZombieHippo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieHippo extends Animal
{
    // Zombie hippo sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];
    
    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    private boolean right, left, away, toward;
    
    public ZombieHippo() {
        super(100);
        
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }
    
    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("zombieHippoWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("zombieHippoWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("zombieHippoWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombieHippoWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }
    
    /**
     * Act - do whatever the ZombieHippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
