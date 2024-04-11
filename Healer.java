import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healer likes to help fellow children with some clerical magic
 * 
 * @author Anya Shah  animations
 * @author Lucas      functions
 * @version 04/10/2024
 */
public class Healer extends Child
{
    // Healing sprites
    private GreenfootImage[] healAway = new GreenfootImage[7];
    private GreenfootImage[] healRight = new GreenfootImage[7];
    private GreenfootImage[] healLeft = new GreenfootImage[7];
    private GreenfootImage[] healToward = new GreenfootImage[7];
    
    // Walking sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];
    
    // Animation counter
    private int animCounter;
    
    // healing variables
    private final int maxAoeCooldown = 200;
    private final int maxProjCooldown = 80;

    private int aoeCooldown = maxAoeCooldown;
    private int projCooldown = maxProjCooldown;
    private int stunDuration = 0;
    
    public Healer(){
        super(300);
        animCounter = 0;
    }
    
    /**
     * 4 healing animations
     */
    public void healAway() {
        for(int i = 0; i < 7; i++) {
            healAway[i] = new GreenfootImage("healAway/healAway" + i + ".png");
        }
        setImage(healAway[animCounter++ % 7]);
    }
    public void healRight() {
        for(int i = 0; i < 7; i++) {
            healRight[i] = new GreenfootImage("healRight/healRight" + i + ".png");
        }
        setImage(healToward[animCounter++ % 7]);
    }
    public void healLeft() {
        for(int i = 0; i < 7; i++) {
            healLeft[i] = new GreenfootImage("healRight/healRight" + i + ".png");
            healLeft[i].mirrorHorizontally();
        }
        setImage(healToward[animCounter++ % 7]);
    }
    public void healToward() {
        for(int i = 0; i < 7; i++) {
            healToward[i] = new GreenfootImage("healToward/healToward" + i + ".png");
        }
        setImage(healToward[animCounter++ % 7]);
    }
    
    /**
     *  4 walking animations
     */
    public void walkAway() {
        for(int i = 0; i < 9; i++) {
            walkAway[i] = new GreenfootImage("healerWalkAway/healerWalkAway" + i + ".png");
        }
        setImage(walkAway[animCounter++ % 9]);
    }
    public void walkRight() {
        for(int i = 0; i < 9; i++) {
            walkRight[i] = new GreenfootImage("healerWalkRight/healerWalkRight" + i + ".png");
        }
        setImage(walkRight[animCounter++ % 9]);
    }
    public void walkLeft() {
        for(int i = 0; i < 9; i++) {
            walkLeft[i] = new GreenfootImage("healerWalkRight/healerWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        setImage(walkLeft[animCounter++ % 9]);
    }
    public void walkToward() {
        for(int i = 0; i < 9; i++) {
            walkToward[i] = new GreenfootImage("healerWalkToward/healerWalkToward" + i + ".png");
        }
        setImage(walkToward[animCounter++ % 9]);
    }
    
    public void act(){
        if(!awake) return;
        stunDuration--;
        if(stunDuration>0){
            setLocation(getX(), getY());
            healToward();
            return;
        }
        double[] allyDetails = detectNearestEntity(Child.class, 2000);
        followAlly(allyDetails);
        checkHeal(allyDetails);
    }
    private void followAlly(double[] details){
        double[] vector;
        if(details[1] >= 95) // too far
            vector = Utility.angleToVector(details[0]);
        else if(details[1] <= 70 && details[1] != -1) // too close
            vector = Utility.angleToVector(details[0]+180);
        else
            vector = new double[] {0, 0}; 
        if(stunDuration<=0){
            setLocation(getX()+vector[0], getY()+vector[1]);
            if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1]))
                walkLeft();
            else if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1]))
                walkRight();
            else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1]))
                walkToward();
            else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1]))
                walkAway();
        }
    }
    private void checkHeal(double[] details){
        double direction = details[0];
        double distance = details[1];
        if(distance<=100 && aoeCooldown<=0){
            getWorld().addObject(new HealingEffect(200, 40), getX(), getY());
            aoeCooldown = maxAoeCooldown;
            healToward();
            stunDuration = 120;
        }
        if(distance>=65 && distance < 500 && projCooldown<=0){
            getWorld().addObject(new HealingBall((int)direction, 8, 10), getX(), getY());
            // if(direction<=45) healRight();
            // else if(direction<=135) healAway();
            // else if(direction<=225) healLeft();
            // else if(direction<=315) healToward();
            // else healRight();
            projCooldown = maxProjCooldown;
        }
        aoeCooldown--; projCooldown--;
    }
}
