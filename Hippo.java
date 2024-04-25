import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Attacks children including the fighter children.
 * Charges towards children and attacks
 * 
 * @author <li> Luke Xiao  | Functions
 * @author <li> Anya Shah  | Animations
 * @author <li> Gennie Won | Sounds
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
    //Sound intialization
    private static GreenfootSound[] hippoSound;
    private static int hippoSoundIndex = 0;
    
    public Hippo() {
        super(200);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }

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

        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    public void act()
    {
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        moveAround();
        animate();
    }

    private void init()
    {
        hippoSoundIndex = 0;
        hippoSound = new GreenfootSound[20];
        for(int i = 0; i < hippoSound.length; i++) {
            hippoSound[i] = new GreenfootSound("hippo1.mp3");
        }
    }
    
    private void playHippoSound()
    {
        hippoSound[hippoSoundIndex].setVolume(50);
        hippoSound[hippoSoundIndex].play();
        hippoSoundIndex++;
        if(hippoSoundIndex >= hippoSound.length) {
            hippoSoundIndex = 0;
        }
    }
    
    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
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