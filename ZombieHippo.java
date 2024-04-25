import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ZombieHippo here.
 * 
 * @author <li> Luke Xiao | Movements
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Fixes
 * 
 * @version 04/18/2024
 */
public class ZombieHippo extends Zombie
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
    // Movement Variables
    private int direction;
    private Child targetChild;
    private ArrayList<Child> children;
    private boolean lockedDirection = false;
    private int chargeCooldown = 20;
    protected double friction = 0.5; // override from Entity
    
    public ZombieHippo() {
        super(100);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
        setImage("zombieHippoWalkToward/walkToward1.png");
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
            walkRight[i].scale(85,43);
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombieHippoWalkRight/walkRight" + i + ".png");
            walkLeft[i].scale(85,43);
            walkLeft[i].mirrorHorizontally();
        }
        
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }
    
    private void charge()
    {
        if(!lockedDirection){
            double[] childDetails = detectNearestEntity(Child.class, 500);
            if(childDetails[1] != -1) setRotation(childDetails[0]);
            lockedDirection = true;
        }
        direction = getRotation();
        move(3);
        // The facing direction
        right = false; left = false; away = false; toward = false;
        if (direction >= 315 || direction <= 45) // Right
        {
            right = true;
        }
        if (direction > 45 && direction <= 135) // Down
        {   
            toward = true;
        }
        if (direction > 135 && direction <= 225) // Left
        {
            left = true;
        }
        if (direction > 225 && direction <= 315) // Up
        {
            away = true;
        }
        // stop charging
        if(getX()<100||getX()>924||getY()<50||getY()>750){
            lockedDirection = false;
        }
        dealDamage();
    }
    
    /**
     * Act - do whatever the ZombieHippo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!super.update()) return;
        animate();
        charge();
        // Greenfoot.playSound("hippo1.mp3");
        if (chargeCooldown > 0) 
        {
            chargeCooldown--; // Decrement cooldown time
        }
        setLocation(getX(), getY());

    }
    
    private void dealDamage(){
        for(Child c: getIntersectingObjects(Child.class)){
            c.takeDamage(5);
            c.push(Greenfoot.getRandomNumber(360), 10);
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