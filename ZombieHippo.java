import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ZombieHippo here.
 * 
 * @author <li> Luke Xiao | Movements
 * @author <li> Anya Shah | Animations
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
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombieHippoWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }
    
    private void charge()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(2);
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            // The initial orientation of the images are facing RIGHT
            if (direction >= 315 || direction <= 45) // Right
            {
                away = true;
                right = true;
            }
            if (direction > 45 && direction <= 135) // Down
            {   
                right = true;
                away = false;
            }
            if (direction > 135 && direction <= 225) // Left
            {
                right = false;
                away = false;
            }
            if (direction > 225 && direction <= 315) // Up
            {
                right = false;
                away = true;
            }
        }
        if (getX() <= 20 || getX() >= 1004)
        {
            setRotation(180);
        }
        if (getY() <= 20 || getY() >= 780)
        {
            setRotation(180);
        }
        targetClosestChildren();
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
        Greenfoot.playSound("hippo1.mp3");
    }
    
    private void targetClosestChildren ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        // Get a list of all children in the World, cast it to ArrayList
        // for easy management
        children = (ArrayList<Child>)getObjectsInRange(40, Child.class);
        if (children.size() == 0){
            children = (ArrayList<Child>)getObjectsInRange(140, Child.class);
        } 
        if (children.size() == 0){
            children = (ArrayList<Child>)getObjectsInRange(350, Child.class);
        } 
        if (children.size() > 0)
        {
            // set the first one as my target
            targetChild = children.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Zoo.getDistance (this, targetChild);
            for (Child o : children)
            {
                // Cast for use in generic method
                //Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Zoo.getDistance(this, o);
                if (distanceToActor < closestTargetDistance)
                {
                    targetChild = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetChild.getX(), targetChild.getY());
        }
        if (isTouching(Child.class))
        {
            targetChild.takeDamage(3);
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