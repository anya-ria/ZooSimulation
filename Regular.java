import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Anya Shah | Animations
 * @author Lucas Fu  | Functions
 * @version 04/12/2024
 */
public class Regular extends Child
{
    // Child Sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];
    
    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxIndex;
    private boolean right, left, away, toward;
    
    private int childNum;

    public Regular(){
        super(100);

        animCounter = 0;
        childNum = Greenfoot.getRandomNumber(3);
        maxIndex = walkAway.length;
        initImages();
    }

    public void act()
    {   
        if(!awake) return;
        super.act();
        if(slippedDuration>0){
            slippedDuration--;
            setLocation(getX(), getY());
            return;
        } else if(slippedDuration==0){
            setRotation(0);
            slippedDuration--; // effectively only makes this code run once
        }
        runAway();
        animate(); 
    }

    private void initImages(){
        // Initialize child 1
        if(childNum == 0) {
            for(int i = 0; i < maxIndex; i++) {
                walkRight[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkLeft[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxIndex; i++) {
                walkAway[i] = new GreenfootImage("child1WalkAway/child1WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkToward[i] = new GreenfootImage("child1WalkToward/child1WalkToward" + i + ".png");
            }
        }
        // Initialize child 2
        if(childNum == 1) {
            for(int i = 0; i < maxIndex; i++) {
                walkRight[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkLeft[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxIndex; i++) {
                walkAway[i] = new GreenfootImage("child2WalkAway/child2WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkToward[i] = new GreenfootImage("child2WalkToward/child2WalkToward" + i + ".png");
            }
        }
        // Initialize child 3
        if(childNum == 2) {
            for(int i = 0; i < maxIndex; i++) {
                walkRight[i] = new GreenfootImage("child3WalkRight/child3WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkLeft[i] = new GreenfootImage("child3WalkRight/child3WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxIndex; i++) {
                walkAway[i] = new GreenfootImage("child3WalkAway/child3WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxIndex; i++) {
                walkToward[i] = new GreenfootImage("child3WalkToward/child3WalkToward" + i + ".png");
            }
        }

        animIndex = 0; 
        animDelay = 8; // # of acts in btw each image
        animCounter = animDelay; 
    }

    private void runAway(){
        double[] enemyDetails = detectNearestEntity(Animal.class, 400);
        double[] vector;
        if(enemyDetails[1] != -1)
            vector = Utility.angleToVector(enemyDetails[0]);
        else {
            enemyDetails = detectNearestEntity(Traitor.class, 200);
            if(enemyDetails[1] != -1)
                vector = Utility.angleToVector(enemyDetails[0]);
            else 
                vector = new double[] {0, 0}; 
        }
        setLocation(getX()-vector[0], getY()-vector[1]);
        // update facing direction
        if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            left = true;
            right = false; toward = false; away = false;
        }
        else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            right = true;
            left = false; toward = false; away = false;
        }
        else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            toward = true;
            left = false; right = false; away = false;
        }
        else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            away = true; 
            left = false; right = false; toward = false;
        }
    }

    private void animate(){
        if(animCounter == 0){
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
            else {
                // if(childNum == 0) {
                    // setImage("child1WalkToward/child1WalkToward0.png");
                // }
                // if(childNum == 1) {
                    // setImage("child2WalkToward/child2WalkToward0.png");
                // }
                // if(childNum == 2) {
                    // setImage("child3WalkToward/child3WalkToward0.png");
                // }
            }
        } 
        else {
            animCounter--; 
        }
    }
}