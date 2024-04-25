import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Functions
 * @version 04/25/2024
 */
public class Regular extends Child
{
    // determines which animation sprites the regular child will use
    private int childNum;

    public Regular(){
        super(100);
        
        animCounter = 0;
        childNum = Greenfoot.getRandomNumber(3);
        maxWalkIndex = walkAway.length;
        initImages();
    }

    public void act()
    {   
        if(!super.update()) return;
        // finds nearest zombie
        double[] enemyDetails = detectNearestEntity(Zombie.class, 400);
        // if zombie could not be found, finds nearest traitor
        if(enemyDetails[1]==-1) enemyDetails = detectNearestEntity(Traitor.class, 200);
        // runs away from them
        runAway(enemyDetails); 
    }

    private void initImages(){
        // Initialize child 1
        if(childNum == 0) {
            for(int i = 0; i < maxWalkIndex; i++) {
                walkRight[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkLeft[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkAway[i] = new GreenfootImage("child1WalkAway/child1WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkToward[i] = new GreenfootImage("child1WalkToward/child1WalkToward" + i + ".png");
            }
        }
        // Initialize child 2
        if(childNum == 1) {
            for(int i = 0; i < maxWalkIndex; i++) {
                walkRight[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkLeft[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkAway[i] = new GreenfootImage("child2WalkAway/child2WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkToward[i] = new GreenfootImage("child2WalkToward/child2WalkToward" + i + ".png");
            }
        }
        // Initialize child 3
        if(childNum == 2) {
            for(int i = 0; i < maxWalkIndex; i++) {
                walkRight[i] = new GreenfootImage("child3WalkRight/child3WalkRight" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkLeft[i] = new GreenfootImage("child3WalkRight/child3WalkRight" + i + ".png");
                walkLeft[i].mirrorHorizontally();
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkAway[i] = new GreenfootImage("child3WalkAway/child3WalkAway" + i + ".png");
            }
            for(int i = 0; i < maxWalkIndex; i++) {
                walkToward[i] = new GreenfootImage("child3WalkToward/child3WalkToward" + i + ".png");
            }
        }
        setImage(walkToward[0]);
        animIndex = 0; 
        animDelay = 8; // # of acts in between each image
        animCounter = animDelay; 
    }

    private void runAway(double[] enemyDetails){
        // the vector array stores x-velocity and y-velocity
        double[] vector;
        // set the move direction to be a bit more random
        int adjustment = rand.nextInt(-15, 16);
        // if the enemy does exist
        if(enemyDetails[1] != -1)
            // sets vector flee from enemy
            vector = Utility.angleToVector(enemyDetails[0]+180+adjustment);
            
        // if the enemy does not exist
        else
            // do not move
            vector = new double[] {0, 0}; 
        
        // moves according to vector, doubled
        setLocation(getX()+vector[0]*2, getY()+vector[1]*2);
        // update facing direction
        updateDirection(vector);
    }

    // animation
    protected void animate(){
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            updateWalking();
        } 
        else {
            animCounter--; 
        }
    }
}