import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZombieMonkey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieMonkey extends Animal
{
    // Zombie monkey sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3]; 
    
    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    private boolean right, left, away, toward;
    
    public ZombieMonkey() {
        super(100);
        
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }
    
    public void addedToWorld(World world){
        setImage("zombieMonkeyWalkToward/walkToward1.png");
    }
    
    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("zombieMonkeyWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("zombieMonkeyWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("zombieMonkeyWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombieMonkeyWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }
    
    /**
     * Act - do whatever the ZombieMonkey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animate();
    }
    
    private void animate() {
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            if(animIndex >= maxIndex){
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
