import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Hippos are our special imports from from the savannas of Africa. Look at those cute little giants!
 * 
 * @author <li> Luke Xiao  | Functions
 * @author <li> Anya Shah  | Animations
 * @author <li> Gennie Won | Sounds
 * @author <li> Lucas Fu   | Cleanup
 * @version 04/25/2024
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
    
    // Movement variables
    private int direction;
    // Sound variales
    private static GreenfootSound[] hippoSound;
    private static int hippoSoundIndex;
    public Hippo() {
        super(200);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }
    
    /**
     * Initialize the hippo sounds
     */
    public static void init()
    {
        hippoSoundIndex = 0;
        hippoSound = new GreenfootSound[20];
        for(int i = 0; i < hippoSound.length; i++) {
            hippoSound[i] = new GreenfootSound("hippo1.mp3");
        }
    }
    public static void playHippoSound()
    {
        hippoSound[hippoSoundIndex].setVolume(40);
        hippoSound[hippoSoundIndex].play();
        hippoSoundIndex++;
        if(hippoSoundIndex >= hippoSound.length) {
            hippoSoundIndex = 0;
        }
    }
    /**
     * Initialize the hippo images
     */
    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("hippoWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkToward[i] = new GreenfootImage("hippoWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkRight[i] = new GreenfootImage("hippoWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkLeft[i] = new GreenfootImage("hippoWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        setImage(walkToward[0]);
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
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        moveAround();
        animate();
    }
    
    // Determining the animation needed for each directions
    protected void animate() {
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            if(animIndex >= maxIndex){
                animIndex = 0; 
            }
            if(right){
                setImage(walkRight[animIndex]);
            }
            else if (!right && !away){
                setImage(walkLeft[animIndex]);
            } 
            else if(right && !away)
            {
                setImage(walkToward[animIndex]);
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
    
    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if(Greenfoot.getRandomNumber(300) == 0) {
            playHippoSound();
        }
        setLocation(getX(), getY());
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            // changes direction at random times
            adjustDirection();
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
}
