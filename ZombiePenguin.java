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
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            left = false; right = false; away = false; toward = false;
            if (direction >= 315 || direction <= 45) // right
            {
                right = true;
                sliding = true;
            }
            if (direction > 45 && direction <= 135) // up
            {   
                away = true;
                sliding = true;
            }
            if (direction > 135 && direction <= 225) // left
            {
                left = true;
                sliding = true;
            }
            if (direction > 225 && direction <= 315) // down
            {
                toward = true; 
                sliding = true;
            }
        }
        if (getX() <= 20 || getX() >= 1004)
        {
            turn(180);
        }
        if (getY() <= 20 || getY() >= 780)
        {
            turn(180);
        }
        dealDamage();
    }
    private void dealDamage(){
        for(Child c: getIntersectingObjects(Child.class)){
            c.takeDamage(10);
        }
        if(detectNearestEntity(Child.class, 10)[1]!=-1)
            turn(Greenfoot.getRandomNumber(60)+180);
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
