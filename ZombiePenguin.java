import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ZombiePenguin here.
 * 
 * @author <li> Luke Xiao | Movements
 * @author <li> Anya Shah | Animations
 * @version 04/23/2024
 */
public class ZombiePenguin extends Zombie
{
    // Zombie penguin sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];

    // Zombie penguin sliding sprites
    private GreenfootImage[] slideRight = new GreenfootImage[3];
    private GreenfootImage[] slideLeft = new GreenfootImage[3];
    private GreenfootImage[] slideToward = new GreenfootImage[3];
    private GreenfootImage[] slideAway = new GreenfootImage[3]; 

    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex, maxSlideIndex;
    private boolean right, left, away, toward, sliding;

    private int direction;
    private Child targetChild;
    private ArrayList<Child> children;
    
    public ZombiePenguin() {
        super(100);
        animCounter = 0;
        maxIndex = walkRight.length;
        maxSlideIndex = slideRight.length;
        initImages();
        setImage("zombiePenguinWalkToward/walkToward1.png");
    }

    private void initImages() {
        // Initialize walking images
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("zombiePenguinWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("zombiePenguinWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("zombiePenguinWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombiePenguinWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }

        // Initializing sliding images
        for(int i = 0; i < maxSlideIndex; i++) {
            slideRight[i] = new GreenfootImage("zombiePenguinSlideRight/slideRight" + i + ".png");
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideLeft[i] = new GreenfootImage("zombiePenguinSlideRight/slideRight" + i + ".png");
            slideLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideAway[i] = new GreenfootImage("zombiePenguinSlideAway/slideAway" + i + ".png");
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideToward[i] = new GreenfootImage("zombiePenguinSlideToward/slideToward" + i + ".png");
        }

        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    /**
     * Act - do whatever the ZombiePenguin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!super.update()) return;
        animate();
        slide();
    }

    private void slide()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            if (direction >= 315 || direction <= 45)
            {
                away = true;
                right = true;
                sliding = true;
            }
            if (direction > 45 && direction <= 135)
            {   
                right = true;
                away = false;
                sliding = true;
            }
            if (direction > 135 && direction <= 225)
            {
                right = false;
                away = false;
                sliding = true;
            }
            if (direction > 225 && direction <= 315)
            {
                right = false;
                away = true;
                sliding = true;
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
            targetChild.takeDamage(1);
        }
    }
    
    protected void animate() {
        if(animCounter == 0){
            if(sliding) {
                animCounter = animDelay; 
                animIndex++; 
                if(animIndex >= maxSlideIndex){
                    animIndex = 0; 
                }
                if(right){
                    setImage(slideRight[animIndex]);
                } 
                else if (left){
                    setImage(slideLeft[animIndex]);
                } 
                else if(toward){
                    setImage(slideToward[animIndex]); 
                } 
                else if(away){
                    setImage(slideAway[animIndex]);
                }
            }
            else {
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
        } 
        else {
            animCounter--; 
        }
    }
}
