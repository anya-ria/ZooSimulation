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
    private boolean sliding;
    
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
            slideRight[i].scale((int)(slideRight[i].getWidth()*1.5),(int)(slideRight[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideLeft[i] = new GreenfootImage("zombiePenguinSlideRight/slideRight" + i + ".png");
            slideLeft[i].scale((int)(slideLeft[i].getWidth()*1.5),(int)(slideLeft[i].getHeight()*1.5));
            slideLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideAway[i] = new GreenfootImage("zombiePenguinSlideAway/slideAway" + i + ".png");
            slideAway[i].scale((int)(slideAway[i].getWidth()*1.5),(int)(slideAway[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxSlideIndex; i++) {
            slideToward[i] = new GreenfootImage("zombiePenguinSlideToward/slideToward" + i + ".png");
            slideToward[i].scale((int)(slideToward[i].getWidth()*1.5),(int)(slideToward[i].getHeight()*1.5));
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
        setLocation(getX(), getY());
        slide();
    }

    private void slide()
    {
        double[] childDetails = detectNearestEntity(Child.class, 500);
        if(childDetails[1]!=-1)
            direction = (int)childDetails[0];
        move(3);
        sliding = true;
        if (Greenfoot.getRandomNumber(100) < 1)
        {
            adjustDirection();
        }
        if (getX() <= 100 || getX() >= 924)
        {
            turn(180);
            direction = getRotation();
            adjustDirection();
        }
        if (getY() <= 50 || getY() >= 750)
        {
            turn(180);
            direction = getRotation();
            adjustDirection();
        }
        dealDamage();
    }
    private void dealDamage(){
        for(Child c: getIntersectingObjects(Child.class)){
            c.takeDamage(5);
        }
        if(isTouching(Child.class)&&((Child)getOneIntersectingObject(Child.class)).isAwake()){
            turn(Greenfoot.getRandomNumber(60)+180);
            move(4);
            // adjust direction
            direction = getRotation();
            adjustDirection();
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
