import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas, Anya Shah
 * @version 2024/4/4
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

    private int speed = 1;
    private int animCounter = 0;
    private int childNum = Greenfoot.getRandomNumber(2);
    private boolean movingRight;
    private boolean movingAway;
    
    public Regular(){
        super(100);
    }

    public void act()
    {   
        // move();
        runAway();
    }

    private void runAway(){
        double[] enemyDetails = detectNearestEnemy(Animal.class, 10000);
        double[] vector;
        if(enemyDetails[1] != -1)
            vector = Utility.angleToVector(enemyDetails[0]);
        else
            vector = new double[] {0, 0}; 
        setLocation(getX()-vector[0], getY()-vector[1]);
    }
    
    // Can delete if needed - Don't know how characters should move
    public void move() {
        if(movingRight) { // move right
            setLocation(getX() + speed, getY());
            move(1);
            animateRight();
        }
        if(!movingRight) { // move left
            setLocation(getX() - speed, getY());
            move(1);
            animateLeft();
        }
        if(movingAway) { // move away
            setLocation(getX(), getY() - speed);
            move(1);
            animateAway();
        }
        if(!movingAway) { // move toward
            setLocation(getX(), getY() + speed);
            move(1);
            animateToward();
        }
    }
    
    public void animateRight() {
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

    public void animateLeft() {
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

    public void animateAway() {
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

    public void animateToward() {
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
