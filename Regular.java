import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas Fu, Anya Shah
 * @version 04/04/2024
 */
public class Regular extends Child
{
    // Child Sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];

    private int animCounter, animDelay, animIndex;
    private int maxIndex;
    private boolean right, away;
    
    private int childNum;

    public Regular(){
        super(100);

        animCounter = 0;
        childNum = Greenfoot.getRandomNumber(2);
        maxIndex = walkRight.length;
        initImages();
    }

    public void act()
    {   
        super.act();
        if(!awake) return;
        runAway();
        animate(); 
    }

    private void initImages(){
        // Initialize child 1
        if(childNum == 0){
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
        } else {
            // Initialize child 2
            for(int i = 0; i < maxIndex; i++) {
                walkRight[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
            }
            for(int i = 0; i < 9; i++) {
                walkLeft[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < 9; i++) {
                walkAway[i] = new GreenfootImage("child2WalkAway/child2WalkAway" + i + ".png");
            }
            for(int i = 0; i < 9; i++) {
                walkToward[i] = new GreenfootImage("child2WalkToward/child2WalkToward" + i + ".png");
            }
        }

        animIndex = 0; 
        animDelay = 5; // # of acts in btw each image
        animCounter = animDelay; 
    }

    private void runAway(){
        double[] enemyDetails = detectNearestEntity(Animal.class, 1000);
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
        if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1]))
            right = false; 
        else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1]))
            right = true; 
        else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1]))
            away = false; 
        else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1]))
            away = true; 
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
            else if (!right){
                setImage(walkLeft[animIndex]);
            } 
            else if(!away){
                setImage(walkToward[animIndex]); 
            } 
            else {
                setImage(walkAway[animIndex]);
            }
        } 
        else {
            animCounter--; 
        }
    }
}