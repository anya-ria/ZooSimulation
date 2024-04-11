import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healer here.
 * 
 * @author Anya Shah 
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
    
    private int animCounter;
    
    public Healer(){
        super(200);
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
}
