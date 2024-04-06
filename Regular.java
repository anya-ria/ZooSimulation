import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public class Regular extends Child
{
    // Images for child 1
    private GreenfootImage[] childOneWalkAway = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkRight = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkLeft = new GreenfootImage[9];
    private GreenfootImage[] childOneWalkToward = new GreenfootImage[9];
    
    // // Images for child 2
    // private GreenfootImage[] childTwoWalkAway;
    // private GreenfootImage[] childTwoWalkRight;
    // private GreenfootImage[] childTwoWalkToward;
    
    // // Images for child 3
    // private GreenfootImage[] childThreeWalkAway;
    // private GreenfootImage[] childThreeWalkRight;
    // private GreenfootImage[] childThreeWalkToward;
    
    public Regular() {
        initAnimateChildOne();
    }
    
    /**
     * Act - do whatever the Regular wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        runAway();
    }
    
    private void runAway(){
        double[] enemyDetails = detectNearestEnemy(Animal.class, 10000);
        if(enemyDetails[1] == -1) return;
        double[] vector = Utility.angleToVector(enemyDetails[0]);
        setLocation(getX()-vector[0], getY()-vector[1]);
    }
    
    public void initAnimateChildOne() {
        for(int i = 0; i < 9; i++) {
            childOneWalkAway[i] = new GreenfootImage("child1WalkAway" + i + ".png");
        }
        
        for(int i = 0; i < 9; i++) {
            childOneWalkRight[i] = new GreenfootImage("child1WalkRight" + i + ".png");
        }
        
        for(int i = 0; i < 9; i++) {
            childOneWalkLeft[i] = new GreenfootImage("child1WalkLeft" + i + ".png");
            childOneWalkLeft[i].mirrorHorizontally();
        }
        
        for(int i = 0; i < 9; i++) {
            childOneWalkToward[i] = new GreenfootImage("child1WalkToward" + i + ".png");
        }
    }
}
