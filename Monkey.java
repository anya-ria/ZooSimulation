import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Monkeys are part of our natural fauna in our country. Look at them cutely walking around!
 * 
<<<<<<< HEAD
 * @author <li> Luke Xiao  | Functions
 * @author <li> Anya Shah  | Animations
 * @author <li> Gennie Won | Sounds 
 * @author <li> Lucas Fu   | Cleanup
 * @version 04/12/2024
=======
 * @author <li> Luke Xiao | Functions
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Cleanup
 * @version 04/25/2024
>>>>>>> e3481e9eb85cbe3943b7a5cfde6a88c4de577ed3
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
    
    private static GreenfootSound[] monkeySound;
    private static int monkeySoundIndex;

    public Monkey()
    {
        super(100);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }
<<<<<<< HEAD
    
    public static void init() {
        monkeySoundIndex = 0;
        monkeySound = new GreenfootSound[20];
        for(int i = 0; i < monkeySound.length; i++) {
            monkeySound[i] = new GreenfootSound("monkey.mp3");
        }
    }
    
    public static void playMonkeySound() {
        monkeySound[monkeySoundIndex].setVolume(50);
        monkeySound[monkeySoundIndex].play();
        monkeySoundIndex++;
        if(monkeySoundIndex >= monkeySound.length) {
            monkeySoundIndex = 0;
        }
    }
=======
    public void act()
    {
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        moveAround();
        animate();
    }
    /**
     * Initialize monkey images
     */
>>>>>>> e3481e9eb85cbe3943b7a5cfde6a88c4de577ed3
    private void initImages() {
        // Initialize monkey images
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkLeft[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkAway[i] = new GreenfootImage("monkeyWalkAway/monkeyWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkToward[i] = new GreenfootImage("monkeyWalkToward/monkeyWalkToward" + i + ".png");
            walkToward[i].scale((int)(walkToward[i].getWidth()*1.3),(int)(walkToward[i].getHeight()*1.3));
        }
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }
<<<<<<< HEAD

    public void act()
    {
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        moveAround();
        animate();
    }

    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        playMonkeySound();
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            // changes direction at random times
            adjustDirection();
        }
        if (getX() <= 30 || getX() >= 320)
        {
            turn(180);
        }
        if (getY() <= 45 || getY() >= 290)
        {
            turn(180);
        }
    }
    
=======
>>>>>>> e3481e9eb85cbe3943b7a5cfde6a88c4de577ed3
    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= maxIndex) {
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

    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            // changes direction at random times
            adjustDirection();
        }
        if (getX() <= 30 || getX() >= 320)
        {
            turn(180);
        }
        if (getY() <= 45 || getY() >= 290)
        {
            turn(180);
        }
    }
}
