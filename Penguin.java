import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Penguins are cute little birds at our zoo :) Look at them slide!
 * 
 * @author <li> Luke Xiao | Functions
 * @author <li> Anya Shah | Animations
 * @author <li> Gennie Won| Sounds
 * @author <li> Lucas Fu  | Cleanup
 * @version 04/25/2024
 */
public class Penguin extends Animal
{
    // Penguin walk sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];

    private GreenfootImage[] slideRight = new GreenfootImage[3];
    private GreenfootImage[] slideLeft = new GreenfootImage[3];
    private GreenfootImage[] slideAway = new GreenfootImage[3];
    private GreenfootImage[] slideToward = new GreenfootImage[3];
    
    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxSlideIndex, maxWalkIndex;
    private boolean sliding;

    // Sounds
    private static GreenfootSound[] penguinSound;
    private static int penguinSoundIndex;
    private static GreenfootSound[] slidingSound;
    private static int slidingSoundIndex;

    public Penguin()
    {
        super(100);
        animCounter = 0;
        maxWalkIndex = walkRight.length;
        initImages();
    }

    public void act()
    {
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        if(sliding) {
            slide();
            //playSlidingSound();
        }
        moveAround();
        animate();
    }
    
    // **************************** SOUNDS ****************************
    public static void init() {
        // Initialize penguin sounds
        penguinSoundIndex = 0;
        penguinSound = new GreenfootSound[20];
        for(int i = 0; i < penguinSound.length; i++) {
            penguinSound[i] = new GreenfootSound("penguin.mp3");
        }
        // Initialize sliding sounds
        slidingSoundIndex = 0;
        slidingSound = new GreenfootSound[20];
        for(int i = 0; i < penguinSound.length; i++) {
            slidingSound[i] = new GreenfootSound("slide.mp3");
        }
    }
    public static void playPenguinSound() {
        penguinSound[penguinSoundIndex].setVolume(70);
        penguinSound[penguinSoundIndex].play();
        penguinSoundIndex++;
        if(penguinSoundIndex >= penguinSound.length) {
            penguinSoundIndex = 0;
        }
    }
    public static void playSlidingSound() {
        slidingSound[slidingSoundIndex].setVolume(70);
        slidingSound[slidingSoundIndex].play();
        slidingSoundIndex++;
        if(slidingSoundIndex >= slidingSound.length) {
            slidingSoundIndex = 0;
        }
    }
    
    // **************************** ANIMATIONS ****************************
    /**
     * Initialize penguin images
     */
    private void initImages()
    {
        // Initializing sliding images
        for(int i = 0; i < maxSlideIndex; i++)
        {
            slideRight[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
            slideRight[i].scale((int)(slideRight[i].getWidth()*1.5),(int)(slideRight[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxSlideIndex; i++)
        {
            slideLeft[i] = new GreenfootImage("penguinSlideRight/slideRight" + i + ".png");
            slideLeft[i].scale((int)(slideLeft[i].getWidth()*1.5),(int)(slideLeft[i].getHeight()*1.5));
            slideLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxSlideIndex; i++)
        {
            slideAway[i] = new GreenfootImage("penguinSlideAway/slideAway" + i + ".png");
            slideAway[i].scale((int)(slideAway[i].getWidth()*1.5),(int)(slideAway[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxSlideIndex; i++)
        {
            slideToward[i] = new GreenfootImage("penguinSlideToward/slideToward" + i + ".png");
            slideToward[i].scale((int)(slideToward[i].getWidth()*1.5),(int)(slideToward[i].getHeight()*1.5));
        }

        // Initializing walking images
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkRight[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkRight[i].scale((int)(walkRight[i].getWidth()*1.5),(int)(walkRight[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkLeft[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkLeft[i].scale((int)(walkLeft[i].getWidth()*1.5),(int)(walkLeft[i].getHeight()*1.5));
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkAway[i] = new GreenfootImage("penguinWalkAway/walkAway"+ i + ".png");
            walkAway[i].scale((int)(walkAway[i].getWidth()*1.5),(int)(walkAway[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkToward[i] = new GreenfootImage("penguinWalkToward/walkToward" + i + ".png");
            walkToward[i].scale((int)(walkToward[i].getWidth()*1.5),(int)(walkToward[i].getHeight()*1.5));
        }
        setImage(walkToward[1]);
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    // Moving in random motion within its fences
    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        //playPenguinSound();
        if (Greenfoot.getRandomNumber(500) < 10)
        {
            // changes direction at random times
            adjustDirection();
        }
        if (getX() <= 695 || getX() >= 970){
            if(Greenfoot.getRandomNumber(500) < 5){
                sliding = true;
            }
        }
        if (getX() <= 695 || getX() >= 970)

            if(Greenfoot.getRandomNumber(500) < 5){
                sliding = true;
            }
        if(Greenfoot.getRandomNumber(500) < 5){
            sliding = false;
        }
        if (getX() <= 695 || getX() >= 980)
        {
            turn(180);
        }
        if (getY() <= 510 || getY() >= 760)
        {
            turn(180);
        }
        animate();
    }
    public void slide()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(3);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            setRotation(direction);
        }
        if (getX() <= 685 || getX() >= 970)
        {
            turn(180);
            move(10);
        }
        if (getY() <= 500 || getY() >= 750)
        {
            turn(180);
            move(10);
        }
    }
    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= maxWalkIndex) {
                animIndex = 0;
            }
            if(right)
            {
                setImage(walkRight[animIndex]);
            }
            else if(left)
            {
                setImage(walkLeft[animIndex]);
            }
            else if(away)
            {
                setImage(walkAway[animIndex]);
            }
            else if(toward)
            {
                setImage(walkToward[animIndex]);
            }
        }
        else
        {
            animCounter--;
        }
    }
}