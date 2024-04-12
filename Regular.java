import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas, Anya Shah
 * @version 04/04/2024
 */
public class Regular extends Child
{
    // Child 1 Sprites
    private GreenfootImage[] childOneWalkAway = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkRight = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkLeft = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkToward = new GreenfootImage[9];
    // Child 2 Sprites
    private GreenfootImage[] childTwoWalkAway = new GreenfootImage[9];
    private GreenfootImage[] childTwoWalkRight = new GreenfootImage[9];
    private GreenfootImage[] childTwoWalkLeft = new GreenfootImage[9];
    private GreenfootImage[] childTwoWalkToward = new GreenfootImage[9];

    private int speed;
    private int animCounter;
    private int childNum;
    private boolean movingRight;
    private boolean movingAway;
    
    public Regular(){
        super(100);
        
        speed = 1;
        animCounter = 0;
        childNum = Greenfoot.getRandomNumber(2);
        if(childNum==1) setImage(childOneWalkToward[0]);
        else setImage(childTwoWalkToward[0]);
    }

    public void act()
    {   
        if(!awake) return;
        runAway();
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
                walkLeft();
            else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1]))
                walkRight();
            else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1]))
                walkToward();
            else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1]))
                walkAway();
    }
    
    public void walkRight() {
        if(childNum == 0) {
            for(int i = 0; i < 9; i++) {
                childOneWalkRight[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
            }
            setImage(childOneWalkRight[animCounter++ % 9]);
        }
        else if (childNum == 1) {
            for(int i = 0; i < 9; i++) {
                childTwoWalkRight[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
            }
            setImage(childTwoWalkRight[animCounter++ % 9]);
        }
    }

    public void walkLeft() {
        if(childNum == 0) {
            for(int i = 0; i < 9; i++) {
                childOneWalkLeft[i] = new GreenfootImage("child1WalkRight/child1WalkRight" + i + ".png");
                childOneWalkLeft[i].mirrorHorizontally();
            }
            setImage(childOneWalkLeft[animCounter++ % 9]);
        }
        else if (childNum == 1) {
            for(int i = 0; i < 9; i++) {
                childTwoWalkLeft[i] = new GreenfootImage("child2WalkRight/child2WalkRight" + i + ".png");
                childTwoWalkLeft[i].mirrorHorizontally();
            }
            setImage(childTwoWalkLeft[animCounter++ % 9]);
        }
    }

    public void walkAway() {
        if(childNum == 0) {
            for(int i = 0; i < 9; i++) {
                childOneWalkAway[i] = new GreenfootImage("child1WalkAway/child1WalkAway" + i + ".png");
            }
            setImage(childOneWalkAway[animCounter++ % 9]);
        }
        else if (childNum == 1) {
            for(int i = 0; i < 9; i++) {
                childTwoWalkAway[i] = new GreenfootImage("child2WalkAway/child2WalkAway" + i + ".png");
            }
            setImage(childTwoWalkAway[animCounter++ % 9]);
        }
    }

    public void walkToward() {
        if(childNum == 0) {
            for(int i = 0; i < 9; i++) {
                childOneWalkToward[i] = new GreenfootImage("child1WalkToward/child1WalkToward" + i + ".png");
            }
            setImage(childOneWalkToward[animCounter++ % 9]);
        }
        else if (childNum == 1) {
            for(int i = 0; i < 9; i++) {
                childTwoWalkToward[i] = new GreenfootImage("child2WalkToward/child2WalkToward" + i + ".png");
            }
            setImage(childTwoWalkToward[animCounter++ % 9]);
        }
    }
}
