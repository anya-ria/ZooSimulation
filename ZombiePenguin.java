import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZombiePenguin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombiePenguin extends Animal
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

    public ZombiePenguin() {
        super(100);

        animCounter = 0;
        maxIndex = walkRight.length;
        maxSlideIndex = slideRight.length;
        initImages();
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
        animate();
    }

    private void animate() {
        if(animCounter == 0){
            if(sliding) {
                animCounter = animDelay; 
                animIndex++; 
                if(animIndex == maxSlideIndex){
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
                if(animIndex == maxIndex){
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
